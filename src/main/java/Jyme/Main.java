package Jyme;

import Jyme.commands.*;
import Jyme.secret.Token;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.concurrent.ExecutionException;

public class Main {

    public static String prefix = "!";

    public static void main(String[] args) {
        DiscordApi client = new DiscordApiBuilder().setToken(Token.token).login().join();

        CommandHandler handler = new JavacordHandler(client);
        handler.setDefaultPrefix(prefix);
        //server invite command, if no invite create one and send it otherwise send existing (bot owner only)
        //dm bot owner on guild join/leave
        //more listeners?
        //total members online in server command and filter bot/actual users
        handler.registerCommand(new ping());
        handler.registerCommand(new botInfo());
        handler.registerCommand(new help(handler));
        handler.registerCommand(new serverInfo());
        handler.registerCommand(new coinflip());
        handler.registerCommand(new hug());
        handler.registerCommand(new eightball());
        handler.registerCommand(new userInfo());
        handler.registerCommand(new ban());
        handler.registerCommand(new profile());
        handler.registerCommand(new say());
        handler.registerCommand(new poll());
        handler.registerCommand(new stop());
        handler.registerCommand(new servers());
        handler.registerCommand(new uptime());
        handler.registerCommand(new host());
        //handler.registerCommand(new chuck());


        System.out.println("Bot is online!");

        //client.addServerJoinListener(event -> System.out.println("Bot has joined a server!\nServer: " + event.getServer().getName()));
        //Create on server join listener
        client.addServerJoinListener(event -> {
            System.out.println("Bot has joined a server!\nServer: " + event.getServer().getName());
            try {
                client.getOwner().get().sendMessage("Bot has joined a server: " + event.getServer().getName() + " With " + event.getServer().getMemberCount() + " members");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        //Create on server leave listener
        client.addServerLeaveListener(event -> {
            System.out.println("Bot has been removed from a server!\nServer: " + event.getServer().getName());
            try {
                client.getOwner().get().sendMessage("Bot has been removed from a server: " + event.getServer().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        //Create server on change name listener
        client.addServerChangeNameListener(event -> System.out.println("Server " + event.getOldName() + "has changed name to: " + event.getNewName()));
    }
}
