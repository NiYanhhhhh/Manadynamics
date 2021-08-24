package com.niyanhhhhh.manadynamics.block.mana;

import com.niyanhhhhh.manadynamics.Main;
import com.niyanhhhhh.manadynamics.block.Blocks;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import vazkii.botania.client.core.handler.ModelHandler;
import vazkii.botania.client.render.IModelRegister;

import javax.annotation.Nonnull;

public abstract class ManaBlockBase extends BlockContainer implements IModelRegister {

    protected ManaBlockBase(Material materialIn, String name) {
        super(materialIn);

        setTranslationKey(Main.MODID + "." + name);
        setRegistryName(Main.MODID + ":" + name);

        Blocks.BLOCKS.add(this);
    }

    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(@Nonnull IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void registerModels() {
        if (Item.getItemFromBlock(this) != Items.AIR)
            ModelHandler.registerBlockToState(this, 0, getDefaultState());
    }
}
