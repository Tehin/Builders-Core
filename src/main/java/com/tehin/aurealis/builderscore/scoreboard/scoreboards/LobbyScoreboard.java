package com.tehin.aurealis.builderscore.scoreboard.scoreboards;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.rol.Rol;
import com.tehin.aurealis.builderscore.scoreboard.CustomScoreboard;
import com.tehin.aurealis.builderscore.scoreboard.score.CustomScore;
import com.tehin.aurealis.builderscore.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyScoreboard extends CustomScoreboard<Spawn> {

    public ArrayList<CustomScore> getScores(Player player) {
        String color = '&' + Core.getInstance().getConfig().getString("server.color");

        ArrayList<CustomScore> scores = new ArrayList<>();

        List<Project> projects = Core.getInstance().getProjectsManager().organizeByPriority(5);
        Rol rol = Rol.getRolByPlayer(player);

        int boardSize = 15;

        scores.add(new CustomScore("&1", "", boardSize--, true));
        scores.add(new CustomScore("Name", player.getName(), boardSize--));
        scores.add(new CustomScore("Rol", rol.getName(), boardSize--));

        scores.add(new CustomScore("&2", "", boardSize--, true));
        scores.add(new CustomScore("Online", Integer.toString(Bukkit.getServer().getOnlinePlayers().size()), boardSize--));
        scores.add(new CustomScore("Current Projects", Integer.toString(Core.getInstance().getProjectsManager().getSize()), boardSize--));

        if (!rol.equals(Rol.VISITOR)) {
            scores.add(new CustomScore("&a", "", boardSize--, true));
            scores.add(new CustomScore(color + "Projects", "", boardSize--));

            for (Project project : projects) {
                scores.add(new CustomScore("&8 * ", "&f" + project.getName() + " &7(" + color + project.getPriority() + "&7)", boardSize--, true));
            }

        }


        return scores;
    }

    public void setUpForPlayer(Player player) {
        player.setScoreboard(parseBoard(getScores(player)));
    }

    @Override
    public ArrayList<CustomScore> getScores(Spawn clazz) {
        return null;
    }

    @Override
    public void setUpScoreboard(Spawn spawn) {
    }

}
