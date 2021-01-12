package com.seb.mymod.events;

import com.seb.mymod.item.ModItems;
import com.seb.mymod.util.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;

public class ModEvents {
    public static Entity lastMob = null;
    public static int CopperedAppleInteractionCount = 0;
    public static Entity pickedUpDiamondBlock = null;
    /*Use subscribe event so this class can be called when event is fired*/

    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event) {
        if(event.getPlayer().getHeldItemMainhand().getItem() ==
                ModItems.COPPERED_APPLE.get()){
            if(event.getTarget().isAlive()){
                LivingEntity target = (LivingEntity)event.getTarget();
                if(target instanceof SheepEntity){
                    PlayerEntity player = event.getPlayer();
                    //This removes the item used to hit sheep on event
                    player.getHeldItemMainhand().shrink(1);

                    target.addPotionEffect(new EffectInstance(Effects.GLOWING,
                            Config.COPPERED_GLOW_DURATION.get()));

                    if(!player.world.isRemote()){
                        String msg = TextFormatting.YELLOW + "Sheep is now glowing";
                        player.sendMessage(new StringTextComponent(msg),player.getUniqueID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event){
        LivingEntity entity = event.getEntityLiving();

        if(entity instanceof SheepEntity){
            World world = entity.getEntityWorld();
            /*Collect expected drops from the Sheep death. */
            Collection<ItemEntity> drops = event.getDrops();

            LogManager.getLogger().debug(entity.getActivePotionEffects());

            if(entity.isPotionActive(Effects.GLOWING)){
                drops.add(new ItemEntity(world, entity.getPosX(),
                        entity.getPosY(), entity.getPosZ(),
                        new ItemStack(ModItems.COPPER_INGOT.get())));
            }
        }
    }

    /*Helps me learn what entities have what ids*/
    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event){
        Entity target = event.getTarget();
        PlayerEntity player = event.getPlayer();
        if(!player.world.isRemote()){
            String msg = TextFormatting.YELLOW + "Mob ID: " + target.getEntityId();
            player.sendMessage(new StringTextComponent(msg),player.getUniqueID());
        }
    }

    /*Helps me learn food string ids*/
    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event){
        PlayerEntity player = event.getPlayer();
        String target = event.getStack().getItem().getName().getString();
        if(!player.world.isRemote()){
            String msg = TextFormatting.YELLOW + "Item String ID -" + target;
            player.sendMessage(new StringTextComponent(msg),player.getUniqueID());
        }
    }

    @SubscribeEvent
    public void onCopperedMob(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        if(event.getPlayer().getHeldItemMainhand().getItem() ==
                ModItems.COPPERED_APPLE.get()){
            if(event.getTarget().isAlive()){
                LivingEntity target = (LivingEntity)event.getTarget();
                if(target instanceof MonsterEntity) {
                    MonsterEntity monster = (MonsterEntity)target;
                    if (true) {
                        if(lastMob == null){
                            lastMob = target;
                        }
                        if(target.getEntityId() != lastMob.getEntityId()){
                            System.out.println("Hit the wrongo!");
                            CopperedAppleInteractionCount = 0;
                        }
                        lastMob = target;
                        CopperedAppleInteractionCount += 1;
                        target.heal(5f);
                        //This removes the item used to hit monster on event
                        player.getHeldItemMainhand().shrink(1);
                        if(CopperedAppleInteractionCount >= 20){
                            String msg = TextFormatting.YELLOW + "Monster is ready to pick up";
                            player.sendMessage(new StringTextComponent(msg),player.getUniqueID());
                            player.world.playSound(player, new BlockPos(player.getPosX(),
                                            player.getPosY(),player.getPosZ()),
                                    SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.HOSTILE,
                                    100f, 50f);
                            monster.setCanPickUpLoot(true);
                        }
                    }
                }
                else if(target instanceof AnimalEntity) {
                    AnimalEntity animal = (AnimalEntity)target;
                    if (true) {
                        if(lastMob == null){
                            lastMob = target;
                        }
                        if(target.getEntityId() != lastMob.getEntityId()){
                            CopperedAppleInteractionCount = 0;
                            System.out.println("Hit the wrongo");
                        }
                        lastMob = target;
                        CopperedAppleInteractionCount += 1;
                        target.heal(5f);
                        //This removes the item used to hit sheep on event
                        player.getHeldItemMainhand().shrink(1);
                        if(CopperedAppleInteractionCount >= 20){
                            String msg = TextFormatting.YELLOW + "Animal is ready to pickup";
                            player.sendMessage(new StringTextComponent(msg),player.getUniqueID());
                            player.world.playSound(player, new BlockPos(player.getPosX(),
                                            player.getPosY(),player.getPosZ()),
                                    SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.HOSTILE,
                                    100f, 50f);
                            animal.setCanPickUpLoot(true);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPickupableMobPickup(LivingEquipmentChangeEvent event) {
        Entity target = event.getEntity();
        if(event.getTo().getItem().getName().getString().equals(new String("Block of Diamond"))){
            pickedUpDiamondBlock = target;
            if(!(target instanceof PlayerEntity)){
                target.remove();
            }
        }
    }

    /* Helper Method */
    /*private static void dropItemInMoundClickEvent(PlayerInteractEvent.RightClickBlock event) {
        BlockPos block = event.getPos();
        World world = event.getWorld();
        MoundTile tileEntity = (MoundTile) world.getTileEntity(block);
        FullMound.SwordMound mound = (FullMound.SwordMound) world.getBlockState(block).getBlock();
        ItemEntity drop = new ItemEntity(world, block.getX(), block.getY(),
                block.getZ(), tileEntity.getToDrop().getDefaultInstance());
        world.addEntity(drop);
        tileEntity.onDroppedItem();
    }*/

    /* Helper for onMoundRightClick*/
    /*private static void moundSwordPlace( PlayerInteractEvent.RightClickBlock event){
        BlockPos block = event.getPos();
        event.getWorld().playSound(block.getX(),block.getY(),block.getZ(),
                SoundEvents.BLOCK_ANVIL_PLACE,SoundCategory.BLOCKS,
                100f,50f,false);
        event.setUseBlock(Event.Result.DENY);
        event.setUseItem(Event.Result.DENY);
        Direction oldDirection = event.getWorld().getBlockState(block)
                .get(HorizontalBlock.HORIZONTAL_FACING);
        SwordItem sword = (SwordItem)event.getItemStack().getItem();
        FullMound.SwordMound swordMound;
        switch(sword.getTier().toString()){
            case "COPPER":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.COPPER_SWORD_MOUND.get().getBlock();
                break;
            case "DIAMOND":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.DIAMOND_SWORD_MOUND.get().getBlock();
                break;
            case "GOLD":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.GOLDEN_SWORD_MOUND.get().getBlock();
                break;
            case "STONE":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.STONE_SWORD_MOUND.get().getBlock();
                break;
            case "WOOD":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.WOODEN_SWORD_MOUND.get().getBlock();
                break;
            case "IRON":
                swordMound =
                        (FullMound.SwordMound)ModBlocks.IRON_SWORD_MOUND.get().getBlock();
                break;
            default:  //Netherite
                swordMound =
                        (FullMound.SwordMound)ModBlocks.NETHERITE_SWORD_MOUND.get().getBlock();
                break;
        }
        switch(oldDirection){
            case EAST:
                event.getWorld().setBlockState(block,
                        swordMound.rotate(swordMound.getDefaultState(),
                                Rotation.CLOCKWISE_90));
                break;
            case SOUTH:
                event.getWorld().setBlockState(block,
                        swordMound.rotate(swordMound.getDefaultState(),
                                Rotation.CLOCKWISE_180));
                break;
            case WEST:
                event.getWorld().setBlockState(block,
                        swordMound.rotate(swordMound.getDefaultState(),
                                Rotation.COUNTERCLOCKWISE_90));
                break;
            default:
                event.getWorld().setBlockState(block,
                        swordMound.getDefaultState());
                break;
        }
        event.getWorld().getTileEntity(block).write(Objects.requireNonNull
                (sword.getShareTag(event.getItemStack())));
        event.getPlayer().getHeldItemMainhand().shrink(1);
    }

    @SubscribeEvent
    public void onMoundRightClick(PlayerInteractEvent.RightClickBlock event){
        BlockPos block = event.getPos();
        World world = event.getWorld();
        if(world.getBlockState(block).getBlock() instanceof EmptyMound){
            if(world.getBlockState(block).getBlock().getDefaultState()
                    .equals(ModBlocks.EMPTY_MOUND.get().getDefaultState())){
                if(event.getItemStack().getItem() instanceof SwordItem){
                    moundSwordPlace(event);
                }
            }
            else{
                if(event.getItemStack().getItem() instanceof SwordItem) {
                    FullMound.SwordMound mound = (FullMound.SwordMound)world
                            .getBlockState(block).getBlock();
                    moundSwordPlace(event);
                    dropItemInMoundClickEvent(event);
                }
            }
        }
    }*/
}
