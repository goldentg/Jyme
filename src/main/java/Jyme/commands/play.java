package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.audio.AudioSourceBase;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;


public class play implements CommandExecutor {
    @Command(aliases = "play", description = "plays music")
    public void onPlay(MessageCreateEvent message, Message msg, DiscordApi api) {
        if (message.getMessageAuthor().isBotOwner()) {
            User user = msg.getUserAuthor().get();
            ServerVoiceChannel vc;
            if (user.isConnected(user.getConnectedVoiceChannel(message.getServer().get()).get())) {
                vc = user.getConnectedVoiceChannel(message.getServer().get()).get();
                if (vc.canConnect(api.getYourself())) {
                    vc.connect().thenAccept(audioConnection -> {
                    //CODE
                    }).exceptionally(e -> {
                        e.printStackTrace();
                        return null;
                    });
                } else {
                    message.getChannel().sendMessage("I cannot connect to this voice channel");
                }
            }
        } else {
            message.getChannel().sendMessage("This command is not ready for public use yet");
        }
    }
}
/*

public class LavaplayerAudioSource extends AudioSourceBase {

    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    /**
     * Creates a new lavaplayer audio source.
     *
     * @param api A discord api instance.
     * @param audioPlayer An audio player from Lavaplayer.
     */
    /*
    public LavaplayerAudioSource(DiscordApi api, AudioPlayer audioPlayer) {
        super(api);
        this.audioPlayer = audioPlayer;
    }

    @Override
    public byte[] getNextFrame() {
        if (lastFrame == null) {
            return null;
        }
        return applyTransformers(lastFrame.getData());
    }

    @Override
    public boolean hasFinished() {
        return false;
    }

    @Override
    public boolean hasNextFrame() {
        lastFrame = audioPlayer.provide();
        return lastFrame != null;
    }

    @Override
    public AudioSource copy() {
        return new LavaplayerAudioSource(getApi(), audioPlayer);
    }
}
*/
