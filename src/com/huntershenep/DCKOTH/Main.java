package com.huntershenep.DCKOTH;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin implements Listener{
	
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {

		Bukkit.getPluginManager().registerEvents(this, this);
		this.getCommand("koth").setExecutor(new Commands(this));
		//this.getCommand("dcsupply").setExecutor(new Commands(this));
		
		EconAPI.setupEconomy();
		
		Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
		    public void run() {
		    	
		    	
		    	
		    	Timers.countdown();
		    	
	        }
		}, 150L, 20L);
			
		
		
		Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
		    public void run() {
		    	
		    	
		    	if(Utils.debug == true) {
		    		Utils.debug();
		    	}
	        }
		}, 150L, 60L);
		
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerLoginEventaa(PlayerLoginEvent event) {
		if(Koth.running) {
			
			if(Utils.playerInFacPlayerArr(event.getPlayer())) {
				Koth.facPlayers.remove(Utils.playerInFacGetIndex(event.getPlayer()));
			}
		}
	}
	
	
	
}
