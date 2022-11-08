package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class profile implements CommandExecutor {
    @Command(aliases = "profile", description = "Displays a users profile pic")
    public void onProfile(MessageCreateEvent message, Message msg) {
        //if no user is mentioned, display the profile of the user who sent the message
        if (msg.getMentionedUsers().size() == 0) {
            String avatar = message.getMessageAuthor().getAvatar().getUrl().toString();
            String username = message.getMessageAuthor().getDiscriminatedName();
            message.getChannel().sendMessage(createEmbed(message, avatar, username));
        } else if (msg.getMentionedUsers().size() == 1) { //if a user is mentioned, display the profile of the mentioned user
            //Get target profile pic
            String avatar = msg.getMentionedUsers().get(0).getAvatar().getUrl().toString();
            String username = msg.getMentionedUsers().get(0).getDiscriminatedName();
            message.getChannel().sendMessage(createEmbed(message, avatar, username));
        }
    }
    private EmbedBuilder createEmbed(MessageCreateEvent message, String avatar, String username) {
        return new EmbedBuilder()
                .setTitle(username + "'s Profile Picture")
                .setDescription("")
                .setColor(new Color(11, 199, 193))
                .setImage(avatar)
                .setTimestampToNow()
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
    }
}
