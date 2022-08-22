package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.Mentionable;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class ban  implements CommandExecutor {
    @Command(aliases = "ban", description = "Bans a user")
    public String ban(MessageCreateEvent message, Mentionable user) {
        //User user = (User) message.getMessageAuthor();


        return user.getMentionTag();

    }
}
