package me.rdl.cc.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.rdl.cc.Main;

public class ChannelSelect implements CommandExecutor,Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public ChannelSelect(Main plugin) {
		plugin.getCommand("cs").setExecutor(this);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		System.out.println("Loaded ChannelSelect Command!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length == 1) {
				if(args[0].equals("YouTube") || args[0].equals("yt")) {
					plugin.getplayers().set("Terminal.channel", "YouTube");
					//
					plugin.saveYamls();
					plugin.loadYamls();
					//
					sender.sendMessage(ChatColor.RED +""+ ChatColor.BOLD +  "You" +ChatColor.WHITE+""+ChatColor.BOLD+"Tube "+ ChatColor.WHITE + "Channel Has been selected!");
					return true;

				}
				if(args[0].equals("Twitch") || args[0].equals("tv")) {
					plugin.getplayers().set("Terminal.channel", "Twitch");
					//
					plugin.saveYamls();
					plugin.loadYamls();
					//
					sender.sendMessage(ChatColor.DARK_PURPLE +""+ ChatColor.BOLD +  "Twitch " + ChatColor.WHITE + "Channel Has been selected!");
					return true;

				}
				if(args[0].equals("Staff") || args[0].equals("staff")) {
					plugin.getplayers().set("Terminal.channel", "Staff");
					//
					plugin.saveYamls();
					plugin.loadYamls();
					//
					sender.sendMessage(ChatColor.RED +""+ ChatColor.BOLD +  "Staff " + ChatColor.WHITE + "Channel Has been selected!");
					return true;

				}
				if(args[0].equals("Regular") || args[0].equals("reg")) {
					plugin.getplayers().set("Terminal.channel", "Regular");
					//
					plugin.saveYamls();
					plugin.loadYamls();
					//
					sender.sendMessage(ChatColor.GRAY +""+ ChatColor.BOLD + "Regular " + ChatColor.WHITE + "Channel Has been selected!");
					return true;

				}
				if(args[0].equals("Donator") || args[0].equals("don")) {
					plugin.getplayers().set("Terminal.channel", "Donators");
					//
					plugin.saveYamls();
					plugin.loadYamls();
					//
					sender.sendMessage(ChatColor.YELLOW +""+ ChatColor.BOLD +  "Donator " + ChatColor.WHITE + "Channel Has been selected!");
					return true;
				}
				
			}
			return true;
		}
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 9, "Channels");
		
		ItemStack C = new ItemStack(Material.BOOK);
		ItemMeta CM = C.getItemMeta();
		CM.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Staff");
		// Lore and Perm
		if(plugin.getConfig().getBoolean("StaffChatPermission")) {
			if(!p.hasPermission("cc.channel.staff")) {
				CM.setLore(Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "No Permission! " + ChatColor.RED + "(cc.channel.staff)"));
			}
		}
		C.setItemMeta(CM);
		
		ItemStack S = new ItemStack(Material.PAPER);
		ItemMeta SM = S.getItemMeta();
		SM.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Regular");
		// Lore and Perm
		S.setItemMeta(SM);
		
		ItemStack A = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta AM = A.getItemMeta();
		AM.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donators");
		// Lore and Perm
		if(plugin.getConfig().getBoolean("DonatorChatPermission")) {
			if(!p.hasPermission("cc.channel.donators")) {
				AM.setLore(Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "No Permission! " + ChatColor.RED + "(cc.channel.staff)"));
			}
		}
		A.setItemMeta(AM);
		
		ItemStack CurrentC = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta CCM = CurrentC.getItemMeta();
		CCM.setDisplayName("Current Channel");
		//Set Lore according to the channel (IF statements)
		if(plugin.getplayers().getString(p.getName() + ".channel") != null) {
			
			
			// If the channel is Staff
			if(plugin.getplayers().getString(p.getName() + ".channel").equals("Staff")) {
				CCM.setLore(Arrays.asList(ChatColor.WHITE + "Current Channel: " + ChatColor.RED + "Staff"));
			}
			// If channel is Regular (Normal chat)
			if(plugin.getplayers().getString(p.getName() + ".channel").equals("Regular")) {
				CCM.setLore(Arrays.asList(ChatColor.WHITE + "Current Channel: Regular"));
			}
			// If channel is Donators.
			if(plugin.getplayers().getString(p.getName() + ".channel").equals("Donators")) {
				CCM.setLore(Arrays.asList(ChatColor.WHITE + "Current Channel: " + ChatColor.YELLOW + "Donators"));
			}
			// YouTube
			if(plugin.getplayers().getString(p.getName() + ".channel").equals("YouTube")) {
				CCM.setLore(Arrays.asList(ChatColor.WHITE + "Current Channel: " + ChatColor.DARK_RED + "YouTube"));
			}
			// Twitch
			if(plugin.getplayers().getString(p.getName() + ".channel").equals("Twitch")) {
				CCM.setLore(Arrays.asList(ChatColor.WHITE + "Current Channel: " + ChatColor.DARK_PURPLE + "Twitch"));
			}
		}
		CurrentC.setItemMeta(CCM);
		
		ItemStack Close = new ItemStack(Material.BARRIER);
		ItemMeta CloseM = Close.getItemMeta();
		CloseM.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close");
		Close.setItemMeta(CloseM);
		/////////////////
		////////////////
		////////////////
		ItemStack Twitch = new ItemStack(Material.WOOL,1,(short) 10);
		ItemStack YouTube = new ItemStack(Material.WOOL,1,(short) 14);
		
		ItemMeta tm = Twitch.getItemMeta();
		ItemMeta ym = YouTube.getItemMeta();
		//
		ym.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You" + ChatColor.WHITE + "" + ChatColor.BOLD + "Tube");
		if(plugin.getConfig().getBoolean("YouTubeChatPermission")) {
			if(!p.hasPermission("cc.channel.youtube")) {
				ym.setLore(Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "No permission! " + ChatColor.RED + "(cc.channel.youtube)"));
			}
		
		}
		
		tm.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Twitch");
		if(plugin.getConfig().getBoolean("TwitchChatPermission")) {
			if(!p.hasPermission("cc.channel.twitch")) {
				tm.setLore(Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "No permission! " + ChatColor.RED + "(cc.channel.twitch)"));
			}
		}
		//
		Twitch.setItemMeta(tm);
		YouTube.setItemMeta(ym);
		
		////////////////
		///////////////
		////////////////
		inv.addItem(A);
		inv.addItem(S);
		inv.addItem(C);
		inv.addItem(Twitch);
		inv.addItem(YouTube);
		inv.setItem(7,CurrentC);
		inv.setItem(8,Close);
		p.openInventory(inv);
		//plugin.getplayers().set(p.getName() +".channel", "Staff");
		//plugin.getplayers().set(p.getName() +".channel", "Regular");
		//plugin.getplayers().set(p.getName() +".channel", "Donators");
		//plugin.saveYamls();
		//plugin.loadYamls();
		return true;
	}
	
	// Events
	
	@EventHandler
	public void onClick(InventoryClickEvent event) { // Inventory events
		if(event.getSlot() == -999) {
			return;
		}
		if(event.getInventory().getItem(event.getSlot()) == null) {
			return;
		}
		if (event.getInventory().getName().equals("Channels")) {
			HumanEntity clicked = event.getWhoClicked();
			Player player = (Player) clicked;
			if ((clicked instanceof Player)) {
				ItemStack item = event.getCurrentItem();
				@SuppressWarnings("unused")
				ItemMeta meta = item.getItemMeta();
				if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Staff")) {
					if(!player.hasPermission("cc.channel.staff")) {
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "Insufficient Permissions.");
						event.setCancelled(true);
					} else {
						plugin.getplayers().set(player.getName() +".channel", "Staff");
						plugin.saveYamls();
						plugin.loadYamls();
						player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Staff" + ChatColor.WHITE + " Channel Selected!");
						player.closeInventory();
						event.setCancelled(true);
					}
				}
				// Twitch
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Twitch")) {
					if(!player.hasPermission("cc.channel.twitch")) {
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "Insufficient Permissions.");
						event.setCancelled(true);
					} else {
						plugin.getplayers().set(player.getName() +".channel", "Twitch");
						plugin.saveYamls();
						plugin.loadYamls();
						player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Twitch" + ChatColor.WHITE + " Channel Selected!");
						player.closeInventory();
						event.setCancelled(true);
					}
				}
				// YouTube
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You" + ChatColor.WHITE + "" + ChatColor.BOLD + "Tube")) {
					if(!player.hasPermission("cc.channel.youtube")) {
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "Insufficient Permissions.");
						event.setCancelled(true);
					} else {
						plugin.getplayers().set(player.getName() +".channel", "YouTube");
						plugin.saveYamls();
						plugin.loadYamls();
						player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You" + ChatColor.WHITE + "" + ChatColor.BOLD + "Tube" + ChatColor.WHITE + " Channel Selected!");
						player.closeInventory();
						event.setCancelled(true);
					}
				}
				//
				if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "" + ChatColor.BOLD + "Regular")) {
					plugin.getplayers().set(player.getName() +".channel", "Regular");
					plugin.saveYamls();
					plugin.loadYamls();
					player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Regular" + ChatColor.WHITE + " Channel Selected!");
					player.closeInventory();
					event.setCancelled(true);
				}
				if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donators")) {
					if(!player.hasPermission("cc.channel.donators")) {
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "Insufficient Permissions.");
						event.setCancelled(true);
					} else {
						plugin.getplayers().set(player.getName() +".channel", "Donators");
						plugin.saveYamls();
						plugin.loadYamls();
						player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donators" + ChatColor.WHITE + " Channel Selected!");
						player.closeInventory();
						event.setCancelled(true);
					}
				}
				
				if (item.getItemMeta().getDisplayName().equals("Current Channel")) {
					
					event.setCancelled(true);
				}
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Close")) {
					player.closeInventory();
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Inventory Closed!.");
					event.setCancelled(true);
				}
				
			}
			
		}
	}

}
