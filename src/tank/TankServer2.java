package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import obs.Cell;
import obs.CoinPile2;
import obs.Stones;
import sun.misc.Queue;

public class TankServer2 extends Thread {

    private ServerSocket serverSock;
    private TankClient cl;
    private int mapSize = 10;
    private int[][] map = new int[mapSize][mapSize];
    private Cell map2[][] = new Cell[mapSize][mapSize];
    private String playerNum;
    private int currentX = 0;// for my player's coordinates
    private int currentY = 0;// for my player's coordinates
    private int[] currentX1 = new int[]{-1, -1, -1, -1};//other players coordinates
    private int[] currentY1 = new int[]{-1, -1, -1, -1};//other players coordinates
    private int[] currentHealth = new int[]{100, 100, 100, 100};//to remove other players WRT life time
    private int[] kk = new int[]{0, 0, 0, 0};
    private int currentDtrection = 0;
    ArrayList<Stones> stone1 = new ArrayList<Stones>();

    public TankServer2(TankClient dd) throws IOException {
        serverSock = new ServerSocket(7000);
        serverSock.setSoTimeout(100000);
        cl = dd;
    }

    @Override
    public void run() {

        int k = 0;
        int l = 0;
        int tte = 0; //time to end
        int coinPileCounter = 0;
        CoinPile2 bestPile = new CoinPile2(1000, 0, mapSize - 1, mapSize, 10000);
        boolean running = false;//set whether client tank is running or not
        ArrayList<String> coor = new ArrayList<String>();//to store coordinates
        ArrayList<CoinPile2> coin = new ArrayList<CoinPile2>();//to store the coins
        CoinPile2 testing = null;//to set a copy of a coinpile
        boolean shooting = false;//set whether client tank is shooting or not
        int priorityX = -1;
        int priorityY = -1;

        //send join message
        cl.run("JOIN#");
        //

        while (true) {
            try {
                Socket server = serverSock.accept();

                //read from the socket
                BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String msg = input.readLine();
                System.out.println(msg);
                //
                //cl.run2(msg);  //send msg to the GUI.
                
                if (msg.charAt(0) == 'I' && msg.charAt(1) == ':') {
                    genarateMap(msg);// generate the initial map
                }

                if (msg.equals("PITFALL#") || msg.equals("DEAD#")) {
                    System.out.println("Fall to the Water");
                    break;
                }

                //set the initial coordinates and directoin
                if (msg.charAt(0) == 'S') {
                    StringTokenizer str = new StringTokenizer(msg, ":");
                    String s = str.nextToken();
                    s = str.nextToken();
                    str = new StringTokenizer(s, ";");
                    String plr = str.nextToken();
                    String coord = str.nextToken();
                    String direc = str.nextToken();

                    str = new StringTokenizer(coord, ",");
                    currentX = Integer.parseInt(str.nextToken());
                    currentY = Integer.parseInt(str.nextToken());
                    currentDtrection = Integer.parseInt(direc.substring(0, 1));
                }
                //

                //store the coin piles
                if (msg.charAt(0) == 'C' && msg.charAt(1) == ':') {
                    StringTokenizer str = new StringTokenizer(msg, ":");
                    String cc = str.nextToken();
                    String coord = str.nextToken();
                    String lt = str.nextToken();

                    str = new StringTokenizer(coord, ",");
                    int x = Integer.parseInt(str.nextToken());
                    int y = Integer.parseInt(str.nextToken());
                    int life = Integer.parseInt(lt);
                   
                    coin.add(new CoinPile2(coinPileCounter, x, y, life, 0));
                    coinPileCounter++;
                }

                //set current position and direction according to the gloable msg
                //update the life time of the coin piles
                if (msg.charAt(0) == 'G' && msg.charAt(1) == ':') {
                    shooting = false;
                    updateCoinPiles(coin);
                    setCurrentPosi(msg);
                    bestPile = bestCoinPile(coin);
                    for (int i = 0; i < 4; i++) {
                        if (currentX1[i] > -1) {
                            //System.out.println(currentHealth[i]);
                            if (currentDtrection == 0 && currentX == currentX1[i] && currentY > currentY1[i]) {//for north direction shooting
                                shooting = true;
                            } else if (currentDtrection == 1 && currentX < currentX1[i] && currentY == currentY1[i]) {//for east direction shooting
                                shooting = true;
                            } else if (currentDtrection == 2 && currentX == currentX1[i] && currentY < currentY1[i]) {//for south direction shooting
                                shooting = true;
                            } else if (currentDtrection == 3 && currentX > currentX1[i] && currentY == currentY1[i]) {//for west direction shooting
                                shooting = true;
                            } else {
                                shooting = false;
                            }
                        }

                        if (currentHealth[i] == 0) {//another plyer's life is zero or not
                           
                            priorityX = currentX1[i];
                            priorityY = currentY1[i];
                            System.out.println("dead man---" + currentX1[i] + "," + currentY1[i]);
                            running = false;
                            shooting = false;
                            bestPile = new CoinPile2(1000, priorityX, priorityY, 100000, 1);
                            System.out.println("best after dead--" + priorityX + "," + priorityY);
                            break;
                        }

                        if (shooting == true) {
                            break;
                        }

                    }
                    
                    //compute the path and direction array according to the best coin pile position
                    if (!running && bestPile != null) {
                        running = true;
                        testing = bestPile;// to set a copy of best coin pile
                        int x = bestPile.getX();
                        int y = bestPile.getY();
                        //System.out.println(x + "," + y);
                        computePath(map2[currentY][currentX], map2[y][x], map2);
                        ArrayList<Cell> test = new ArrayList();
                        test = path(map2[y][x]);
                        setMap2Default(map2);
                        coor = direction(test);
                        k = coor.size();
                        tte = k;// time to end
                        l = 0;
                    }
                    //

                    if (bestPile != null && testing != null) {
                        if (testing.getI() != 1000 && tte > bestPile.getDistance()) {//check for distance
                            running = false;
                        } else if (testing.getI() != 1000 && coin.get(testing.getI()).getLT() == 0) {//check for life time of coin pile
                            running = false;
                        } else if (testing.getI() != 1000 && k == l) {//check time to end is one
                            coin.get(testing.getI()).setLT(0);
                            running = false;//tank doesn't choose the path
                        } else if (testing.getI() == 1000 && k == l) {
                            running = false;
                        }
                    }
                    if (l < k && running && shooting) {
                        cl.run("SHOOT#");
                        //System.out.println("SHOOT#");
                    } 
                    else if (l < k && running) {
                        cl.run(coor.get(l));
                        //System.out.println(coor.get(l));
                        tte--;//decrease time to end value
                        l++;// increment the setting path value
                    }
                }
                //
                //

                for (int i = 0; i < 4; i++) {
                    if (currentX1[i] != -1) {
                        for (int m = 0; m < coin.size(); m++) {
                            if (coin.get(m).getX() == currentX1[i] && coin.get(m).getY() == currentY1[i]) {
                                coin.get(m).setLT(0);
                                //System.out.println(currentX1[i] + "," + currentY1[i] + "----Others taken..");
                            }
                        }
                    }
                }

                if ((msg.equals("CELL_OCCUPIED#") || msg.equals("OBSTACLE;25#")) && l > 0) {
                    l--;
                    tte++;
                }

                server.close();

            } catch (InterruptedException ex) {
                
            } catch (SocketTimeoutException st) {
                System.out.println("Socket Time out...");
                break;
            } catch (IOException ex) {
                System.out.println("error>>" + ex);
                break;
            }
        }
    }
    

