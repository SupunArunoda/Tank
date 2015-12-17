package tank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TankClient extends Thread{
    private Socket socket;
    private DataOutputStream dos;
    private String ip="127.0.0.1";
    private ServerSocket serverSocket;
    private int port=6000;
public void connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: ");
			//return false;
		}
	}

    
    public void run(String msg){
     connect();
        try {        
            dos.writeBytes(msg);
            dos.flush();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
}
