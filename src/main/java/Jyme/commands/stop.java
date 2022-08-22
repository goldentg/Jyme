package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class stop implements CommandExecutor {
    @Command(aliases = "stop", description = "Stops the bot")
    public void onStop(MessageCreateEvent message) {
        if (message.getMessageAuthor().isBotOwner()) {
            message.getChannel().sendMessage("Stopping the bot...");
            System.exit(0);
        }
    }
}
