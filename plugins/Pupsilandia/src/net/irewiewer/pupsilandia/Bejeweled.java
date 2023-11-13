package net.irewiewer.pupsilandia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Bejeweled implements Listener
{
	@SuppressWarnings("unused")
	private final Main plugin;
	
	public Bejeweled(final Main pl) { this.plugin = pl; }
	
	private HashMap <UUID, Inventory> games;
	private int score;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEmeraldClick(PlayerInteractEvent event)
	{
		try {
			if(event.getItem().getType() == Material.EMERALD) 
			{
			    this.games = new HashMap();
			    this.score = 0;
	
			    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
			    {
			        public void run()
			        {
			            for (Inventory game: Bejeweled.this.games.values())
			            {
			                Bejeweled.this.applyOneGravity(game);
			                Bejeweled.this.fill(game);
			                Bejeweled.this.removeThreesomes(game);
			            }
			        }
			    }, 5L, 10L);
			}
		} catch(Exception e) {}
		
		//event.getPlayer().getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
	}
    
    private void newGame(Player player)
    {
    	this.score = 0;

    	Inventory game = Bukkit.getServer().createInventory(player, 54, "Bejeweled");
        fill(game);
        while (threesomeExists(game))
        {
            removeThreesomes(game);
            applyAllGravity(game);
            fill(game);
        }
        player.openInventory(game);
        this.games.put(player.getUniqueId(), game);
    }

    private void fill(Inventory game) {
        Random rand = new Random();

        for (int col = 0; col < 9; col++)
        {
            int slot = col;

            if (game.getItem(slot) == null)
            {
                Material newItem = Material.GRASS;
                switch (rand.nextInt(6))
                {
                    case 0:
                        newItem = Material.DIAMOND;
                        break;
                    case 1:
                        newItem = Material.EMERALD;
                        break;
                    case 2:
                        newItem = Material.GOLD_INGOT;
                        break;
                    case 3:
                        newItem = Material.IRON_INGOT;
                        break;
                    case 4:
                        newItem = Material.REDSTONE;
                        break;
                    case 5:
                        newItem = Material.LAPIS_LAZULI;
                        break;
                    case 6:
                        newItem = Material.COAL;
                }

                game.setItem(slot, new ItemStack(newItem, 1));
            }
        }
    }

    private void selectSlot(Inventory game, int slot, InventoryClickEvent e)
    {
    	try
    	{
	    	if (this.games.containsKey(e.getView().getPlayer().getUniqueId()))
	    	{
		        ItemStack item = game.getItem(slot);
		        if (item != null)
		            item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
	    	}
    	} catch(Exception error) {}
    }

    private int selectedSlot(Inventory game)
    {
        for (int i = 0; i < 54; i++)
        {
            ItemStack item = game.getItem(i);
            if ((item != null) && (item.getEnchantments().size() > 0)) return i;
        }

        return -1;
    }

    private void deselect(Inventory game)
    {
        for (int i = 0; i < 54; i++)
        {
            ItemStack item = game.getItem(i);
            if ((item != null) && (item.getEnchantments().size() > 0))
                item.removeEnchantment(Enchantment.ARROW_INFINITE);
        }
    }

    private boolean adjacent(int slot1, int slot2)
    {
        int row1 = slot1 / 9;
        int col1 = slot1 % 9;
        int row2 = slot2 / 9;
        int col2 = slot2 % 9;
        if (
        	(
        		(row1 == row2) &&
        		(Math.abs(col1 - col2) == 1)
        	) || (
        		(col1 == col2) &&
        		(Math.abs(row1 - row2) == 1)
        	)
           )
        {
            return true;
        }
        return false;
    }

    private void swap(Inventory game, int slot1, int slot2)
    {
        ItemStack item1 = game.getItem(slot1);
        ItemStack item2 = game.getItem(slot2);
        Material type1 = Material.GRASS;
        Material type2 = Material.GRASS;

        if (item1 != null) type1 = item1.getType();
        if (item2 != null) type2 = item2.getType();


        if (item1 == null) game.setItem(slot2, null);
        else game.setItem(slot2, new ItemStack(type1, 1));

        if (item2 == null) game.setItem(slot1, null);
        else game.setItem(slot1, new ItemStack(type2, 1));
    }

    private boolean supported(Inventory game, int slot)
    {
        int row = slot / 9;
        int col = slot % 9;
        for (int i = row; i < 6; i++)
            if (game.getItem(i * 9 + col) == null)
            	return false;

        return true;
    }

    private boolean threesomeExists(Inventory game)
    {
        for (int row = 0; row < 6; row++)
        {
            Material prev = Material.GRASS;
            int seq = 0;
            for (int col = 0; col < 9; col++)
            {
                ItemStack item = game.getItem(row * 9 + col);
                Material type = Material.GRASS;
                if (item != null)
                    type = item.getType();
                
                if ((item != null) && (type == prev) && (supported(game, row * 9 + col)))
                {
                    seq++;
                    if (seq == 3)
                        return true;
                } else seq = 1;

                prev = type;
            }
        }

        for (int col = 0; col < 9; col++)
        {
            Material prev = Material.GRASS;
            int seq = 0;
            for (int row = 0; row < 6; row++)
            {
                ItemStack item = game.getItem(row * 9 + col);
                Material type = Material.GRASS;
                if (item != null)
                	type = item.getType();

                if ((item != null) && (type == prev) && (supported(game, row * 9 + col)))
                {
                    seq++;
                    if (seq == 3)
                        return true;
                } else seq = 1;

                prev = type;
            }
        }

        return false;
    }

    private void removeThreesomes(Inventory game)
    {
        List marked = new ArrayList();

        for (int row = 0; row < 6; row++)
        {
            Material prev = Material.GRASS;
            int seq = 0;
            for (int col = 0; col < 9; col++)
            {
                ItemStack item = game.getItem(row * 9 + col);
                Material type = Material.GRASS;
                if (item != null)
                    type = item.getType();

                if ((item != null) && (type == prev) && (supported(game, row * 9 + col)))
                {
                    seq++;
                    if (seq == 3)
                    {
                        marked.add(Integer.valueOf(row * 9 + col));
                        marked.add(Integer.valueOf(row * 9 + col - 1));
                        marked.add(Integer.valueOf(row * 9 + col - 2));
                    } else if (seq > 3)
                        marked.add(Integer.valueOf(row * 9 + col));
                } else seq = 1;

                prev = type;
            }
        }

        for (int col = 0; col < 9; col++)
        {
        	Material prev = Material.GRASS;
            int seq = 0;
            for (int row = 0; row < 6; row++)
            {
                ItemStack item = game.getItem(row * 9 + col);
                Material type = Material.GRASS;
                if (item != null)
                    type = item.getType();

                if ((item != null) && (type == prev) && (supported(game, row * 9 + col)))
                {
                    seq++;
                    if (seq == 3)
                    {
                        marked.add(Integer.valueOf(row * 9 + col));
                        marked.add(Integer.valueOf((row - 1) * 9 + col));
                        marked.add(Integer.valueOf((row - 2) * 9 + col));
                    } else if (seq > 3)
                        marked.add(Integer.valueOf(row * 9 + col));
                } else seq = 1;

                prev = type;
            }
        }

        if (marked.size() > 0)
            for (HumanEntity player: game.getViewers())
            	player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        for (Iterator prev = marked.iterator(); prev.hasNext();)
        {
            int slot = ((Integer) prev.next()).intValue();
            game.setItem(slot, null);
        }
    }

    private void applyAllGravity(Inventory game) 
    {
        for (int col = 0; col < 9; col++) 
        {
            int emptySlots = 0;
            for (int row = 5; row >= 0; row--)
                if (game.getItem(row * 9 + col) == null)
                    emptySlots++;
                else if (emptySlots > 0)
                {
                	game.setItem((row + emptySlots) * 9 + col, game.getItem(row * 9 + col));
                	game.setItem(row * 9 + col, null);
                }
        }
    }

    private void applyOneGravity(Inventory game)
    {
        for (int col = 0; col < 9; col++)
            for (int row = 4; row >= 0; row--)
                if (game.getItem((row + 1) * 9 + col) == null)
                    swap(game, (row + 1) * 9 + col, row * 9 + col);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void eventRightClick(PlayerInteractEvent e)
    {
        if (e.getPlayer().getItemInHand().getType() == Material.EMERALD)
        	newGame(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void eventCloseInventory(InventoryCloseEvent e)
    {
    	try
    	{
	    	if(this.games.containsKey(e.getView().getPlayer().getUniqueId()))
	    	{
	    		e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You've got " + ChatColor.GREEN + this.score + ChatColor.LIGHT_PURPLE + " points!");
	    		this.games.remove(e.getPlayer().getUniqueId());
	    	}
    	}catch(Exception error) {}
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void eventClick(InventoryClickEvent e)
    {
    	try {
	        if (this.games.containsKey(e.getView().getPlayer().getUniqueId()))
	            e.setCancelled(true);
	        else return;
    	}catch(Exception error) {}

        Inventory game = e.getInventory();

        int rawSlot = e.getRawSlot();
        if ((rawSlot >= 0) && (rawSlot < 54))
        {
            int selected = selectedSlot(game);
            if (selected == -1)
                selectSlot(game, e.getSlot(), e);
            else
            {
                deselect(game);
                if (adjacent(selected, e.getSlot()))
                {
                    swap(game, selected, e.getSlot());

                    if (threesomeExists(game))
                    {
                    	removeThreesomes(game);
                    	this.score += 1;
                    }

                    else swap(game, selected, e.getSlot());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void eventDrag(InventoryDragEvent e) {
        if (this.games.containsKey(e.getView().getPlayer().getUniqueId()))
            e.setCancelled(true);
    }
}