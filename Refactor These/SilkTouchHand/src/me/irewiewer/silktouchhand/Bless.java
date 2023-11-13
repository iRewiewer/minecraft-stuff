package me.irewiewer.silktouchhand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Bless implements CommandExecutor
{
	private final Main plugin;
	
	public Bless(final Main plugin) { this.plugin = plugin; }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		List<BlessedPlayer> updatedPlayers = new ArrayList<>();
		
		if(args.length == 1)
		{
			if(sender instanceof ConsoleCommandSender)
			{
				if(plugin.getFTS() == true)
				{
					plugin.updateConfig(false);
					
					BlessedPlayer target = new BlessedPlayer();
					target.setName(args[0]);
					target.setIsBlessed(true);
					
					updatedPlayers.add(target);
					
					Bukkit.getPlayer(target.getName()).sendMessage(ChatColor.GOLD + "You've been blessed.");
					Bukkit.getServer().getConsoleSender().sendMessage("You've blessed " + target.getName());
				}
				else // Not the first time the plugin ran
				{
					for(BlessedPlayer iter : plugin.players.getPlayers())
					{
						if(iter.getName().equals(args[0]) && iter.getIsBlessed() == false)
						{
							BlessedPlayer target = new BlessedPlayer();

							target.setName(args[0]);
							target.setIsBlessed(true);
							
							updatedPlayers.add(target);
							
							Bukkit.getPlayer(target.getName()).sendMessage(ChatColor.GOLD + "You've been blessed.");
							Bukkit.getServer().getConsoleSender().sendMessage("You've blessed " + target.getName());
						}
						else updatedPlayers.add(iter);
					}
				}
			}
			else // Is instanceof Player
			{
				for(BlessedPlayer iter : plugin.players.getPlayers())
				{
					if(iter.getName().equals(((Player)sender).getDisplayName()) && iter.getIsBlessed() == true)
					{
						if(plugin.getFTS() == true)
						{
							plugin.updateConfig(false);
							
							BlessedPlayer target = new BlessedPlayer();
							target.setName(args[0]);
							target.setIsBlessed(true);
							
							updatedPlayers.add(target);
							
							Bukkit.getPlayer(target.getName()).sendMessage(ChatColor.GOLD + "You've been blessed.");
							Bukkit.getPlayer(iter.getName()).sendMessage("You've blessed " + target.getName());
						}
						else // Not the first time the plugin ran
						{
							for(BlessedPlayer subiter : plugin.players.getPlayers())
							{
								if(subiter.getName().equals(args[0]) && subiter.getIsBlessed() == false)
								{
									BlessedPlayer target = new BlessedPlayer();

									target.setName(args[0]);
									target.setIsBlessed(true);
									
									updatedPlayers.add(target);
									
									Bukkit.getPlayer(target.getName()).sendMessage(ChatColor.GOLD + "You've been blessed.");
									Bukkit.getPlayer(subiter.getName()).sendMessage("You've blessed " + target.getName());
								}
								else updatedPlayers.add(subiter);
							}
						}
					}
					else Bukkit.getPlayer(iter.getName()).sendMessage(ChatColor.GRAY + "You're not blessed. You can't do that.");
				}
			}
		}
		else
		{
			if(sender instanceof Player) ((Player)sender).sendMessage(ChatColor.GRAY + "You must specify a player.");
            else if (sender instanceof ConsoleCommandSender) Bukkit.getServer().getConsoleSender().sendMessage("You must specify a player.");
		}
		
		plugin.players.setPlayers(updatedPlayers);
		
		try { Main.writeFile(plugin.players); } catch (IOException error) { error.printStackTrace(); }
		
		return true;
	}
}