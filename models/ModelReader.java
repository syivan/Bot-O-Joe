package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class ModelReader {

    private final String modelName;
    private List<String> modelResponses;
    private final Scanner fileScan;

    public ModelReader(String fileName) throws FileNotFoundException {
        String modelFile = "models/" + fileName + "bot.txt";
        fileScan = new Scanner(new File(modelFile));
        modelName = fileScan.nextLine().substring(1);
        addModelResponses();
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
