package zProgram.code.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zProgram.code.BasicMsg;
import zProgram.code.Configuration;

public class ReplyCommand implements CommandExecutor{

    public BasicMsg plugin;
    public Configuration config;

    public ReplyCommand(BasicMsg plugin, Configuration config){
        this.plugin = plugin;
        this.config = config;
    }
    public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            if (plugin.reply.containsKey(p)){
                String msg = "";
                for (int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.reply-msg").replace("%player%", p.getName()).replace(config.getString("config.variable"), config.getString("config.prefix")).replace("%arg-1%", plugin.reply.get(p).getName())).replace("%message%", msg));
                    plugin.reply.get(p).sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.reply-msg").replace("%player%", p.getName()).replace(config.getString("config.variable"), config.getString("config.prefix")).replace("%arg-1%", plugin.reply.get(p).getName())).replace("%message%", msg));
                    plugin.reply.replace(plugin.reply.get(p), ((Player) p).getPlayer());
                }
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.error.no-reply").replace(config.getString("config.variable"), config.getString("config.prefix"))));
            }
        } else {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', (config.getString("config.error.no-arg1").replace("%c", config.getString("config.prefix")))) + " /reply [<message>]");
        }
        return false;
    }
}
