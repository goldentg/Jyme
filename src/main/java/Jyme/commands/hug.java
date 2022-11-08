package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class hug implements CommandExecutor {
    @Command(aliases = "hug", description = "Hugs a user")
    public void onHug(MessageCreateEvent message) {
        //send the message
        message.getChannel().sendMessage(createEmbed(message));
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message) {
        return new EmbedBuilder()
                .setTitle("Hug")
                .setDescription(message.getMessageAuthor().getDiscriminatedName() + " hugged you")
                .setColor(new Color(11, 199, 193))
                .setImage("https://clipartcraft.com/images/teddy-bear-clipart-hugging-2.png")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setTimestampToNow();
    }
}

