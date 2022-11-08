package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.DiscordApi;


public class purge implements CommandExecutor {
    @Command(aliases = "purge", description = "Purge messages from a channel")
public void onPurge(Message message, MessageCreateEvent event, DiscordApi api) {
        //Check if the user has the permission to manage messages
        if (message.getAuthor().canManageMessagesInTextChannel()) {
            //check if command was ran in a server
            if (message.isServerMessage()) {
                //check if the bot has the permission to manage messages
                if (message.getServer().get().hasPermission(api.getYourself(), PermissionType.MANAGE_MESSAGES)) {
                    //delete the command message
                    message.delete();
                    //get the amount of messages to delete
                    String[] messageArray = event.getMessageContent().split(" ");
                    int numOfMessages = Integer.parseInt(messageArray[messageArray.length - 1]);
                    //get the messages
                    MessageSet messages = message.getChannel().getMessages(numOfMessages).join();
                    //delete the messages
                    for (Message msg : messages) {
                        msg.delete();
                    }
                    //send a success message
                    message.getChannel().sendMessage("Purging messages...");
                } else {
                    message.getChannel().sendMessage("I do not have permissions to do this");
                }
            } else {
                message.getChannel().sendMessage("You must enter this command in a server");
            }
        } else {
            message.getChannel().sendMessage("You do not have permission to do this");
        }
    }

}
