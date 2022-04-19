package com.tehin.aurealis.builderscore.commands;

import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.entity.Player;

public class CoreSubCommand {

    private final String permission;
    private final int length;
    private final String usage;

    public CoreSubCommand(String permission, int length, String usage) {
        this.permission = permission;
        this.length = length;
        this.usage = usage;
    }

    public String getPermission() {
        return permission;
    }

    public int getLength() {
        return length;
    }

    public String getUsage() {
        return usage;
    }

    protected void sendUsage(Player player) {
        Utils.sendMessage(player, "&cUsage: " + getUsage());
    }

    protected boolean isValid(Player player, String[] args) {
        return Utils.verifyCommand(player, getPermission(), args.length, getLength(), getUsage());
    }

}
