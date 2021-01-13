package com.seb.mymod.tileentity;

import com.seb.mymod.block.ModBlocks;
import com.seb.mymod.util.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
    public static final RegistryObject<TileEntityType<MoundTile>> MOUND_TILE_ENTITY =
            Registration.TILE_ENTITY_TYPES.register("mound_tile_entity",
                    () -> TileEntityType.Builder.create(MoundTile::new,
            ModBlocks.EMPTY_MOUND.get(), ModBlocks.NETHERITE_SWORD_MOUND.get(),
            ModBlocks.IRON_SWORD_MOUND.get(),
            ModBlocks.DIAMOND_SWORD_MOUND.get(), ModBlocks.WOODEN_SWORD_MOUND.get(),
            ModBlocks.GOLDEN_SWORD_MOUND.get(), ModBlocks.STONE_SWORD_MOUND.get())
                    .build(null));

    public static void register() { }
}
