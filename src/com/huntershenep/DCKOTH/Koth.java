package com.huntershenep.DCKOTH;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.huntershenep.DCCORE.CoreMethods;
import com.huntershenep.DCUTILS.Raiding.ThePlayer;

public class Koth {
	public static boolean running = false;
	public static boolean captured = false;
	public static ArrayList<Player> facPlayers = new ArrayList<Player>();
	public static int factionID = 0; //Make sure to clear this mf after ends
	
	public static int dollarsPerIPperPlayer = 100;
	
	
	
	
	public static void startKoth() {
		CoreMethods.titleAPIbroadcast("&eKOTH &bSTARTED", "&4&lPvP&f event at &4&l/FL");
		CoreMethods.pbroadcast("KOTH started! Head to &4/FL&f and capture the KOTH point and keep possession for 5 minutes. Each faction member contesting will receive a cash reward.", "&eKOTH");
		running = true;
		Timers.timeLeft = Timers.kothMaxTime;
	}
	
	public static void doStuff() {
		boolean anyoneIn = false;

		ArrayList<Player> inside = new ArrayList<Player>();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();
			
			
			
	    	if(Commands.getWorldGuard().getRegionManager (Bukkit.getWorld("factionLand")).getRegion("koth").contains(x, y, z)) {
	    		//System.out.println("KOTH - Detected somebody in KOTH zone");
	    		anyoneIn = true;
	    		inside.add(player);
	    		
	    	}
		}
		
		
		
		if(anyoneIn == true) {
			boolean currentFacStillInside = false;
			for(int i = 0; i < inside.size(); i++) {
				if(ThePlayer.getPlayerFac(inside.get(i)) == factionID) {
					currentFacStillInside = true;
					break;
				}
			}
			
			if(currentFacStillInside == false) {
				facPlayers.clear();
				captured = false;
				Timers.timeLeft = Timers.kothMaxTime;
			}
			
			
			if(!captured) {
				//Get the ID of the first person on the list
				factionID = ThePlayer.getPlayerFac(inside.get(0));
				System.out.println("KOTH -  has not been capture but starting this process");
				//See if they're factionless
				boolean factionless = false;
				if(factionID == 0) {
					factionless = true;
					System.out.println("KOTH - Person in zone is factionLess");
				}
				else {
					factionless = false;
					System.out.println("KOTH - Person HAS a faction");
				}
			
				//If they have a faction, and their factionID = ^ , add them to the fac contestor array.
				if(!factionless) {
					System.out.println("KOTH - Since they have a faction, we'll start this");
					captured = true;
					for(int i = 0; i < inside.size(); i++) {
						if(ThePlayer.getPlayerFac(inside.get(i)) == factionID){
							if(!Utils.playerInFacPlayerArr(inside.get(i))) {
								facPlayers.add(inside.get(i));
								System.out.println("KOTH - We made it to this spot ");
							}
						}
					}
				}
				else {
					Utils.message("You must be in a faction to partake in KoTH", inside.get(0));
				}
				
			}
			else {
				//The point is already captured. So we'll just add players who aren't already in the list.
				
				
				for(int i = 0; i < inside.size(); i++) {
					if(ThePlayer.getPlayerFac(inside.get(i)) == factionID){
						if(!Utils.playerInFacPlayerArr(inside.get(i))) {
							facPlayers.add(inside.get(i));
							System.out.println("KOTH - already captured, adding nigga to arraylist");
						}
					}
				}
				//MAKE SURE TO REMOVE PLAYERS WHO LOG OUT.
			}
			
			
			
		}
		else {
			factionID = 0;
			captured = false;
			
			if(facPlayers.size() > 0) {
				facPlayers.clear();
			}
			
		}
    	
    	
	}
	
	
}
