package com.tehin.aurealis.builderscore.project;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.size.Size;
import com.tehin.aurealis.builderscore.project.type.CustomWorldType;
import com.tehin.aurealis.builderscore.serialization.CustomSerializable;
import com.tehin.aurealis.builderscore.utils.Utils;

public class Project extends CustomSerializable<Project> implements Comparable<Project> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4125926047075261183L;

	
	private String 
	name,
	client,
	deadline;
	
	private Size size;
	private UUID leader;
	private boolean closed = false; // If it's closed, only the leader can teleport and modify.
	int priority;
	
	private ArrayList<UUID> members;
	
	private String worldName;
	private Location spawn;
	
	private transient Scoreboard board;

	CustomWorldType worldType;
	
	public Project(UUID leader, String client, String name, Size size, String deadline, int priority, CustomWorldType worldType) {
		this.name = name;
		this.worldName = name;
		this.client = client;
		this.size = size;
		this.leader = leader;
		this.deadline = deadline;
		this.priority = priority;
		this.worldType = worldType;
		
		this.members = new ArrayList<>();
		members.add(leader);
		
		Core.getInstance().getCustomScoreboardManager().setBoard(this);
	}
	
	// Methods
	public byte[] serialize() {
		return super.serializeClass(this);
	}
	
	public static Project deserialize(byte[] array) {
		CustomSerializable<Project> des = new CustomSerializable<>();
		return des.deserializeClass(array);
	}
	
	public void create() {
		Core.getInstance().getProjectsManager().generateProject(this);
	}
	
	public void teleport(Player player, boolean bypass) {
		if ((!bypass && !isMember(player) || this.closed)) {
			Utils.sendMessage(player, "&cYou are not member of this project or it's closed, please use /visit " + name);
			return;
		}
		
		player.setGameMode(GameMode.CREATIVE);
		player.teleport(getSpawn());
		player.setScoreboard(getBoard());

		if (bypass) {
			Utils.sendMessage(player, "&aTeleporting to " + getName() + " with bypass.");
			return;
		}
		
		Utils.sendMessage(player, "&aTeleporting to " + getName() + "...");
		
	}
	
	public void visit(Player player) {
		player.teleport(getSpawn());
		player.setGameMode(GameMode.SPECTATOR);
		player.sendMessage(Utils.toColor("&aYou are currently visiting " + name + " project."));
		
		player.setScoreboard(getBoard());
	}
	
	
	public boolean isMember(Player player) {
		return members.contains(player.getUniqueId());
	}

	public boolean isMember(UUID playerUuid) {
		return members.contains(playerUuid);
	}
	
	public void addPlayer(UUID uuid) {
		if (members.size() == 6) return;
		members.add(uuid);
		updateBoard();
	}
	
	public void removePlayer(UUID uuid) {
		members.remove(uuid);
		updateBoard();
	}
	
	// Compare to
	@Override
	public int compareTo(Project project) {
		int result = project.getPriority() - this.priority;
		
		return result;
	}
	
	// Setters & Getters
	public boolean isLeader(Player player) {
		return player.getUniqueId().equals(this.leader);
	}
	
	public Scoreboard getBoard() {
		return this.board;
	}
	
	public void setBoard(Scoreboard board) {
		this.board = board;
	}
	
	public void updateBoard() {
		Core.getInstance().getCustomScoreboardManager().updateScoreboard(this);
	}
	
	public Location getSpawn() {
		if (this.spawn == null) return getWorld().getSpawnLocation();
		return this.spawn;
	}
	
	public void setSpawn(Location loc) {
		this.spawn = loc;
		getWorld().setSpawnLocation((int) loc.getX(), (int) loc.getY(), (int) loc.getZ());
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public UUID getLeader() {
		return leader;
	}

	public void setLeader(UUID leader) {
		this.leader = leader;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public World getWorld() {
		return Bukkit.getServer().getWorld(this.worldName);
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public ArrayList<UUID> getMembers() {
		return members;
	}
	
	public ArrayList<Player> getOnlineMembers() {
		ArrayList<Player> online = new ArrayList<>();
		getMembers().forEach((uuid) -> {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) online.add(player);
		});
		
		return online;
	}

	public void setMembers(ArrayList<UUID> members) {
		this.members = members;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public String getDeadline() {
		return this.deadline;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CustomWorldType getWorldType() {
		return worldType;
	}

	public void setWorldType(CustomWorldType worldType) {
		this.worldType = worldType;
	}

}
