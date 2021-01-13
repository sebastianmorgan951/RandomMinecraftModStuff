package com.seb.mymod.item;

import com.seb.mymod.sound.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

public class ComicallyLargeSpoon extends SwordItem {

    private boolean hitSomething;

    public ComicallyLargeSpoon(IItemTier tier, int attackDamageIn,
                               float attackSpeedIn, Item.Properties builderIn){
        super(tier,attackDamageIn,attackSpeedIn,builderIn);
        hitSomething = false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if(entity instanceof PlayerEntity){
            if(hitSomething){
                hitSomething = false;
                return false;
            }
            entity.playSound(ModSoundEvents.SPOON_WOOSH.get(),1f,1f);
            return false;
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        Vector3d lookVec = player.getLookVec();
        Vector3d currMotion = player.getMotion();
        player.playSound(ModSoundEvents.SPOON_GROUND_SMASH.get(),1f,1f);
        player.setMotion(currMotion.getX()+(-1*lookVec.getX()),
                -1*lookVec.getY(),
                currMotion.getZ()+(-1* lookVec.getZ()));
        hitSomething = true;
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if(entity.isSneaking()){
            entity.playSound(ModSoundEvents.BONK.get(),0.5f,0.5f);
            return false;
        }
        entity.playSound(ModSoundEvents.BONK.get(),1.5f,1.5f);
        hitSomething = true;
        return false;
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
    }
}
