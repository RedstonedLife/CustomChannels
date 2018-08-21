package me.rdl.cc.events;



import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.rdl.cc.Main;

public class JoinEvent implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public JoinEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		System.out.println("JoinEvent is now loaded!");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPlayedBefore() == false) {
			plugin.getplayers().set(p.getName() +".channel", "Regular");
			plugin.saveYamls();
			plugin.loadYamls();
			System.out.println(p.getName() + " Has been added to the players database!");
		}
		p.sendMessage(ChatColor.WHITE + "Current Channel: " + ChatColor.GRAY + "" + ChatColor.BOLD + plugin.getplayers().getString(e.getPlayer().getName() + ".channel"));
	}
	
	
	
}
