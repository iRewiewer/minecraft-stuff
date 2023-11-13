package me.irewiewer.MobTweaks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// if there's no argument
		if(args.length == 0)
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"Kits Available:\n[God]"));
		}
		else
		{
			if(args[0].equalsIgnoreCase("god")) // first kit
			{
				Player targetPlayer = null;
				
				// if a player isn't specified and is run by console
				if(sender instanceof ColouredConsoleSender)
				{
					if(args.length == 1)
						sender.sendMessage("Too few arguments! Correct syntax: /god set <player>");
					else
						targetPlayer = Bukkit.getPlayer(args[1]);
				}
				else
				{
					if(args.length == 1) // give to self is player is not specified
						targetPlayer = (Player) sender;
					else
						targetPlayer = Bukkit.getPlayer(args[1]);
				}
					

				if(targetPlayer != null) // if player is online
				{
					ItemStack[] set = {
							new ItemStack(Material.NETHERITE_HELMET),
							new ItemStack(Material.NETHERITE_CHESTPLATE),
							new ItemStack(Material.NETHERITE_LEGGINGS),
							new ItemStack(Material.NETHERITE_BOOTS),
							new ItemStack(Material.NETHERITE_SWORD),
							new ItemStack(Material.BOW),
							new ItemStack(Material.CROSSBOW),
							new ItemStack(Material.SHIELD),
							new ItemStack(Material.ELYTRA)
					};
					
					for(int i = 0; i < set.length ; i++)
					{
						set[i] = godEnchant(set[i]);
					}
					
					for(int i = 0; i < set.length ; i++)
						targetPlayer.getInventory().addItem(set[i]);
					
					ItemStack GApples = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 3);
					ItemStack Fireworks = new ItemStack(Material.FIREWORK_ROCKET, 256);
					targetPlayer.getInventory().addItem(GApples);
					targetPlayer.getInventory().addItem(Fireworks);
				}
				else
				{
					if(!((sender instanceof ColouredConsoleSender) && args.length == 1))
						sender.sendMessage("That player is offline.");
				}
			}
			else
			{
				sender.sendMessage("That's not a valid kit name.");
			}
		}
	
		return true;
	}
}
