package eternalcraft.common.core.handlers;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import eternalcraft.common.core.EternalcraftSettings;
import eternalcraft.common.core.Reference;

/**
 * Handles the loading of the configuration
 * @author bau5
 *
 */
public class ConfigHandler {
	/**
	 * Loading information from the config.
	 * 
	 * @param theConfig The configuration file as determined by FML
	 */
	public static EternalcraftSettings loadConfig(Configuration theConfig){
		EternalcraftSettings settings = new EternalcraftSettings();
		EternalcraftSettings defaults = settings.getDefaults();
		try{
			theConfig.load();
			settings.VERSION_CHECK = theConfig.get(Configuration.CATEGORY_GENERAL, "Version Check Enabled", defaults.doVersionCheck()).getBoolean(defaults.doVersionCheck());
			settings.MACHINE_BLOCK_ID = theConfig.get(Configuration.CATEGORY_BLOCK, "Machine Block", defaults.getMachineBlockID()).getInt(defaults.getMachineBlockID());
			settings.successfullyLoaded = true;
		}catch(Exception ex){
			FMLLog.log(Level.SEVERE, ex, Reference.MOD_NAME +": Error encountered while loading config file.");
		}finally{
			theConfig.save();
		}
		return settings;
	}
}
