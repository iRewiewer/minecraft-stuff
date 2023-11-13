package me.irewiewer.MobTweaks;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.EntityType;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

import org.bukkit.inventory.ItemStack;

public class listener implements Listener {

	@EventHandler
	public void event(CreatureSpawnEvent event)
	{
		if(event.getEntityType() == EntityType.CREEPER)
		{
			Creeper creeper = (Creeper) event.getEntity();
			
			creeper.setCustomNameVisible(true);
			creeper.setCustomName(ChatColor.GOLD + "Retard");
			creeper.setPowered(true);
		}
		
		if(event.getEntityType() == EntityType.SKELETON)
		{
			Skeleton skeleton = (Skeleton) event.getEntity();
			
			skeleton.setCustomNameVisible(true);
			skeleton.setCustomName(ChatColor.GOLD + "PowGuy");
			
			ItemStack powBow = new ItemStack(Material.BOW);
			powBow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 100);
			
			
			skeleton.getEquipment().setItemInMainHand(powBow);
			skeleton.getEquipment().setItemInMainHandDropChance(100);
		}
	}
	
}
