package Jyme.commands;


import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.management.relation.Role;

public class poll implements CommandExecutor {
    @Command(aliases = "poll", description = "Generates a poll")
    public void onPoll(MessageCreateEvent message) {
        String pollMessage = message.getReadableMessageContent().substring(6);
    }
}
