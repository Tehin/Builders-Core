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

    @Override
    public void registerSubCommands() {
        registerSubCommand(new CreateCmd("create", "Creates a new project that can be visited by the customers", "builders.create", 7, "/project create <project name> <client name> <length> <width> <time limit> <priority ? 1 to 100> <type ? FLAT or VOID>"));
        registerSubCommand(new GoCmd("go", "Teleports you to the selected project if you are a member of it", null, 1, "/project go <project name>"));
        registerSubCommand(new PlayerCmd("player", "Adds or removes a player from the current project", null, 2, "/project player <add | remove> <player>"));
        registerSubCommand(new VisitCmd("visit", "Teleports you as a spectator (customer) to see the build", null, 1, "/project visit <project name>"));
        registerSubCommand(new SetSpawnCmd("setspawn","Sets the spawn of the current project if you are a member of it",null, 1, "/project setspawn"));
        registerSubCommand(new HelpCmd("help", "Shows you the available commands", null, 1, "/project help",  super.getSubCommands()));
    }

    public boolean sendCmd(Player player, String cmd, String[] subCmdArgs) {
        boolean requiresProject = Utils.isCmdValid(new String[]{"chat", "removep", "addp"}, cmd); // Checks if the command requires a project
        Project project = Core.getInstance().getProjectsManager().getProjectByPlayerLoc(player); // If needed

        if (requiresProject && project == null) {
            Utils.sendMessage(player, "&cYou are not in the world of a project, please teleport.");
            return false;
        }

        super.getSubCommand(cmd).exec(player, subCmdArgs, project);
        return true;
    }
}
