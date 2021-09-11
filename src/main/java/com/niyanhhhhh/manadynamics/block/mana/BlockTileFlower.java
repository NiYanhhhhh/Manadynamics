package com.niyanhhhhh.manadynamics.block.mana;

import com.niyanhhhhh.manadynamics.Main;
import com.niyanhhhhh.manadynamics.block.Blocks;
import com.niyanhhhhh.manadynamics.block.mana.conduction.TileManaConnect;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.handler.ModelHandler;
import vazkii.botania.client.render.IModelRegister;

import javax.annotation.Nonnull;
import java.util.Objects;

public abstract class BlockTileFlower extends BlockBush implements ITileEntityProvider, IWandable, IModelRegister {

    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.3, 0, 0.3, 0.8, 1, 0.8);

    public BlockTileFlower(String name) {

        setTranslationKey(Main.MODID + "." + name);
        setRegistryName(new ResourceLocation(Main.MODID, name));
        setHardness(0.1F);
        setSoundType(SoundType.PLANT);
        setTickRandomly(false);

        Blocks.BLOCKS.add(this);
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return AABB.offset(state.getOffset(world, pos));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels() {
        if (Item.getItemFromBlock(this) != Items.AIR)
            ModelHandler.registerBlockToState(this, 0, getDefaultState());
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

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, BlockPos pos, EnumFacing side) {
        return ((TileManaConnect) Objects.requireNonNull(world.getTileEntity(pos))).onWanded(stack, player);
    }

}
