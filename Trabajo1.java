
package Trabajo1;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Trabajo1 extends JavaPlugin {
    
    public static Trabajo1 instance;

 @Override
    public void onLoad() {
        this.getLogger().info("El plugin ha sido cargado");
    }
   
    @Override
    public void onEnable() {
            instance = this;
            File config = new File(getDataFolder()+File.separator+"config.yml");
            if (!config.exists()) {
                getConfig().options().copyDefaults(true);
                saveConfig();
            }
            this.getLogger().info("El plugin ha sido activado");
    }
   
    @Override
    public void onDisable() {
        this.getLogger().info("El plugin ha sido descargado");
    }
    public void registerCommands() {
        this.getCommand("chat").setExecutor(new Comando(this));
    }
    public void registerEvents() {
       getServer().getPluginManager().registerEvents(new ToggleChat(this), (this));
       getServer().getPluginManager().registerEvents(new SlowChat(this), (this));
    }
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
            return true;
    }
}