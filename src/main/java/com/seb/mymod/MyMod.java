package com.seb.mymod;

import com.seb.mymod.block.ModBlocks;
import com.seb.mymod.enchantment.ModEnchantments;
import com.seb.mymod.item.ModItems;
import com.seb.mymod.sound.ModSoundEvents;
import com.seb.mymod.tileentity.ModTileEntities;
import com.seb.mymod.util.Registration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MyMod.MOD_ID)
public class MyMod
{
    public static final String MOD_ID = "mymod";

    /*Adding a new tab to our Creative Menu for Minecraft, we override the
    * createIcon method here to make our own tab! */
    public static final ItemGroup CREATE_TAB = new ItemGroup("MyModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.COMICALLY_LARGE_SPOON.get());
        }
    };

    // Directly reference a log4j logger.
    /*log4j is an external library forge uses to log things happening in game
    * and during initialization, mostly for debugging purposes and outputting
    * things to the console! */
    private static final Logger LOGGER = LogManager.getLogger();

    //Constructor
    public MyMod() {

        registerModAdditions();

        /* Forge has different stages for setting up certain processes. We call
        * all of this to set up our mod loading process in the correct order! */
        // Register the enqueueIMC method for modloading
        // Inter-mod communication
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //Inter-mod communication
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerModAdditions(){
        //Initializes our deferredRegistry additions
        Registration.init();

        //Registers items and blocks added
        ModItems.register();
        ModBlocks.register();
        ModTileEntities.register();
        ModSoundEvents.register();
        ModEnchantments.register();
        /*ModContainers.register();*/

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("mymod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
}
