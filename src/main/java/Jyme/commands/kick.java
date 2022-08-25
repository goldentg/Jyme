package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class kick implements CommandExecutor {
    @Command(aliases = "kick", description = "Kicks a user")
    public void onKick(MessageCreateEvent message, Message msg, DiscordApi api) {
        User user = msg.getMentionedUsers().get(0);
        if (message.isServerMessage()) {
            if (message.getServer().get().canKickUser((User) message.getMessageAuthor(), user)) {
                if (msg.getMentionedUsers().size() > 0) {
                    User bot = api.getYourself();
                    if (message.getServer().get().canKickUser(bot, user)) {
                    message.getServer().get().kickUser(user);
                    message.getChannel().sendMessage(user.getMentionTag() + " has been kicked by: " + ((User) message.getMessageAuthor()).getMentionTag());
                    } else {
                        message.getChannel().sendMessage("I do not have permissions to do this");
                    }
                } else {
                    message.getChannel().sendMessage("You must mention a user to kick");
                }
            } else {
                message.getChannel().sendMessage("You do not have permissions to do this");
            }
        } else {
            message.getChannel().sendMessage("You must enter this command in a server");
        }
    }
}
