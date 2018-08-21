package me.rdl.cc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rdl.cc.Main;

public class CSay implements CommandExecutor {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public CSay(Main plugin) {
		plugin.getCommand("csay").setExecutor(this);
		System.out.println("");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Only the console/terminal can use this command!");
			return true;
		} else {
			
		}
		return true;
	}

}
