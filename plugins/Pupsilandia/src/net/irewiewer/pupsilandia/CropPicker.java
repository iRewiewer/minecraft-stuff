package net.irewiewer.pupsilandia;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CropPicker implements Listener
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public CropPicker(final Main pl) { this.plugin = pl; }
	
	@EventHandler
	public void onCropBreak(PlayerInteractEvent event)
	{
		if(event.getClickedBlock() != null)
			if(event.getClickedBlock().getBlockData() instanceof Ageable &&
			  (event.getClickedBlock().getBlockData().getMaterial().equals(Material.CARROTS) ||
			   event.getClickedBlock().getBlockData().getMaterial().equals(Material.POTATOES) ||
			   event.getClickedBlock().getBlockData().getMaterial().equals(Material.WHEAT) ||
			   event.getClickedBlock().getBlockData().getMaterial().equals(Material.BEETROOTS) ||
			   event.getClickedBlock().getBlockData().getMaterial().equals(Material.COCOA)
			  )
			)
			{
				Ageable ageable = (Ageable) event.getClickedBlock().getBlockData();
				
				if(ageable.getAge() == 7 || // fully grown wheat, carrots, potatoes
				    (
				     ageable.getAge() == 3 && // fully grown beetroot
				     event.getClickedBlock().getBlockData().getMaterial().equals(Material.BEETROOTS)
				    ) ||
				    (
				      ageable.getAge() == 2 &&
				    		  event.getClickedBlock().getBlockData().getMaterial().equals(Material.COCOA)
				    )  
				  )
				{
					Collection<ItemStack> drops = event.getClickedBlock().getDrops();
				     
			        for (ItemStack drop : drops)
			        	event.getPlayer().getWorld().dropItem(event.getClickedBlock().getLocation().add(0, 0.5, 0), drop);
	
					ageable.setAge(0);
					event.getClickedBlock().setBlockData(ageable);
				}
			}
	}
}
