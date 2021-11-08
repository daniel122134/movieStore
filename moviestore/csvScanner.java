package moviestore;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class csvScanner {
    private File destination;
    private Scanner myReader;
    private FileWriter myWriter;



    public csvScanner(String destination, boolean append) throws IOException {
        this.destination = new File(destination);
        this.myReader = new Scanner(this.destination);
        this.myWriter = new FileWriter(this.destination, append);

    }



    public ArrayList<String[]> readAll(){
        ArrayList<String[]> result = new ArrayList<>();
        while (myReader.hasNextLine()){
            String wholeLine = myReader.nextLine();
            int numofcommas = 0;
            for(int i = 0; i < wholeLine.length(); i++){
                if(wholeLine.charAt(i) == ','){
                    numofcommas++;
                }
            }
            String[] row = new String[numofcommas+1];
            for(int i =0; i < row.length-1; i++){
                String word = wholeLine.substring(0, wholeLine.indexOf(','));
                wholeLine = wholeLine.substring(wholeLine.indexOf(',')+ 1);
                row[i] = word;
            }
            row[row.length-1] = wholeLine;
            result.add(row);
        }
        return result;
    }
    public void writeAll(ArrayList<String[]> arr) throws IOException {
        String line = "";
        int i = arr.size()-1;
            line = "";
            this.myWriter.write(arr.get(i)[01]);
            for(int j = 1; j < arr.get(i).length; j++){
                line = line + ", " +  arr.get(i)[j];
            }
            this.myWriter.write(line + "\n");
            this.myWriter.close();

        }


    }




