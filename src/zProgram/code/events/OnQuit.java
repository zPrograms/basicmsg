package zProgram.code.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import zProgram.code.BasicMsg;

public class OnQuit implements Listener{

    public BasicMsg plugin;

    public OnQuit(BasicMsg plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public boolean equals(PlayerQuitEvent event) {
        if(plugin.reply.containsKey(event)){
            plugin.reply.remove(event);

        }

        return false;

    }
}
