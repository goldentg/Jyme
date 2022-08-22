package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.net.URL;
import java.net.URLConnection;

public class Chuck implements CommandExecutor {
    @Command(aliases = "chuck", description = "Displays a random Chuck Norris joke")
    public void onChuck(MessageCreateEvent message) {
      //  URLConnection connection = new URL(https://api.chucknorris.io/jokes/random).openConnection();
        //message.getChannel().sendMessage("https://api.chucknorris.io/jokes/random");
    }
}
