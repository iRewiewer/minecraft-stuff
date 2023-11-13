package net.irewiewer.pupsilandia;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SurvivalFlight implements CommandExecutor
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public SurvivalFlight(final Main plugin) { this.plugin = plugin; }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			if(((Player)sender).getGameMode().equals(GameMode.SURVIVAL) ||
			   ((Player)sender).getGameMode().equals(GameMode.ADVENTURE))
			
			((Player)sender).setAllowFlight(true);
			
			else sender.sendMessage(ChatColor.GRAY + "You need to be in Survival/Adventure to use this command.");
			
		}
		
		return true;
	}
}
