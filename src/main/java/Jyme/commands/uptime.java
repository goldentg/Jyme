package Jyme.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import java.util.Date;

public class uptime implements CommandExecutor {
    @Command(aliases = "uptime", description = "Displays bot uptime")
    public void onUptime() {
        //long seconds = (new Date().getTime() - DataHolder.startTime)
    }
}
