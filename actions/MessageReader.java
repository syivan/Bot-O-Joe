package actions;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReader extends ListenerAdapter {

    private final String prefix = "!";

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(prefix + "guide")) {
            new Commands().displayGuide(event);
        } else if (args[0].equalsIgnoreCase(prefix + "construct")) {
            if (args.length >= 2) {
                new Commands().constructModel(args[1]);
            }
        }
    }
}
