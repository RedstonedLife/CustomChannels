package me.rdl.cc.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.rdl.cc.Main;

public class ChatEvent implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getconfigs();
	
	public ChatEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		System.out.println("ChatEvent is now loaded!");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		// Checks if the channel is null if it is, It will just set it to regular, that will happened only on a File malfunction.
		if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel") != null) {
			Player sender = e.getPlayer();
			
			// If the channel is Staff
			if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel").equals("Staff")) {
				
				if(plugin.getConfig().getBoolean("Spyable")) {System.out.println("Staff Channel: "+sender.getName()+" >> " +e.getMessage());}

				
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					
					if(plugin.getplayers().getString(p.getName() + ".channel").equals("Staff")) {
						p.sendMessage(plugin.getconfigs().getString("StaffChatFormat").replace("&", "§").replace("{DISPLAYNAME}", sender.getName()).replace("{MESSAGE}", e.getMessage().replace("&", "§")));
					}
					
				}
				e.setCancelled(true);
			}
			// If channel is Regular (Normal chat)
			if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel").equals("Regular")) {
				return;
			}
			// If channel is Donators.
			if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel").equals("Donators")) {
				
				if(plugin.getConfig().getBoolean("Spyable")) {System.out.println("Donators Channel: "+sender.getName()+" >> " +e.getMessage());}
				
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(plugin.getplayers().getString(p.getName() + ".channel").equals("Donators")) {
						p.sendMessage(plugin.getconfigs().getString("DonatorChatFormat").replace("&", "§").replace("{DISPLAYNAME}", sender.getName()).replace("{MESSAGE}", e.getMessage().replace("&", "§")));
					}
					
				}
				e.setCancelled(true);
			}
			
			/////////////////////////////////////
			/////////////////////////////////////
			/////////////////////////////////////
			
			if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel").equals("Twitch")) {
				
				if(plugin.getConfig().getBoolean("Spyable")) {System.out.println("Streamers Channel: "+sender.getName()+" >> " +e.getMessage());}
				
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(plugin.getplayers().getString(p.getName() + ".channel").equals("Twitch")) {
						p.sendMessage(plugin.getconfigs().getString("TwitchChatFormat").replace("&", "§").replace("{DISPLAYNAME}", sender.getName()).replace("{MESSAGE}", e.getMessage().replace("&", "§")));
					}
					
				}
				e.setCancelled(true);
			}
			
			///////
			//////
			//////
			if(plugin.getplayers().getString(e.getPlayer().getName() + ".channel").equals("YouTube")) {
				
				if(plugin.getConfig().getBoolean("Spyable")) {System.out.println("YouTubers Channel: "+sender.getName()+" >> " +e.getMessage());}
				
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(plugin.getplayers().getString(p.getName() + ".channel").equals("YouTube")) {
						p.sendMessage(plugin.getconfigs().getString("YouTubeChatFormat").replace("&", "§").replace("{DISPLAYNAME}", sender.getName()).replace("{MESSAGE}", e.getMessage().replace("&", "§")));
					}
					
				}
				e.setCancelled(true);
			}
			
		} else {
			
		}
	}
	
}
