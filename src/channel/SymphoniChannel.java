
package channel;

import java.io.IOException;
import java.net.ServerSocket;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

/**
 *
 * @author root
 */
public class SymphoniChannel extends BaseChannel {
    
    /**
     * Public constructor (used by Class.forName("...").newInstance())
     */
    public SymphoniChannel () {
        super();
    }
    /**
     * Construct client ISOChannel
     * @param host  server TCP Address
     * @param port  server port number
     * @param p     an ISOPackager
     * @see ISOPackager
     */
    public SymphoniChannel (String host, int port, ISOPackager p) {
        super(host, port, p);
    }
    /**
     * Construct server ISOChannel
     * @param p     an ISOPackager
     * @see ISOPackager
     * @exception IOException
     */
    public SymphoniChannel (ISOPackager p) throws IOException {
        super(p);
    }
    /**
     * constructs a server ISOChannel associated with a Server Socket
     * @param p     an ISOPackager
     * @param serverSocket where to accept a connection
     * @exception IOException
     * @see ISOPackager
     */
    public SymphoniChannel (ISOPackager p, ServerSocket serverSocket) 
        throws IOException
    {
        super(p, serverSocket);
    }
    /**
     * @param m the Message to send (in this case it is unused)
     * @param len   message len (ignored)
     * @exception IOException
     */
    protected void sendMessageTrailler(ISOMsg m, int len) throws IOException {
        serverOut.write (10);
    }
    /**
     * @return a byte array with the received message
     * @exception IOException
     */
    protected byte[] streamReceive() throws IOException {
        int i;
        byte[] buf = new byte[4096];
        for (i=0; i<4096; i++) {
            int c = serverIn.read();
            //System.out.println("char > "+c);
            //if (c == 0x10)
            if (c==10)
                break;
            buf[i] = (byte) c;
        }
        if (i == 4096)
            throw new IOException("message too long");

        byte[] d = new byte[i];
        System.arraycopy(buf, 0, d, 0, i);
        return d;
    }

   
}
