package com.niyanhhhhh.manadynamics.handler.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CapabilityHandler {

    @CapabilityInject(ManaConnectHandler.class)
    public static Capability<ManaConnectHandler> MANA_CONNECT_CAPABILITY;

}
