package me.irewiewer.silktouchhand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Blessed implements CommandExecutor
{
	private final Main plugin;
	
	public Blessed(final Main plugin) { this.plugin = plugin; }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender) Bukkit.getServer().getConsoleSender().sendMessage("[CONSOLE] is always blessed.");
		else
		{
			for(BlessedPlayer iter : plugin.players.getPlayers())
			{
				if(plugin.getFTS() == true) sender.sendMessage(ChatColor.GRAY + "There are currently no blessed ones.");
				else
				{
					if(iter.getName().equals(((Player)sender).getDisplayName()) && iter.getIsBlessed() == true)
						sender.sendMessage(ChatColor.GRAY + "You are indeed a part of the chosen ones.");
					else sender.sendMessage(ChatColor.GRAY + "You're not part of the chosen ones.");
				}
			}
		}

		return true;
	}
}