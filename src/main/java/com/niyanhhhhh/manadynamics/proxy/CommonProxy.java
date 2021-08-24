package com.niyanhhhhh.manadynamics.proxy;

import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nullable;

public class CommonProxy {

    public CommonProxy() {
    }

    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(ManaConnectHandler.class, new Capability.IStorage<ManaConnectHandler>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<ManaConnectHandler> capability, ManaConnectHandler instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ManaConnectHandler> capability, ManaConnectHandler instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, ManaConnectHandler::new);
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
