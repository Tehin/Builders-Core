package com.tehin.aurealis.builderscore;

import com.tehin.aurealis.builderscore.commands.project.*;
import com.tehin.aurealis.builderscore.commands.spawn.SpawnCmd;
import com.tehin.aurealis.builderscore.scoreboard.scoreboards.ProjectScoreboard;
import com.tehin.aurealis.builderscore.scoreboard.scoreboards.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tehin.aurealis.builderscore.database.MongoDB;
import com.tehin.aurealis.builderscore.listeners.PlayerListener;
import com.tehin.aurealis.builderscore.listeners.ScoreboardListener;
import com.tehin.aurealis.builderscore.listeners.WorldListener;
import com.tehin.aurealis.builderscore.project.manager.ProjectsManager;
import com.tehin.aurealis.builderscore.spawn.manager.SpawnsManager;

public class Core extends JavaPlugin {
	
	private static Core instance;
	
	private MongoDB mongodb;

	private ProjectsManager projectsmanager;
	private ProjectScoreboard projectscoreboardmanager;
	private LobbyScoreboard spawnscoreboardmanager;
	private SpawnsManager spawnsmanager;

	@Override
	public void onEnable() {
		instance = this;
		
		this.mongodb = new MongoDB();
		this.projectsmanager = new ProjectsManager();
		this.projectscoreboardmanager = new ProjectScoreboard();
		this.spawnscoreboardmanager = new LobbyScoreboard();
		this.spawnsmanager = new SpawnsManager();

		// Set-Up
		loadCommands();
		loadConfigs();
		loadDatabase();
		loadListeners();
		getSpawnsManager().loadSpawns();
		getProjectsManager().loadProjects();
	}
	
	@Override
	public void onDisable() {
		//TODO: Save projects as a backup every certain time, (crashes doesn't call onDisable)
		getSpawnsManager().saveSpawns();
		getProjectsManager().saveProjects();
	}
	
	// Methods
	private void loadDatabase() {
		this.mongodb.connect(getConfig().getString("database.connection-uri"));
	}

	private void loadCommands() {
		getCommand("project").setExecutor(new ProjectCmd("project"));
		getCommand("spawn").setExecutor(new SpawnCmd("spawn"));
	}
	
	private void loadConfigs() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new WorldListener(), this);
		pm.registerEvents(new ScoreboardListener(), this);
	}
	
	// Instances
	public static Core getInstance() {
		return instance;
	}
	
	public MongoDB getDB() {
		return this.mongodb;
	}

	public ProjectsManager getProjectsManager() {
		return projectsmanager;
	}

	public ProjectScoreboard getProjectScoreboardManager() {
		return projectscoreboardmanager;
	}

	public LobbyScoreboard getSpawnScoreboardManager() {
		return spawnscoreboardmanager;
	}

	public SpawnsManager getSpawnsManager() {
		return spawnsmanager;
	}
}
