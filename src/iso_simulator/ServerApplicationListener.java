package iso_simulator;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.q2.qbean.QConfig;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.apache.log4j.BasicConfigurator;

public class ServerApplicationListener implements ISORequestListener, Configurable {

    private Configuration configuration;

    @Override
    public void setConfiguration(Configuration configuration) throws ConfigurationException {
        this.configuration = configuration;
    }

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {

        try {
            ISOMsg respMsg = (ISOMsg) isoMsg.clone();
            respMsg.setResponseMTI();
            System.out.println("MTI : " + isoMsg.getMTI().toString());
            if (isoMsg.getMTI().toString().equalsIgnoreCase("0800")) {
                respMsg.set(39, "00");                
            } 
            else if (isoMsg.getMTI().toString().equalsIgnoreCase("0200")) {
                
                /* IDPEL Setting*/
                String idpel = "";
                if (isoMsg.getString(48).trim().contains("|")){
                    String idpelx[] = isoMsg.getString(48).trim().split("\\|");
                    idpel = idpelx[0]; 
                }
                else {
                    idpel = isoMsg.getString(48).trim();
                }
                
                System.out.println("IDPEL : "+idpel);
                
                try {
                Configuration cfg = QConfig.getConfiguration("idpel_"+idpel.trim());            
                BasicConfigurator.configure();
                    
                if (isoMsg.getString(3).toString().equalsIgnoreCase("380000")) {
                    
                    respMsg.set(4, cfg.get(isoMsg.getString(3)+"_4"));
                    respMsg.set(8, cfg.get(isoMsg.getString(3)+"_8"));                 
                    respMsg.set(39, cfg.get(isoMsg.getString(3)+"_39"));
                    respMsg.set(44, cfg.get(isoMsg.getString(3)+"_44"));                    
                    respMsg.set(48, cfg.get(isoMsg.getString(3)+"_48"));
                    respMsg.set(61, cfg.get(isoMsg.getString(3)+"_61"));
                    respMsg.set(62, cfg.get(isoMsg.getString(3)+"_62"));
                    try {
                        Thread.sleep(Integer.parseInt(cfg.get(isoMsg.getString(3)+"_timedelay")));
                    }
                    catch(Exception e){e.printStackTrace();}
                    
                }
                else if (isoMsg.getString(3).toString().equalsIgnoreCase("180000")) {
                   
                    respMsg.set(39, cfg.get(isoMsg.getString(3)+"_39"));
                    
                    if (respMsg.getString(39).equalsIgnoreCase("00")){
                        respMsg.set(55, "Transaksi Sukses");                                      
                        respMsg.set(61, cfg.get(isoMsg.getString(3)+"_61"));
                        respMsg.set(62, cfg.get(isoMsg.getString(3)+"_62"));
                    }
                    
                    try {
                        Thread.sleep(Integer.parseInt(cfg.get(isoMsg.getString(3)+"_timedelay")));
                    }
                    catch(Exception e){e.printStackTrace();}
                    
                }
                else if (isoMsg.getString(3).toString().equalsIgnoreCase("380001")) {
                    
                  
                    respMsg.set(39, cfg.get(isoMsg.getString(3)+"_39"));
                    respMsg.set(55, "Transaksi Sukses");                                      
                    respMsg.set(61, cfg.get(isoMsg.getString(3)+"_61"));
                    respMsg.set(62, cfg.get(isoMsg.getString(3)+"_62"));
                    
                    try {
                        Thread.sleep(Integer.parseInt(cfg.get(isoMsg.getString(3)+"_timedelay")));
                    }
                    catch(Exception e){e.printStackTrace();}
                    
                }
                }
                catch(Exception er){
                    er.printStackTrace();
                    /* idpel not found */
                    respMsg.set(39, "14");
                }
            }
            else {
                /* unrecognized request */
                respMsg.set(39, "05");
            }

            isoSource.send(respMsg);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
