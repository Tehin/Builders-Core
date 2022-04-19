package com.tehin.aurealis.builderscore.project.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.bson.types.Binary;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.mongodb.client.FindIterable;
import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class ProjectsManager {
	
	public static HashMap<String, Project> projects = new HashMap<>();
	
	
	// Projects names in the HashMap are saved as lower case, but we need the actual name.
	public void saveProjects() {
		Core.getInstance().getDB().getProjects().drop();
		if (projects.size() == 0) {
			Utils.sendConsole("[BuildersCore] &cThere are no projects to save.");
			return;
		}
		
		List<Document> docs = new ArrayList<>();

		projects.forEach((lowerCaseName, project) -> {
			Document doc = new Document("Name", project.getName());  
			doc.put("Class", project.getClass().getName());
			doc.put("Bytes", project.serialize());
			docs.add(doc);
		});
		
		Utils.sendConsole("[BuildersCore] &aSuccessfully saved " + projects.size() + " projects.");
		Core.getInstance().getDB().getProjects().insertMany(docs);
	}
	
	public void loadProjects() {
		FindIterable<Document> documents = Core.getInstance().getDB().getProjects().find();
		
		long length = StreamSupport.stream(documents.spliterator(), false).count();

		if (length == 0) {
			Utils.sendConsole("[BuildersCore] &cThere are no projects to load.");
			return;
		}

		for (Document doc : documents) {
			Binary b = (Binary) doc.get("Bytes");
			Project project = Project.deserialize(b.getData());
			project.updateBoard();
			
			projects.put(project.getName().toLowerCase(), project);
		}
		
		Utils.sendConsole("[BuildersCore] &aSuccessfully loaded " + projects.size() + " projects.");
	}
	
	public void generateProject(Project project) {
		// Create the world
		WorldCreator wc = new WorldCreator(project.getWorldName());
		
		wc.type(WorldType.FLAT);
		wc.generatorSettings(project.getWorldType().getGeneratorSettings());
		
		World world = wc.createWorld();
		world.setPVP(false);
		world.setAnimalSpawnLimit(0);
		world.setAutoSave(true);
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setSpawnLocation(0, 51, 0);
			
		Location center = new Location(world, 0, 50, 0);
		center.getBlock().setType(Material.BEDROCK);

		projects.put(project.getName().toLowerCase(), project);
	}
	
	public Project getProjectByName(String name) {
		return projects.get(name.toLowerCase());
	}
	
	public Project getProjectByPlayerLoc(Player player) {
		return getProjectByName(player.getLocation().getWorld().getName());
	}
	
	public boolean exists(String name) {
		return projects.containsKey(name.toLowerCase());
	}
	
	public boolean isAtProject(Player player) {
		Project project = getProjectByPlayerLoc(player);
		
		return project != null;
	}
	
	public ArrayList<Project> organizeByPriority() {
		ArrayList<Project> sorted = new ArrayList<Project>(projects.values());
		Collections.sort(sorted);
		
		return sorted;
	}

}
