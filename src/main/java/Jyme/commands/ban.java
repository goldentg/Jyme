package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;


public class ban  implements CommandExecutor {
    @Command(aliases = "ban", description = "Bans a user")
    public void onBan(MessageCreateEvent message, Message message2, DiscordApi api) {

        User user;
        user = message2.getMentionedUsers().get(0);
        //check if message was sent in server
        if (message.isServerMessage()) {
            //Check if a user is mentioned
            if (message2.getMentionedUsers().size() > 0) {
                //Check if the user has permission to ban
                if (message2.getServer().get().canBanUser(message2.getUserAuthor().get(), user)) {
                    //Check if the bot has permission to ban
                    User bot = api.getYourself();
                    if (message.getServer().get().canBanUser(bot, user)) {
                        //Ban the user
                        message.getServer().get().banUser(user);
                        //Send a message to the channel
                        message.getChannel().sendMessage(user.getDiscriminatedName() + " has been banned by: " + message2.getUserAuthor().get().getMentionTag());
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