    public void genarateMap(String dtail) {
        String I;
        String brick;
        String stone;
        String water;
        ArrayList<String> brk = new ArrayList<String>();// for bricks
        ArrayList<String> stn = new ArrayList<String>();// for stones
        ArrayList<String> wtr = new ArrayList<String>();// for water

        StringTokenizer str = new StringTokenizer(dtail, ":");
        I = str.nextToken();
        playerNum = str.nextToken();
        brick = str.nextToken();
        stone = str.nextToken();
        water = str.nextToken();

        str = new StringTokenizer(brick, ";");
        for (int i = 0; str.hasMoreTokens(); i++) {
            brk.add(i, str.nextToken());
        }

        str = new StringTokenizer(stone, ";");
        for (int i = 0; str.hasMoreTokens(); i++) {
            stn.add(i, str.nextToken());
        }

        str = new StringTokenizer(water, ";");
        for (int i = 0; str.hasMoreTokens(); i++) {
            wtr.add(i, str.nextToken());
        }

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = 1;//available spaces
            }
        }

        for (int i = 0; i < brk.size(); i++) {
            String[] coordinates = brk.get(i).split(",");
            int y = Integer.parseInt(coordinates[0]);
            int x = Integer.parseInt(coordinates[1]);
            map[x][y] = 0;// stones or bricks
        }

        for (int i = 0; i < stn.size(); i++) {
            String[] coordinates = stn.get(i).split(",");
            int y = Integer.parseInt(coordinates[0]);
            int x = Integer.parseInt(coordinates[1]);
            Stones st = new Stones(x, y);
            stone1.add(st);
            map[x][y] = 0;// stones or bricks
        }

        for (int i = 0; i < wtr.size(); i++) {
            String[] coordinates = wtr.get(i).split(",");
            int y = Integer.parseInt(coordinates[0]);
            int x = -1;
            if (i == wtr.size() - 1) {
                x = Integer.parseInt(coordinates[1].substring(0, coordinates[1].length() - 1));
            } else {
                x = Integer.parseInt(coordinates[1]);
            }
            map[x][y] = 0;// stones or bricks
        }

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("\n");
        }

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map2[i][j] = new Cell(j, i, map[i][j]);//copy the initial map details to Cell object whicjh consist position and value( 0 ir 1)
            }
        }
    }

    public CoinPile2 bestCoinPile(ArrayList<CoinPile2> coin) {// to return the minimu distance for a coin from current position
        CoinPile2 best = null;
        int distance = 0;
        int minDis = 10000;//set for minimm distance
        if (!coin.isEmpty()) {
            for (int i = 0; i < coin.size(); i++) {
                if (coin.get(i).getLT() > 0) {
                    try {
                        computePath(map2[currentY][currentX], map2[coin.get(i).getY()][coin.get(i).getX()], map2);
                        ArrayList<Cell> test = new ArrayList();
                        test = path(map2[coin.get(i).getY()][coin.get(i).getX()]);
                        setMap2Default(map2);
                        ArrayList<String> coor = direction(test);// to travel through the coordinates 
                        distance = coor.size();//get the size
                        if (coin.get(i).getLT() / 1000 >= distance && distance <= minDis) {//set minimum distance when several coins are apperared
                            best = coin.get(i);
                            coin.get(i).setDistance(distance);
                            minDis = distance;
                        }
                    } catch (InterruptedException ex) {
                        
                    }
                }
            }
        }
        return best;
    }

    public void updateCoinPiles(ArrayList<CoinPile2> piles) {//to update coinpiles dynamically
        int s = piles.size();
        if (!piles.isEmpty()) {
            for (int i = 0; i < s; i++) {
                int j = piles.get(i).getLT();
                if (j < 1000) {
                    piles.get(i).setLT(0);//set the coin pile lifr time
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
                if (p.equals(playerNum)) {//check for my player P0
                    String coor = str.nextToken();//get co-ordinates
                    currentDtrection = Integer.parseInt(str.nextToken());
                    StringTokenizer str1 = new StringTokenizer(coor, ",");
                    currentX = Integer.parseInt(str1.nextToken());
                    currentY = Integer.parseInt(str1.nextToken());
                } else {// to set other player coordinates
                    String coor = str.nextToken();
                    StringTokenizer str1 = new StringTokenizer(coor, ",");
                    currentX1[j] = Integer.parseInt(str1.nextToken());//set other plyer's X coordinates
                    currentY1[j] = Integer.parseInt(str1.nextToken());//set other plyer's Y coordinates
                    String dir = str.nextToken();//set the direction
                    String wshot = str.nextToken();//check whether shot
                    currentHealth[j] = Integer.parseInt(str.nextToken());
                    if (currentHealth[j] == 0 && kk[j]!=0) {
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

    public void computePath(Cell source, Cell dest, Cell m[][]) throws InterruptedException {// compute easiest path to cell
        source.setIsVisited(true);
        Queue cellQueue = new Queue();
        cellQueue.enqueue(source);
        
        while (!cellQueue.isEmpty()) {
            Cell u = (Cell) cellQueue.dequeue();

            if (u.getX() == dest.getX() && u.getY() == dest.getY()) {//whether tank has reached to the destination
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

    public void setMap2Default(Cell ma[][]) {//to set visited cells as non visited
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

    public ArrayList<Cell> path(Cell dest) {//to return path as cell object easiest way
        int crntx = currentX;
        int crnty = currentY;
        ArrayList<Cell> path = new ArrayList<Cell>();
        while (!(crntx == dest.getX() && crnty == dest.getY())) {//add path while current position equals to destination position
            path.add(dest);
            dest = dest.getparent();
        }
        return path;
    }

    public ArrayList<String> direction(ArrayList<Cell> coordinates) {//to return direction arraylist with directions to travel
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
                    dirc.add("DOWN#");//to change the direction
                    dirc.add("DOWN#");//to go on the direction
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
}
