package net.irewiewer.pupsilandia;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class OneSleeperAnytime implements Listener
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public OneSleeperAnytime(final Main pl) { this.plugin = pl; }
	
	@EventHandler
	public void onBedClick(PlayerBedEnterEvent event)
	{
		World world = Bukkit.getWorlds().get(0);
		
		if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK ||
			event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_SAFE ||
			event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_NOW || 
			event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.TOO_FAR_AWAY ||
			event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OTHER_PROBLEM
			)
		{
			event.useBed();
			event.setUseBed(Result.ALLOW);
			
			long time = world.getTime();
			
			if(time > 0 && time < 13000)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + event.getPlayer().getDisplayName() +" napped through the day.. It's night time now.");

				world.setTime(13001);
				event.setCancelled(true);
			}
			else
			{
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + event.getPlayer().getDisplayName() + " slept through the night. It's day time now.");
				world.setTime(0);
			}
			
		}
	}
}