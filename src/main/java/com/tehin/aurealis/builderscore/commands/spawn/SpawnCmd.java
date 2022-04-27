package com.tehin.aurealis.builderscore.commands.spawn;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.commands.CoreCommand;
import com.tehin.aurealis.builderscore.commands.spawn.sub.SetCmd;
import com.tehin.aurealis.builderscore.project.Project;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.utils.Utils;

import java.util.Arrays;

public class SpawnCmd extends CoreCommand {

	public SpawnCmd(String name) {
		super(name);
	}

	@Override
	public void registerSubCommands() {
		registerSubCommand(new SetCmd("set", "Sets the selected spawn.","builders.rol.admin", 1, "/spawn set <lobby>"));
	}

	@Override
	public boolean sendCmd(Player player, String cmd, String[] subCmdArgs) {
		super.getSubCommand(cmd).exec(player, subCmdArgs, null);
		return true;
	}

}
