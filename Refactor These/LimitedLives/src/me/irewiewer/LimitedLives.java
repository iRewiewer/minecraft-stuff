package me.irewiewer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LimitedLives extends JavaPlugin
{

	public void onEnable()
	{
		getLogger().info("has been enabled");
		new ListenToThePlayersCommands(this);
	}
		
	public void onDisable()
	{
		getLogger().info("has been disabled");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player)sender;
		
		if ((cmd.getName().equalsIgnoreCase("checklives")) && ((sender instanceof Player)))
		{
			player.sendMessage(ChatColor.RED + "You have " + ChatColor.GREEN + ListenToThePlayersCommands.lives + ChatColor.RED + " lives left.");
		}
		
		return true;
	}
}