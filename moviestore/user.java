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

    public String[] userToArray(){
        String[] userData = new String[4];
        userData[0] = this.username;
        userData[1] = this.password;
        userData[2] = String.valueOf(this.age);
        userData[3] = this.name;
        return userData;
    }
    public user(String[] userdata){
        this.username = userdata[0];
        this.password = userdata[1];
        this.age = Integer.valueOf(userdata[2]);
        this.name= userdata[3];
    }
    public void saveInMemeory() throws IOException {
        csvScanner mveReader = new csvScanner("test.csv", true);
        ArrayList<String[]> tt = mveReader.readAll();
        tt.add(this.userToArray());
                csvScanner writer = new csvScanner("test.csv",true);
                    writer.writeAll(tt);
                }




    public int getAge() {
        return age;
    }
}
