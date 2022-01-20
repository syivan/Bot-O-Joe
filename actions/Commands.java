package actions;

import models.ModelBuilder;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Commands extends ListenerAdapter {

    public void displayGuide(GuildMessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        String embedTitle = "How to bot-O-Joe";
        String fieldConstruct = "!construct \"NAME_OF_MODEL\"";
        String descConstruct = "allows bot-O-Joe to generate responses when the name specified is found";
        String fieldAddResponse = "!addresponse \"RESPONSE\"";
        String descAddResponse = "allows user to add a response for the bot-O-Joe to use";
        embed.setTitle(embedTitle);
        embed.addField(fieldConstruct, descConstruct, false);
        embed.addField(fieldAddResponse, descAddResponse, false);
        embed.setFooter(String.format("Presented to %s", event.getMessage().getAuthor().getName()), event.getAuthor().getAvatarUrl());
        event.getChannel().sendMessage(embed.build()).queue();
        embed.setColor(Color.GREEN);
        embed.clear();
    }

    public void constructModel(GuildMessageReceivedEvent event, String model)  {
        if (!isModelPresent(model)) {
            model = model.toLowerCase();
            ModelBuilder modelBuild = new ModelBuilder(model);
            modelBuild.buildModelFile();
        } else
            event.getMessage().reply(String.format("Whoops model %s already exists", model)).queue();
    }

    public void addModelResponse(GuildMessageReceivedEvent event, String...userInput) {
        String modelName = userInput[1];
        String modelResponse = retrieveBotResponse(userInput);
        if (isModelPresent(modelName)) {
            ModelBuilder modelFile = new ModelBuilder(modelName);
            modelFile.addBotResponse(modelResponse);
        } else
            //event.getAuthor().openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessage())
            event.getMessage().reply(String.format("Welp model %s does not exist", modelName)).queue();
    }

    private String retrieveBotResponse(String... userInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < userInput.length; i++) {
            sb.append(userInput[i] + " ");
        }
        String response = sb.toString().trim();
        return response;
    }

    private boolean isModelPresent(String model) {
        model = model.toLowerCase();
        String fileDir = "models/" + model + "bot.txt";
        File tempFile = new File(fileDir);
        boolean isPresent = tempFile.exists();
        return isPresent;
    }


}
