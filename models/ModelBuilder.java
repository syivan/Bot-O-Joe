package models;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for building model to be used in retrieving bot response
 * after reading the entered message of guild member.
 * TWO specific functions:
 * 1. construction of a .txt file that will hold responses in response to a guild message (message contains model key)
 * 2. addition of responses to a model file, as the user is able to add responses using !add
 * @author Ivan Sy
 * @version 1.01 - 01/20/2022
 */
public class ModelBuilder {

    /**
     * constructs .txt file that will hold a key, which will be the model name, which will also hold
     * String responses to message in guild channel when a member enters an instance of a model key in a message
     * @param modelName name of the model (key)
     */
    public void buildModelFile(String modelName) {
        try {
            FileWriter modelWriter = new FileWriter( "models/" + modelName + "bot.txt", false);
            modelWriter.write(modelName);
            modelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds responses to the model file, adding a new response to the pool of possible responses the model bot
     * can reply to the user with
     * @param modelName name of model file the response will be added to
     * @param botResponse the new response added to the specified model
     */
    public void addBotResponse(String modelName, String botResponse) {
        try {
            FileWriter modelWriter = new FileWriter("models/" + modelName + "bot.txt", true);
            modelWriter.write(String.format("\n%s", botResponse));
            modelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns {DEBUG}BUILDER, indicating an instance of ModelBuilder
     * @return {DEBUG}BUILDER
     */
    @Override
    public String toString() {
        return "{DEBUG}BUILDER";
    }

}
