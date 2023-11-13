package me.irewiewer.MobTweaks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Uhc implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule announceAdvancements true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule commandBlockOutput false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule disableElytraMovementCheck false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule disableRaids true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doEntityDrops true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doFireTrick true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doImmediateRespawn false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doInsomnia true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLimitedCrafting false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doMobLoot true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doMobSpawning true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doPatrolSpawning true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doTileDrops true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doTraderSpawning false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doWeatherCycle true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule drowningDamage true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule fallDamage true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule fireDamage true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule forgiveDeadPlayers false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule logAdminCommands false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule maxCommandChainLength 65536");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule maxEntityCramming 24");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule mobGriefing true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule randomTickSpeed 3");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule reducedDebugInfo true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule sendCommandFeedback false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule showDeathMessages true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule spawnRadius 1");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule spectatorsGenerateChunks true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule universalAnger true");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "difficulty hard");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setworldspawn 0 90 0");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -3 84 -3 3 84 3");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder center 0 0");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 2000 1");
		
		return true;
	}
}
