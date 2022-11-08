package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;


public class servers implements CommandExecutor {
    @Command(aliases = "servers", description = "Displays server info (bot owner only)")
    public void onServer(MessageCreateEvent message, DiscordApi api) {
        if (message.getMessageAuthor().isBotOwner()) {
            //create variables
            String servers = "";
            int users = 0;
            int serverc = 0;
            //loop through servers
            for (Server s : api.getServers()) {


                servers += "\n" + s.getName() + "(" + s.getMemberCount() + ")";
                users += s.getMemberCount();
                serverc++;
            }
           //send embedded output
            message.getChannel().sendMessage(createEmbed(serverc, servers, users));
        } else {
            message.getChannel().sendMessage("You dont have permissions to do this");
        }
    }
    private EmbedBuilder createEmbed(int serverc, String servers, int users) {
        return new EmbedBuilder()
                .setTitle("Servers and Member Counts")
                .setDescription("(" + serverc + "):" + servers + "\nAverage users per server: " + (users / serverc) + "\nTotal Users: " + users + "\n Total Servers: " + serverc)
                .setColor(new Color(11, 199, 193));
    }
}
