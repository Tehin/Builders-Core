package com.tehin.aurealis.builderscore.commands.project;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.commands.CoreCommand;
import com.tehin.aurealis.builderscore.commands.project.sub.*;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.entity.Player;

public class ProjectCmd extends CoreCommand {

    public ProjectCmd(String name) {
        super(name);
    }

    public boolean sendCmd(Player player, String cmd, String[] subCmdArgs) {
        boolean requiresProject = Utils.isCmdValid(new String[]{"chat", "removep", "addp"}, cmd); // Checks if the command requires a project
        Project project = Core.getInstance().getProjectsManager().getProjectByPlayerLoc(player); // If needed

        if (requiresProject && project == null) {
            Utils.sendMessage(player, "&cYou are not in the world of a project, please teleport.");
            return false;
        }

        switch(cmd) {
            case "help":
                //TODO
                break;
            case "create":
                new CreateCmd("builders.create", 7, "/project create <project name> <client name> <length> <width> <time limit> <priority ? 1 to 100> <type ? FLAT or VOID>")
                        .exec(player, subCmdArgs);
                break;
            case "go":
                new GoCmd(null, 1, "/project go <project name>")
                        .exec(player, subCmdArgs);
                break;
            case "chat":
                //TODO
                break;
            case "player":
                new PlayerCmd(null, 2, "/project player <add | remove> <player>")
                        .exec(player, subCmdArgs, project);
                break;
            case "visit":
                new VisitCmd(null, 1, "/project visit <project name>")
                        .exec(player, subCmdArgs);
                break;
            case "setspawn":
                new SetSpawnCmd(null, 1, "/project setspawn")
                        .exec(player, subCmdArgs, project);
                break;
            default:
                Utils.sendMessage(player, "&cThat command does not exist, please use /project help");
        }


        return false;
    }
}
