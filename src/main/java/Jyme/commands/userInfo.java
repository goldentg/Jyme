package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class userInfo implements CommandExecutor {
    @Command(aliases = "userinfo", description = "Displays information about the user")
    public void onUserInfo(MessageCreateEvent message, Message msg) {
        //collect a mentioned user
        User targetUser = msg.getMentionedUsers().get(0);
        //collect the server
        Server server = message.getServer().get();

        if (msg.getMentionedUsers().size() == 0) {
            //gather info about the user who sent the message
            String userName = message.getMessageAuthor().getDisplayName();
            String userID = message.getMessageAuthor().getIdAsString();
            String userAvatar = message.getMessageAuthor().getAvatar().getUrl().toString();
            String userDiscriminator = String.valueOf(message.getMessageAuthor().getDiscriminator());
            String userJoinedDate = message.getMessageAuthor().getCreationTimestamp().toString().substring(0, 10);
            //send the message
            message.getChannel().sendMessage(createEmbed(message, userName, userID, userAvatar, userDiscriminator, userJoinedDate));
        } else if (msg.getMentionedUsers().size() == 1) {
            //gather info about the user who was mentioned
            String username = targetUser.getDisplayName(server);
            String userId = targetUser.getIdAsString();
            String userAvatar = targetUser.getAvatar().getUrl().toString();
            String userDiscriminaor = targetUser.getDiscriminator();
            String userJoinedDate = targetUser.getCreationTimestamp().toString().substring(0, 16);
            //send the message
            message.getChannel().sendMessage(createEmbed(message, username, userId, userAvatar, userDiscriminaor, userJoinedDate));

        }

    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, String userName, String userID, String userAvatar, String userDiscriminator, String userJoinedDate) {
        return new EmbedBuilder()
                .setTitle("User Information")
                .setDescription("Username: " + userName + "\nUser ID: " + userID + "\nUser Discriminator: " + userDiscriminator + "\nAccount Created: " + userJoinedDate)
                .setColor(new Color(11, 199, 193))
                .setImage(userAvatar)
                .setTimestampToNow()
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
    }
}

