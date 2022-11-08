package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.text.NumberFormat;

public class host implements CommandExecutor {
    @Command(aliases = "host", description = "Displays host information (bot owner only)")
    public void onHost(MessageCreateEvent message) throws IOException {
        //if the user is the bot owner
        if (message.getMessageAuthor().isBotOwner()) {
            Runtime runtime = Runtime.getRuntime();
            NumberFormat format = NumberFormat.getInstance();

            OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

            //get memory information
            long maxMemory = runtime.maxMemory();
            long allocatedMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            //get CPU load
            double cpuLoad = operatingSystemMXBean.getSystemLoadAverage();
            //get host name
            String hostname = InetAddress.getLocalHost().getHostName();

            //Send the host information to the channel
            message.getChannel().sendMessage(createEmbed(message, format, maxMemory, allocatedMemory, freeMemory, cpuLoad, hostname));
        } else {
            message.getChannel().sendMessage("You do not have permissions to do this");
        }
    }

    private EmbedBuilder createEmbed(MessageCreateEvent message, NumberFormat format, long maxMemory, long allocatedMemory, long freeMemory, double cpuLoad, String hostname) {
        return new EmbedBuilder()
                .setTitle("Host Statistics\nHostname: " + hostname)
                .setDescription("Free memory: " + format.format(freeMemory) + "\nAllocated memory: " + format.format(allocatedMemory) + "\nMax memory: " + format.format(maxMemory) + "\nTotal free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory))) + "\nPercentage free: " + ((double)freeMemory / (double) maxMemory)*100 + "%" + "\nPercent Used: " + ((double)(maxMemory - freeMemory) / (double)maxMemory)*100 + "%" + "\nCPU load: " + cpuLoad)
                .setColor(new Color(11, 199, 193))
                .setFooter(message.getMessageAuthor().getDiscriminatedName(), message.getMessageAuthor().getAvatar());
    }
}
