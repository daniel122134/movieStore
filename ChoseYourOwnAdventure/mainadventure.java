package ChoseYourOwnAdventure;

import java.util.Scanner;

public class mainadventure {


    public static void printAllStriesCall(Page start) {
        printAllStories(start, "");
    }
    public static void printAllStories(Page start, String currentStory) {
        if (start.hasNext()) {
            printAllStories(start.nextpage(1), currentStory + start.getText());
            printAllStories(start.nextpage(2), currentStory + start.getText());

        } else {
            System.out.println(currentStory + " " + start.getText());
            return;
        }
    }

    public static void main(String[] args) {

    }
}
