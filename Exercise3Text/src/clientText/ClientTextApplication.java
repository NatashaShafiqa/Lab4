package clientText;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTextApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		ClientTextFrame clientTextFrame = new ClientTextFrame();
		clientTextFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		// Status of connection is updated
		clientTextFrame.updateConnectionStatus(socket.isConnected());
		
		// Read from network
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		
		// Display the word length
		int textLength = dis.readInt();
		clientTextFrame.readTextLength(textLength);
		
		// Close everything
		dis.close();
		socket.close();

	}
	
}
