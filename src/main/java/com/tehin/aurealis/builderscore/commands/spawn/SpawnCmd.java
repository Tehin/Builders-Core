package com.tehin.aurealis.builderscore.commands.spawn;

import com.tehin.aurealis.builderscore.commands.CoreCommand;
import com.tehin.aurealis.builderscore.commands.spawn.sub.SetCmd;
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
	public boolean sendCmd(Player player, String cmd, String[] subCmdArgs) {
		switch (cmd) {
			case "set":
				new SetCmd("builders.spawn.config", 1, "/spawn set <lobby>")
						.exec(player, subCmdArgs);
				break;
		}

		return false;
	}

}
