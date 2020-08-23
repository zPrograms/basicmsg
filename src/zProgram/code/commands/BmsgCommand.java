package zProgram.code.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zProgram.code.BasicMsg;
import zProgram.code.Configuration;

public class BmsgCommand implements CommandExecutor {

    public BasicMsg plugin;
    public Configuration config;

    public BmsgCommand(BasicMsg plugin, Configuration config) {
        this.plugin = plugin;
        this.config = config;
    }
    public boolean onCommand(final CommandSender p, Command cmd, String label, String[] args) {
        if (!(p instanceof Player)) {
            p.sendMessage(config.getString("config.error.console").replace("%c", config.getString("config.prefix")));
            return false;

        } else {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    for (String msg : config.getStringList("config.messages.help")) {
                        p.sendMessage(msg.replace(config.getString("config.variable"), config.getString("config.prefix")));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission(config.getString("config.perms.reload"))) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.messages.load").replace(config.getString("config.variable"), config.getString("config.prefix"))));
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                            @Override
                            public void run(){
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.messages.reload").replace(config.getString("config.variable"), config.getString("config.prefix"))));
                                config.reload();
                            }

                        }, 20L *3);

                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.error.no-perms").replace(config.getString("config.variable"), config.getString("config.prefix"))));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.error.unknown-arg").replace(config.getString("config.variable"), config.getString("config.prefix"))) + " /bmsg [<text>]");
                }
            } else {
                for (String msg1 : config.getStringList("config.messages.help")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg1.replace(config.getString("config.variable"), config.getString("config.prefix"))));
                }
            }
        }
        return true;
    }
}
