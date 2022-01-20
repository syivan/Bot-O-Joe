package actions;

import models.ModelReader;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageReader extends ListenerAdapter {

    private List<String> subjects;

    {
        subjects = new ArrayList<>();
    }

    public MessageReader() {
        constructSubjects();
    }

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
