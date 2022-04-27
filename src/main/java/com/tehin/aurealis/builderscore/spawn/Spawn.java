package com.tehin.aurealis.builderscore.spawn;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Spawn {
	
	private String name;
	private Location loc;

	public Spawn(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}
	
	// Methods
	public void tp(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.teleport(getSpawn());
		Utils.sendMessage(player, "&aSuccessfully teleported to " + getSpawnName() + " spawn.");
	}
	
	public void create() {
		Core.getInstance().getSpawnsManager().createSpawn(this);
	}

	// Getters
	public Location getSpawn() {
		return this.loc;
	}


	public String getSpawnName() {
		return this.name;
	}
	
	public String getWorldName() {
		return this.loc.getWorld().getName();
	}
	
	// Setters
	public void setSpawn(Location loc) {
		this.loc = loc;
	}

}
