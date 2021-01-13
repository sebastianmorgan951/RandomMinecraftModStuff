package com.seb.mymod.enchantment;

import com.seb.mymod.item.ComicallyLargeSpoon;
import com.seb.mymod.util.Registration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;

public class ModEnchantments {

    public static final RegistryObject<Enchantment> HEAVINESS =
            Registration.ENCHANTMENTS.register("heaviness", () ->
            new HeavyEnchantment(Enchantment.Rarity.COMMON,
            ModEnchantments.HEAVY_TYPE,
            0, EquipmentSlotType.MAINHAND));

    private static final EnchantmentType HEAVY_TYPE =
            EnchantmentType.create("mymod:heaviness", (item) ->
                    item instanceof ComicallyLargeSpoon);

    public static void register() { }


}
