package net.irewiewer.pupsilandia;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class TreeCutter implements Listener
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public TreeCutter(final Main pl) { this.plugin = pl; }
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event)
	{
        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();
        String type = block.getType().name();
        
        if (!player.getGameMode().equals(GameMode.CREATIVE) &&
        	tool.getType().name().endsWith("_AXE") &&
    		(
    			type.endsWith("_LOG") ||
    			(
    				type.endsWith("_STEM") &&
    				!type.endsWith("MUSHROOM_STEM")
    			)
        	) &&
    		(
        		tool.getItemMeta().getDisplayName().equals("cutter") || 
        		tool.getItemMeta().getDisplayName().equals("Cutter")
        	)
	       )
        	//tool.getItemMeta().hasDisplayName() == false)
        {
            if (type.startsWith("STRIPPED_"))
            	type = type.substring(9);

            List<Block> logs = getLogs(block.getLocation(), type);
            logs.remove(block);
            
            Damageable meta = (Damageable) tool.getItemMeta();
            int dur = tool.getType().getMaxDurability() - meta.getDamage() - 1;
            
            if (logs.size() > dur)
                logs = logs.subList(0, dur);

            meta.setDamage(meta.getDamage() + logs.size());
            
            if(meta.getDamage() <= 0) meta.setDamage(1);
            
            tool.setItemMeta((ItemMeta) meta);

            for (Block log : logs)
                log.breakNaturally();
        }

    }
	
	public List<Block> getSurrounding(Location origin, String type)
	{
        List<Block> blocks = new ArrayList<>();
        
        for (int x = -1; x < 2; x++)
            for (int y = 0; y < 2; y++)
                for (int z = -1; z < 2; z++)
                {
                    Location loc = origin.clone().add(x, y, z);
                    Block block = loc.getBlock();
                    if (!origin.equals(loc) && block.getType().name().endsWith(type))
                    	blocks.add(block);
                }

        return blocks;
    }

    public List<Block> getLogs(Location origin, String type)
    {
        List<Block> logs = new ArrayList<>();
        List<Block> next = getSurrounding(origin, type);

        while (!next.isEmpty())
        {
            List<Block> nextNext = new ArrayList<>();
            for (Block log : next)
            {
                if (!logs.contains(log))
                {
                    logs.add(log);
                    nextNext.addAll(getSurrounding(log.getLocation(), type));
                }

            }

            next = nextNext;
        }

        return logs;

    }
}