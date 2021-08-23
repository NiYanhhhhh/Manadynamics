package com.niyanhhhhh.manadynamics.handler.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CapabilityHandler {

    @CapabilityInject(IManaConnectHandler.class)
    public static Capability<IManaConnectHandler> MANA_CONNECT_CAPABILITY;

}
