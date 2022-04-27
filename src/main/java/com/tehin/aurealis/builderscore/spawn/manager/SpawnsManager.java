package com.tehin.aurealis.builderscore.spawn.manager;

import java.util.HashMap;
import java.util.Set;

import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.spawn.Spawn;
import org.bukkit.entity.Player;

public class SpawnsManager {
	
	private HashMap<String, Spawn> spawns = new HashMap<>();
	
	public void loadSpawns() {
		FileConfiguration config = Core.getInstance().getConfig();
		
		ConfigurationSection section = config.getConfigurationSection("spawns");
		
		if (section == null || section.getKeys(false).size() == 0) {
			spawns.put("lobby", new Spawn("lobby", Bukkit.getServer().getWorld("world").getSpawnLocation()));
			return;
		}
		
		Set<String> list = section.getKeys(false);
		
		list.forEach(name -> {
			Spawn spawn = new Spawn(name.toLowerCase(), (Location) config.get("spawns." + name));
			spawns.put(name, spawn);
		});
		
	}
	
	public void saveSpawns() {
		FileConfiguration config = Core.getInstance().getConfig();
		
		spawns.forEach((name, spawn) -> {
			config.set("spawns." + name, spawn.getSpawn());
		});

		Core.getInstance().saveConfig();
	}
	
	public Spawn getSpawn(String name) {
		return spawns.get(name);
	}
	
	// Lobby
	public Spawn getLobby() {
		return spawns.getOrDefault("lobby", new Spawn("", null));
	}

	public void sendToSpawn(Player player) {
		Spawn lobby = getLobby();
		if (lobby == null) {
			Utils.sendMessage(player, "&cThe lobby spawn has not been set.");
			return;
		}

		Core.getInstance().getSpawnScoreboardManager().setUpForPlayer(player);
		lobby.tp(player);
	}
	
	public void setLobbySpawn(Spawn spawn) {
		spawns.put("lobby", spawn);
	}
	
	public boolean exists(String name) {
		return spawns.containsKey(name);
	}
	
	public void createSpawn(Spawn spawn) {
		spawns.put(spawn.getSpawnName().toLowerCase(), spawn);
	}
}
