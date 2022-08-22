package Jyme;

import Jyme.commands.*;
import Jyme.secret.Token;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static String prefix = "!";

    public static void main(String[] args) {
        DiscordApi client = new DiscordApiBuilder().setToken(Token.token).login().join();

        CommandHandler handler = new JavacordHandler(client);
        handler.setDefaultPrefix(prefix);

        handler.registerCommand(new ping());
        handler.registerCommand(new botInfo());
        handler.registerCommand(new help(handler));
        handler.registerCommand(new serverInfo());
        handler.registerCommand(new coinflip());
        //handler.registerCommand(new ban());


        System.out.println("Bot is online!");

        client.addServerJoinListener(event -> System.out.println("Bot has joined a server!\nServer: " + event.getServer().getName()));
        client.addServerLeaveListener(event -> System.out.println("Bot has been removed from a server!\nServer: " + event.getServer().getName()));
        client.addServerChangeNameListener(event -> System.out.println("Server " + event.getOldName() + "has changed name to: " + event.getNewName()));
    }
}
