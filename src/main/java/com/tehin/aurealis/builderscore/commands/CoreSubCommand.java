package com.tehin.aurealis.builderscore.commands;

import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.entity.Player;

public abstract class CoreSubCommand {

    private String prefix;
    private String description;
    private final String permission;
    private final int length;
    private final String usage;

    public CoreSubCommand(String prefix, String description, String permission, int length, String usage) {
        this.prefix = prefix;
        this.description = description;
        this.permission = permission;
        this.length = length;
        this.usage = usage;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDescription() {
        return description;
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

//    Methods

    public abstract boolean exec(Player player, String[] args, Project project);

    protected void sendUsage(Player player) {
        Utils.sendMessage(player, "&cUsage: " + getUsage());
    }

    protected boolean isValid(Player player, String[] args) {
        return Utils.verifyCommand(player, getPermission(), args.length, getLength(), getUsage());
    }

}
