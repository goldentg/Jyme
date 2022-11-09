package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;


import java.awt.*;

public class slap implements CommandExecutor {
    @Command(aliases = "slap", description = "Slaps a user")
        public void onSlap(Message msg) {
        //store author as user
        User auth = msg.getAuthor().asUser().get();
        //Check if a user is mentioned
            if (msg.getMentionedUsers().size() != 0) {
                //collect mentioned user
                User target = msg.getMentionedUsers().get(0);
                msg.getChannel().sendMessage(createEmbed(target, auth));
            } else {
                msg.reply("You must mention a user to slap");
            }
        }

    private EmbedBuilder createEmbed(User target, User auth) {
        return new EmbedBuilder()
                .setTitle("Slap")
                .setDescription(auth.getMentionTag() + " slapped " + target.getMentionTag())
                .setColor(new Color(11, 199, 193))
                .setImage("https://clipground.com/images/slap-in-the-face-clipart-3.jpg")
                .setFooter(auth.getDiscriminatedName(), auth.getAvatar())
                .setTimestampToNow();
    }
    }

