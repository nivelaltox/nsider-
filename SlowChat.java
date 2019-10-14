package Trabajo1;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SlowChat implements Listener {
    private final Trabajo1 plugin;
    public SlowChat(Trabajo1 plugin) {
        this.plugin = plugin;
    }
    private final Map<String, Long> cooldownTime = new HashMap();
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
    public void onPlayerChat(PlayerChatEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("chatmanage.slow")) {
            return;
        }
        long now = System.currentTimeMillis();
        String name = p.getName();
        Long lastChat = (Long)cooldownTime.get(name);
        if (lastChat != null) {
            long earliestNext = lastChat.longValue() + this.plugin.getConfig().getInt("ChatManage.Slow") * 1000;
            if (now < earliestNext) {
                int timeRemaining = (int)((earliestNext - now) / 1000L) + 1;
                Utils.sendMessage(p, "Please wait " + timeRemaining + "more seconds" + (timeRemaining > 1 ? "s" : ""));
                event.setCancelled(true);
                return;
            }
        }
        cooldownTime.put(name, Long.valueOf(now));
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        cooldownTime.remove(p.getName());
    }
}
