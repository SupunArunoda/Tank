/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TankServer extends Thread{
    ServerSocket serverSocket;
    TankClient cli;
    public TankServer(TankClient client) throws IOException{
        serverSocket=new ServerSocket(7000);
        //serverSocket.setSoTimeout(100000);
        this.cli=client;
    }
    @Override
    public void run(){
        cli.run("#JOIN");
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
