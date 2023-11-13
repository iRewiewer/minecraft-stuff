package me.irewiewer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenToThePlayersCommands implements Listener
{
	public static int lives = 10;
	public boolean isBanned = false;

	public ListenToThePlayersCommands(LimitedLives plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
	
		if (this.isBanned)
		{
			player.kickPlayer(ChatColor.RED + "Sorry, but you have " + ChatColor.GREEN + "0" + ChatColor.RED + " lives left. Goodbye!");
		}
	
		event.setJoinMessage(ChatColor.YELLOW + "Welcome " + player.getDisplayName() + " to the server!");
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		lives -= 1;
		
		Player player = event.getEntity();
		
		if (lives > 0)
		{
			if (lives == 1)
				event.setDeathMessage(ChatColor.RED + "Aw shucks! You've died! You have " + ChatColor.GREEN + lives + ChatColor.RED + " life left.");
			else
				event.setDeathMessage(ChatColor.RED + "Aw shucks! You've died! You have " + ChatColor.GREEN + lives + ChatColor.RED + " lives left.");
		}
		else
		{
			player.kickPlayer(ChatColor.RED + "Sorry, but you have " + ChatColor.GREEN + "0" + ChatColor.RED + " lives left. Goodbye!");
			this.isBanned = true;
		}
	}
}