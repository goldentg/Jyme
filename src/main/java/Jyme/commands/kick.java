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
        //Working user
        User user;
        user = msg.getMentionedUsers().get(0);
        if (message.isServerMessage()) {
            //Check if a user is mentioned
            if (msg.getMentionedUsers().size() > 0) {
                //Check if the user has permission to kick
                if (msg.getServer().get().canKickUser(msg.getUserAuthor().get(), user)) {
                    User bot = api.getYourself();
                    //Check if the bot has permission to kick
                    if (message.getServer().get().canKickUser(bot, user)) {
                        //kick the user
                        message.getServer().get().kickUser(user);
                        //Send a message to the channel
                        message.getChannel().sendMessage(user.getDiscriminatedName() + " has been kicked by: " + msg.getUserAuthor().get().getMentionTag());
                    } else {
                        message.getChannel().sendMessage("I do not have permissions to do this");
                    }
                } else {
                    message.getChannel().sendMessage("You do not have permissions to do this");
                }
            } else {
                message.getChannel().sendMessage("You must mention a user to ban");
            }
        } else {
            message.getChannel().sendMessage("You must enter this command in a server");
        }


    }
}