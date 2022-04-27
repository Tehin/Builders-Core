package com.tehin.aurealis.builderscore.scoreboard.scoreboards;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.events.ScoreboardUpdateEvent;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.scoreboard.CustomScoreboard;
import com.tehin.aurealis.builderscore.scoreboard.score.CustomScore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectScoreboard extends CustomScoreboard<Project> {

    @Override
    public ArrayList<CustomScore> getScores(Project project) {
        String color = '&' + Core.getInstance().getConfig().getString("server.color");

        ArrayList<CustomScore> scores = new ArrayList<>();

        scores.add(new CustomScore("&1", "", 14, true));
        scores.add(new CustomScore("Name", project.getName(), 13));
        scores.add(new CustomScore("Deadline", project.getDeadline(), 12));
        scores.add(new CustomScore("Size", project.getSize().getLength() + "&fx&b" + project.getSize().getWidth(), 11));
        scores.add(new CustomScore("Client", project.getClient(), 10));
        scores.add(new CustomScore("&2", "", 9, true));
        scores.add(new CustomScore("&bMembers", "&f" + project.getMembers().size(), 8));

        ArrayList<UUID> members = project.getMembers();

        // TODO: Test new project scoreboard system
        int ascendantPosition = 3;
        for (UUID memberUuid : members) {
            boolean isLeader = memberUuid.equals(project.getLeader());
            String player = Bukkit.getOfflinePlayer(memberUuid).getName() + " " + (isLeader ? (color + "(L)") : "");

            // Shouldn't happen but I am afraid.
            if (ascendantPosition == 2) continue;

            scores.add(new CustomScore("&" + ascendantPosition + "&8 * ", "&f" + player, ascendantPosition, true));
        }

        return scores;
    }

    @Override
    public void setUpScoreboard(Project project) {
        project.setBoard(parseBoard(getScores(project)));
        Bukkit.getPluginManager().callEvent(new ScoreboardUpdateEvent(project.getMembersAtProject(), project.getBoard()));
    }

}
