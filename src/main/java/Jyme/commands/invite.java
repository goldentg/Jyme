package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.invite.Invite;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class invite implements CommandExecutor {
    @Command(aliases = "invite", description = "Bot owner only")
    public void onInvite (MessageCreateEvent message, DiscordApi api) {
        if (message.getMessageAuthor().isBotOwner()) {

            String invite = "";
            String noPerm = "";
            for (Server s : api.getServers()) {
            if (s.hasPermission(api.getYourself(), PermissionType.CREATE_INSTANT_INVITE)) {
                //invite += s.getInvites().toString();
                Invite inv = new InviteBuilder(s.getRegularChannels().get(1))
                        .setNeverExpire()
                        .setMaxUses(69)
                        .create().join();
                invite += inv.getCode();
            } else {
                noPerm += s.getName() + "\n";
                message.getChannel().sendMessage("These servers did not have the permissions to do this: " + noPerm);
            }
                }


               // InviteBuilder ib = new InviteBuilder(s.getRegularChannels().get(0));
            message.getChannel().sendMessage(invite);

            }

        }
    }

