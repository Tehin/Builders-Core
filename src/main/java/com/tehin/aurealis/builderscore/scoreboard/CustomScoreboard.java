package com.tehin.aurealis.builderscore.scoreboard;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.scoreboard.score.CustomScore;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

public abstract class CustomScoreboard<T> {

    public abstract ArrayList<CustomScore> getScores(T clazz);

    public abstract void setUpScoreboard(T clazz);

    public Scoreboard parseBoard(ArrayList<CustomScore> scores) {
        String name = Core.getInstance().getConfig().getString("server.name");
        String ip = Core.getInstance().getConfig().getString("server.ip");
        String color = '&' + Core.getInstance().getConfig().getString("server.color");

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective(name, "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(Utils.toColor(color + "&l" + name));

        scores.add(new CustomScore("&k", "", 1, true));
        scores.add(new CustomScore("&4", ip, 0, true));
        addBasicEntries(scores, obj, color);

        return board;
    }

    public void updateScoreboard(T clazz) {
        setUpScoreboard(clazz);
    }

    private void addBasicEntries(ArrayList<CustomScore> entries, Objective obj, String color) {
        entries.forEach(e -> {
            if (e.isRemoveColons()) {
                obj.getScore(Utils.toColor(e.getPrefix() + color + e.getContent())).setScore(e.getPosition());
                return;
            }

            obj.getScore(Utils.toColor("&f" + e.getPrefix() + ": " + color + e.getContent())).setScore(e.getPosition());
        });
    }
}
