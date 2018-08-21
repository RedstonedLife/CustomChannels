package me.rdl.cc.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.rdl.cc.Main;

public class CustomChannels implements CommandExecutor, Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	private FileConfiguration config = plugin.getConfig();
	// Private Ints
	public int i = 0;
	public int i1 = 0;
	public int i2 = 0;
	public int i3 = 0;
	public int i4 = 0;
	//
	
	
	public CustomChannels(Main plugin) {
		plugin.getCommand("cc").setExecutor(this);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		System.out.println("Loaded ChannelSelect Command!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		i=0;
		i1=0;
		i2=0;
		i3=0;
		i4=0;
		
			if(sender instanceof Player) {
				Player p = (Player) sender;
				// Create Inventory
				Inventory inv = Bukkit.createInventory(null, 9, "Select an Action!...");
				
				// Create ItemStacks
				ItemStack Info = new ItemStack(Material.BOOK);
				ItemStack Reload = new ItemStack(Material.POTION);
				ItemStack ChannelFormats = new ItemStack(Material.ENCHANTED_BOOK);
				ItemStack Close = new ItemStack(Material.BARRIER);
				ItemStack resetplayer = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				ItemMeta rpm = resetplayer.getItemMeta();
				rpm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Reset Players");
				rpm.setLore(Arrays.asList(ChatColor.WHITE + "Resets a given player channel to " + ChatColor.GRAY + "" + ChatColor.BOLD + "Regular"));
				SkullMeta rpm1 = (SkullMeta) resetplayer.getItemMeta();
				rpm1.setOwner("RedstonedLife");
				resetplayer.setItemMeta(rpm);
				
				// Create ItemMetas
				ItemMeta im = Info.getItemMeta();
				ItemMeta rm = Reload.getItemMeta();
				ItemMeta cfm = ChannelFormats.getItemMeta();
				ItemMeta cm = Close.getItemMeta();
				
				// Set ItemMetas
				// Info Meta
				
				im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Info");
				im.setLore(Arrays.asList(ChatColor.WHITE + "Displays Plugin Info!",ChatColor.GREEN + "" + ChatColor.BOLD + "No Permission Required!"));
				Info.setAmount(1);
				// Reload Meta
				if(!p.hasPermission("cc.reload")) {
					rm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload");
					rm.setLore(Arrays.asList(ChatColor.WHITE + "Reloads the plugin configs!",ChatColor.RED + "" + ChatColor.BOLD + "No permission " + ChatColor.RED + "(cc.reload)"));
				} else {
					rm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload");
					rm.setLore(Arrays.asList(ChatColor.WHITE + "Reloads the plugin configs!"));
					Reload.setAmount(1);
				}
				//Channel Formats Meta
				if(!p.hasPermission("cc.cf")) {
					cfm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats");
					cfm.setLore(Arrays.asList(ChatColor.WHITE + "Allows to select what channel format to see!",ChatColor.RED + "" + ChatColor.BOLD + "No permission " + ChatColor.RED + "(cc.cf)"));
				} else {
					cfm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats");
					cfm.setLore(Arrays.asList(ChatColor.WHITE + "Allows to select what channel format to see!"));
					ChannelFormats.setAmount(1);
				}
				// Close Meta
				cm.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Close");
				cm.setLore(Arrays.asList(ChatColor.WHITE + "Closes the menu!"));
				Close.setAmount(1);
				// Add Metas to ItemStacks
				Close.setItemMeta(cm);
				ChannelFormats.setItemMeta(cfm);
				Reload.setItemMeta(rm);
				Info.setItemMeta(im);
				// Add items to inv
				inv.addItem(Info);
				inv.addItem(Reload);
				inv.addItem(resetplayer);
				inv.setItem(7, ChannelFormats);
				inv.setItem(8, Close);
				
				
				// Open Inv
				p.openInventory(inv);
				
			}else {
			if(args.length==0) {
				sender.sendMessage(ChatColor.RED + "Usage: /cc <info,reload> OR /cc <channel | staff,donator> <format>");
				return true;
			} else {
				if(args.length==2) {
					if(args[0].equals("Staff") || args[0].equals("staff") && sender.hasPermission("cc.cf") || sender.hasPermission("cc.*")) {
						if(args[1].equals("format") || args[1].equals("Format")) {
							sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Staff" + ChatColor.WHITE + " Channel");
							sender.sendMessage(config.getString("StaffChatFormat").replace("&", "§"));
							return true;
						}
					}
					if(args[0].equals("donator") || args[0].equals("Donator") && sender.hasPermission("cc.cf") || sender.hasPermission("cc.*")) {
						if(args[1].equals("format") || args[1].equals("Format")) {
							sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donator" + ChatColor.WHITE + " Channel");
							sender.sendMessage(config.getString("DonatorChatFormat").replace("&", "§"));
							return true;
						}
					}
				}
				if(args.length == 1) {
					if(args[0].equals("info")) {
						sender.sendMessage(ChatColor.RED +""+ ChatColor.BOLD +"CustomChannels" + ChatColor.WHITE + " Info:");
						sender.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.RED + plugin.getDescription().getVersion());
						sender.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.RED + plugin.getDescription().getAuthors());
						sender.sendMessage(ChatColor.GRAY + "Channels: " + ChatColor.GREEN + "Staff (Operational)"+ChatColor.GRAY+","+ChatColor.GREEN + "Donators (Operational)");
						return true;
					}
					if(args[0].equals("reload") && sender.hasPermission("cc.reload") || sender.hasPermission("cc.*")) {
						sender.sendMessage(ChatColor.RED + "Saving all configs and databases!...");
						plugin.saveYamls();
						sender.sendMessage(ChatColor.GREEN + "All Configs and databases have been saved!");
						sender.sendMessage(ChatColor.RED + "Reloading all configs and databases...");
						plugin.loadYamls();
						sender.sendMessage(ChatColor.GREEN + "Reloaded all configs and databases!");
						return true;
					}
					
				}
			}
		
		} 
		return true;
	}

	
	// Events
	
	@EventHandler
	public void onClick(InventoryClickEvent event) { // Inventory events
		i=0;
		i1=0;
		i2=0;
		i3=0;
		i4=0;
		if(event.getSlot() == -999) {
			return;
		}
		if(event.getInventory().getItem(event.getSlot()) == null) {
			return;
		}
		if (event.getInventory().getName().equals("Select a Player!...")) {
			HumanEntity clicked = event.getWhoClicked();
			Player player = (Player) clicked;
			if((clicked instanceof Player)) {
				ItemStack item = event.getCurrentItem();
				ItemMeta meta = item.getItemMeta();
				//
				
				if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Back")) {
					player.closeInventory();
					///
					Inventory inv = Bukkit.createInventory(null, 9, "Select an Action!...");
					Player p = player;
					// Create ItemStacks
					ItemStack Info = new ItemStack(Material.BOOK);
					ItemStack Reload = new ItemStack(Material.POTION);
					ItemStack ChannelFormats = new ItemStack(Material.ENCHANTED_BOOK);
					ItemStack Close = new ItemStack(Material.BARRIER);
					ItemStack resetplayer = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					ItemMeta rpm = resetplayer.getItemMeta();
					rpm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Reset Players");
					rpm.setLore(Arrays.asList(ChatColor.WHITE + "Resets a given player channel to " + ChatColor.GRAY + "" + ChatColor.BOLD + "Regular"));
					SkullMeta rpm1 = (SkullMeta) resetplayer.getItemMeta();
					rpm1.setOwner("RedstonedLife");
					resetplayer.setItemMeta(rpm);
					
					// Create ItemMetas
					ItemMeta im = Info.getItemMeta();
					ItemMeta rm = Reload.getItemMeta();
					ItemMeta cfm = ChannelFormats.getItemMeta();
					ItemMeta cm = Close.getItemMeta();
					
					// Set ItemMetas
					// Info Meta
					
					im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Info");
					im.setLore(Arrays.asList(ChatColor.WHITE + "Displays Plugin Info!",ChatColor.GREEN + "" + ChatColor.BOLD + "No Permission Required!"));
					Info.setAmount(1);
					// Reload Meta
					if(!p.hasPermission("cc.reload")) {
						rm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload");
						rm.setLore(Arrays.asList(ChatColor.WHITE + "Reloads the plugin configs!",ChatColor.RED + "" + ChatColor.BOLD + "No permission " + ChatColor.RED + "(cc.reload)"));
					} else {
						rm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload");
						rm.setLore(Arrays.asList(ChatColor.WHITE + "Reloads the plugin configs!"));
						Reload.setAmount(1);
					}
					//Channel Formats Meta
					if(!p.hasPermission("cc.cf")) {
						cfm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats");
						cfm.setLore(Arrays.asList(ChatColor.WHITE + "Allows to select what channel format to see!",ChatColor.RED + "" + ChatColor.BOLD + "No permission " + ChatColor.RED + "(cc.cf)"));
					} else {
						cfm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats");
						cfm.setLore(Arrays.asList(ChatColor.WHITE + "Allows to select what channel format to see!"));
						ChannelFormats.setAmount(1);
					}
					// Close Meta
					cm.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Close");
					cm.setLore(Arrays.asList(ChatColor.WHITE + "Closes the menu!"));
					Close.setAmount(1);
					// Add Metas to ItemStacks
					Close.setItemMeta(cm);
					ChannelFormats.setItemMeta(cfm);
					Reload.setItemMeta(rm);
					Info.setItemMeta(im);
					// Add items to inv
					inv.addItem(Info);
					inv.addItem(Reload);
					inv.addItem(resetplayer);
					inv.setItem(7, ChannelFormats);
					inv.setItem(8, Close);
					
					
					// Open Inv
					p.openInventory(inv);
					///
					event.setCancelled(true);
				}
				//
				//
				//
				//
				if(item.getItemMeta().getDisplayName().equals(meta.getDisplayName())) {
					if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Back")) {return;};
					String p = meta.getDisplayName();
					plugin.getplayers().set(p + ".channel", "Regular");
					player.sendMessage(ChatColor.RED +""+ChatColor.BOLD+ p + " " + ChatColor.WHITE + "Channel resseted to " + ChatColor.GRAY + "" + ChatColor.BOLD + "Regular");
					player.closeInventory();
					event.setCancelled(true);
				}
			}
		}
		if (event.getInventory().getName().equals("Select an Action!...")) {
			
			HumanEntity clicked = event.getWhoClicked();
			Player player = (Player) clicked;
			if ((clicked instanceof Player)) {
				ItemStack item = event.getCurrentItem();
				@SuppressWarnings("unused")
				ItemMeta meta = item.getItemMeta();
				if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Info")) { // Plugin Info
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD +"CustomChannels" + ChatColor.WHITE + " Info:");
					player.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.RED + plugin.getDescription().getVersion());
					player.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.RED + plugin.getDescription().getAuthors());
					player.sendMessage(ChatColor.GRAY + "Channels: " + ChatColor.GREEN + "Staff (Operational)"+ChatColor.GRAY+","+ChatColor.GREEN + "Donators (Operational)");
					player.closeInventory();
					event.setCancelled(true);
				}
				if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload")) { // Plugin Reload
					player.sendMessage(ChatColor.RED + "Saving all configs and databases!...");
					plugin.saveYamls();
					player.sendMessage(ChatColor.GREEN + "All Configs and databases have been saved!");
					player.sendMessage(ChatColor.RED + "Reloading all configs and databases...");
					plugin.loadYamls();
					player.sendMessage(ChatColor.GREEN + "Reloaded all configs and databases!");
					player.closeInventory();
					event.setCancelled(true);
				}
				////
				////
				
				//
				//
				if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Reset Players")) {
					Inventory inv = Bukkit.createInventory(null, 36, "Select a Player!...");
					ItemStack back = new ItemStack(Material.BARRIER);
					ItemMeta bm = back.getItemMeta();
					bm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");
					bm.setLore(Arrays.asList(ChatColor.WHITE + "Will bring up back the control panel menu!"));
					back.setItemMeta(bm);
					inv.setItem(31, back);
					int k=0;
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short)3);
						ItemMeta hm = head.getItemMeta();
						SkullMeta hms = (SkullMeta) head.getItemMeta();
						////
						hm.setDisplayName(p.getDisplayName());
						hm.setLore(Arrays.asList(ChatColor.WHITE + "Channel: " + ChatColor.GRAY + plugin.getplayers().getString(p.getDisplayName() + ".channel")));
						hms.setOwner(p.getDisplayName());
						head.setItemMeta(hm);
						////
						inv.addItem(head);
						
						////
						k++;
					}
					player.closeInventory();
					player.openInventory(inv);
				}
				
				////
				////
				if (item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats")) { // Channel Formats
					player.closeInventory();
					event.setCancelled(true);
					// Create Inventory
					Inventory newInv = Bukkit.createInventory(null, 9, "Select a Channel to view!...");
					// Create Itemstacks
					ItemStack Staff = new ItemStack(Material.BOOK);
					ItemStack Donator = new ItemStack(Material.GOLD_INGOT);
					ItemStack Regular = new ItemStack(Material.PAPER);
					ItemStack Twitch = new ItemStack(Material.WOOL,1,(short) 10);
					ItemStack YouTube = new ItemStack(Material.WOOL,1,(short) 14);
					//
					ItemMeta tm = Twitch.getItemMeta();
					ItemMeta ym = YouTube.getItemMeta();
					//
					ym.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You" + ChatColor.WHITE + "" + ChatColor.BOLD + "Tube");
					tm.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Twitch");
					//
					ItemStack Close = new ItemStack(Material.BARRIER);
					ItemStack NullC = new ItemStack(Material.STAINED_GLASS_PANE,8);
					
					// Create Item metas
					ItemMeta sm = Staff.getItemMeta();
					ItemMeta dm = Donator.getItemMeta();
					ItemMeta cm = Close.getItemMeta();
					ItemMeta rm = Regular.getItemMeta();
					ItemMeta ncm = NullC.getItemMeta();
					
					// Set Item Metas
					ncm.setDisplayName("");
					//
					sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Staff");
					
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p1.getName() + ".channel").equals("Staff")) {
							i++;
						}
					}
					sm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.RED + i,ChatColor.WHITE + "Format: " + config.getString("StaffChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					//Donators
					dm.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donators");
					
					
					for(Player p2 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p2.getName() + ".channel").equals("Donators")) {
							i1++;
						}
					}
					dm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.YELLOW + i1,ChatColor.WHITE + "Format: " + config.getString("DonatorChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					////
					///
					////
					rm.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Regular");
					
					
					for(Player p3 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p3.getName() + ".channel").equals("Regular")) {
							i2++;
						}
					}
					rm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i2,ChatColor.WHITE + "Format: <{DISPLAYNAME}> {MESSAGE}",ChatColor.WHITE + "Clicking on the item will update the player count!"));
					//
					
					for(Player p5 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p5.getName() + ".channel").equals("Twitch")) {
							i3++;
						}
					}
					tm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i3,ChatColor.WHITE + "Format: " + config.getString("TwitchChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					
					//
					for(Player p6 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p6.getName() + ".channel").equals("YouTube")) {
							i4++;
						}
					}
					ym.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i4,ChatColor.WHITE + "Format: " + config.getString("YouTubeChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					//Close
					
					
					cm.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Back");
					cm.setLore(Arrays.asList(ChatColor.WHITE + "This will open back the other menu!"));
					//Add Metas to Items
					Staff.setItemMeta(sm);
					Donator.setItemMeta(dm);
					Close.setItemMeta(cm);
					Regular.setItemMeta(rm);
					YouTube.setItemMeta(ym);
					Twitch.setItemMeta(tm);
					// Add Items to inv
					newInv.addItem(Staff);
					newInv.addItem(Regular);
					newInv.addItem(Donator);
					newInv.addItem(YouTube);
					newInv.addItem(Twitch);
					
					newInv.setItem(8, Close);
					
					// Open new Inv
					player.openInventory(newInv);
					
				}
			
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Close")) {
					player.closeInventory();
					
					
					event.setCancelled(true);
				}
				// Channel Formats Inv if statements
				
				
			}
			
		
		} else if (event.getInventory().getName().equals("Select a Channel to view!...")) {
			HumanEntity clicked = event.getWhoClicked();
			Player player = (Player) clicked;
			
			if ((clicked instanceof Player)) {
				ItemStack item = event.getCurrentItem();
				@SuppressWarnings("unused")
				ItemMeta meta = item.getItemMeta();
				//
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Staff")) {
					i=0;
					i1=0;
					i2=0;
					i3=0;
					i4=0;
					ItemMeta sm = item.getItemMeta();
					
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p1.getName() + ".channel").equals("Staff")) {
							i++;
						}
					}
					sm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.RED + i,ChatColor.WHITE + "Format: " + config.getString("StaffChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					
					event.setCancelled(true);
				}
				
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Donators")) {
					i=0;
					i1=0;
					i2=0;
					i3=0;
					i4=0;
					ItemMeta dm = item.getItemMeta();
					
					for(Player p2 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p2.getName() + ".channel").equals("Donators")) {
							i1++;
						}
					}
					dm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.YELLOW + i1,ChatColor.WHITE + "Format: " + config.getString("DonatorChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					
					event.setCancelled(true);
				}
				
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Regular")) {
					i=0;
					i1=0;
					i2=0;
					i3=0;
					i4=0;
					ItemMeta rm = item.getItemMeta();
					for(Player p4 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p4.getName() + ".channel").equals("Regular")) {
							i2++;
						}
					}
					rm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i2,ChatColor.WHITE + "Format: <{DISPLAYNAME}> {MESSAGE}",ChatColor.WHITE + "Clicking on the item will update the player count!"));
					
					event.setCancelled(true);
				}
				
				//
				//
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Twitch")) {
					i=0;
					i1=0;
					i2=0;
					i3=0;
					i4=0;
					ItemMeta tm = item.getItemMeta();
					for(Player p5 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p5.getName() + ".channel").equals("Twitch")) {
							i3++;
						}
					}
					tm.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i3,ChatColor.WHITE + "Format: " + config.getString("TwitchChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					event.setCancelled(true);
				}
				//
				//
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You" + ChatColor.WHITE + "" + ChatColor.BOLD + "Tube")) {
					i=0;
					i1=0;
					i2=0;
					i3=0;
					i4=0;
					ItemMeta ym = item.getItemMeta();
					for(Player p6 : Bukkit.getOnlinePlayers()) {
						if(plugin.getplayers().getString(p6.getName() + ".channel").equals("YouTube")) {
							i4++;
						}
					}
					ym.setLore(Arrays.asList(ChatColor.WHITE + "Current Players on this channel: " + ChatColor.GRAY + i4,ChatColor.WHITE + "Format: " + config.getString("YouTubeChatFormat").replace("&", "§"),ChatColor.WHITE + "Clicking on the item will update the player count!"));
					event.setCancelled(true);
				}
				//
				//
				
				
				// Back Button
				
				
				
				
				
				//
				
				if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Back")) {
					Inventory inv = Bukkit.createInventory(null, 9, "Select an Action!...");
					
					// Create ItemStacks
					ItemStack Info = new ItemStack(Material.BOOK);
					ItemStack Reload = new ItemStack(Material.POTION);
					ItemStack ChannelFormats = new ItemStack(Material.ENCHANTED_BOOK);
					ItemStack Close = new ItemStack(Material.BARRIER);
					//
					
					ItemStack resetplayer = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					ItemMeta rpm = resetplayer.getItemMeta();
					rpm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Reset Players");
					rpm.setLore(Arrays.asList(ChatColor.WHITE + "Resets a given player channel to " + ChatColor.GRAY + "" + ChatColor.BOLD + "Regular"));
					SkullMeta rpm1 = (SkullMeta) resetplayer.getItemMeta();
					rpm1.setOwner("RedstonedLife");
					resetplayer.setItemMeta(rpm);
					
					//
					
					// Create ItemMetas
					ItemMeta im = Info.getItemMeta();
					ItemMeta rm = Reload.getItemMeta();
					ItemMeta cfm = ChannelFormats.getItemMeta();
					ItemMeta cm = Close.getItemMeta();
					
					// Set ItemMetas
					// Info Meta
					im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Info");
					im.setLore(Arrays.asList(ChatColor.WHITE + "Displays Plugin Info!"));
					Info.setAmount(1);
					// Reload Meta
					rm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Reload");
					rm.setLore(Arrays.asList(ChatColor.WHITE + "Reloads the plugin configs!"));
					Reload.setAmount(1);
					//Channel Formats Meta
					cfm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD  + "Channel Formats");
					cfm.setLore(Arrays.asList(ChatColor.WHITE + "Allows to select what channel format to see!"));
					ChannelFormats.setAmount(1);
					// Close Meta
					cm.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Close");
					cm.setLore(Arrays.asList(ChatColor.WHITE + "Closes the menu!"));
					Close.setAmount(1);
					// Add Metas to ItemStacks
					Close.setItemMeta(cm);
					ChannelFormats.setItemMeta(cfm);
					Reload.setItemMeta(rm);
					Info.setItemMeta(im);
					// Add items to inv
					inv.addItem(Info);
					inv.addItem(Reload);
					inv.addItem(resetplayer);
					inv.setItem(7, ChannelFormats);
					inv.setItem(8, Close);
					
					
					// Open Inv
					player.closeInventory();
					player.openInventory(inv);
					
					event.setCancelled(true);
				}
			}
		}
	}

}
