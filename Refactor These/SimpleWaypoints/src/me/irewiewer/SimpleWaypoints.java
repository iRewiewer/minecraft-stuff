package me.irewiewer;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;

//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.io.IOException;

//import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleWaypoints extends JavaPlugin{

	@Override
	public void onEnable()
	{
		getLogger().info("Successfully enabled.");
	}
	
	//player.sendMessage("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");	
	
	@Override
	public void onDisable()
	{
		getLogger().info("Successfully disabled.");
	}

	@EventHandler
	public void onjoin(PlayerJoinEvent e)
	{
		File f = new File(this.getDataFolder()+File.separator+"SW"+e.getPlayer().getName()+".yml");
		if(!(f.exists()))
		{
			try {
				f.createNewFile();
				this.getLogger().info("Created file for "+e.getPlayer().getName());
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player)sender;
		
	    if ( cmd.getName().equalsIgnoreCase("sw") && (sender instanceof Player) )
	    {
	    	if(args.length == 0)
	    	{
	    		player.sendMessage(ChatColor.GOLD + "-----------------------------------------------------");
		    	player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints Usage" + ChatColor.GOLD + "]");
			    player.sendMessage("");
			    player.sendMessage(ChatColor.GOLD + "/sw - " + ChatColor.YELLOW + "displays the usage section");
			    player.sendMessage(ChatColor.GOLD + "/sw add <name> <x> <y> <z> [comments] - " + ChatColor.YELLOW + "add a new waypoint to your personal list");
			    player.sendMessage(ChatColor.GOLD + "/sw del <name> - " + ChatColor.YELLOW + "delete a previously saved waypoint");
			    player.sendMessage(ChatColor.GOLD + "/sw list - " + ChatColor.YELLOW + "displays your personal waypoints list");
			    player.sendMessage(ChatColor.GOLD + "-----------------------------------------------------");
		      
		        return true;
	    	}
	    	else
	    	{
	    		//---------------------------------------------------//
	    		if(args[0].equals("add"))
	    		{
	    			if(args.length < 4)
	    	    	{
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " +
	    	    		ChatColor.RED + "Too few arguments.");
	    	    	}
	    			else
	    	    	{
	    	    		/* arg[1] = name
	    	    		   arg[2] = x
	    	    		   arg[3] = y
	    	    		   arg[4] = z
	    	    		   arg[5] = comments
	    	    		*/
	    	    		
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " +
	    	    	    ChatColor.YELLOW + "Waypoint \"" + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "\" has been added.");
	    	    	}
	    		}

	    		//---------------------------------------------------//
	    		if(args[0].equals("del"))
	    		{
	    			if(args.length > 2)
	    			{
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " +
	    	    	    ChatColor.RED + "Too many arguments.");
	    			}
	    			else
	    			{
	    				if(args.length < 2 )
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " +
	    	    	    ChatColor.YELLOW + "Waypoint \"" + ChatColor.BLUE + args[1] + ChatColor.YELLOW + "\" has been deleted.");
	    			}
	    		}
	    		
	    		//---------------------------------------------------//
	    		if(args[0].equals("list"))
	    		{
	    			if(args.length > 1)
	    	    	{
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " +
	    	    		ChatColor.RED + "Too many arguments.");
	    	    	}
	    	    	else
	    	    	{
	    	    		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] ");
	    	    		player.sendMessage(ChatColor.YELLOW + "Spawn:" + ChatColor.BLUE + " 0 70 0 " + ChatColor.GRAY + "[spawn point]");
	    	    		player.sendMessage(ChatColor.YELLOW + "ZombieFarm:" + ChatColor.BLUE + " 29 82 420 " + ChatColor.GRAY + "[zombie spawner farm]");
	    	    		player.sendMessage(ChatColor.YELLOW + "WTemple1:" + ChatColor.BLUE + " 2033 40 923 " + ChatColor.GRAY + "[make it into a guardian farm]");
	    	    		player.sendMessage(ChatColor.YELLOW + "WTemple2:" + ChatColor.BLUE + " 330 26 555 " + ChatColor.GRAY + "[unexplored yet]");
	    	    		player.sendMessage(ChatColor.YELLOW + "Home:" + ChatColor.BLUE + " 1000 100 1000 " + ChatColor.GRAY + "[Groove Street, home.]");
	    	    	}
	    		}
	    		else
	    		{	
	    			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "SimpleWaypoints" + ChatColor.GOLD + "] " + 
	    			ChatColor.RED + "Unknown command.");
	    		}
	    	}
	    	
	    	return true;
	    }

	    return false;
	}
}
