package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Mentionable;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;


public class ban  implements CommandExecutor {
    @Command(aliases = "ban", description = "Bans a user")
    public void onBan(MessageCreateEvent message, Message message2, DiscordApi api) {
        //if (message.getMessageAuthor().isServerAdmin() && message.isServerMessage()) {

            //Working user
            User user = message2.getMentionedUsers().get(0);
            if (message.isServerMessage()) {
                if (message.getServer().get().canBanUser((User) message.getMessageAuthor(), user)) {
                    //Check if a user is mentioned
                    if (message2.getMentionedUsers().size() > 0) {
                        User bot = api.getYourself();
                        //Check if bot has permissions to ban
                        if (message.getServer().get().hasPermission(bot, PermissionType.BAN_MEMBERS)) {
                            Server server = message.getServer().get();
                            server.banUser(user);

                            message.getChannel().sendMessage("user: " + user.getMentionTag() + " has been banned");
                        } else {
                            message.getChannel().sendMessage("I do not have permissions to ban a member");
                        }
                    } else {
                        message.getChannel().sendMessage("You must message a user to ban");
                    }
                } else {
                    message.getChannel().sendMessage("You do not have permission to use this command");
                }
            } else {
                message.getChannel().sendMessage("You must enter this command in a server");
            }

    }

}
