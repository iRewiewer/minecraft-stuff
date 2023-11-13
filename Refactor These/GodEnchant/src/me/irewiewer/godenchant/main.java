package me.irewiewer.godenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

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
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}

	// all enchants maxed
	public ItemStack godEnchant(ItemStack item)
	{
		item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 32767);
		item.addUnsafeEnchantment(Enchantment.CHANNELING, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 32767);
		item.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 32767);
		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 32767);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 32767);
		item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 32767);
		item.addUnsafeEnchantment(Enchantment.FROST_WALKER, 32767);
		item.addUnsafeEnchantment(Enchantment.IMPALING, 32767);
		item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 32767);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 32767);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 32767);
		item.addUnsafeEnchantment(Enchantment.LOYALTY, 32767);
		item.addUnsafeEnchantment(Enchantment.LUCK, 32767);
		item.addUnsafeEnchantment(Enchantment.LURE, 32767);
		item.addUnsafeEnchantment(Enchantment.MENDING, 32767);
		item.addUnsafeEnchantment(Enchantment.MULTISHOT, 32767);
		item.addUnsafeEnchantment(Enchantment.OXYGEN, 32767);
		item.addUnsafeEnchantment(Enchantment.PIERCING, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 32767);
		item.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 32767);
		item.addUnsafeEnchantment(Enchantment.RIPTIDE, 32767);
		item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 32767);
		item.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 32767);
		item.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 32767);
		item.addUnsafeEnchantment(Enchantment.THORNS, 32767);
		item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 32767);
		
		return item;
	}
	
	@SuppressWarnings({"deprecation"})
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		// enchant item in player's hand using godEnchant()
		if(label.equalsIgnoreCase("godenchant") ||
		   label.equalsIgnoreCase("godench") ||
		   label.equalsIgnoreCase("gench") ||
		   label.equalsIgnoreCase("ge"))
		{
			Player player = (Player) sender;
			
			ItemStack item = player.getItemInHand();
			
			item = godEnchant(item);
			
			player.setItemInHand(item);
		}

		return true;
	}
}