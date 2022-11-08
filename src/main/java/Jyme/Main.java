package Jyme;

import Jyme.commands.*;
import Jyme.secret.Token;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.util.concurrent.ExecutionException;

public class Main {

//CUSTOMIZATION:


    //SET PREFIX
    public static String prefix = "!";
    //SET ACTIVITY STATUS
    public static String activity = "!help for help";
    //SET ACTIVITY TYPE
    public static String activityType = "PLAYING";
    //Activity Type accepted options (PLAYING) is default
    //PLAYING
    //COMPETING
    //LISTENING
    //STREAMING
    //WATCHING

    //Log Settings:
    //Options:
    //true
    //false
    public static Boolean serverLeaveLogs = true;
    public static Boolean serverJoinLogs = true;
    public static Boolean serverNameChangeLogs = true;



//END CUSTOMIZATION
    public static void main(String[] args) {
        // Launch the bot
        DiscordApi client = new DiscordApiBuilder().setToken(Token.token).login().join();

        //create command handler
        CommandHandler handler = new JavacordHandler(client);
        //set prefix
        handler.setDefaultPrefix(prefix);
        //ideas
        //server invite command, if no invite create one and send it otherwise send existing (bot owner only)
        //dm bot owner on guild join/leave
        //more listeners?
        //total members online in server command and filter bot/actual user
        handler.registerCommand(new ping());
        handler.registerCommand(new botInfo());
        handler.registerCommand(new help(handler));
        handler.registerCommand(new serverInfo());
        handler.registerCommand(new coinflip());
        handler.registerCommand(new hug());
        handler.registerCommand(new eightball());
        handler.registerCommand(new userInfo());
        handler.registerCommand(new kick());
        handler.registerCommand(new ban());
        handler.registerCommand(new profile());
        handler.registerCommand(new say());
        handler.registerCommand(new poll());
        handler.registerCommand(new stop());
        handler.registerCommand(new servers());
        handler.registerCommand(new uptime());
        handler.registerCommand(new host());
        handler.registerCommand(new invite());
        handler.registerCommand(new play());
        handler.registerCommand(new disconnect());
        handler.registerCommand(new binary());
        handler.registerCommand(new wolfram());
        handler.registerCommand(new report());
        handler.registerCommand(new purge());
        //handler.registerCommand(new chuck());

        //output status log
        System.out.println("Bot is online!");


        //Create on server join listener

        //if server join logs are enabled
        if (serverJoinLogs) {
            client.addServerJoinListener(event -> {
                System.out.println("Bot has joined a server!\nServer: " + event.getServer().getName());
                try {
                    client.getOwner().get().sendMessage("Bot has joined a server: " + event.getServer().getName() + " With " + event.getServer().getMemberCount() + " members");
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        //Create on server leave listener

        //if server leave logs enabled
        if (serverLeaveLogs) {
            client.addServerLeaveListener(event -> {
                System.out.println("Bot has been removed from a server!\nServer: " + event.getServer().getName());
                try {
                    client.getOwner().get().sendMessage("Bot has been removed from a server: " + event.getServer().getName());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        //Set bot activity
        //Make sure activity type is valid
        if (activityType != "") {
            //if activity type is valid
            if (activity != "") {
                try {
                    //set activity
                    client.updateActivity(ActivityType.valueOf(activityType), activity);
                    //output status log
                    System.out.println("Bot activity set to: " + activity + ", With activity type: " + activityType);
                } catch (Exception e) {
                    System.out.println("Something went wrong while setting bot activity");
                }
            } else {
                System.out.println("Activity settings are missing or incorrect, skipping activity initialization");
            }
        } else {
            System.out.println("Activity settings are missing or incorrect, skipping activity initialization");
        }

        //Create server on change name listener

        //if server name change logs enabled
        if (serverNameChangeLogs) {
            client.addServerChangeNameListener(event -> System.out.println("Server " + event.getOldName() + "has changed name to: " + event.getNewName()));
        }
    }
}
