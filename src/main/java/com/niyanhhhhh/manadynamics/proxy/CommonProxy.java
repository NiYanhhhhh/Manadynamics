package com.niyanhhhhh.manadynamics.proxy;

import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public CommonProxy() {
    }

    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(ManaConnectHandler.class, new ManaConnectHandler.IStorageManaConnect(), ManaConnectHandler::new);
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
