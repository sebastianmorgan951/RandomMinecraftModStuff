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
    public static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot", //Item name always lower case
            () -> new Item(new Item.Properties().group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties().group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPERED_APPLE =
            Registration.ITEMS.register("coppered_apple",
                    () -> new CopperedApple());

    /* TOOLS */

    /*Use the copper enum to create this, give this shovel a very low damage
    * and high attack speed, and otherwise fairly standard stats. This will be
    * better than a wooden shovel. */
    public static final RegistryObject<Item> COPPER_SHOVEL =
            Registration.ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModItemTier.COPPER,0f,
            0f, new Item.Properties().defaultMaxDamage(150)
                    .addToolType(ToolType.SHOVEL, 2)
                    .group(MyMod.CREATE_TAB)));

    /*Use our enum to create this, Swords don't need a tool type. */
    public static final RegistryObject<Item> COPPER_SWORD =
            Registration.ITEMS.register("copper_sword",
                    () -> new SwordItem(ModItemTier.COPPER,3,
                            -1f, new Item.Properties()
                            .defaultMaxDamage(150)
                            .group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COMICALLY_LARGE_SPOON =
            Registration.ITEMS.register("comically_large_spoon",
                    () -> new SwordItem(ModItemTier.SPOON,14,
                            -3.5f,new Item.Properties()
                            .defaultMaxDamage(150).group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_PICKAXE =
            Registration.ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModItemTier.COPPER,0,
                            0f, new Item.Properties()
                            .defaultMaxDamage(150)
                            .addToolType(ToolType.PICKAXE, 2)
                            .group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_HOE =
            Registration.ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModItemTier.COPPER,0,
                            1f, new Item.Properties()
                            .defaultMaxDamage(150)
                            .addToolType(ToolType.HOE, 2)
                            .group(MyMod.CREATE_TAB)));

    /*Axes usually have high damage and low attack speed, so here we make our
    * attack speed = -3. When attackspeed = 0, we have an in game attack speed
    * of 4, so by setting this variable to -3, the attack speed is now = 1 in
    * game. We also set the damage to be a bit higher. */
    public static final RegistryObject<Item> COPPER_AXE =
            Registration.ITEMS.register("copper_axe",
                    () -> new AxeItem(ModItemTier.COPPER,5f,
                            -3f, new Item.Properties().defaultMaxDamage(150)
                            .addToolType(ToolType.AXE, 2)
                            .group(MyMod.CREATE_TAB)));

    /* Have an empty register method here because we have static variables in this
    * class, adding a little method makes the way this class loads its static variables
    * much more reliable. */


    /* ARMOR */

    public static final RegistryObject<Item> COPPER_HELMET =
            Registration.ITEMS.register("copper_helmet",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD,
                            new Item.Properties().group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE =
            Registration.ITEMS.register("copper_chestplate",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST,
                            new Item.Properties().group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_LEGGINGS =
            Registration.ITEMS.register("copper_leggings",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS,
                            new Item.Properties().group(MyMod.CREATE_TAB)));

    public static final RegistryObject<Item> COPPER_BOOTS =
            Registration.ITEMS.register("copper_boots",
                    () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET,
                            new Item.Properties().group(MyMod.CREATE_TAB)));

    public static void register() {
    }

    public enum ModArmorMaterial implements  IArmorMaterial{
        COPPER(50, new int[] { 2, 4, 4, 2 }, 10,
                SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, Ingredient.fromStacks
                (new ItemStack(ModItems.COPPER_INGOT.get())),
                MyMod.MOD_ID + ":copper", 0, 0.1f);

        private final int durability;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmorMaterial(int durability, int[] damageReductionAmountArray,
                         int enchantability, SoundEvent soundEvent,
                         Ingredient repairMaterial, String name, float toughness,
                         float knockbackResistance) {

            this.durability = durability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() { //Asks for knockBack resistance
            return knockbackResistance;
        }
    }

    public enum ModItemTier implements IItemTier{

        /*Can register other tool types using this same constructor with a different
        * name to store all of its attributes! */

        SPOON(3,500,3f,0f, 20,
                Ingredient.fromStacks(new ItemStack(Items.NETHERITE_INGOT))),

        COPPER(2, 150, 2.5f, 0f, 15,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())));

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
