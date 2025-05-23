package com.dukeavi.xanadu_mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dukeavi.xanadu_mod.Items.ItemNought;
import com.dukeavi.xanadu_mod.events.TimeMessageHandler;
import com.dukeavi.xanadu_mod.events.PlayerJoinHandler;
import com.dukeavi.xanadu_mod.events.PlayerWakeUpHandler;
import com.dukeavi.xanadu_mod.ModSounds;
import net.fabricmc.api.ModInitializer;

/*
	This is the Xanadu mod, reminiscing your (maybe past) schoolkid life
 	You'll be awoken at dawn, called back at sunset, and screamed at if you sleep too late
 	Enjoy
*/

public class Xanadumod implements ModInitializer {
	public static final String MOD_ID = "xanadu-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String MOD_NAME = "Xanadumod";

	/*
	 * This is the main class of the mod. It is used to register items, blocks, and other
	 * things. It is also used to initialize the mod.
	 * @author Dukeavi
	 * @version 1.0
	 * @since 14/05/2025
	 *
	*/
	
	@Override
	public void onInitialize() {
		ItemNought.registerModItems();
		ModSounds.registerSounds();
		TimeMessageHandler.register();
		PlayerJoinHandler.register();
		PlayerWakeUpHandler.register();
	}
}