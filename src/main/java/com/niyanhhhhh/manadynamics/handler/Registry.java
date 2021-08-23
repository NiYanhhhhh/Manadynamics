package com.niyanhhhhh.manadynamics.handler;

import com.niyanhhhhh.manadynamics.block.Blocks;
import com.niyanhhhhh.manadynamics.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.botania.client.render.IModelRegister;

@Mod.EventBusSubscriber
public class Registry {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        r.registerAll(Items.ITEMS.toArray(new Item[0]));
        r.registerAll(Blocks.BLOCKS.stream().map(ItemBlock::new).toArray(ItemBlock[]::new));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        r.registerAll(Blocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Blocks.BLOCKS.stream().filter(block -> block instanceof IModelRegister)
                .forEach(block -> ((IModelRegister) block).registerModels());
        Items.ITEMS.stream().filter(item -> item instanceof IModelRegister)
                .forEach(item -> ((IModelRegister) item).registerModels());
    }

}