package moviestore;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface menu {
        public void  resetfilter();
        public void filterByGenre(String genre);
        public void filterByLength(int Maxlength, int minlength);
        public void filterAge(user u );
        public void sortbyImdb();
        public buyable searchMovie(String querry);
        public void sortbyname();
        public void printfullist();
        public void removeFromFile(buyable item) throws IOException, CsvException;




    }

