package com.huntershenep.DCKOTH;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Commands implements CommandExecutor {
    private final Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    
    //COMMANDS
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		
		if (cmd.getName().equalsIgnoreCase("koth")) {
			
			if(sender instanceof ConsoleCommandSender) {
				if(args[0].equalsIgnoreCase("start")) {
					
						Koth.startKoth();
						
					
				}
			}
			else {
				Player player = (Player) sender;
				if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("start")) {
						if(player.hasPermission("diamcraft.admin")) {
							Koth.startKoth();
							
						}
					}
					if(args[0].equalsIgnoreCase("debug")) {
						if(player.hasPermission("diamcraft.admin")) {
							if(Utils.debug) {
								Utils.debug = false;
							}
							else {
								Utils.debug = true;
							}
							Utils.message("Debug mode toggled.", player);
							
						}
					}
				}
			}
			
			
		}
		return true;
	}
    
    
    static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
     
        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            //log.severe ("*** ERROR Trying to attach GroupManager to GameOfThrones Plugin!");
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
    
}