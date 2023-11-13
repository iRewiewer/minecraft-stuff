package me.irewiewer.silktouchhand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener
{
	public static String[] readFile()
	{
		String[] blessedPlayers = new String[999999];
		
		try
		{
			String PATH = System.getProperty("user.dir");
			File myObj = new File(PATH + "\\SilkTouchHand\\blessed.yml");
			
			BufferedReader myReader;
			myReader = new BufferedReader(new FileReader(myObj));
			
			String line = myReader.readLine();
			
			blessedPlayers[0] = line;
			
			for(int i = 1; i <= Integer.parseInt(blessedPlayers[0]) ; i++)
			{
				blessedPlayers[i] = myReader.readLine();
			}

			myReader.close();

		} catch (Exception error)
		{
			System.out.println("Error@Line:40;In:EventListener.java: " + error);
	    }
		
		return blessedPlayers;
	}
	
	public static void writeFile(String[] blessedPlayers)
	{
		try
		{
			String PATH = System.getProperty("user.dir");
			File myObj = new File(PATH + "\\SilkTouchHand\\blessed.yml");
			FileWriter myWriter = new FileWriter(myObj);  
			
			
			for(int i = 0; i <= Integer.parseInt(blessedPlayers[0]); i++)
			{
				myWriter.write(blessedPlayers[i]);
				myWriter.write("\n");
			}
			
			myWriter.close();
		} catch (Exception error)
		{
			System.out.println("Error@Line:64;In:EventListener.java: " + error);
		}
	}
	
	public static boolean checkIfBlessed(Player player)
	{
		boolean isBlessed = false;
		
		String[] blessedPlayers = readFile();
		
		if(Integer.parseInt(blessedPlayers[0]) == 0) isBlessed = false;
		else
		{
			for(int i = 1; i <= Integer.parseInt(blessedPlayers[0]) && isBlessed == false; i++)
			{
				if(blessedPlayers[i].equals(player.getName()))
				{
					isBlessed = true;
				}
			}
		}
		
		return isBlessed;
	}
	
	@EventHandler void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		Player player = event.getPlayer();
		Material material = block.getType();
		
		// if no item in player's hand & player is blessed, silk touch the block
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