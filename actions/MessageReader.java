package actions;

import models.ModelReader;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MessageReader is responsible for reading messages in guild channel from the member,
 * and sending the generated response to the guild channel in which the model key was detected
 * @author Ivan Sy
 * @version 1.01 - 01/20/2022
 * @see ModelReader
 */
public class MessageReader extends ListenerAdapter {

    // list will contain the model keys
    private List<String> subjects;

    {
        this.subjects = new ArrayList<>();
    }

    /**
     * constructor initializes the class instance list and
     * stores the valid model keys into this list
     * Note: valid model keys end in bot.txt and when read in a member message, they generate response
     */
    public MessageReader() {
        constructSubjects();
    }

    /**
     * reads the message of the member and IF a valid model key (model name) is found in the message, a response
     * will be generated in the same channel
     * @param event the event to retrieve the channel the message originated from
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<String> messageContent = Arrays.asList(event.getMessage().getContentRaw().split(" "));
        boolean subjectFound = false;
        for (int i = 0; i < messageContent.size() && !subjectFound; i ++) {
            String wordValue = messageContent.get(i).toLowerCase();
            if (subjects.contains(wordValue)) {
                ModelReader modelReader = new ModelReader(wordValue);
                String generatedResponse = modelReader.generateModelResponse();
                event.getChannel().sendMessage(generatedResponse).queue();
                subjectFound = true;
            }
        }
    }

    /*
    responsible for reading the models directory and adding to the subjects list all the valid
    model keys, which are denoted by ending in bot.txt
     */
    private void constructSubjects() {
        File modelsDirectory = new File("models/");
        File[] fileList = modelsDirectory.listFiles();
        for (File subjectFile : fileList) {
            if (subjectFile.isFile() && subjectFile.getName().endsWith("bot.txt")) {
                String fileName = subjectFile.getName();
                int divider = fileName.lastIndexOf("bot.txt");
                String subjectName = fileName.substring(0, divider).toLowerCase();
                subjects.add(subjectName);
            }
        }
    }


}
