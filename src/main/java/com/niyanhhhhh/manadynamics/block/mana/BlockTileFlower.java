package com.niyanhhhhh.manadynamics.block.mana;

import com.niyanhhhhh.manadynamics.Main;
import com.niyanhhhhh.manadynamics.block.Blocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class BlockTileFlower extends BlockFlower implements ITileEntityProvider {

    public BlockTileFlower(String name) {
        setTranslationKey(Main.MODID + "." + name);
        setRegistryName(Main.MODID + ":" + name);

        Blocks.BLOCKS.add(this);
    }

    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public void harvestBlock(@Nonnull World world, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public boolean eventReceived(IBlockState state, World world, BlockPos pos, int par5, int par6) {
        super.eventReceived(state, world, pos, par5, par6);
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(par5, par6);
    }

    @Nonnull
    @Override
    public EnumFlowerColor getBlockType() {
        return EnumFlowerColor.YELLOW;
    }
}
