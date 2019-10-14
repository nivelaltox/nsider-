package Trabajo1;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ToggleChat implements Listener {
    private final Trabajo1 plugin;
    public ToggleChat(Trabajo1 plugin) {
        this.plugin = plugin;
    }
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (Comando.muted) {
            if (!p.hasPermission("ChatManage.togglechat")) {
                event.setCancelled(true);
                Utils.sendMessage(p, "Chat is locked temporarily");
            }
            else if (p.hasPermission("ChatManage.togglechat")) {
                event.setCancelled(false);
            }
        }
        else if (Comando.muted) {}
    }
}
