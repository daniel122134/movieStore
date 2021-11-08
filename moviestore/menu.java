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

public abstract class menu {
        public ArrayList<buyable> fullist;
        public ArrayList<buyable> filtered;

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
        public void resetfilter() {
                this.filtered = fullist;
        }
        public void filterByGenre(String genre) {
                for (int i = 0; i < filtered.size(); i++) {
                        if (FilmProduct.class.cast(filtered.get(i)).getGenre() != genre) {
                                filtered.remove(i);
                        }
                }
        }
        public void filterByLength(int Maxlength, int minlength) {
                for (int i = 0; i < filtered.size(); i++) {
                        if (FilmProduct.class.cast(filtered.get(i)).getLength() > Maxlength || FilmProduct.class.cast(filtered.get(i)).getLength() < minlength) {
                                filtered.remove(i);
                        }
                }
        }
        public void filterAge(user u) {
                for (int i = 0; i < filtered.size(); i++) {
                        if (FilmProduct.class.cast(filtered.get(i)).getAgeRating() > u.getAge()) {
                                filtered.remove(i);
                        }
                }
        }
        public void printfullist() {
                for (int i = 0; i < filtered.size(); i++) {
                        System.out.println(filtered.get(i).getName() + " " + filtered.get(i).getPrice());
                }

        }
        public void removeFromFile(buyable item)  {
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
                        System.out.println("file empty quitting");
                        System.exit(0);
                } catch (CsvException e) {
                        e.printStackTrace();
                        System.out.println("error, quitting");
                        System.exit(0);
                }
                for (int i = 0; i < tt.size(); i++) {
                        if (tt.get(i)[1].equals(item.name)) {
                                tt.remove(i);
                                try (CSVWriter writer = new CSVWriter(new FileWriter("movies.csv"))) {
                                        writer.writeAll(tt);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("file not writable, quitting");
                                        System.exit(0);
                                }
                        }
                }
        }



        public static ArrayList<buyable> MergeSort(ArrayList<buyable> arr, int left, int right){
                if(right == left){
                        ArrayList<buyable> finalNum = new ArrayList<>(1);
                        finalNum.add(arr.get(left));
                        return finalNum;
                }
                ArrayList<buyable> leftArr = MergeSort(arr, left, (right+ left)/2 );
                ArrayList<buyable> rightArr =MergeSort(arr, ((left + right)/2)+ 1, right);
                //mergeing
                ArrayList<buyable> merged =  merger(leftArr,rightArr);
                return merged;

        }
        public static  ArrayList<buyable> merger(ArrayList<buyable> arr1, ArrayList<buyable> arr2) {
                ArrayList<buyable> newarr = new ArrayList<buyable>(arr1.size() + arr2.size());
                int i = 0;
                int j = 0;
                int c = 0;
                while (i < arr1.size() && j < arr2.size()) {
                        if (FilmProduct.class.cast(arr1.get(i)).imdbScore > FilmProduct.class.cast(arr2.get(j)).imdbScore) {
                                newarr.set(c, arr2.get(j));
                                j++;
                                c++;
                        } else {
                                newarr.set(c, arr1.get(i));
                                i++;
                                c++;
                        }

                }
                while (i < arr1.size()) {
                        newarr.set(c, arr1.get(i));
                        i++;
                        c++;
                }

                while (j < arr2.size()) {
                        newarr.set(c, arr2.get(j));
                        j++;
                        c++;
                }
                return newarr;
        }
        public void sortbyImdb() {
                int right = filtered.size()-1;
                int left = 0;
                this.filtered = MergeSort(filtered, left, right);
        }
        public buyable search(String query) {
                buyable searched = null;
                for (int i = 0; i < filtered.size(); i++) {
                        if (filtered.get(i).getName().equalsIgnoreCase(query))
                        {
                                searched = filtered.get(i);
                        }else{
                                filtered.remove(i);
                        }


                }
                return searched;
        }
        public void sortbyname(){
                int right = filtered.size()-1;
                int left = 0;
                this.filtered = MergeSortNames(filtered, left, right);
        }
        public static ArrayList<buyable> MergeSortNames(ArrayList<buyable> arr, int left, int right){
                if(right == left){
                        ArrayList<buyable> finalNum = new ArrayList<>(1);
                        finalNum.set(0,arr.get(left));
                        return finalNum;
                }
                ArrayList<buyable> leftArr = MergeSortNames(arr, left, (right+ left)/2 );
                ArrayList<buyable> rightArr =MergeSortNames(arr, ((left + right)/2)+ 1, right);
                //mergeing
                ArrayList<buyable> merged =  mergerNames(leftArr,rightArr);
                return merged;

        }
        public static  ArrayList<buyable> mergerNames(ArrayList<buyable> arr1, ArrayList<buyable> arr2) {
                ArrayList<buyable> newarr = new ArrayList<buyable>(arr1.size() + arr2.size());
                int i = 0;
                int j = 0;
                int c = 0;
                while (i < arr1.size() && j < arr2.size()) {
                        if (arr1.get(i).name.compareTo(arr2.get(j).name) > 0) {
                                newarr.set(c, arr2.get(j));
                                j++;
                                c++;
                        } else {
                                newarr.set(c, arr1.get(i));
                                i++;
                                c++;
                        }

                }
                while (i < arr1.size()) {
                        newarr.set(c, arr1.get(i));
                        i++;
                        c++;
                }

                while (j < arr2.size()) {
                        newarr.set(c, arr2.get(j));
                        j++;
                        c++;
                }
                return newarr;
        }
    }

