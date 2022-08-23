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
        if (message.getMessageAuthor().isBotOwner()) {
            Runtime runtime = Runtime.getRuntime();
            NumberFormat format = NumberFormat.getInstance();
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

           // MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

           // OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
              //      mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

            StringBuilder sb = new StringBuilder();
            long maxMemory = runtime.maxMemory();
            long allocatedMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();

            double cpuLoad = operatingSystemMXBean.getSystemLoadAverage();

            String hostname = InetAddress.getLocalHost().getHostName();


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
