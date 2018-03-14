
package participant;

import iso_simulator.Constants;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import static org.jpos.transaction.TransactionConstants.PREPARED;

public class ReversalResponseParticipant implements TransactionParticipant{
    @Override
    public int prepare(long l, Serializable serializable) {
        Context ctx = (Context)serializable;
        ISOMsg respMsg = (ISOMsg)ctx.get(Constants.RESPONSE_KEY);
        ISOMsg reqMsg = (ISOMsg)ctx.get(Constants.REQUEST_KEY);
        
        try {
            
            Space sp = SpaceFactory.getSpace("tspace:symphoni");            
            sp.out ("jpos-send", reqMsg,10000);
            ISOMsg response = (ISOMsg) sp.in ("jpos-receive", 10000);
            System.out.println(new String(response.pack()));
            
            ctx.put(Constants.RESPONSE_KEY,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PREPARED;
    }

    @Override
    public void commit(long l, Serializable serializable) {

    }

    @Override
    public void abort(long l, Serializable serializable) {

    }
}