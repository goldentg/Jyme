package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Optional;

public class serverInfo implements CommandExecutor {
    @Command(aliases = "serverinfo", description = "Displays information about the server")
    public String onServerInfo(MessageCreateEvent message) {
        if (message.isServerMessage()) {
            Optional<Server> srv = message.getServer();
            //String serverOwner = String.valueOf(message.getServer().map(server -> server.getOwner().));
           // Optional<String> serverName = Optional.ofNullable(String.valueOf(message.getServer().map(server -> server.getName())));
            Optional<Object> serverName = srv.map(server -> server.getName());
           // String channels = String.valueOf(message.getServer().map(server -> server.getChannels().size()));
            Optional<Integer> channels = message.getServer().map(server -> server.getChannels().size());
            Optional<String> afkChannel = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getAfkChannel)));
            Optional<String> boostLevel = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getBoostLevel)));
            Optional<String> memberCount = Optional.ofNullable(String.valueOf(message.getServer().map(Server::getMemberCount)));
            //String region = String.valueOf(message.getServer().map(server -> server.getRegion()));


            // String serverName = message.getServer().getName();

            //String serverOwner = message.getServer();
            String toString = "name: " + serverName + " Channels: " + channels + " AFK Channel: " + afkChannel + " Server boost level " + boostLevel + " Member count: " + memberCount;
            return toString;

        } else {
            return "You are not sending the command in a server";
        }
    }
}
