package com.seb.mymod.item;

import com.seb.mymod.MyMod;
import com.seb.mymod.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    //Use type RegistryObject because it is the return type of the following statement
    /* In this variable we keep track of what has been added to our DeferredRegistry
    * variables, and we call our Item DeferredRegistry here to store our items as
    * we make them. We do not have to store the items corresponding to our blocks here,
    * as we can simply do that at the same time as the blocks are registered in the
    * Block DeferredRegistry!*/

    public static final RegistryObject<Item> COMICALLY_LARGE_SPOON =
            Registration.ITEMS.register("comically_large_spoon",
                    () -> new ComicallyLargeSpoon(ModItemTier.SPOON,14,
                            -3.5f,new Item.Properties()
                            .defaultMaxDamage(150).group(MyMod.CREATE_TAB)));

    public static void register() {
    }

    public enum ModItemTier implements IItemTier{

        /*Can register other tool types using this same constructor with a different
        * name to store all of its attributes! */

        SPOON(3,500,3f,0f, 20,
                Ingredient.fromStacks(new ItemStack(Items.NETHERITE_INGOT)));

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency,
                    float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }
}
