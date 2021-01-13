package com.seb.mymod.item;

import com.seb.mymod.enchantment.ModEnchantments;
import com.seb.mymod.sound.ModSoundEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

public class ComicallyLargeSpoon extends SwordItem {

    public static final float[] ATTACK_SPEEDS = {-3.5f,-3.55f,-3.58f,-3.6f};
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
        int heavinessLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HEAVINESS.get(),
                itemstack);
        Vector3d lookVec = player.getLookVec();
        Vector3d currMotion = player.getMotion();
        player.playSound(ModSoundEvents.SPOON_GROUND_SMASH.get(),1f,1f);
        player.setMotion(currMotion.getX()+((-1-heavinessLevel)*lookVec.getX()),
                (-1-heavinessLevel)*lookVec.getY(),
                currMotion.getZ()+((-1-heavinessLevel)* lookVec.getZ()));
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
