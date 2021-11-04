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

public class main {
    static Scanner in = new Scanner(System.in);

    public static void openShop(user meber) throws IOException, CsvException {
        user member = meber;
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
                                men.searchMovie(in.next());

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
                                menu.searchMovie(in.next());

                            case(-9):
                                menu.resetfilter();
                                ok = false;
                                break;



                        }


                    }


            }
        }
    }

    private static List<String[]> createCsvDataSimple(user m) {
        String[] header = {"name", "username", "password", "collection"};
        String[] record1 = {m.getName(), m.getUsername(), m.getPassword(), m.getCollection().get(0).getName()};
        String[] record2 = {"2", "second name", "address 2", "22222"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);

        return list;
    }

    public static void main(String[] args) throws IOException, CsvException {

        Scanner in = new Scanner(System.in);
        System.out.println("welcome to MovieTime");

        //setting up the libary


//regisration place

        while (true) {
            System.out.println("type login or anything else to register...");
            if (in.next().equals("login")) {
                System.out.println("enter username:");
                String check = in.next();
                CSVReader userReader = new CSVReader(new FileReader("c:\\test\\test.csv"));
                List<String[]> users = userReader.readAll();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i)[0].equals(check)) {
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
                System.out.println("full name");
                String name = in.next();
                System.out.println("username");
                String username = in.next();
                System.out.println("age");
                int age = in.nextInt();
                System.out.println("password");
                String password = in.next();
                user user2 = new user(username, password, String.valueOf(age), name);
                user2.saveInMemeory();
                openShop(user2);


            }
        }
    }
}


/*
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("userinfo.txt"), "utf-8"))) {
                writer.append(us.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
*/