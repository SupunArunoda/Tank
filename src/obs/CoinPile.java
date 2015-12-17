/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obs;

import java.util.logging.Level;
import java.util.logging.Logger;
import map.View;

/**
 *
 * @author Supun
 */
public class CoinPile extends Thread{

    private int X;
    private int Y;
    private int life;
    private int value;
    private View view;
    private int distance;
    public CoinPile(int X,int Y,int life,int value,int distance,View view) {
        this.distance=distance;
        this.X=X;
        this.Y=Y;
        this.life=life;
        this.value=value;
        this.view=view;
        this.start();
    }
    @Override
    public  void run(){
        try {
            getView().updateCoin(this.getX(),this.getY(),"NEW");
            this.sleep(getLife());
            getView().updateCoin(this.getX(),this.getY(),"REMOVE");
        } catch (InterruptedException ex) {
            Logger.getLogger(CoinPile.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    /**
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the available
     */
    
    
}
