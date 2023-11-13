package me.irewiewer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelRank extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		getLogger().info(ChatColor.YELLOW + "[LevelRank]" + ChatColor.WHITE + " LevelRank has been enabled successfully!");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(ChatColor.YELLOW + "[LevelRank]" + ChatColor.WHITE + " LevelRank has been disabled successfully!");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("levelrank") && sender instanceof Player )
		{
			Player player = (Player) sender;
			
			int level;
			level = player.getLevel();
			
			player.sendMessage(ChatColor.YELLOW + "[LevelRank] " + ChatColor.WHITE + "You're level " + ChatColor.GREEN + level + ChatColor.WHITE + ".");
			
			return true;
		}
		
		return false;
	}
	
}
