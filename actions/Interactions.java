package actions;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class Interactions extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<String> messageContent = Arrays.asList(event.getMessage().getContentRaw().split(" "));
        //if (messageContent.contains)
    }

    private String containsIgnoreCase(List<String> content) {

//        for (String value: content) {
//            if (value.toLowerCase().contains())
//        }
        return "";
    }

}
