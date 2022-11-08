package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class binary implements CommandExecutor {
    @Command(aliases = "binary", description = "Converts a message to binary")
    public void onBinary (MessageCreateEvent message, Message msg) {
        //store args in a string
        String input = msg.getContent().substring(8);
        //create a string builder
        StringBuilder sb = new StringBuilder();

        //convert string to binary
        char[] chars = input.toCharArray();
        for (char c: chars) {
            String binary = Integer.toBinaryString(c);
            String formatted = String.format("%8s", binary);
            String output = formatted.replaceAll(" ", "0");
            sb.append(output);
            sb.append(" ");
        }
        //send converted binary message
        message.getChannel().sendMessage(createEmbed(sb.toString(), input, message));
    }

    private EmbedBuilder createEmbed(String binaryMessage, String message, MessageCreateEvent msg) {
        return new EmbedBuilder()
                .setTitle("Convert to Binary")
                .setDescription("Original message: " + message + "\nBinary: " + binaryMessage)
                .setColor(new Color(11, 199, 193))
                .setFooter(msg.getMessageAuthor().getDiscriminatedName(), msg.getMessageAuthor().getAvatar());
    }
}
