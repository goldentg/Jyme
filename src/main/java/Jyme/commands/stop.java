package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class stop implements CommandExecutor {
    @Command(aliases = "stop", description = "Stops the bot (Bot owner only)")
    public void onStop(MessageCreateEvent message) {
        //Check if the user is the bot owner
        if (message.getMessageAuthor().isBotOwner()) {
            message.getChannel().sendMessage("Stopping the bot...");
            System.out.println("Stopping the bot...");
            //Stop the bot
            System.exit(0);
        }
    }
}
