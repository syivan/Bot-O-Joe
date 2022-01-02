package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ModelBuilder {

    public ModelBuilder(String modelName) throws IOException {
        FileWriter modelWriter = new FileWriter(modelName + "bot.txt", true);

    }

    public boolean isModelPresent(String fileName) {
        String dir = "C:\\Users\\ivans\\Documents\\Bot-O-Joe\\models\\";
        String file = fileName + "bot.txt";
        File tempFile = new File(dir + file);
        boolean isPresent = tempFile.exists();
        return isPresent;
    }





}
