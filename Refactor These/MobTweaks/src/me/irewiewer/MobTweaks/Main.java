package me.irewiewer.MobTweaks;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.command.ColouredConsoleSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.command.Command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import org.bukkit.plugin.java.JavaPlugin;

import me.irewiewer.silktouchhand.Bless;
import me.irewiewer.silktouchhand.Blessed;
import me.irewiewer.silktouchhand.Unbless;

public class main extends JavaPlugin
{
	private static main instance;

	public static main getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
	
		getCommand("kit").setExecutor(new Kit(this));
		getCommand("max").setExecutor(new Max(this));
		getCommand("uhc").setExecutor(new Uhc(this));
		getCommand("rank").setExecutor(new Rank(this));
		getServer().getPluginManager().registerEvents((Listener) new listener(), this);
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}
	
	@SuppressWarnings("deprecation") // for bow item in hand
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(label.equalsIgnoreCase("title"))
		{
			// if there's no argument
			if(args.length == 0)
			{
				sender.sendMessage(ChatColor.RED + "Null parameters. Correct syntax: /title <prefix/suffix> <title name>");
			}
			else
			{
				if(args.length == 1)
				{
					sender.sendMessage(ChatColor.RED + "Null title parameter. Correct syntax: /title <prefix/suffix> <title name>");
				}
				else
				{
					Player player = (Player) sender;
					String pname = player.getDisplayName();
					
					if(args[0].equalsIgnoreCase("prefix"))
					{
						player.setDisplayName(args[1] + " " + pname);
						player.setPlayerListName(args[1] + " " + pname);
					}
					
					if(args[0].equalsIgnoreCase("suffix"))
					{
						player.setDisplayName(pname + " " + args[1]);
						player.setPlayerListName(pname + " " + args[1]);
					}	
				}
			}
		}
		
		if(label.equalsIgnoreCase("disable"))
		{
			sender.sendMessage(this + " has been disabled.");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		return false;
	}
}