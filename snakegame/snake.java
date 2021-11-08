package snakegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class snake {
    public ArrayList<Point> snk;
    public int length;
    public snake() {
        Point head = new Point();
        head.setLocation(7,6);
        this.snk = new ArrayList<Point>();
        snk.add(head);
        this.length =1;

    }
    public void moveForward(Point dirPoint, boolean isApple){
        Point head = new Point(snk.get(0).x + dirPoint.x, snk.get(0).y+dirPoint.y);


        if(!isApple) {
            snk.remove(snk.size() - 1);
        }else{
            length++;
        }
        snk.add(0, head);
    }
}

