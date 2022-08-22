package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class userInfo implements CommandExecutor {
    @Command(aliases = "userinfo", description = "Displays information about the user")
    public void onUserInfo(MessageCreateEvent message) {
        //message.getChannel().sendMessage(createEmbed(message));
        String userName = message.getMessageAuthor().getDisplayName();
        String userID = message.getMessageAuthor().getIdAsString();
        String userAvatar = message.getMessageAuthor().getAvatar().getUrl().toString();
        String userDiscriminator = String.valueOf(message.getMessageAuthor().getDiscriminator());

        String userJoined = message.getMessageAuthor().getCreationTimestamp().toString();
        String userJoinedDate = message.getMessageAuthor().getCreationTimestamp().toString().substring(0, 10);
        String userJoinedTime = message.getMessageAuthor().getCreationTimestamp().toString().substring(11, 16);
        String userJoinedDateTime = message.getMessageAuthor().getCreationTimestamp().toString().substring(0, 16);
        message.getChannel().sendMessage(createEmbed(message, userName, userID, userAvatar, userDiscriminator, userJoined, userJoinedDate, userJoinedTime, userJoinedDateTime));

    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, String userName, String userID, String userAvatar, String userDiscriminator, String userJoined, String userJoinedDate, String userJoinedTime, String userJoinedDateTime) {
        return new EmbedBuilder()
                .setTitle("User Information")
                .setDescription("Username: " + userName + "\nUser ID: " + userID + "\nUser Discriminator: " + userDiscriminator + "\nUser Joined: " + userJoinedDate)
                .setColor(new Color(11, 199, 193))
                .setImage(userAvatar)
                .setTimestampToNow()
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
    }
}

