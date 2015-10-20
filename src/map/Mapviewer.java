/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Supun
 */
public class Mapviewer {
   static int mapMax=10;
   static int map[][]=new int[mapMax][mapMax];
   static int x=0,y=0;
   static String P0;
   static String P1;
   static String P2;
   static String P3;
   static String P4;
   
    
    public static void createMap(String address){
        
        String I;
        String playerNum;
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
                map[i][j]=0;
            } }
        for(int i=0;i<brick_pos.size();i++){
            String positions[]=brick_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]=1;//1 for brick
        }
        for(int i=0;i<stone_pos.size();i++){
            String positions[]=stone_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]=2;//2 for stone
        }
        for(int i=0;i<water_pos.size();i++){
            String positions[]=water_pos.get(i).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            map[y][x]=3;//3 for water
        }
        printMap();
         
    }
    private static void printMap(){
        for(int i=0;i<mapMax;i++){
            for(int j=0;j<mapMax;j++){
                System.out.print(map[i][j]);
            } 
             System.out.println();
         }
        
    }
    public static void updateMap(String G){
        String raw_st = G.substring(2,G.length());
        System.out.println(raw_st);
       
        StringTokenizer upmap=new StringTokenizer(raw_st, ":");
        for(int i=0;upmap.hasMoreTokens();i++){
            
            String k=upmap.nextToken();
            if(k.charAt(0)=='P'){
                playerUpdateStatus(k);
            }
        
        
    }
        
    
}
    private static void playerUpdateStatus(String P){
        int id=0;//unique id for each player 
        if("P0".equals(P.substring(0, 2))){
            P0=P;
            id=10;
        }
        else if("P1".equals(P.substring(0, 2))){
            P1=P;
            id=11;
        }
        else if("P2".equals(P.substring(0, 2))){
            P2=P;
            id=12;
        }
        else if("P3".equals(P.substring(0, 2))){
            P3=P;
            id=13;
        }
        if("P4".equals(P.substring(0, 2))){
            P4=P;
            id=14;
        }
            ArrayList<String> tokens=new ArrayList<String>();
            StringTokenizer player=new StringTokenizer(P, ";");
            while(player.hasMoreTokens()){
                tokens.add(player.nextToken());
            }
            String positions[]=tokens.get(1).split(",");
            x=Integer.parseInt(positions[0]);
            y=Integer.parseInt(positions[1]);
            
            
            map[y][x]=id;
            printMap();
        }
    
}
