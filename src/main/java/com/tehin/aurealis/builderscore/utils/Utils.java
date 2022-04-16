package com.tehin.aurealis.builderscore.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	
	/* Document Utils */
	public static Document updateDoc(Document document, String path, Object toInsert) {
		Document set = new Document("$set", document);
		set.append("$set", new Document(path, toInsert));

		return set;
	}
	
	/* Messages Utils */
	public static String toColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static void sendConsole(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(toColor(msg));
	}
	
	public static void sendNoPermission(Player player) {
		player.sendMessage(toColor("&cNo permission."));
	}
	
	public static void sendMessage(Player player, String msg) {
		player.sendMessage(toColor(msg));
	}
	
	public static void sendMessageIfOnline(List<UUID> targets, String msg) {
		for (UUID uuid : targets) {
			Player online = Bukkit.getPlayer(uuid);
			
			if  (online == null) continue;
			online.sendMessage(Utils.toColor(msg));
		}
	}
	
	/* Commands Utils */ 
	public static boolean verifyCommand(CommandSender sender, String permission, String[] args, int length, String usage) {
		if (!(sender instanceof Player)) return false;
		
		Player player = (Player) sender;
		
		if (permission != null && !player.hasPermission("builders.spawn.config")) {
			Utils.sendNoPermission(player);
			return false;
		}
		
		if (args.length != length) {
			player.sendMessage(Utils.toColor("&cUsage: " + usage));
			return false;
		}
		
		return true;
	}
	
	/* Other Utils */
	public static boolean isValid(String[] options, String value) {
		return Arrays.toString(options).contains(value);
	}
	
	
}
