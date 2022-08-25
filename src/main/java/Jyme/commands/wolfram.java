package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class wolfram implements CommandExecutor {

    private String query = "https://api.wolframalpha.com/v1/result?i=<QUERY>&appid=";
    private String queryLink = "https://www.wolframalpha.com/input/?i=<QUERY>";
    private String key = "DEMO";

    private Message msg;

    @Command(aliases = {"wolfram", "wa"}, description = "Search wolframalpha")
    public void onCommand(DiscordApi api, User user, TextChannel channel, String[] args, MessageCreateEvent m) {
        if (m.getMessageAuthor().isBotOwner()) {
            long epoch = System.currentTimeMillis();
            String url = query.replace("<QUERY>", String.join("%20", args)) + key;
            String link = queryLink.replace("<QUERY>", String.join("%20", args));
            channel.sendMessage(new EmbedBuilder().setTitle("Querying WolframAlpha")).thenAcceptAsync(message -> msg = message);
            try {
                String reply = "Yes";
                URLConnection connection = new URL(url).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reply = reader.lines().collect(Collectors.joining("\n"));
                msg.edit(user.getMentionTag(), new EmbedBuilder()
                                .setTitle("Answer")
                                .setUrl(link)
                                .setDescription(String.format("```%s```", reply))
                                .setColor(Color.GREEN)
                                .setFooter("Took " + (System.currentTimeMillis() - epoch) + "ms"))
                        .exceptionally(e -> {
                            msg.edit(user.getMentionTag(), new EmbedBuilder().setColor(Color.RED).setTitle("Unable to query WolframAlpha"));
                            return null;
                        });
            } catch (IOException e) {
                msg.edit(user.getMentionTag(), new EmbedBuilder().setColor(Color.RED).setTitle("Unable to query WolframAlpha"));
                e.printStackTrace();
            }
        } else {
            m.getChannel().sendMessage("This command is not ready for public use yet and is only useable by the bot owner");
        }
    }
}