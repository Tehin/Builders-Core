package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HelpCmd extends CoreSubCommand {
    private Collection<CoreSubCommand> subCommands;

    public HelpCmd(String prefix, String description, String permission, int length, String usage, Collection<CoreSubCommand> subCommands) {
        super(prefix, description, permission, length, usage);
        this.subCommands = subCommands;
    }

    @Override
    public boolean exec(Player player, String[] args, Project project) {
        List<String> help = new ArrayList<>();

        help.add("&8&m------------");
        help.add("&" + Core.getInstance().getConfig().getString("server.color") + "&lProject SubCommands:");
        help.add("");
        subCommands.forEach(subCmd -> {
            help.add("    &8* &f/project " + subCmd.getPrefix() + " &7(" + subCmd.getDescription() + "&7)");
        });
        help.add("&8&m------------");

        Utils.sendMessage(player, help);
        return true;
    }
}
