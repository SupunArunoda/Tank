
package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Mapviewer;

public class TankServer extends Thread{
    ServerSocket serverSocket;
    Socket socket;
    TankClient cli;
    public TankServer(TankClient client) throws IOException{
        serverSocket=new ServerSocket(7000);
        //serverSocket.setSoTimeout(100000);
        this.cli=client;
    }
    @Override
    public void run(){
        cli.run("JOIN#");//request to join the game server
        
        while(true){
            try {
                socket=serverSocket.accept();
                BufferedReader msg=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s=msg.readLine();
                System.out.println(s);
                if(s.charAt(0)=='I'&&s.charAt(1)==':'){//for game Initialisation
                    Mapviewer.createMap(s);
                }
                if(s.charAt(0)=='G'&&s.charAt(1)==':'){
                    
                    Mapviewer.updateMap(s);
                }
                if(s.charAt(0)=='C'&&s.charAt(1)==':'){//for coinPiles
                    
                    Mapviewer.updateCoins(s);
                }
                if(s.charAt(0)=='L'&&s.charAt(1)==':'){//for LifePacks
                    
                    Mapviewer.updateLifePacks(s);
                }
                 
            } catch (IOException ex) {
                Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    public static void main(String[] args) {
        TankClient tankClient=new TankClient();
        try {
            TankServer tankServer=new TankServer(tankClient);
            tankServer.start();
        } catch (IOException ex) {
            Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
