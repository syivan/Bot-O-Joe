package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Model {

    private final String modelName;
    private List<String> modelResponses;
    private final Scanner fileScan;

    public Model(String fileName) throws FileNotFoundException  {
        fileScan = new Scanner(new File(fileName));
        modelName = fileScan.nextLine().substring(1);
        //addModelResponses();
    }

//    private addModelResponses() {
//        while (scan.hasNextLine()) {
//
//        }
//    }


}
