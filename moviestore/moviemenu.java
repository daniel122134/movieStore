package moviestore;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class moviemenu extends menu {
    public moviemenu(ArrayList<movie> arr) {
        this.fullist = new ArrayList<buyable>();
        for(int i =0; i < arr.size(); i++){
            fullist.add(buyable.class.cast(arr.get(i)));
        }
        this.filtered = fullist;
    }
    public moviemenu() {
        this.fullist = new ArrayList<buyable>();
        this.filtered = fullist;

    }
    public void addMovies()  {
        CSVReader mveReader = null;
        try {
            mveReader = new CSVReader(new FileReader("movies.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file missing quitting");
            System.exit(0);
        }
        List<String[]> tt = null;
        try {
            tt = mveReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tt.size(); i++) {
            String[] thismovie = tt.get(i);
            fullist.add(new movie(Integer.valueOf(thismovie[0]), thismovie[1], thismovie[2], Integer.valueOf(thismovie[3]), thismovie[4], Integer.valueOf(thismovie[5])));
        }

    }

}
