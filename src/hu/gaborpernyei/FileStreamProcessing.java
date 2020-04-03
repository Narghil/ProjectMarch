package hu.gaborpernyei;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileStreamProcessing {

    public static void main(String[] args) {
        ProcessFile pf = new ProcessFile();
        pf.readFile();
    }



}

class ProcessFile{
    Map<Integer, String> linesMap  = new TreeMap();

    void readFile() {
        Path path = Paths.get("adatok.txt");
        try {
            /*T -> this.addString( T )*/
            Files.lines(path).forEach( T -> this.addString( T )  );
            System.out.println(linesMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    void addString(String str){
        String[] splitted = str.split(":");
        linesMap.put( linesMap.size() +1, splitted[1]);
    }

}
