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
            //String serverOwner = String.valueOf(message.getServer().map(server -> server.getOwner().));
           // Optional<String> serverName = Optional.ofNullable(String.valueOf(message.getServer().map(server -> server.getName())));
            //Optional<Object> serverName = srv.map(server -> server.getName());

            //good
            String serverName = srv.map(server -> server.getName()).orElse("No server name");


           // String channels = String.valueOf(message.getServer().map(server -> server.getChannels().size()));
            //Optional<Integer> channels = message.getServer().map(server -> server.getChannels().size());
            //Optional<String> afkChannel = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getAfkChannel)));

            //Optional<String> boostLevel = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getBoostLevel)));

           // String serverOwner = String.valueOf(message.getServer().map(Server::getOwner));
            //Optional<User> serverOwner = String.valueOf(srv.map(server -> server.getOwner()).orElse("No server owner");)

            //good
            int serverChannels = message.getServer().map(server -> server.getChannels().size()).orElse(0);
            //Optional<String> memberCount = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getMemberCount)));
            //String region = String.valueOf(message.getServer().map(server -> server.getRegion()));

            //good
            int memberCountInt = message.getServer().map(Server::getMemberCount).orElse(0);

            //Optional<Object> serverIcon = message.getServer().map(server -> server.getIcon());


            // String serverName = message.getServer().getName();

            //String serverOwner = message.getServer();
            //String toString = "name: " + serverName + ", Channels: " + serverChannels + ", Member count: " + memberCountInt;
           // return toString;

            message.getChannel().sendMessage(createEmbed(serverName, serverChannels, memberCountInt, message));

        } else {
            return "You are not sending the command in a server";
        }
        return null;
    }

    private EmbedBuilder createEmbed(String serverName, int serverChannels, int memberCountInt, MessageCreateEvent message) {
        return new EmbedBuilder()
                .setTitle("Server Information")
                .setDescription("Server name: " + serverName + "\n" + "Channels: " + serverChannels + "\n" + "Member count: " + memberCountInt)
                .setColor(new Color(11, 199, 193))
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setTimestampToNow();



    }
}
