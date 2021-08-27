package com.niyanhhhhh.manadynamics.handler.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.item.ItemTwigWand;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void onTwigWandRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        IBlockState blockState = world.getBlockState(pos);

        if (stack.isEmpty() || !(stack.getItem() instanceof ItemTwigWand) || world.isRemote) return;

        if (player.isSneaking()) {

        } else {

        }

        // Waiting for niyan reply how to achieve this function.
    }
}
