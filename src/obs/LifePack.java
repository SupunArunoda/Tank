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
public class LifePack extends Thread{
    private int x;
    private int y;
    private int life;
    private View view;

    public LifePack(int x, int y, int life,View view) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.view=view;
        this.start();
    }
    @Override
    public  void run(){
        try {
            view.updateLifePack(this.getX(),this.getY(),"NEW");
            this.sleep(getLife());
            view.updateLifePack(this.getX(),this.getY(),"REMOVE");
        } catch (InterruptedException ex) {
            Logger.getLogger(LifePack.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
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
    
}
