package com.niyanhhhhh.manadynamics.item;

import com.niyanhhhhh.manadynamics.Main;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.client.render.IModelRegister;

public class ItemBase extends Item implements IModelRegister {

    public ItemBase(String name) {
        setRegistryName(new ResourceLocation(Main.MODID, name));
        setTranslationKey(name);

        Items.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
