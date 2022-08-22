package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.Mentionable;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;


public class ban  implements CommandExecutor {
    @Command(aliases = "ban", description = "Bans a user")
    public void onBan(MessageCreateEvent message, Message message2) {
        if (message.getMessageAuthor().isServerAdmin() && message.isServerMessage()) {

            //Mentionable user = (Mentionable) message.getMessageAuthor();
           // message.getChannel().ban(user);
           // User user = message.getUserMentioned(0);
            //String user = message.getReadableMessageContent().substring(5);
            //User user = message.getMentionedUsers().get(0);

            //Working user
            User user = message2.getMentionedUsers().get(0);
            if (message2.getMentionedUsers().size() > 0) {

                Server server = message.getServer().get();
                server.banUser(user);

                message.getChannel().sendMessage("user: " + user.getMentionTag() + " has been banned");
            } else {
                message.getChannel().sendMessage("You must message a user to ban");
            }
        } else {
            message.getChannel().sendMessage("You do not have permission to use this command or this command was not entered in a server");
        }

    }

}
