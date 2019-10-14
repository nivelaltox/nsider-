package Trabajo1;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
	/*
	 * Utils go here, Simple utils, Messages, Logging, etc...
	 */
	public static void sendMessage(Player p, String msg) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg)); // Simple //
	}
	public static void broadcastMessage(String msg) {
		// This gets a little tricky
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
	public static void log(Level level, String msg) {
		// You can either print it or use the getServer method for colors.
		System.out.println(msg);
	}
}
