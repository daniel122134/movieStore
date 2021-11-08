package ChoseYourOwnAdventure;

import java.util.ArrayList;

public class Page {
    private String text;
    private Page p1;
    private Page p2;


    public Page(String text, Page p1, Page p2){
        this.text = text;
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getText() {
        return text;
    }

    public Page nextpage(int choice){
        switch (choice){
            case(1):
                return p1;
            case(2):
                return p2;
        }
        return null;
    }

    public boolean hasNext(){
        return (!(this.p1 == null && this.p2 == null));
    }
    public void print(){
        System.out.println(text);
    }



}
