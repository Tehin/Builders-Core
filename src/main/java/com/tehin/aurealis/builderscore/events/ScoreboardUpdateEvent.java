package com.tehin.aurealis.builderscore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.tehin.aurealis.builderscore.project.Project;

public class ScoreboardUpdateEvent extends Event  {
	
	private Project project;
	
	public ScoreboardUpdateEvent(Project project) {
		this.project = project;
	}
	
	public Project getProject() {
		return this.project;
	}

	private static final HandlerList handlers = new HandlerList(); //This are the unimplemented methods.
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
