/*package com.seb.mymod.container;

import com.seb.mymod.util.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {
    public static final RegistryObject<ContainerType<MoundContainer>> MOUND_CONTAINER =
            Registration.CONTAINERS.register("mound_container",
                    () -> IForgeContainerType.create((((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.getEntityWorld();
                        return new MoundContainer(windowId, world, pos, inv, inv.player);
                    }))));

    public static void register(){ }
}
*/