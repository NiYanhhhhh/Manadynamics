package com.niyanhhhhh.manadynamics.proxy;

import com.niyanhhhhh.manadynamics.handler.capability.IManaConnectHandler;
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
        CapabilityManager.INSTANCE.register(IManaConnectHandler.class, new Capability.IStorage<IManaConnectHandler>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IManaConnectHandler> capability, IManaConnectHandler instance, EnumFacing side) {
                if (!(instance instanceof ManaConnectHandler))
                    throw new RuntimeException("IManaConnectHandler instance does not implement ManaConnectHandler");
                return ((ManaConnectHandler) instance).serializeNBT();
            }

            @Override
            public void readNBT(Capability<IManaConnectHandler> capability, IManaConnectHandler instance, EnumFacing side, NBTBase nbt) {
                if (!(instance instanceof ManaConnectHandler))
                    throw new RuntimeException("IManaConnectHandler instance does not implement ManaConnectHandler");
                ((ManaConnectHandler) instance).deserializeNBT((NBTTagCompound) nbt);
            }
        }, ManaConnectHandler::new);
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
