package com.tehin.aurealis.builderscore.rol;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public enum Rol {

    ADMIN("Admin", "builders.rol.admin"),
    BUILDER("Builder", "builders.rol.builder"),
    VISITOR("Visitor", "");

    private String
            permission,
            name;
    private static final HashMap<String, Rol> map = new HashMap<>();

    static {
        for (Rol rol : values()) map.put(rol.getPermission(), rol);
    }

    Rol(String name, String permission) {
        this.permission = permission;
        this.name = name;
    }

    public String getPermission() {
        return this.permission;
    }
    public String getName() { return this.name; }

    public static Rol getRolByPlayer(Player player) {
        for (Rol rol : values()) {
            if (player.hasPermission(rol.getPermission())) return rol;
        }

        return VISITOR;
    }

}
