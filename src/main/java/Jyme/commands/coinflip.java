package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.Random;

public class coinflip implements CommandExecutor {
    @Command(aliases = "coinflip", description = "Flips a coin")
    public void onCoinflip(MessageCreateEvent message) {
        //random number generator
        int coinSide = new Random().nextInt(2);
        String coinSideString = "";
            //if 0, heads
           if (coinSide == 0) {
               coinSideString = "Heads";
               } else if (coinSide == 1) { //if 1, tails
                coinSideString = "Tails";
        }
        //send message
        message.getChannel().sendMessage(createEmbed(message, coinSideString));
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, String coinSideString) {
        return new EmbedBuilder()
                .setTitle("Coinflip")
                .setDescription("You flipped a coin and it landed on " + coinSideString)
                .setColor(new Color(11, 199, 193))
                .setTimestampToNow()
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
    }
}

