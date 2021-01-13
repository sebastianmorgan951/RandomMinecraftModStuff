package com.seb.mymod.util;

import com.seb.mymod.MyMod;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    /*This variable stores our mod items */
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MyMod.MOD_ID);

    /*Same purpose as the above DeferredRegister object, except this keeps
     * track of blocks */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MyMod.MOD_ID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MyMod.MOD_ID);

    public static final DeferredRegister<Effect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MyMod.MOD_ID);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,MyMod.MOD_ID);

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MyMod.MOD_ID);

    //This is how we would register something else, whether its a potion or
    //a sound or something
    /*public static final DeferredRegister<> WHATEVER =
            DeferredRegister.create(ForgeRegistries.SOMETHING, MyMod.MOD_ID);*/

    /* This method gets our EventBus, which carries around all our mod
    * information and communicates between classes and parts of the mod,
    * and adds our blocks and items to this. Then, when the mod starts,
    * all of the new stuff we've added will be on this EventBus! */
    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        EFFECTS.register(eventBus);
        TILE_ENTITY_TYPES.register(eventBus);
        SOUND_EVENTS.register(eventBus);
        ENCHANTMENTS.register(eventBus);
    }
}
