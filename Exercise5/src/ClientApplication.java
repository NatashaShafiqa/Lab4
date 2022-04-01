import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApplication {

public static void main(String[] args) {
		
		ClientPage ctp = new ClientPage();
		
		try {
			// Connect to server at localhost, port 4228
			Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
			
			// Call method to get input from user
			String text = ctp.englishTextInput();
			int language = ctp.languageInput();
			
			//Create stream to write data on the network
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

			// Send input data to server
			outputStream.writeUTF(text);
			outputStream.writeInt(language);
			
			// Create input stream
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			
			// Output is translated and displayed
			String outputText = inputStream.readUTF();
			System.out.println("\nResult: " + outputText);
			
			// Close everything
			inputStream.close();
			outputStream.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
