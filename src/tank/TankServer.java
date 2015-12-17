
package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import map.Mapviewer;
import map.View;
import obs.Cell;
import obs.CoinPile;
import obs.CoinPile2;
import sun.misc.Queue;

public class TankServer extends Thread{
    ServerSocket serverSocket;
    Socket socket;
    static TankClient cli;
    int mapSize=20;
    private Cell map2[][] = new Cell[mapSize][mapSize];
    private String playerNum;
    private int currentX = 0;
    private int currentY = 0;
    int coinPileCounter = 0;
    int k = 0;
        int l = 0;
        int tte = 0; //time to end
    private int[] currentX1 = new int[]{-1, -1, -1, -1};
    private int[] currentY1 = new int[]{-1, -1, -1, -1};
    private int currentDtrection = 0;
    private int[] kk = new int[]{0, 0, 0, 0};
    private int[] currentHealth = new int[]{100, 100, 100, 100};
     CoinPile2 bestPile = new CoinPile2(1000, 0, mapSize - 1, mapSize, 10000);
        CoinPile2 testing = null;
        private boolean running=false;
        
        ArrayList<String> coor = new ArrayList<String>();
        ArrayList<CoinPile2> coin = new ArrayList<CoinPile2>();
    public TankServer(TankClient client) throws IOException{
        serverSocket=new ServerSocket(7000);
        this.cli=client;
    }
    public void updateCoinPiles(ArrayList<CoinPile2> piles) {
        int s = piles.size();
        if (!piles.isEmpty()) {
            for (int i = 0; i < s; i++) {
                int j = piles.get(i).getLT();
                if (j < 1000) {
                    piles.get(i).setLT(0);
                } else {
                    piles.get(i).setLT(j - 1000);
                }

            }
        }
    }
    public void setCurrentPosi(String msg) {
        StringTokenizer str = new StringTokenizer(msg, ":");
        ArrayList<String> detail = new ArrayList<String>();
        while (str.hasMoreTokens()) {
            detail.add(str.nextToken());
        }

        int j = 0;
        for (int i = 1; i < detail.size(); i++) {
            str = new StringTokenizer(detail.get(i), ";");
            String p = str.nextToken();
            if (p.charAt(0) == 'P') {
                if (p.equals(playerNum)) {
                    String coor = str.nextToken();
                    currentDtrection = Integer.parseInt(str.nextToken());
                    StringTokenizer str1 = new StringTokenizer(coor, ",");
                    currentX = Integer.parseInt(str1.nextToken());
                    currentY = Integer.parseInt(str1.nextToken());
                } else {
                    String coor = str.nextToken();
                    StringTokenizer str1 = new StringTokenizer(coor, ",");
                    currentX1[j] = Integer.parseInt(str1.nextToken());
                    currentY1[j] = Integer.parseInt(str1.nextToken());
                    String dir = str.nextToken();
                    String wshot = str.nextToken();
                    currentHealth[j] = Integer.parseInt(str.nextToken());
                    if (currentHealth[j] == 0 && kk[j] != 0) {
                        currentX1[j] = -1;
                        currentY1[j] = -1;
                        currentHealth[j] = -1;
                    } else if (currentHealth[j] == 0 && kk[j] == 0) {
                        kk[j] = kk[j] + 1;
                    }
                    j++;
                }
            }
        }
    }
    public void computePath(Cell source, Cell dest, Cell m[][]) throws InterruptedException {
        source.setIsVisited(true);
        Queue cellQueue = new Queue();
        cellQueue.enqueue(source);

        while (!cellQueue.isEmpty()) {
            Cell u = (Cell) cellQueue.dequeue();

            if (u.getX() == dest.getX() && u.getY() == dest.getY()) {
                break;
            }

            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1:
                        if ((u.getY() - 1) >= 0 && m[u.getY() - 1][u.getX()].getValue() == 1 && !m[u.getY() - 1][u.getX()].isIsVisited()) {
                            m[u.getY() - 1][u.getX()].setIsVisited(true);
                            m[u.getY() - 1][u.getX()].setparent(u);
                            cellQueue.enqueue(m[u.getY() - 1][u.getX()]);
                        }
                    case 2:
                        if ((u.getX() + 1) < mapSize && m[u.getY()][u.getX() + 1].getValue() == 1 && !m[u.getY()][u.getX() + 1].isIsVisited()) {
                            m[u.getY()][u.getX() + 1].setIsVisited(true);
                            m[u.getY()][u.getX() + 1].setparent(u);
                            cellQueue.enqueue(m[u.getY()][u.getX() + 1]);
                        }

                    case 3:
                        if ((u.getY() + 1) < mapSize && m[u.getY() + 1][u.getX()].getValue() == 1 && !m[u.getY() + 1][u.getX()].isIsVisited()) {
                            m[u.getY() + 1][u.getX()].setIsVisited(true);
                            m[u.getY() + 1][u.getX()].setparent(u);
                            cellQueue.enqueue(m[u.getY() + 1][u.getX()]);
                        }

                    case 4:
                        if ((u.getX() - 1) >= 0 && m[u.getY()][u.getX() - 1].getValue() == 1 && !m[u.getY()][u.getX() - 1].isIsVisited()) {
                            m[u.getY()][u.getX() - 1].setIsVisited(true);
                            m[u.getY()][u.getX() - 1].setparent(u);
                            cellQueue.enqueue(m[u.getY()][u.getX() - 1]);
                        }

                }
            }
        }
    }
    public ArrayList<Cell> path(Cell dest) {
        int crntx = currentX;
        int crnty = currentY;
        // System.out.println("path2222  " + crntx + "," + crnty);
        ArrayList<Cell> path = new ArrayList<Cell>();
        while (!(crntx == dest.getX() && crnty == dest.getY())) {
            //System.out.println("path--" + dest.getX() + "," + dest.getY());
            path.add(dest);
            dest = dest.getparent();
        }
        return path;
    }
    public void setMap2Default(Cell ma[][]) {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (ma[i][j].isIsVisited() == true) {
                    ma[i][j].setIsVisited(false);
                }
                if (ma[i][j].getparent() != null) {
                    ma[i][j].setparent(null);
                }
            }
        }
    }
      public ArrayList<String> direction(ArrayList<Cell> coordinates) {
        ArrayList<String> dirc = new ArrayList<String>();
        int crntX = currentX;
        int crntY = currentY;
        int crntDire = currentDtrection;
        for (int i = coordinates.size() - 1; i >= 0; i--) {
            if (crntX == coordinates.get(i).getX() && crntY < coordinates.get(i).getY()) {
                crntX = coordinates.get(i).getX();
                crntY = coordinates.get(i).getY();
                if (crntDire == 2) {
                    dirc.add("DOWN#");
                } else {
                    crntDire = 2;
                    dirc.add("DOWN#");
                    dirc.add("DOWN#");
                }
            } else if (crntX == coordinates.get(i).getX() && crntY > coordinates.get(i).getY()) {
                crntX = coordinates.get(i).getX();
                crntY = coordinates.get(i).getY();
                if (crntDire == 0) {
                    dirc.add("UP#");
                } else {
                    crntDire = 0;
                    dirc.add("UP#");
                    dirc.add("UP#");
                }
            } else if (crntX > coordinates.get(i).getX() && crntY == coordinates.get(i).getY()) {
                crntX = coordinates.get(i).getX();
                crntY = coordinates.get(i).getY();
                if (crntDire == 3) {
                    dirc.add("LEFT#");
                } else {
                    crntDire = 3;
                    dirc.add("LEFT#");
                    dirc.add("LEFT#");
                }
            } else if (crntX < coordinates.get(i).getX() && crntY == coordinates.get(i).getY()) {
                crntX = coordinates.get(i).getX();
                crntY = coordinates.get(i).getY();
                if (crntDire == 1) {
                    dirc.add("RIGHT#");
                } else {
                    crntDire = 1;
                    dirc.add("RIGHT#");
                    dirc.add("RIGHT#");
                }
            }
        }
        return dirc;
    }
    public CoinPile2 bestCoinPile(ArrayList<CoinPile2> coin) {
        CoinPile2 best = null;
        int distance = 0;
        int minDis = 10000;
        if (!coin.isEmpty()) {
            for (int i = 0; i < coin.size(); i++) {
                if (coin.get(i).getLT() > 0) {
                    try {
                        computePath(map2[currentY][currentX], map2[coin.get(i).getY()][coin.get(i).getX()], map2);
                        ArrayList<Cell> test = new ArrayList();
                        test = path(map2[coin.get(i).getY()][coin.get(i).getX()]);
                        setMap2Default(map2);
                        ArrayList<String> coor = direction(test);
                        distance = coor.size();
                        if (coin.get(i).getLT() / 1000 >= distance && distance <= minDis) {
                            best = coin.get(i);
                            coin.get(i).setDistance(distance);
                            minDis = distance;
                        }
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        return best;
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
                    
                    updateCoinPiles(coin);
                    setCurrentPosi(s);
                    bestPile = bestCoinPile(coin);
                    if (!running && bestPile != null) {
                        running = true;
                        testing = bestPile;
                        int x = bestPile.getX();
                        int y = bestPile.getY();
                        try {
                            //System.out.println(x + "," + y);
                            computePath(map2[currentY][currentX], map2[y][x], map2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ArrayList<Cell> test = new ArrayList();
                        test = path(map2[y][x]);
                        setMap2Default(map2);
                        coor = direction(test);
                        for (int i = 0; i < coor.size(); i++) {
                            //System.out.println(coor.get(i));
                        }
                        k = coor.size();
                        tte = k;
                        l = 0;
                    }
                    if (bestPile != null && testing != null) {
                        //System.out.println("best coin---" + bestPile.getX() + "," + bestPile.getY());
                        if (testing.getI() != 1000 && tte > bestPile.getDistance()) {
                            running = false;
                        } else if (testing.getI() != 1000 && coin.get(testing.getI()).getLT() == 0) {
                            running = false;
                        } else if (testing.getI() != 1000 && k == l) {
                            coin.get(testing.getI()).setLT(0);
                            running = false;
                        } else if (testing.getI() == 1000 && k == l) {
                            running = false;
                        }
                    }

                   if (l < k && running) {
                        cli.run(coor.get(l));
                        //System.out.println(coor.get(l));
                        tte--;
                        l++;
                    }
                    
                }
               else if(s.charAt(0)=='C'&&s.charAt(1)==':'){//for coinPiles
                    
                    Mapviewer.updateCoins(s);
                    StringTokenizer str = new StringTokenizer(s, ":");
                    String cc = str.nextToken();
                    String coord = str.nextToken();
                    String lt = str.nextToken();

                    str = new StringTokenizer(coord, ",");
                    int x = Integer.parseInt(str.nextToken());
                    int y = Integer.parseInt(str.nextToken());
                    int life = Integer.parseInt(lt);
                    //System.out.println(x + "," + y);
                    coin.add(new CoinPile2(coinPileCounter, x, y, life, 0));
                    coinPileCounter++;
                }
               else if(s.charAt(0)=='L'&&s.charAt(1)==':'){//for LifePacks
                    
                    Mapviewer.updateLifePacks(s);
                }
               else if (s.charAt(0) == 'S'&& s.charAt(1)==':') {
                    Mapviewer.updateInitialTank(s);
                    StringTokenizer str = new StringTokenizer(s, ":");
                    String s2 = str.nextToken();
                    s2 = str.nextToken();
                    str = new StringTokenizer(s2, ";");
                    String plr = str.nextToken();
                    String coord = str.nextToken();
                    String direc = str.nextToken();

                    str = new StringTokenizer(coord, ",");
                    currentX = Integer.parseInt(str.nextToken());
                    currentY = Integer.parseInt(str.nextToken());
                    currentDtrection = Integer.parseInt(direc.substring(0, 1));
                    
                }else if(s.equals("TOO_QUICK#")){//if user inputs fast
                    JOptionPane.showMessageDialog(null, "Too Quick Input", "Ooops..", JOptionPane.OK_OPTION);
                }else if(s.equals("PITFALL#")){//if tank falls to a pit
                    JOptionPane.showMessageDialog(null, "You have fallen to a Pit.. GAME OVER", "Game Over", JOptionPane.OK_OPTION);
                    return;
                }else if(s.equals("DEAD#")){//if tank dead
                    JOptionPane.showMessageDialog(null, "You are Dead", "GAME OVER", JOptionPane.OK_OPTION);
                    return;
                }
                else if(s.equals("GAME_FINISHED#")){//if game has finished
                    JOptionPane.showMessageDialog(null, "Game has Finished", "Finishing Game", JOptionPane.OK_OPTION);
                    return;
                }else if(s.equals("GAME_ALREADY_STARTED#")){//If game has already start
                    JOptionPane.showMessageDialog(null, "Game has already started", "Already Started", JOptionPane.OK_OPTION);
                }else if(s.equals("OBSTACLE;25#")){//If found obstacle
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
    /*public static void main(String[] args) {
        TankClient tankClient=new TankClient();
        
        try {
            TankServer tankServer=new TankServer(tankClient);
            tankServer.start();
        } catch (IOException ex) {
            Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
