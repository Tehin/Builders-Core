package com.tehin.aurealis.builderscore.scoreboard;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.events.ScoreboardUpdateEvent;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.scoreboard.score.CustomScore;
import com.tehin.aurealis.builderscore.utils.Utils;

public class CustomScoreboardManager {
	
	public void setBoard(Project project) {
		/* */
		// 	&b&lAurealis
		//					   14
		// &fName: Aurealis	   13
		// &fDeadline: &b20/20 12
		// &fSize: &b300x300  11
		// &fClient: &nTehin  10
		//					  9
		// &bMembers (5):	  8
		// &8* &fTest1 &b(L)  7
		// &8* &fTest2		  6
		// &8* &fTest3	  	  5
		// &8* &fTest4		  4
		// &8* &fTest5		  3
		// &8* &fTest6		  2
		// 					  1
		// &bwww.builders.com 0
		/* */
		String name = Core.getInstance().getConfig().getString("server.name");
		String ip = Core.getInstance().getConfig().getString("server.ip");
		String color = '&' + Core.getInstance().getConfig().getString("server.color");
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective(name, "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(Utils.toColor(color + "&l" + name));
		
		ArrayList<CustomScore> scores = new ArrayList<>();
		
		scores.add(new CustomScore("&1", "", 14, true));
		scores.add(new CustomScore("Name", project.getName(), 13));
		scores.add(new CustomScore("Deadline", project.getDeadline(), 12));
		scores.add(new CustomScore("Size", project.getSize().getLength() + "&fx&b" + project.getSize().getWidth(), 11));
		scores.add(new CustomScore("Client", project.getClient(), 10));
		scores.add(new CustomScore("&2", "", 9, true));
		scores.add(new CustomScore("&bMembers", "&f" + project.getMembers().size(), 8));
		
		ArrayList<UUID> members = project.getMembers();
		
		// We are 2 slots forward because we will use 'i' for the position, and after the players, there are two positions.
		for (int i = 2; i < (members.size() + 2); i++) {
			int realPosition = i - 2;
			boolean isLeader = members.get(realPosition).equals(project.getLeader());
			String player = Bukkit.getOfflinePlayer(members.get(realPosition)).getName() + " " + (isLeader ? (color + "(L)") : "");
			
			scores.add(new CustomScore("&" + i + "&8 * ", "&f" + player, i, true));
		}
	
		scores.add(new CustomScore("&3", "", 1, true));
		scores.add(new CustomScore("&4", ip, 0, true));
		
		addBasicEntries(scores, obj, color);
		project.setBoard(board);
	}
	
	public void updateScoreboard(Project project) {
		setBoard(project);

		Bukkit.getPluginManager().callEvent(new ScoreboardUpdateEvent(project));
	}
	
	private void addBasicEntries(ArrayList<CustomScore> entries, Objective obj, String color) {
		entries.forEach(e -> {
			if (e.isRemovePoints()) {
				obj.getScore(Utils.toColor(e.getPrefix() + color + e.getContent())).setScore(e.getPosition());
				return;
			}
			
			obj.getScore(Utils.toColor("&f" + e.getPrefix() + ": " + color + e.getContent())).setScore(e.getPosition());
		});
	}

}
