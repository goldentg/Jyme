package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class say implements CommandExecutor {
    @Command(aliases = "say", description = "Says a message")
    public void onSay(MessageCreateEvent message, Message msg) {
        //collect the arguments
        String args = msg.getContent().substring(5);
        //if author is a server admin
        if(msg.getAuthor().isServerAdmin()) {
            //if the message was sent in a server
            if (message.isServerMessage()) {
                //delete the command message
                message.deleteMessage();
                //send the message
                message.getChannel().sendMessage(createEmbed(args, message));
            } else {
                message.getChannel().sendMessage("You must send this message in a server");
            }
        } else {
            message.getChannel().sendMessage("You do not have permissions to do this");
        }
    }

    private EmbedBuilder createEmbed(String message, MessageCreateEvent msg) {
        return new EmbedBuilder()
                .setTitle("Announcement")
                .setDescription(message)
                .setColor(new Color(0x0BC7C1))
                .setFooter(msg.getMessageAuthor().getDiscriminatedName(), msg.getMessageAuthor().getAvatar());
    }
}
