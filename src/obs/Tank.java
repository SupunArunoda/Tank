package obs;

import map.View;

public class Tank extends Thread{
private int x;
private int y;
private int direc;
private View view;
    public Tank (int x, int y, int direc,View view) {
        this.x = x;
        this.y = y;
        this.direc = direc;
        this.view=view;
        this.start();
        
    }
@Override
    public void run(){
        view.updateTank(getX(), getY(), getDirec());
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
     * @return the direc
     */
    public int getDirec() {
        return direc;
    }

    /**
     * @param direc the direc to set
     */
    public void setDirec(int direc) {
        this.direc = direc;
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
