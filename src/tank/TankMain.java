/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.io.IOException;

/**
 *
 * @author Supun
 */
public class TankMain {
    public static void main(String[] args) throws IOException {
        TankClient client=new TankClient();
        TankServer2 server2=new TankServer2(client);
        server2.run();
    }
}
