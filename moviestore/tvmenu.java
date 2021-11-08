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
import java.util.Locale;

public class tvmenu extends menu {
    public ArrayList<tvShow> fullist;
    public ArrayList<tvShow> filtered;


    public tvmenu() {
        this.fullist = new ArrayList<>();
        this.filtered = fullist;

    }
    public tvmenu(ArrayList<tvShow> tv) {
        this.fullist = tv;
        this.filtered = fullist;


    }
    public void addShows()  {
        CSVReader mveReader = null;
        try {
            mveReader = new CSVReader(new FileReader("shows.csv"));
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
            fullist.add(new tvShow(Integer.valueOf(thismovie[0]), thismovie[1], thismovie[2], Integer.valueOf(thismovie[3]), thismovie[4], Integer.valueOf(thismovie[5]), Boolean.valueOf(thismovie[6].toLowerCase(Locale.ROOT)), Integer.valueOf(thismovie[7])));
        }
    }

}



