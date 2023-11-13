package me.irewiewer.silktouchhand;

import java.io.FileNotFoundException;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener
{
 	@SuppressWarnings("unused")
	private final Main plugin;
 	
 	public BlockBreakListener(final Main pl) { this.plugin = pl; }

 	public static boolean checkIfBlessed(Player player) throws FileNotFoundException
	{
		BlessedPlayers blessedPlayers = Main.readFile();
		
		for(BlessedPlayer blessed : blessedPlayers.getPlayers())
			if(blessed.getName().equals(player.getDisplayName()) && blessed.getIsBlessed() == false)
				return false;
		
		return true;
	}
	
	@EventHandler void onBlockBreak(BlockBreakEvent event) throws FileNotFoundException
	{
		Block block = event.getBlock();
		Player player = event.getPlayer();
		Material material = block.getType();
		
		// If no item in player's hand & player is blessed, silk touch the block
		if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR) && 
		   checkIfBlessed(player) &&
		   player.getGameMode() == GameMode.SURVIVAL)
		{
			event.setCancelled(true);
			block.setType(Material.AIR);
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(material));
		}

	}
	
}