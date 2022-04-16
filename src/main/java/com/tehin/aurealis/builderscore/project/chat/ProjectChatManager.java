package com.tehin.aurealis.builderscore.project.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class ProjectChatManager {
	
	private HashMap<UUID, Project> pchats = new HashMap<>();
	
	public void toggleChat(Player player, Project project) {
		pchats.put(player.getUniqueId(), project);
	}
	
	public void sendPrivateMessage(Player player, String message) {
		Project project = pchats.get(player.getUniqueId());
		
		String parsedMessage = '&' + Core.getInstance().getConfig().getString("server.color") + "[Pj] " + player.getName() + ": " + message;
		
		Utils.sendMessageIfOnline(project.getMembers(), parsedMessage);
	}
	
	public void removePlayerFromChat(Player player) {
		pchats.remove(player.getUniqueId());
	}
	
	public boolean isOnChat(Player player) {
		return pchats.containsKey(player.getUniqueId());
	}
}
