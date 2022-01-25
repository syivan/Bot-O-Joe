package actions;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * An Event Listener responsible for reading user input and working on member messages
 * that utilizes the command prefix, represented as an exclamation mark ( ! )
 * All valid commands start with !
 * @author Ivan Sy
 * @version 1.01 - 01/21/2022
 * @see Commands
 */
public class CommandReader extends ListenerAdapter {

    //commands are preceded by !
    private final String prefix = "!";

    /**
     * member message in the channel is read and all valid commands, preceded by ! and followed by a
     * valid command key, is directed to the intended response
     * A valid command example: "!guide" "!addresponse hello!"
     * @param event the event to retrieve channel where the member message is generated
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        args[0] = args[0].toLowerCase();
        if (args[0].equalsIgnoreCase(prefix + "guide")) { //DISPLAYS GUIDE
            new Commands().displayGuide(event);
        } else if (args[0].equalsIgnoreCase(prefix + "construct")) { //CONSTRUCTS MODEL
            if (args.length >= 2) {
                new Commands().constructModel(event, args[1]);
            }
        } else if (args[0].equalsIgnoreCase(prefix + "addresponse")) { //ADDS RESPONSE
            if (args.length >= 3) {
                new Commands().addModelResponse(event, args);
            }
        }
    }

}
