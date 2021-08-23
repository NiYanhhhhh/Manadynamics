package com.niyanhhhhh.manadynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import vazkii.botania.client.core.handler.ModelHandler;
import vazkii.botania.client.render.IModelRegister;

public class BlockBase extends Block implements IModelRegister {

    public BlockBase(Material material, String name) {
        super(material);
        setTranslationKey(name);

        Blocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {
        if(Item.getItemFromBlock(this) != Items.AIR)
            ModelHandler.registerBlockToState(this, 0, getDefaultState());
    }
}
