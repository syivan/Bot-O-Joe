package models;

import java.io.FileWriter;
import java.io.IOException;

public class ModelBuilder {

    private String modelName;

    public ModelBuilder(String modelName) {
        this.modelName = modelName.toLowerCase();
    }

    public void buildModelFile() {
        try {
            FileWriter modelWriter = new FileWriter( "models/" + modelName + "bot.txt", false);
            modelWriter.write(modelName);
            modelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBotResponse(String botResponse) {
        try {
            FileWriter modelWriter = new FileWriter("models/" + modelName + "bot.txt", true);
            modelWriter.write(String.format("\n%s", botResponse));
            modelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
