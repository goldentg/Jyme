package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;


import java.awt.*;

public class ping implements CommandExecutor {
/*
    @Override
    public void onMessageCreate(MessageCreateEvent message) {
    if (message.getMessageContent().equalsIgnoreCase(Main.prefix + "ping")) {
    message.getChannel().sendMessage("Pong!");
    }
    }
*/
    @Command(aliases = "ping", description = "Displays a pong message")
    public void OnPingCommand(MessageCreateEvent message) {
        message.getChannel().sendMessage(createEmbed(message));
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message) {
        return new EmbedBuilder()
        .setTitle("Ping")
                .setDescription("Pong!")
                .setColor(new Color(11, 199, 193))
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
                //.setImage("https://clipground.com/images/ping-pong-paddle-clip-art-2.jpg");
    }
}
