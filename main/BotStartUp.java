package main;

import actions.CommandReader;
import actions.MessageReader;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import javax.security.auth.login.LoginException;

/**
 * Runs the Bot-O-Joe Program
 * @author Ivan Sy
 * @version 1.01 - 01/20/2022
 */
public class BotStartUp {

    /**
     * constructs the JDABuilder responsible for registering listeners
     * @param args Command line arguments (ignored)
     * @throws LoginException thrown when provided token in createDefault method is not valid
     */
    public static void main(String[] args) throws LoginException {
        JDABuilder jda = JDABuilder.createDefault("OTI1MzA0Njc1MDM5ODM4MjEw.YcrLIQ.2y2stOmRWibu_6EN3Dv4epx6be0");
        jda.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jda.addEventListeners(new CommandReader(), new MessageReader());
        jda.build();
    }
}
