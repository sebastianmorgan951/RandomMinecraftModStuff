package com.seb.mymod.block;

import com.seb.mymod.MyMod;
import com.seb.mymod.util.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {

    /*These RegistryObjects will use our static 'register' function defined at the bottom
    * to add our blocks to both the "BLOCKS" and "ITEMS" DeferredRegistry objects from
    * the Registration class. While defining new items, we use the "AbstractBlock" class
    * as a basis, then add properties onto it. Through these properties, we can define
    * how the blocks sound when we interact with them, how hard they are to mine, what
    * tool we need to use to mine them, how blast resistant they are, and tweak other
    * properties. */

    public static final RegistryObject<Block> EMPTY_MOUND = register("empty_mound",
            () -> new EmptyMound(AbstractBlock.Properties.create(Material.ROCK)
            .hardnessAndResistance(2f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> DIAMOND_SWORD_MOUND = register(
            "diamond_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> WOODEN_SWORD_MOUND = register(
            "wooden_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> GOLDEN_SWORD_MOUND = register(
            "golden_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> IRON_SWORD_MOUND = register(
            "iron_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> STONE_SWORD_MOUND = register(
            "stone_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> NETHERITE_SWORD_MOUND = register(
            "netherite_sword_mound", () -> new FullMound.SwordMound(AbstractBlock
                    .Properties.create(Material.IRON).hardnessAndResistance(3f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    public static final RegistryObject<Block> COMICALLY_LARGE_SPOON_MOUND =
            register("comically_large_spoon_mound",
                    () -> new FullMound.SwordMound.SpoonMound(AbstractBlock.Properties
                    .create(Material.IRON).hardnessAndResistance(3f)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(2)));

    /*Empty register function which will allow us to make a default behavior for this class
    * in the future if we want to add some registration behaviour that always happens. */
    public static void register() { }

    /*Takes type of block we need to supply, then adds these blocks to both our "ITEM" and
    * "BLOCK" DeferredRegistry variables from our Registration class. We ensure that all
    * Blocks registered as Items within this method are added into our new creative mode
    * item category as well! */
    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block){
        RegistryObject<T> toRet = Registration.BLOCKS.register(name,block);
        Registration.ITEMS.register(name,() -> new BlockItem(toRet.get(),
                new Item.Properties().group(MyMod.CREATE_TAB)));
        return toRet;
    }
}
