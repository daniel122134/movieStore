package snakegame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class board {
    private int[][] mat;
    public snake snake;
    private HashMap<Integer, Point> directions;
    private int highscore;
    private boolean appleExsists;


    public board() throws IOException {
        this.mat = new int[15][15];
        this.snake = new snake();
        mat[snake.snk.get(0).y][snake.snk.get(0).x] = 1;
        this.directions =  new HashMap<Integer, Point>();
        directions.put(1, new Point(-1, 0));
        directions.put(2, new Point(1, 0));
        directions.put(3, new Point(0, 1));
        directions.put(4, new Point(0, -1));

        Scanner rd = new Scanner(new File("scores.txt"));;
        this.highscore = Integer.parseInt(rd.next());
        this.appleExsists = false;

    }
    public void generateApple() {
        if (!this.appleExsists) {
            Random rand = new Random();
            boolean ok = true;
            int x = 0;
            int y = 0;
            while (ok) {
                x = rand.nextInt(14);
                y = rand.nextInt(14);
                if (mat[y][x] == 0) {
                    ok = false;
                }
            }
            this.mat[y][x] = 3;
            this.appleExsists = true;
        }
    }
    public void printBoard() {

        for (int i = 0; i < mat.length; i++) {
            String line = "";
            for (int j = 0; j < mat[0].length; j++) {
                line = line + "  " + mat[i][j];
            }
            System.out.println(line);
        }
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");

    }
    public boolean checkGameOver(Point dirPoint){
        if(snake.snk.get(0).y +dirPoint.y == mat.length || snake.snk.get(0).y +dirPoint.y == -1 || snake.snk.get(0).x +dirPoint.x == -1 || snake.snk.get(0).x +dirPoint.x == mat[0].length){
            return true;
        }
        if(mat[snake.snk.get(0).y +dirPoint.y][snake.snk.get(0).x +dirPoint.x] == 1){
            return true;
        }
        return  false;
    }

    public void setHighScore(int score) throws IOException {
        if(score > (this.highscore)){
            FileWriter writer = new FileWriter("scores.txt", false);
            writer.write(String.valueOf(score));
            writer.close();
        }



    }

    public String toString() {

        String all = "";
        for (int i = 0; i < mat.length; i++) {
            String line = "";
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    line = line + "  ";
                } else {
                    line = line + "*";
                }

            }
            all = all + "\n" + line;
        }
        return all;
    }

    public boolean moveForeward(int dir) throws IOException {
        Point dirPoint = directions.get(dir);
        if(!checkGameOver(dirPoint)) {


            if (mat[snake.snk.get(0).y + dirPoint.y][snake.snk.get(0).x + dirPoint.x] == 3) {
                this.appleExsists = false;
                this.snake.moveForward(dirPoint, true);
            } else {
                mat[snake.snk.get(snake.snk.size() - 1).y][snake.snk.get(snake.snk.size() - 1).x] = 0;
                this.snake.moveForward(dirPoint, false);

            }
            mat[snake.snk.get(0).y][snake.snk.get(0).x] = 1;
        }else{
            System.out.println("gameover!");
            System.out.println("you score is:");
            System.out.println(snake.length);
            System.out.println("highest score is");
            System.out.println(this.highscore);
            setHighScore(snake.length);
            return false;
        }
        return true;
    }
}
