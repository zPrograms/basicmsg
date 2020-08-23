package zProgram.code;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zProgram.code.commands.BmsgCommand;
import zProgram.code.commands.MsgCommand;
import org.bukkit.plugin.java.JavaPlugin;
import zProgram.code.commands.ReplyCommand;
import zProgram.code.events.OnQuit;

import java.util.HashMap;

public class BasicMsg extends JavaPlugin {

    private Configuration config;
    public HashMap<CommandSender, Player> reply;

    public void onEnable(){
        registerConfig();
        registerCommands();
        registerEvents();
        getLogger().info("Plugin created by zProgram.");
        getLogger().info("You are using version "+ getDescription().getVersion() + ".");
        reply = new HashMap<>();
    }

    public void onDisable(){
        getLogger().info("Thx for using this plugin <3.");
        int number = (int) (Math.random() * 2 + 1);
        if (number == 1){
            getLogger().info("Goodbye!");
        }else if (number == 2){
            getLogger().info("See you later!");
        }else{
            getLogger().info("You shouldn't watch this..");
        }

    }

    public void registerCommands(){
        this.getCommand("msg").setExecutor(new MsgCommand(this, config));
        this.getCommand("bmsg").setExecutor(new BmsgCommand(this, config));
        this.getCommand("reply").setExecutor(new ReplyCommand(this, config));
        getLogger().info("Commands loaded!");
    }
    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new OnQuit(this), this);
        getLogger().info("Events loaded!");
    }

    public void registerConfig(){
        this.config = new Configuration(this, "config");
        config.reload();
        getLogger().info("Config loaded!");
    }







}
