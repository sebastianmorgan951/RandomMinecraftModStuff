package com.seb.mymod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MoundTile extends TileEntity implements IClearable {

    private ItemStack containedTool = ItemStack.EMPTY;

    public MoundTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

    }

    public MoundTile() {
        super(ModTileEntities.MOUND_TILE_ENTITY.get());
    }

    /*This is the "read" function, */
    @Override
    public void read(BlockState blockState, CompoundNBT nbt){
        super.read(blockState,nbt);
        if (nbt.contains("Tool")) {
            this.setContainedTool(ItemStack.read(nbt.getCompound("Tool")));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt){
        super.write(nbt);
        if (!this.getContainedTool().isEmpty()) {
            nbt.put("Tool", this.getContainedTool()
                    .write(new CompoundNBT()));
        }
        return nbt;
    }

    public void setContainedTool(ItemStack tool) {
        this.containedTool = tool;
        this.markDirty();
    }

    public ItemStack getContainedTool(){
        return this.containedTool;
    }

    @Override
    public void clear() {
        this.containedTool = ItemStack.EMPTY;
    }
}
