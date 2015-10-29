
package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import map.Mapviewer;
import map.View;

public class TankServer extends Thread{
    ServerSocket serverSocket;
    Socket socket;
    static TankClient cli;
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
               else if(s.charAt(0)=='G'&&s.charAt(1)==':'){
                    
                    //Mapviewer.updateMap(s);
                    Mapviewer.updatePlayer(s);
                }
               else if(s.charAt(0)=='C'&&s.charAt(1)==':'){//for coinPiles
                    
                    Mapviewer.updateCoins(s);
                }
               else if(s.charAt(0)=='L'&&s.charAt(1)==':'){//for LifePacks
                    
                    Mapviewer.updateLifePacks(s);
                }
               else if (s.charAt(0) == 'S'&& s.charAt(1)==':') {
                    Mapviewer.updateInitialTank(s);
                    
                }else if(s.equals("TOO_QUICK#")){
                    JOptionPane.showMessageDialog(null, "Too Quick Input", "Ooops..", JOptionPane.OK_OPTION);
                }else if(s.equals("PITFALL#")){
                    JOptionPane.showMessageDialog(null, "You have fallen to a Pit.. GAME OVER", "Game Over", JOptionPane.OK_OPTION);
                    return;
                }else if(s.equals("DEAD#")){
                    JOptionPane.showMessageDialog(null, "You are Dead", "GAME OVER", JOptionPane.OK_OPTION);
                    return;
                }
                else if(s.equals("GAME_FINISHED#")){
                    JOptionPane.showMessageDialog(null, "Game has Finished", "Finishing Game", JOptionPane.OK_OPTION);
                    return;
                }else if(s.equals("GAME_ALREADY_STARTED#")){
                    JOptionPane.showMessageDialog(null, "Game has already started", "Already Started", JOptionPane.OK_OPTION);
                }else if(s.equals("OBSTACLE;25#")){
                    JOptionPane.showMessageDialog(null, "Obstacle has found", "Obstacles", JOptionPane.OK_OPTION);
                }
                
                 
            } catch (IOException ex) {
                Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    public static void moveTank(String s){
        if(s.equals("UP")){
            cli.run("UP#");
        }else if(s.equals("DOWN")){
            cli.run("DOWN#");
        }else if(s.equals("LEFT")){
            cli.run("LEFT#");
        }else if(s.equals("RIGHT")){
            cli.run("RIGHT#");
        }else if(s.equals("SHOOT")){
            cli.run("SHOOT#");
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
