
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import obs.CoinPile;
import obs.LifePack;
import obs.Tank;

/**
 *
 * @author Supun
 */
public class Mapviewer {
    
    static View view;
   static int mapMax=10;
   static String map[][]=new String[mapMax][mapMax];
   static int x=0,y=0,coiintCounter=0;
   static String playerNum=null;
   static ArrayList<String> P0;
   static ArrayList<String> P1;
   static ArrayList<String> P2;
   static ArrayList<String> P3;
   static ArrayList<String> P4;
    static int currentX,currentY,currentDtrection;//to my player
    static Tank mytank=null;
   
    
    public static void createMap(String address){//To create the initial Map
        
        String I;
        
        String brick;
        String stone;
        String water;
        ArrayList<String> brick_pos = new ArrayList<String>();
        ArrayList<String> stone_pos = new ArrayList<String>();
        ArrayList<String> water_pos = new ArrayList<String>();
        //String dtail="I:P0:5,7;1,3;6,8;8,7;0,4;2,6:4,8;3,2;7,1;9,3;7,4;4,2;8,1;3,6;5,8:6,2;3,1;1,7;1,2;4,7;9,2;5,4;7,2;8,6;2,4#";
String new_add=address.substring(0, address.length()-1);// to remove last # mark
        StringTokenizer str = new StringTokenizer(new_add, ":");
        I = str.nextToken();//check first value I,G, S etc
        playerNum = str.nextToken();//get player names
        brick = str.nextToken();//get bricks pattern
        stone = str.nextToken();//get stones pattern
        water = str.nextToken();//get water patterns
        System.out.println("I = "+I+" brick = "+brick+" stone = "+stone+" water = "+water);
        
        StringTokenizer bri=new StringTokenizer(brick, ";");
        for(int i=0;bri.hasMoreTokens();i++){
            brick_pos.add(i, bri.nextToken());
        }
        StringTokenizer sto=new StringTokenizer(stone, ";");
        for(int i=0;sto.hasMoreTokens();i++){
            stone_pos.add(i, sto.nextToken());
        }
        StringTokenizer wat=new StringTokenizer(water, ";");
        for(int i=0;wat.hasMoreTokens();i++){
            water_pos.add(i, wat.nextToken());
        }
        for(int i=0;i<mapMax;i++){
            for(int j=0;j<mapMax;j++){
                map[i][j]="0";
            } }
        for(int i=0;i<brick_pos.size();i++){
            String positions[]=brick_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]="1";//1 for brick
        }
        for(int i=0;i<stone_pos.size();i++){
            String positions[]=stone_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]="2";//2 for stone
        }
        for(int i=0;i<water_pos.size();i++){
            String positions[]=water_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]="3";//3 for water
        }
        for(int i=0;i<mapMax;i++){
            for(int j=0;j<mapMax;j++){
                System.out.println(map[i][j]);
        }
        }
        setGUI(map);//To visual the app in GUI
         
    }
    public static void updatePlayer(String address){//Update players contnuosly
        StringTokenizer str = new StringTokenizer(address, ":");
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
                    mytank.setDirec(currentDtrection);
                    mytank.setX(currentX);
                    mytank.setY(currentY);
                    view.updateTank(mytank.getX(), mytank.getY(), mytank.getDirec());
                } }}
    }
    public static void updateInitialTank(String address){//Update the initial tanks from command S
        //String msg=address.substring(0, address.length()-1);// to remove last # mark
     StringTokenizer str = new StringTokenizer(address, ":");
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
                mytank= new Tank(currentX, currentY,currentDtrection , view);
                   
    }
   public static void updateCoins(String address){//update all coins in map
        String msg=address.substring(0, address.length()-1);// to remove last # mark
        StringTokenizer str = new StringTokenizer(msg, ":");
                    String coin = str.nextToken();
                    String co_dir = str.nextToken();
                    String life_t = str.nextToken();
                    String po=str.nextToken();

                    str = new StringTokenizer(co_dir, ",");
                    int x = Integer.parseInt(str.nextToken());
                    int y = Integer.parseInt(str.nextToken());
                    int life = Integer.parseInt(life_t);
                    int points=Integer.parseInt(po);
                    //System.out.println(x + "," + y);
//                    new CoinPile( y, x, life, points,view);
                   
                    
    }
    public static void updateLifePacks(String address){//update Life packs
        //L:3,0:68784#
        String msg=address.substring(0, address.length()-1);// to remove last # mark
        StringTokenizer str = new StringTokenizer(msg, ":");
                    String lifeNa = str.nextToken();
                    String li_dir = str.nextToken();
                    String life_t = str.nextToken();
                    

                    str = new StringTokenizer(li_dir, ",");
                    int x = Integer.parseInt(str.nextToken());
                    int y = Integer.parseInt(str.nextToken());
                    int life = Integer.parseInt(life_t);
                    //System.out.println(x + "," + y);
                    new LifePack(y, x, life, view);
     
    }
     static void setGUI(String x[][]){
        view=new View(map,mapMax);
        view.setVisible(true);
        
        
    }
   
    public static void updateMap(String G){
        String raw_st = G.substring(2,G.length());
        
       
        StringTokenizer upmap=new StringTokenizer(raw_st, ":");
        for(int i=0;upmap.hasMoreTokens();i++){
            
            String k=upmap.nextToken();
            if(k.charAt(0)=='P'){
                playerUpdateStatus(k);
            }
        
        
    }
        
    
}
    private static void playerUpdateStatus(String P){
        
        
            ArrayList<String> tokens=new ArrayList<String>();
            StringTokenizer player=new StringTokenizer(P, ";");
            while(player.hasMoreTokens()){
                tokens.add(player.nextToken());
            }
            String positions[]=tokens.get(1).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            
            
            map[y][x]=tokens.get(0);
            if("P0".equals(tokens.get(0))){
                P0 =tokens;
            }
            else if("P1".equals(tokens.get(0))){
                P1 =tokens;
            }
            else if("P2".equals(tokens.get(0))){
                P2 =tokens;
            }
            else if("P3".equals(tokens.get(0))){
                P3 =tokens;
            }
            else if("P4".equals(tokens.get(0))){
                P4 =tokens;
            }

            System.out.println(P0);
            System.out.println(P1);
        }
     /*public ArrayList<String> direction(ArrayList<Cell> coordinates) {
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
    }*/
    public static void getLifePacks(String L){
        
        String details[]=L.split(":");
        String[] positions =details[0].split(",");
        x=Integer.parseInt(positions[0]);
        y=Integer.parseInt(positions[1]);
        int time=Integer.parseInt(details[1].substring(0,(details[1]).length()-1));
      
        
    }
    
    
}
