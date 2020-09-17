package com.huntershenep.DCKOTH;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.huntershenep.DCUTILS.Raiding.ThePlayer;

public class Timers {
	
	public static int timeLeft = 0;
	public static int kothMaxTime = 180;
	
	
	public static void countdown() {
		if(Koth.running == true) {
			Koth.doStuff();
			
			if(Koth.captured == true) {
				timeLeft--;
				if(timeLeft % 10 == 0) {
					broadcastTimeleft();
				}
				
				
				if(timeLeft == 0) {
					Utils.doWinnerStuff();
				}
				
			}
			else {
				timeLeft = kothMaxTime;
			}
		}
	}
	
	
	
	public static void broadcastTimeleft() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.getWorld().getName().equals("factionLand")) {
				if(Koth.captured) {
					String time = "";
					if(timeLeft > 60) {
						time = timeLeft/60 + " min " + timeLeft % 60 + " sec.";
					}
					else if (timeLeft == 60) {
						time = "1 min.";
					}
					else if (timeLeft < 60) {
						time = timeLeft % 60 + " sec.";
					}
					
					Utils.message("Controlled by &b" + ThePlayer.getFactionName(Koth.facPlayers.get(0)) + "&f | Time left: " + time, player);
				}
				else {
					Utils.message("KOTH is running but has not been captured!", player);
				}
			}
		}
	}
	
}
