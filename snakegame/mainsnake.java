package snakegame;

import com.google.gson.Gson;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class mainsnake implements KeyListener {


    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);
        board myBoard = new board();
        myBoard.printBoard();
        boolean ok = true;
        int movemnets = 0;
        while (ok) {
            if(movemnets > 4){
                myBoard.generateApple();
                movemnets = 0;
            }
            TimeUnit.SECONDS.sleep(1);
            int dir = in.nextInt();
            ok = myBoard.moveForeward(dir);
            movemnets++;
            System.out.flush();
            if (ok) {
                myBoard.printBoard();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
