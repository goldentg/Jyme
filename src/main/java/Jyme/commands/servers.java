package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

public class servers implements CommandExecutor {
    @Command(aliases = "servers", description = "Displays server info (bot owner only)")
    public void onServer(MessageCreateEvent message, DiscordApi api) {
        if (message.getMessageAuthor().isBotOwner()) {
            String servers = "";
            int users = 0;
            int serverc = 0;
            for (Server s : api.getServers()) {
                servers += "/n" + s.getName() + "(" + s.getMemberCount() + ")";
                users += s.getMemberCount();
                serverc++;
            }
            message.getChannel().sendMessage("```x1" +"\nServers and member counts (" + serverc + "):" + servers + "\nAverage users Per Server: " + (users / serverc) + "\nTotal Users: " + users + "\nTotal Servers: " + serverc + "\n```");
        } else {
            message.getChannel().sendMessage("You dont have permissions to do this");
        }
    }
}
