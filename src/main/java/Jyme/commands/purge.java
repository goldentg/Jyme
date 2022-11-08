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
        if (message.getAuthor().canManageMessagesInTextChannel()) {
            if (message.isServerMessage()) {

                if (message.getServer().get().hasPermission(api.getYourself(), PermissionType.MANAGE_MESSAGES)) {
                    message.delete();

                    String[] messageArray = event.getMessageContent().split(" ");
                    int numOfMessages = Integer.parseInt(messageArray[messageArray.length - 1]);
                    MessageSet messages = message.getChannel().getMessages(numOfMessages).join();
                    for (Message msg : messages) {
                        msg.delete();
                    }
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
