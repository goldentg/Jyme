package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;


public class uptime implements CommandExecutor {
    @Command(aliases = "uptime", description = "Displays bot uptime")
    public void onUptime(MessageCreateEvent message) {
        //Get the uptime of the bot
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        //convert the uptime
        long uptime = runtime.getUptime();
        long uptimeSeconds = uptime / 1000;
        long uptimeMinutes = uptimeSeconds / 60;
        long uptimeHours = uptimeMinutes / 60;
        long uptimeDays = uptimeHours / 24;

        //Send the uptime to the channel
        message.getChannel().sendMessage(createEmbed(message, uptimeSeconds, uptimeMinutes, uptimeHours, uptimeDays));
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message,long uptimeSeconds, long uptimeMinutes,long uptimeHours,  long uptimeDays) {
        return new EmbedBuilder()
                .setTitle("Bot Uptime")
                .setDescription("Uptime in seconds: " + uptimeSeconds + "\nUptime in minutes: " + uptimeMinutes + "\nUptime in hours: " + uptimeHours + "\nUptime in days: " + uptimeDays)
                .setColor(new Color(11, 199, 193))
                .setFooter(message.getMessageAuthor().getDiscriminatedName(), message.getMessageAuthor().getAvatar());
    }
}
