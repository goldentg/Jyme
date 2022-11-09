package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;


import java.awt.*;

public class hug implements CommandExecutor {
    @Command(aliases = "hug", description = "Hugs a user")
    public void onHug(Message message) {
        //Store author as User
        User auth = message.getAuthor().asUser().get();
        if (message.getMentionedUsers().size() != 0) {
            User target = message.getMentionedUsers().get(0);
            //send the message
            message.getChannel().sendMessage(createEmbed(target, auth));
        } else {
            message.reply("You must mention a user to hug");
        }
    }

    private EmbedBuilder createEmbed(User target, User auth) {
        return new EmbedBuilder()
                .setTitle("Hug")
                .setDescription(auth.getMentionTag() + " hugged " + target.getMentionTag())
                .setColor(new Color(11, 199, 193))
                .setImage("https://clipartcraft.com/images/teddy-bear-clipart-hugging-2.png")
                .setFooter(auth.getDiscriminatedName(), auth.getAvatar())
                .setTimestampToNow();
    }
}

