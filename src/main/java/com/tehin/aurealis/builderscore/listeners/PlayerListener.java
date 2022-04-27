package com.tehin.aurealis.builderscore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tehin.aurealis.builderscore.Core;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Core.getInstance().getSpawnsManager().sendToSpawn(e.getPlayer());
	}
	
	@EventHandler
    public void onDamage(EntityDamageEvent event){
		if (event.getEntity() instanceof Player){
		    if(event.getCause() == DamageCause.FALL){
		        event.setCancelled(true);
		    }
    	}
	}
}
