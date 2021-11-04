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

public class moviemenu implements menu {
    public ArrayList<movie> fullist;
    public ArrayList<movie> filtered;

    public moviemenu(ArrayList<movie> arr) {
        this.fullist = arr;
        this.filtered = fullist;

    }

    public moviemenu() {
        this.fullist = new ArrayList<>();
        this.filtered = fullist;

    }

    public void addMovies() throws IOException, CsvException {
        CSVReader mveReader = new CSVReader(new FileReader("c:\\test\\movies.csv"));
        List<String[]> tt = mveReader.readAll();
        for (int i = 0; i < tt.size(); i++) {
            String[] thismovie = tt.get(i);
            fullist.add(new movie(Integer.valueOf(thismovie[0]), thismovie[1], thismovie[2], Integer.valueOf(thismovie[3]), thismovie[4], Integer.valueOf(thismovie[5])));
        }

    }

    public buyable remove(int x) {
        buyable collect = this.filtered.get(x);
        filtered.remove(x);
        for (int i = 0; i < fullist.size(); i++) {
            if (this.fullist.get(i).name == collect.name) {
                fullist.remove(i);
            }
        }
        return collect;
    }

    @Override
    public void resetfilter() {
        this.filtered = fullist;
    }

    @Override
    public void filterByGenre(String genre) {
        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getGenre() != genre) {
                filtered.remove(i);
            }
        }
    }

    @Override
    public void filterByLength(int Maxlength, int minlength) {
        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getLength() > Maxlength || filtered.get(i).getLength() < minlength) {
                filtered.remove(i);
            }
        }
    }

    @Override
    public void filterAge(user u) {
        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getAgeRating() > u.getAge()) {
                filtered.remove(i);
            }
        }
    }

    @Override
    public void sortbyImdb() {
        boolean ok = true;
        while (ok) {
            ok = false;
            for (int i = 0; i < filtered.size() - 1; i++) {
                if (filtered.get(i).imdbScore < filtered.get(i + 1).imdbScore) {
                    movie c = filtered.get(i);
                    filtered.set(i, filtered.get(i + 1));
                    filtered.set(i + 1, c);
                    ok = true;
                }
            }
        }
    }

    @Override
    public buyable searchMovie(String querry) {
        buyable that = null;
        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getName().equalsIgnoreCase(querry))
            {
                that = filtered.get(i);
            }else{
                filtered.remove(i);
            }


        }
        return that;
    }

    @Override
    public void sortbyname() {
        boolean ok = true;
        while (ok) {
            ok = false;
            for (int i = 0; i < filtered.size() - 1; i++) {
                if (filtered.get(i).name.compareTo(filtered.get(i + 1).name) > 0) {
                    movie c = filtered.get(i);
                    filtered.set(i, filtered.get(i + 1));
                    filtered.set(i + 1, c);
                    ok = true;
                }
            }
        }
    }

    @Override
    public void printfullist() {
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println(filtered.get(i).getName() + " " + filtered.get(i).getPrice());
        }

    }

    @Override
    public void removeFromFile(buyable item) throws IOException, CsvException {
        CSVReader mveReader = new CSVReader(new FileReader("c:\\test\\movies.csv"));
        List<String[]> tt = mveReader.readAll();
        for (int i = 0; i < tt.size(); i++) {
            if (tt.get(i)[1].equals(item.name)) {
                tt.remove(i);
                try (CSVWriter writer = new CSVWriter(new FileWriter("c:\\test\\movies.csv"))) {
                    writer.writeAll(tt);
                }
            }
        }
    }
}
