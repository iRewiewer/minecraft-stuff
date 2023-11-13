package me.irewiewer.silktouchhand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main extends JavaPlugin
{
	private static Main instance;
	public static Path path = Paths.get(System.getProperty("user.dir") + "\\plugins\\SilkTouchHand");
	public BlessedPlayers players;
	public FileConfiguration config;
	
	// Config variables
	private boolean firstTimeSetup;
	
	public static Main getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		setup();
		
		getConfigVars();
		saveDefaultConfig();
	
		config = getConfig();
	    
		getCommand("bless").setExecutor(new Bless(this));
		getCommand("unbless").setExecutor(new Unbless(this));
		getCommand("blessed").setExecutor(new Blessed(this));
		
		// Event Listener - Used for enchantment table crafting limitation
		getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}

	public void setup()
	{
		// Make SilkTouchHand folder if it doesn't exist
		try { Files.createDirectories(path); } catch (Exception error) { System.err.println(error); }
		
		// Make blessed file if it doesn't exist
	    try
	    {
			File file = new File(path + "\\blessed.yml");
	    	
	    	if (file.createNewFile())
	    		// Initialise file with 0 blessed players
	    		try(FileWriter fileWriter = new FileWriter(file)) { fileWriter.close(); } catch (Exception error) { System.out.println(error); }
	    } catch (Exception error) { System.out.println(error); }
	}
	
	
	// Get method for currently saved config variables
	public void getConfigVars()
	{
		firstTimeSetup = getConfig().getBoolean("firstTimeSetup");
	}
	
	// First Time Setup - Checks if the plugin ran before
	public boolean getFTS() { return firstTimeSetup; }
	
	public void resetConfig()
	{
		FileConfiguration config = getConfig();
		config.set("firstTimeSetup", false);
		
		getConfigVars();
		saveConfig();
		saveDefaultConfig();
		getConfigVars();
	}

	public void updateConfig(boolean fTS)
	{
		config.set("firstTimeSetup", fTS);
		saveConfig();
		saveDefaultConfig();
		getConfigVars();
	}
	
	/// Blessed File Related ///
	
	// Read a BlessedPlayers object from the blessed.json file
	public static BlessedPlayers readFile() throws FileNotFoundException
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\blessed.json"));
		BlessedPlayers playersJson = gson.fromJson(bufferedReader, BlessedPlayers.class);
		
		return playersJson;
	}
	
	// Write a BlessedPlayers object to the blessed.json file
	public static void writeFile(BlessedPlayers players) throws IOException
	{
		// Pretty printing is an invaluable asset
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		FileWriter writer = new FileWriter(path + "\\blessed.json");
		writer.write(gson.toJson(players));
		writer.close();
	}
}

class BlessedPlayers
{
	private List<BlessedPlayer> players = new ArrayList<>();
	
	public BlessedPlayers() {}

	// Get ; Set ; Add
	public List<BlessedPlayer> getPlayers() { return players; }
	public void setPlayers(List<BlessedPlayer> players) { this.players = players; }
	public void addPlayers(BlessedPlayer player) { this.players.add(player); }
}

class BlessedPlayer
{
	private String name;
	private boolean isBlessed;

	public BlessedPlayer() { }
	
	// Get ; Set
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	// Get ; Set
	public boolean getIsBlessed() { return isBlessed; }
	public void setIsBlessed(boolean isBlessed) { this.isBlessed = isBlessed; }
}