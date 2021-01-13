package com.seb.mymod.sound;

import com.seb.mymod.MyMod;
import com.seb.mymod.util.Registration;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents {

    public static final RegistryObject<SoundEvent> BONK =
            Registration.SOUND_EVENTS.register("bonk",
            () -> new SoundEvent(new ResourceLocation(MyMod.MOD_ID, "bonk")));

    public static final RegistryObject<SoundEvent> SPOON_WOOSH =
            Registration.SOUND_EVENTS.register("spoon_woosh",
            () -> new SoundEvent(new ResourceLocation(MyMod.MOD_ID,"spoon_woosh")));

    public static final RegistryObject<SoundEvent> SPOON_GROUND_SMASH =
            Registration.SOUND_EVENTS.register("spoon_ground_smash",
            () -> new SoundEvent(new ResourceLocation(MyMod.MOD_ID, "spoon_ground_smash")));

    public static void register() { }
}
