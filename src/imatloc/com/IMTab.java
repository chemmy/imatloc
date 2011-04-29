package imatloc.com;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class IMTab extends Activity {
	private Handler mHandler = new Handler();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);
	
        ConnectionConfiguration config = new ConnectionConfiguration(
        		"10.141.177.29", 5222);
		// Create a connection to the jabber.org server.
		XMPPConnection conn1 = new XMPPConnection(config);
		//config.setSASLAuthenticationEnabled(true);
        //config.setDebuggerEnabled(true);
		config.setCompressionEnabled(true);
		config.setSelfSignedCertificateEnabled(true);
		config.setSASLAuthenticationEnabled(false);			


		try {
			conn1.connect();
			conn1.login("chemmy", "123456", "imatloc");
			
			ChatManager chatmanager = conn1.getChatManager();
			Chat newChat = chatmanager.createChat("chemmy@chemmy-laptop", 
				new MessageListener(){

					@Override
					public void processMessage(Chat chat, Message message) {
						// TODO Auto-generated method stub
						Log.v("Chat", "Rcv Msg:"+message.toXML());
					}
			        
			    });
			newChat.sendMessage("Hi, I'm at imatloc.com");

		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	
}
