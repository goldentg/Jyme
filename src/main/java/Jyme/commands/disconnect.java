package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.VoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class disconnect implements CommandExecutor {
    @Command(aliases = "disconnect", description = "Leave a voice channel")
    public void onDisconnect(MessageCreateEvent message, DiscordApi api) {
        if (message.getMessageAuthor().isBotOwner()) {
            if (api.getYourself().isConnected((ServerVoiceChannel) message.getServer().get())) {
                //api.disconnect();
                User bot = api.getYourself();
               VoiceChannel vc = bot.getConnectedVoiceChannel(message.getServer().get()).get();
               vc.getApi().disconnect();
            }
        } else {
            message.getChannel().sendMessage("This command is not ready for public use yet");
        }
    }
}
