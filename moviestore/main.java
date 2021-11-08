package moviestore;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.Gson;




//implement jsons or just go for sqlite?
public class main {
    static Scanner in = new Scanner(System.in);

    public static void openShop(user member1)  {
        user member = member1;
        moviemenu men = new moviemenu();
        men.addMovies();
        tvmenu menu = new tvmenu();
        menu.addShows();
        boolean ok = true;
        while (true) {
            ok = true;
            System.out.println("movies or shows? - press 1 for movies and 2 for shows...");
            switch (in.nextInt()) {
                case 1:
                    if(ok == false){
                        break;
                    }
                    ok = true;
                    while (ok) {
                        men.printfullist();
                        System.out.println("movies are ordered from 0 and up, enter the number of the movie to chose it or -9 to go back\" + \"\\n\" + \"some filters: \" + \"\\n\" + \"age:-1  , genre:-2  , length:-3\" + \"\\n\" + \"to sort by rating enter -4 and to use search enter -5");
                        int x = in.nextInt();
                        if (x > 0) {
                            System.out.println(men.filtered.get(x).offer());
                            if (in.nextInt() == 1) {
                                System.out.println("thanks for buying!");
                                buyable temp = men.remove(x);
                                member.add(temp);
                                men.removeFromFile(temp);
                            }

                        }
                        switch (x) {
                            case (-1):
                                men.filterAge(member);
                                break;

                            case (-2):
                                System.out.println("enter genre name");
                                men.filterByGenre(in.next());
                                break;

                            case (-3):
                                System.out.println("enter min length");
                                int min = in.nextInt();
                                System.out.println("enter max length");
                                men.filterByLength(in.nextInt(), min);
                                break;
                            case (-4):
                                men.sortbyImdb();
                                break;
                            case (-5):
                                System.out.println("search:");
                                men.search(in.next());

                            case (-9):
                                ok = false;
                                men.resetfilter();
                                break;


                        }
                    }

                case 2:
                    if(ok == false){
                        break;
                    }
                     ok = true;
                    while (ok) {
                        menu.printfullist();
                        System.out.println("shows are ordered from 0 and up, enter the number of the movie to chose it or -9 to go back" + "\n" + "some filters: " + "\n" + "age:-1  , genre:-2  , length:-3" + "\n" + "to sort by rating enter -4 and to use search enter -5");
                        int x1 = in.nextInt();

                        if (x1 >= 0) {
                            System.out.println(menu.filtered.get(x1).offer());
                            if (in.nextInt() == 1) {
                                System.out.println("thanks for buying!");
                                member.add(menu.remove(x1));


                            }
                        }
                        switch (x1) {
                            case (-1):
                                menu.filterAge(member);
                                break;

                            case (-2):
                                System.out.println("enter genre name");
                                menu.filterByGenre(in.next());
                                break;

                            case (-3):
                                System.out.println("enter min length");
                                int min = in.nextInt();
                                System.out.println("enter max length");
                                menu.filterByLength(in.nextInt(), min);
                                break;
                            case (-4):
                                menu.sortbyImdb();
                                break;
                            case (-5):
                                System.out.println("search:");
                                menu.search(in.next());

                            case(-9):
                                menu.resetfilter();
                                ok = false;
                                break;



                        }


                    }


            }
        }
    }



    public static void main(String[] args)  {

        String[] userData = new String[4];
        userData[0] = "av";
        userData[1] = "ner";
        userData[2] = "5";
        userData[3] = "name";
        user myiuser = new user(userData);
        Gson gson = new Gson();
        String userwithj = gson.toJson(myiuser);
        System.out.println(userwithj);










        Scanner in = new Scanner(System.in);
        System.out.println("welcome to MovieTime");

        //setting up the libary
        File myObj = new File("filename.csv");
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file missing quitting");
            System.exit(0);
        }
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








//regisration place

        while (true) {
            System.out.println("type login or anything else to register...");
            if (in.next().equals("login")) {
                System.out.println("enter username:");
                String username = in.next();
                csvScanner userReader = null;
                try {
                    userReader = new csvScanner("test.csv", true);
                } catch (IOException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    System.out.println("file missing quitting");
                    System.exit(0);
                }
                List<String[]> users = userReader.readAll();
                for (int i = 0; i < users.size(); i++) {
                    String savedUsername = users.get(i)[0];
                    String savedPassword = users.get(i)[1];
                    if (users.get(i)[0].equals(username)) {
                        System.out.println("enter password:");
                        if (in.next().equals(users.get(i)[1])) {
                            System.out.println("login succesful");
                            String[] user1 = users.get(i);
                            openShop(new user(user1[0], user1[1], user1[2], user1[3]));
                        }
                        System.out.println("wrong password going back to main menu" + " \n");
                        break;


                    }

                }
                System.out.println("no usernaeme found going back to main menu" + " \n");


            } else {
                String[] userReg = new String[4];
                System.out.println("full name");
                userReg[0] = in.next();
                System.out.println("username");
                userReg[1] = in.next();
                System.out.println("age");
                userReg[2] = in.next();
                System.out.println("password");
                userReg[3] = in.next();
                user user2 = new user(userReg);
                try {
                    user2.saveInMemeory();
                } catch (IOException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    System.out.println("file empty quitting");
                    System.exit(0);
                }
                openShop(user2);


            }
        }
    }
}



