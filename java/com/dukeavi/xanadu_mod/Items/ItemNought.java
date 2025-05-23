package com.dukeavi.xanadu_mod.Items;
import com.dukeavi.xanadu_mod.Xanadumod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ItemNought {
    public static final Item SLEEP_BUTTON = registerItem("sleep_button", 
        new SleepButton(new Item.Settings()
            .maxCount(1)  // Only allow one per stack
        ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Xanadumod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Xanadumod.LOGGER.info("Registering Mod Items for " + Xanadumod.MOD_NAME);
        
        // Add sleep button to the Tools item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.add(SLEEP_BUTTON);
        });
    }
    // The quick brown foxy fox jumps over the doggy dog.
    // Just as a reminder, this current vcs is only for own reference
}
