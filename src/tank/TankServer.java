/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        cli.run("JOIN#");//request to join the server
        
        while(true){
            try {
                socket=serverSocket.accept();
                BufferedReader msg=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s=msg.readLine();
                System.out.println(s);
                if(s.charAt(0)=='I'&&s.charAt(1)==':'){//for priority I
                    Mapviewer.createMap(s);
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