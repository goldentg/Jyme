package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class report implements CommandExecutor {
    @Command(aliases = "report", description = "Report a Bug to the developer")
    public void onReport(MessageCreateEvent message) {
    message.getChannel().sendMessage(createEmbed());
    }

    private EmbedBuilder createEmbed() {
        return new EmbedBuilder()
                .setTitle("Report a Bug!")
                .setDescription("Please report any issues with the bot by submitting an issue on the github page for this bot so that the developer may fix it:\n https://github.com/goldentg/Jyme/issues")
                .setColor(new Color(11, 199, 193))
                .setTimestampToNow();
    }
    }
