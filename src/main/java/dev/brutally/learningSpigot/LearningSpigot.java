package dev.brutally.learningSpigot;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class LearningSpigot extends JavaPlugin implements Listener {

	private final Map<String, String> lastPlayerMessages = new HashMap<>();

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		String message = event.getMessage();

		String lastMessage = this.lastPlayerMessages.get(playerName);

		if (lastMessage != null && lastMessage.equalsIgnoreCase(message)) {
			player.sendMessage(ChatColor.RED + "Please don't repeat messages.");
			event.setCancelled(true);
		} else
			this.lastPlayerMessages.put(playerName, message);
	}
}
