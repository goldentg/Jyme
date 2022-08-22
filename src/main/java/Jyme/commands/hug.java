package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class hug implements CommandExecutor {
    @Command(aliases = "hug", description = "Hugs a user")
    public void onHug(MessageCreateEvent message, Message msg) {
        if (msg.getMentionedUsers().size() == 1) {
            User user = msg.getMentionedUsers().get(0);
            message.getChannel().sendMessage(createEmbed(message, user));
        } else {
            message.getChannel().sendMessage("Please mention a user to hug");
        }
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, User user) {
        return new EmbedBuilder()
                .setTitle("Hug")
                .setDescription("<@" + message.getMessageAuthor().getId() + ">" + " hugged  " + user.getMentionTag())
                .setColor(new Color(11, 199, 193))
                .setImage("https://clipartcraft.com/images/teddy-bear-clipart-hugging-2.png")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setTimestampToNow();
    }
}

