package com.seb.mymod.world.gen;

import com.seb.mymod.block.ModBlocks;
import net.minecraft.block.Block;

public enum NonOreRandomlyPlacedType {
    EMPTY_MOUND(ModBlocks.EMPTY_MOUND.get(),1,13,50),
    SPOON_MOUND(ModBlocks.COMICALLY_LARGE_SPOON_MOUND.get(),1,6,40);

    private final Block block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    NonOreRandomlyPlacedType(Block block, int maxVeinSize, int minHeight, int maxHeight){
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }


    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public Block getBlock() {
        return block;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static NonOreRandomlyPlacedType get(Block block){
        for(NonOreRandomlyPlacedType generatedTing : values()){
            if(block==generatedTing.block){
                return generatedTing;
            }
        }
        return null;
    }
}
