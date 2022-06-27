package net.irewiewer.lastlife;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SessionStop implements CommandExecutor
{
	private final Main plugin;
	
	public SessionStop(final Main plugin) { this.plugin = plugin; }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// amount to wait in ticks , 20 ticks = 1 second
		
		int kills = 0;
		
		for(Boogey iter : plugin.boogeys)
		{
			if(iter.getHasKilled() == false)
			{
				Bukkit.getServer().broadcastMessage(
						ChatColor.YELLOW + "Player " +
						ChatColor.LIGHT_PURPLE + iter.getBoogeyName() +
						ChatColor.YELLOW + " failed to kill whilst being a boogeyman." +
						ChatColor.YELLOW + "Their lives have been reduced to " +
						ChatColor.RED + "1" +
						ChatColor.YELLOW + " .");
				
				Player player = Bukkit.getPlayer(iter.getBoogeyName());
				player.getWorld().strikeLightning(player.getLocation());
			}
			else kills += 1;
		}
		
		if(kills == plugin.boogeys.size())
		{
			Bukkit.getServer().broadcastMessage(
					ChatColor.YELLOW + "No boogeymen lost their lives this"
							+ "session for they have taken another's.");
		}
		
		return true;
	}

}
