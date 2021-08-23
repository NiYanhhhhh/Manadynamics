package com.niyanhhhhh.manadynamics.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ingotOrichalcum = new ItemIngotOrichalcum();

}
