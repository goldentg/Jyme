package Jyme.commands;

import de.btobastian.sdcf4j.Command;

import java.awt.*;
import java.util.*;

import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class eightball implements CommandExecutor {
    @Command(aliases = "8ball", description = "Displays a message from a magic 8-ball")
    public void onEightball(MessageCreateEvent message) {

        String[] responses = new String[]{
                        //no responses
                        "My reply is no",
                        "Don’t count on it",
                        "Very doubtful",
                        "Outlook not so good",
                        "My sources say no",

                        //affirmative responses
                        "Yes",
                        "Most likely",
                        "It is certain",
                        "Signs point to yes",
                        "Outlook good",
                        "As I see it, yes",
                        "Yes, definitely",
                        "It is decidedly so",
                        "Without a doubt",
                        "You may rely on it",

                        //non commital responses
                        "Better not tell you now",
                        "Reply hazy try again",
                        "Concentrate and ask again",
                        "Cannot predict now",
                        "Ask again later"
                };


        int random = (int) (Math.random() * responses.length);
        //System.out.println(responses[random]);
        message.getChannel().sendMessage(createEmbed(responses[random]));
    }

    private EmbedBuilder createEmbed(String response) {
        return new EmbedBuilder()
                .setTitle("Magic 8-Ball")
                .setDescription(response)
                .setColor(new Color(11, 199, 193))
                .setTimestampToNow();
    }
}
