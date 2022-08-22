package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

public class say implements CommandExecutor {
    @Command(aliases = "say", description = "Says a message")
    public void onSay(MessageCreateEvent message, Message msg) {
        String args = msg.getContent().substring(5);
        if(msg.getAuthor().isServerAdmin()) {
            if (message.isServerMessage()) {
                message.deleteMessage();
                message.getChannel().sendMessage(args);
            } else {
                message.getChannel().sendMessage("You must send this message in a server");
            }
        } else {
            message.getChannel().sendMessage("You do not have permissions to do this");
        }
    }
}
