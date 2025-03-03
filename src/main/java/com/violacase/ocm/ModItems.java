package com.violacase.ocm;

import com.violacase.ocm.items.SunGemItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.violacase.ocm.OCM.MODID;

public class ModItems {
    // Create a Deferred Register to hold Blocks which will all be registered under the "ocm" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "ocm" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "ocm" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new food item with the id "ocm:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> SUN_GEM = ITEMS.register("sun_gem", () -> new SunGemItem(new Item.Properties().rarity(Rarity.EPIC)));


    // Creates a creative tab with the id "ocm:ocm_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> OCM_TAB = CREATIVE_MODE_TABS.register("ocm_tab", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> SUN_GEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(SUN_GEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
    }).build());
}
