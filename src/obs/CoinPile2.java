/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obs;

/**
 *
 * @author Supun
 */
public class CoinPile2 {

    private int i;//Coin Pile Value
    private int x;//horizontal osition
    private int y;//vertical position
    private int LT;//life
    private int distance;//distance to coin

    public CoinPile2(int i,int x, int y, int LT, int distance) {
        this.i=i;
        this.x = x;
        this.y = y;
        this.LT = LT;
        this.distance=distance;
    }

    public int getLT() {
        return LT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLT(int LT) {
        this.LT = LT;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
}