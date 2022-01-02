package main;

import actions.Interactions;
import actions.MessageReader;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class BotStartUp {

    public static void main(String[] args) throws LoginException {
        JDABuilder jda = JDABuilder.createDefault("OTI1MzA0Njc1MDM5ODM4MjEw.YcrLIQ.2y2stOmRWibu_6EN3Dv4epx6be0");
        jda.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jda.addEventListeners(new MessageReader());
        jda.build();
    }
}
