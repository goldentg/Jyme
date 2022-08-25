package Jyme.commands;


import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;


public class poll implements CommandExecutor {
    @Command(aliases = "poll", description = "Generates a poll")
    public void onPoll(MessageCreateEvent message, Message msg) {
        String upVote = "⬆";
        String downVote = "⬇";
        String pollMessage = message.getReadableMessageContent().substring(6);
        message.getChannel().sendMessage(createEmbed(message, pollMessage)).thenCompose(message1 -> message1.addReactions(upVote, downVote));

    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, String pollMessage) {

        return new EmbedBuilder()
                .setTitle("Poll")
                .setDescription(pollMessage)
                .setFooter(message.getMessageAuthor().getDiscriminatedName(), message.getMessageAuthor().getAvatar())
                .setColor(new Color(16, 132, 194));
    }
}
