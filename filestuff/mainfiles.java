package filestuff;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
public class mainfiles {

    //find a file that contains a word in its name inside a directory
    public static File searchFile(String keyWord, File startfile) {
        File[] files = startfile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                if (searchFile(keyWord, files[i]) != null) {
                    return searchFile(keyWord, files[i]);
                }
            } else {
                if (files[i].getName().contains(keyWord)) {
                    return files[i];
                }
            }

        }
        return null;
    }

    //return a file that contains a word in  a folder
    public static boolean isInFile(String keyWord, File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        String line;
        while(reader.hasNextLine()){
            line = reader.nextLine();
            if(line.contains(keyWord)){
                return true;
            }
        }
        return false;

    }
    public static File SearchContent(String keyWord, File directory) throws FileNotFoundException {
        File[] files = directory.listFiles();
        for(int i = 0; i < files.length; i++){
            if(isInFile(keyWord, files[i])){
                return files[i];
            }
        }
        return null;
    }


    //lisitng all files in a directory
    public static void mergeFiles(File file, File target) throws IOException {
            FileWriter wr = new FileWriter(target, true);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String next = reader.nextLine();
                wr.write(next+ "\n");
            }
            wr.close();



    }
    public static File listFiles(File directory) throws IOException {
        File[] files = directory.listFiles();
        File textfile = new File(directory.getPath()+ "\\infom.txt");
        FileWriter writer = new FileWriter(textfile, true);
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                mergeFiles(listFiles(files[i]), textfile);
            }else{
              writer.write(files[i].getName() + "\n");
                writer.write(files[i].getName().hashCode() + "\n");
            }
        }
        writer.close();
        return textfile;
    }

    public static void main(String[] args) throws IOException {


    }
}





