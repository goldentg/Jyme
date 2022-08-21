package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class botInfo implements CommandExecutor {
    @Command(aliases = "botinfo", description = "Displays information about the bot")
    public void onBotInfo(MessageCreateEvent message) {
    message.getChannel().sendMessage(createEmbed(message));
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message) {
        return new EmbedBuilder()
                .setTitle("Bot Information")
                .setDescription("This bot was created by NetPhoenix#6950 in Java")
                .setColor(new Color(11, 199, 193))
                .setTimestampToNow()
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
    }
}
