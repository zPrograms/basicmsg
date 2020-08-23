package zProgram.code.commands;

import org.bukkit.ChatColor;
import zProgram.code.BasicMsg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zProgram.code.Configuration;

public class MsgCommand implements CommandExecutor {


    public BasicMsg plugin;
    private final Configuration config;

    public MsgCommand(BasicMsg plugin, Configuration config){
        this.config = config;
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {

        if (!(p instanceof Player)){
            p.sendMessage(config.getString("config.error.console").replace(config.getString("config.variable"), config.getString("config.prefix")));
            return false;
        }
        if (args.length > 0){
            Player pr = Bukkit.getServer().getPlayer(args[0]);

            if (pr != null){
                if (!(args[0].equalsIgnoreCase(p.getName()))){

                    if (args.length > 1) {
                        String msg = "";
                        for (int i = 1; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.pm-player").replace("%player%", p.getName()).replace(config.getString("config.variable"), config.getString("config.prefix")).replace("%arg-1%", pr.getName())).replace("%message%", msg));
                            pr.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.pm-arg-1").replace("%player%", p.getName()).replace(config.getString("config.variable"), config.getString("config.prefix")).replace("%arg-1%", pr.getName())).replace("%message%", msg));
                        }
                        if (!(plugin.reply.containsKey(p))){
                            plugin.reply.put(p, pr);
                            plugin.reply.put(pr, ((Player) p).getPlayer());
                        }else{
                            plugin.reply.replace(p, pr);
                            plugin.reply.replace(pr, ((Player) p).getPlayer());
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.error.no-arg2").replace(config.getString("config.variable"), config.getString("config.prefix"))));
                    }
                }else{
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("config.error.same-player").replace(config.getString("config.variable"), config.getString("config.prefix")).replace("%player%", p.getName())));
                }
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',(config.getString("config.error.offline").replace(config.getString("config.variable"), config.getString("config.prefix")))));
            }

        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',(config.getString("config.error.no-arg1").replace("%c", config.getString("config.prefix")))) + "/msg [<player>] [<message>]");
        }

        return true;
    }
}
