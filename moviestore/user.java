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

public class user {
    private String name;
    private int age;
    private String username;
    private String password;
    private ArrayList<buyable> collection;


    public user(String username, String password ,String age, String name){
        this.username = username;
        this.name = name;
        this.password = password;
        this.age = Integer.valueOf(age);
        this.collection = new ArrayList<buyable>();
    }
    public void add(buyable foo){
        this.collection.add(collection.size(), foo);
    }
    public String toString(){
        return(this.username + " "+ this.password);
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<buyable> getCollection() {
        if(collection.size() == 0){
            return null;
        }
        return collection;
    }
    public void saveInMemeory() throws IOException, CsvException {
        CSVReader mveReader = new CSVReader(new FileReader("c:\\test\\test.csv"));
        List<String[]> tt = mveReader.readAll();
        String[] userData = new String[4];
        userData[0] = this.username;
        userData[1] = this.password;
        userData[2] = String.valueOf(this.age);
        userData[3] = this.name;

        tt.add(userData);
                try (CSVWriter writer = new CSVWriter(new FileWriter("c:\\test\\test.csv"))) {
                    writer.writeAll(tt);
                }
            }



    public int getAge() {
        return age;
    }
}
