package Jyme.commands;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class chuck implements CommandExecutor {
    @Command(aliases = "chuck", description = "Displays a random Chuck Norris joke")
    public CompletableFuture<Message> getWebPage(MessageCreateEvent message) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("https://api.chucknorris.io/jokes/random"));
        String response =  request.execute().parseAsString();
        //DeserializationContext objectMapper = null;
        return message.getChannel().sendMessage(response);
        //return null;
    }
    }

