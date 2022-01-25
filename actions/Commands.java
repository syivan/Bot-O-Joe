package actions;

import models.ModelBuilder;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;

/**
 * class contains functionality for the user such as displaying a Guide interface, and
 * having the user be able to construct a model file and add responses to that respective file
 * Commands class allows the member to fully utilize the bot by making giving them the chance to
 * construct their own model (constructModel) and add responses to it (addModelResponse)
 */
public class Commands extends ListenerAdapter {

    /**
     * displays an embedded structure to the message channel, generated from specified event, which contains
     * the basic guide and functionality the member is able to interact with the bot
     * @param event the channel in which the member-sent message is located
     */
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

    /**
     * constructs the model, thus after completion the bot.txt file is generated
     * @param event the channel to send the error message IF the model the member intends to create already exists
     * @param model the model key, appended by "bot.txt" to create the .txt file
     */
    public void constructModel(GuildMessageReceivedEvent event, String model)  {
        if (!isModelPresent(model)) {
            model = model.toLowerCase();
            ModelBuilder modelBuild = new ModelBuilder();
            modelBuild.buildModelFile(model);
        } else
            event.getMessage().reply(String.format("Whoops model %s already exists", model)).queue();
    }

    /**
     * adds a model response to an existing of pool responses for the specified model
     * @param event the channel to send an error message IF the model specified does not exist
     * @param userInput the user input passed with the command !addresponse, which is added to the response pool
     */
    public void addModelResponse(GuildMessageReceivedEvent event, String...userInput) {
        String modelName = userInput[1].toLowerCase();
        String modelResponse = retrieveBotResponse(userInput);
        if (isModelPresent(modelName)) {
            ModelBuilder modelFile = new ModelBuilder();
            modelFile.addBotResponse(modelName, modelResponse);
        } else
            //event.getAuthor().openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessage())
            event.getMessage().reply(String.format("Welp model %s does not exist", modelName)).queue();
    }

    //method is passed a string array containing all words (separated by whitespace)
    //and uses Stringbuilder to append all the words into one String value
    private String retrieveBotResponse(String... userInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < userInput.length; i++) {
            sb.append(userInput[i] + " ");
        }
        String response = sb.toString().trim();
        return response;
    }

    //checks to see if the model is present in the models directory
    private boolean isModelPresent(String model) {
        String fileDir = "models/" + model + "bot.txt"; //a model is denoted by a succeeding bot.txt
        File tempFile = new File(fileDir);
        boolean isPresent = tempFile.exists();
        return isPresent;
    }


}
