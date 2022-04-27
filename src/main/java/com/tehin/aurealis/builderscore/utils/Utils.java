package com.tehin.aurealis.builderscore.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
	
	public static void sendNoPermission(@NotNull Player player) {
		player.sendMessage(toColor("&cNo permission."));
	}

	public static void sendMessage(@NotNull Player player, String msg) {
		player.sendMessage(toColor(msg));
	}
	public static void sendMessage(@NotNull Player player, List<String> msg) {
		msg.forEach(s -> {
			player.sendMessage(toColor(s));
		});
	}

	public static void sendMessageIfOnline(@NotNull List<UUID> targets, String msg) {
		for (UUID uuid : targets) {
			Player online = Bukkit.getPlayer(uuid);
			
			if  (online == null) continue;
			online.sendMessage(Utils.toColor(msg));
		}
	}
	
	/* Commands Utils */ 
	public static boolean verifyCommand(Player player, String permission, int argsLength, int commandLength, String usage) {
		if (player == null) return false;

		if (argsLength != commandLength) {
			player.sendMessage(Utils.toColor("&cUsage: " + usage));
			return false;
		}

		if (permission != null && !player.hasPermission(permission)) {
			Utils.sendNoPermission(player);
			return false;
		}
		
		return true;
	}
	
	/* Other Utils */
	public static boolean isCmdValid(String[] options, String cmd) {
		return Arrays.toString(options).contains(cmd);
	}
	
	
}
