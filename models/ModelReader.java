package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ModelReader {

    private String modelName;
    private List<String> modelResponses;
    private Scanner fileScan;

    {
        modelResponses = new ArrayList<>();
    }

    public ModelReader(String fileName) {
        try {
            String modelFile = "models/" + fileName + "bot.txt";
            fileScan = new Scanner(new File(modelFile));
            modelName = fileScan.nextLine().substring(1);
            addModelResponses();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addModelResponses() {
        while (fileScan.hasNextLine()) {
          modelResponses.add(fileScan.nextLine());
        }
    }

    public String generateModelResponse() {
        Random dice = new Random();
        int responseCount = modelResponses.size();
        return modelResponses.get(dice.nextInt(responseCount));
    }

    @Override
    public String toString() {
        return modelName + "{DEBUG}";
    }

}
