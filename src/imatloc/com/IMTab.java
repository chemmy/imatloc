package imatloc.com;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.os.Bundle;

public class IMTab extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	
        ConnectionConfiguration config = new ConnectionConfiguration(
        		"127.0.0.1", 5222);
		// Create a connection to the jabber.org server.
		XMPPConnection conn1 = new XMPPConnection(config);
		config.setSASLAuthenticationEnabled(true);
        //config.setDebuggerEnabled(true);
		config.setCompressionEnabled(true);


		try {
			conn1.connect();
			conn1.login("chemmy","820916" );

		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	
}
