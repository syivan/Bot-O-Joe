package actions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

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

    public void constructModel(String modelName) {

    }


}
