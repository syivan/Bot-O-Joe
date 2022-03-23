package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Responsible for storing model key and responses into a List through
 * reading of an external .txt file which contains the model key (model name) and the response lines
 * @author Ivan Sy
 * @version 1.01 - 01/20/22
 */
public class ModelReader {

    private String modelName;
    private List<String> modelResponses;
    private Scanner fileScan;

    {
        this.modelResponses = new ArrayList<>();
    }

    /**
     * constructor initializes and builds a String list from the specified model name (fileNamebot.txt)
     * that contains the responses that will be randomly chosen and generated
     * @param fileName the specified model name to retrieve from a .txt file
     */
    public ModelReader(String fileName) {
        try {
            String modelFile = "models/" + fileName + "bot.txt";
            this.fileScan = new Scanner(new File(modelFile));
            this.modelName = fileScan.nextLine().substring(1);
            addModelResponses(); //adds the responses to the String list
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addModelResponses() {
        while (fileScan.hasNextLine()) {
          modelResponses.add(fileScan.nextLine());
        }
    }

    /**
     * returns a response from the pool of responses stored in the response list
     * and randomly chooses one
     * @return a response from the response list
     */
    public String generateModelResponse() {
        Random dice = new Random();
        int responseCount = modelResponses.size();
        return modelResponses.get(dice.nextInt(responseCount));
    }

    /**
     * returns the model name, appended by {DEBUG}READER to indicate an instance of the ModelReader class
     * @return the model name, appended by {DEBUG}READER
     */
    @Override
    public String toString() {
        return modelName + "{DEBUG}" + "READER";
    }

    /**
     * returns name of the model
     * @return name of the model
     */
    public String getModelName() {
        return modelName;
    }

}
