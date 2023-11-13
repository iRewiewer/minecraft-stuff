package net.irewiewer.pupsilandia;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private static Main instance;
	
	public static Main getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		// Fly command - fly in survival / adventure mode
		getCommand("fly").setExecutor(new SurvivalFlight(this));
		
		// Event Listener - Used for listening to bed click event
		getServer().getPluginManager().registerEvents(new OneSleeperAnytime(this), this);
		
		// Event Listener - Used for listening to crop breaking event
		getServer().getPluginManager().registerEvents(new CropPicker(this), this);

		// Event Listener - Used for listening to the player cutting a tree
		getServer().getPluginManager().registerEvents(new TreeCutter(this), this);

		// Event Listener - Used for listening to leaves decaying
		getServer().getPluginManager().registerEvents(new FastLeafDecay(this), this);
		
		// Event Listener - Used for listening to the player mining an ore vein
		getServer().getPluginManager().registerEvents(new VeinMiner(this), this);

		// Event Listener - Used for listening to the player right-clicking with an emerald
		getServer().getPluginManager().registerEvents(new Bejeweled(this), this);
	}
	
	@Override
	public void onDisable()
	{
		FastLeafDecay.scheduledBlocks.clear();
	}
}