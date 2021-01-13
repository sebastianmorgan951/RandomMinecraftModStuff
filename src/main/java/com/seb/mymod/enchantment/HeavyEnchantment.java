package com.seb.mymod.enchantment;

import com.seb.mymod.item.ComicallyLargeSpoon;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class HeavyEnchantment extends Enchantment {

    private static final int[] MIN_COST = new int[]{1, 5, 5};
    private static final int[] LEVEL_COST = new int[]{11, 8, 8};
    private static final int[] LEVEL_COST_SPAN = new int[]{20, 20, 20};
    public final int damageType;

    protected HeavyEnchantment(Rarity rarityIn, EnchantmentType typeIn,
                               int damageType, EquipmentSlotType... slots) {
        super(rarityIn, typeIn, slots);
        this.damageType = damageType;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    /**
     * Calculates the additional damage that will be dealt by an item with this enchantment. This alternative to
     * calcModifierDamage is sensitive to the targets EnumCreatureAttribute.
     */
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
        return 1.0F + (float) Math.max(0, level - 1) * 3.0F;
            //The below part can be added on to give specific creatures unique effects
        /*} else if (this.damageType == 1 && creatureType == CreatureAttribute.UNDEAD) {
            return (float)level * 2.5F;
        } else {
            return this.damageType == 2 && creatureType == CreatureAttribute.ARTHROPOD ? (float)level * 2.5F : 0.0F;
        }*/
    }

    /**
     * Determines if the enchantment passed can be applied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof DamageEnchantment || ench instanceof HeavyEnchantment);
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     */
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ComicallyLargeSpoon ? true : super.canApply(stack);
    }
}
