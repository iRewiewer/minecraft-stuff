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

public class VeinMiner implements Listener
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public VeinMiner(final Main pl) { this.plugin = pl; }
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event)
	{
        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();
        String type = block.getType().name();
        
        if (!player.getGameMode().equals(GameMode.CREATIVE) &&
        	tool.getType().name().endsWith("_PICKAXE") &&
    		type.endsWith("_ORE") &&
    		(
        		tool.getItemMeta().getDisplayName().equals("miner") || 
        		tool.getItemMeta().getDisplayName().equals("Miner")
        	)
	       )
        	//tool.getItemMeta().hasDisplayName() == false)
        {
            if (type.startsWith("DEEPSLATE_"))
            	type = type.substring(10);

            List<Block> ores = getOres(block.getLocation(), type);
            ores.remove(block);
            
            Damageable meta = (Damageable) tool.getItemMeta();
            int dur = tool.getType().getMaxDurability() - meta.getDamage() - 1;
            
            if (ores.size() > dur)
            	ores = ores.subList(0, dur);

            meta.setDamage(meta.getDamage() + ores.size());
            
            tool.setItemMeta((ItemMeta) meta);

            for (Block log : ores)
                log.breakNaturally();
        }

    }
	
	public List<Block> getSurrounding(Location origin, String type)
	{
        List<Block> blocks = new ArrayList<>();
        
        for (int x = -1; x < 2; x++)
            for (int y = -1; y < 2; y++)
                for (int z = -1; z < 2; z++)
                {
                    Location loc = origin.clone().add(x, y, z);
                    Block block = loc.getBlock();
                    if (!origin.equals(loc) && block.getType().name().endsWith(type))
                    	blocks.add(block);
                }

        return blocks;
    }

    public List<Block> getOres(Location origin, String type)
    {
        List<Block> ores = new ArrayList<>();
        List<Block> next = getSurrounding(origin, type);

        while (!next.isEmpty())
        {
            List<Block> nextNext = new ArrayList<>();
            for (Block ore : next)
            {
                if (!ores.contains(ore))
                {
                	ores.add(ore);
                    nextNext.addAll(getSurrounding(ore.getLocation(), type));
                }

            }

            next = nextNext;
        }

        return ores;

    }
}