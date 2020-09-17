package com.huntershenep.DCKOTH;

import org.bukkit.entity.Player;

import com.huntershenep.DCCORE.CoreMethods;
import com.huntershenep.DCUTILS.MAXIP.IP;

public class Utils {
	
	public static boolean debug = false;
	
	
	// Is the playerin the contesting faction array list?
	public static boolean playerInFacPlayerArr(Player player) {
		boolean exists = false;
		
		for(int i = 0; i < Koth.facPlayers.size(); i++) {
			if(Koth.facPlayers.get(i).getName().equals(player.getName())) {
				exists = true;
			}
		}
		
		return exists;
		
	}

	
	//Get index of player in the contesting faction array list
	public static int playerInFacGetIndex(Player player) {
		int index = 9999;
		for(int i = 0; i < Koth.facPlayers.size(); i++) {
			if(Koth.facPlayers.get(i).getName().equals(player.getName())) {
				index = i;
			}
		}
		
		return index;
	}
	
	
	
	//Msg
	public static void message(String message, Player player) {
		CoreMethods.sendmessage(message, player, "&eKOTH");
	}
	
	
	
	
	public static void doWinnerStuff() {
		String playerNames = "";
		int cashAmt = IP.numberOfIPsOnline() * Koth.dollarsPerIPperPlayer;
		
		
		for(int i = 0; i < Koth.facPlayers.size(); i++) {
			
			System.out.println("[DC-KOTH] Rewarding " + Koth.facPlayers.get(i).getName() + cashAmt + " for winning KoTH");
			
			EconAPI.econ.depositPlayer(Koth.facPlayers.get(i), cashAmt);
			Utils.message("&aYou've received $" + cashAmt + " for winning the KOTH!", Koth.facPlayers.get(i));
			if(i == Koth.facPlayers.size() - 1 && Koth.facPlayers.size() > 1) {
				playerNames+= "and " + Koth.facPlayers.get(i).getName();
			}
			else {
				playerNames+= Koth.facPlayers.get(i).getName() + ", ";
			}
				
			
		}
		
		CoreMethods.pbroadcast(playerNames + " &bhas received &a$" + cashAmt + " &bfrom winning &eKOTH&b! ", "&eKOTH");
		
		Koth.running = false;
		Koth.captured = false;
		Koth.factionID = 0;
	}
	
	
	public static void debug() {
		System.out.println("###########################################################################################");
		System.out.println("Running: " + Koth.running);
		System.out.println("Captured: " + Koth.captured);
		System.out.println("FactionID: " + Koth.factionID);
		System.out.println("Contestors Array Size(): " + Koth.facPlayers.size());
		System.out.println("timeLeft: " + Timers.timeLeft);
			
		System.out.println("###########################################################################################");
	}
	
	
}
