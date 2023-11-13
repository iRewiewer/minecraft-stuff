package me.irewiewer.MobTweaks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Max implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		
		ItemStack item = player.getInventory().getItemInMainHand();
		
		item = godEnchant(item);
		
		player.getInventory().setItemInMainHand(item);
		
		return true;
	}
	
	public ItemStack godEnchant(ItemStack item)
	{
		for(Enchantment e : Enchantment.values())
			item.addUnsafeEnchantment(e, 32767);
		
		return item;
	}
}
