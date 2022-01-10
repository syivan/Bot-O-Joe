package actions;

import models.ModelBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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

    private boolean isModelPresent(String model) {
        String fileDir = "models/" + model + "bot.txt";
        File tempFile = new File(fileDir);
        boolean isPresent = tempFile.exists();
        System.out.println(isPresent);
        return isPresent;
    }


}
