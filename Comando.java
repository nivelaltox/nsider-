package Trabajo1;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
   
    private final Trabajo1 plugin;
    
    public Comando(Trabajo1 instance) {
        plugin = instance;
    }
    public static boolean muted = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                Player p = (Player) sender;
	  if (cmd.getName().equalsIgnoreCase("chat")) {
		  // Do Stuff
		  if (args.length == 0) {
			  if (p.hasPermission("ChatManage.help")) {
				  Utils.sendMessage(p, ChatColor.RED+"Commands:");
				  Utils.sendMessage(p, ChatColor.GOLD+"/chat");
				  Utils.sendMessage(p, ChatColor.GOLD+"/chat clear");
				  Utils.sendMessage(p, ChatColor.GOLD+"/chat toggle");
				  Utils.sendMessage(p, ChatColor.GOLD+"/chat slow");
				  return true;
			  }
			  else {
				  Utils.sendMessage(p, ChatColor.RED+"You do not have enough permission to perform this command.");
			  }
		  }
		  if ((args.length == 1) && (args[0].equalsIgnoreCase("clear"))) {
			  if (p.hasPermission("ChatManage.clearchat")) { 
				  for (int x = 0; x < 100; x++){
                   Utils.broadcastMessage("");
			  }
			  Utils.broadcastMessage(ChatColor.GRAY+"Chat has been cleared by &c"+ ChatColor.RED + p.getName()); 
		  }
		  else {
			  Utils.sendMessage(p, ChatColor.RED+"You do not have enough permission to perform this command.");
		  }
	  }
		  if ((args.length == 1) && ((args[0].equalsIgnoreCase("toggle")))) {
			  if (!p.hasPermission("ChatManage.togglechat")) {
	            	Utils.sendMessage(p, ChatColor.RED+"You do not have enough permission to perform this command.");
   	      }
		  if ((!muted) && (p.hasPermission("ChatManage.togglechat"))) {
			  muted = true;
			  Utils.broadcastMessage(ChatColor.GRAY+"Chat has been Locked by "+ChatColor.RED+p.getName());
		  }
		  else if ((muted) && (p.hasPermission("ChatManage.togglechat"))) {
			  muted = false; 
			  Utils.broadcastMessage(ChatColor.GRAY+"Chat has been Unlocked by "+ChatColor.RED + p.getName());
		  }
	  }
	  if ((args.length == 1) && (args[0].equalsIgnoreCase("slow"))) {
		  if (!p.hasPermission("ChatManage.slowchat")) {
			  Utils.sendMessage(p, ChatColor.RED+"You do not have enough permission to perform this command.");
			  return true; 
		  }
		  Utils.sendMessage(p, ChatColor.RED+"Chat Slow: &c"+ChatColor.GRAY + String.valueOf(this.plugin.getConfig().getInt("ChatManage.Slow")) + ChatColor.RED+" second" + (this.plugin.getConfig().getInt("ChatManage.Slow") == 1 ? "" : ChatColor.RED+"s"));
		  return true;
	  }
	  if ((args.length == 2) && (p.hasPermission("ChatManage.slowchat"))) {
		  if (!p.hasPermission("ChatManage.slowchat")) {
			  Utils.sendMessage(p, ChatColor.RED+"You do not have enough permission to perform this command."); 
			  return true; 
		  }
		  try {
			  int interval = Integer.parseInt(args[1]); 
			  this.plugin.getConfig().set("ChatManage.Slow", Integer.valueOf(interval)); 
			  this.plugin.saveConfig();
              Utils.broadcastMessage(ChatColor.GRAY+"Chat has been slowed for "+ ChatColor.RED + args[1] + ChatColor.GRAY+" second" + (interval == 1 ? "" : ChatColor.GRAY+"s"));
            }
                catch (NumberFormatException e)
                {
	            	Utils.sendMessage(p, ""+ ChatColor.RED + args[1] + ChatColor.GRAY +"is not a valid number.");
                }
             }
}
	return true;
}
}