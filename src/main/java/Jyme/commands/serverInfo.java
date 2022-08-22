package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.Optional;

public class serverInfo implements CommandExecutor {
    @Command(aliases = "serverinfo", description = "Displays information about the server")
    public String onServerInfo(MessageCreateEvent message) {
        if (message.isServerMessage()) {
            Optional<Server> srv = message.getServer();
            Server s = message.getServer().get();


            String serverName = srv.map(server -> server.getName()).orElse("No server name");
            int roleCount = s.getRoles().size();
            int voiceChannels = s.getVoiceChannels().size();
            User owner = s.getOwner().orElse(s.requestOwner().join());
            int serverChannels = message.getServer().map(server -> server.getChannels().size()).orElse(0);
            int memberCountInt = message.getServer().map(Server::getMemberCount).orElse(0);

            message.getChannel().sendMessage(createEmbed(serverName, serverChannels, memberCountInt, message, roleCount, voiceChannels, owner));

        } else {
            return "You are not sending the command in a server";
        }
        return null;
    }

    private EmbedBuilder createEmbed(String serverName, int serverChannels, int memberCountInt, MessageCreateEvent message, int roleCount, int voiceChannel, User owner) {
        return new EmbedBuilder()
                .setTitle("Server Information")
                .setDescription("Server name: " + serverName + "\n" + "Channels: " + serverChannels + "\n" + "Member count: " + memberCountInt + "\n" + "Roles: " + roleCount + "\n" + "Voice channels: " + voiceChannel + "\n" + "Server owner: " + owner.getNicknameMentionTag())
                .setColor(new Color(11, 199, 193))
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setTimestampToNow();



    }
}
