package com.niyanhhhhh.manadynamics;

import com.niyanhhhhh.manadynamics.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {

    public static final String MODID = "@MODID@";
    public static final String NAME = "@MODNAME@";
    public static final String VERSION = "@VERSION@";
    public static final String CLIENT_PORXY_CLASS = "com.niyanhhhhh.manadynamics.proxy.ClientProxy";
    public static final String COMMON_PORXY_CLASS = "com.niyanhhhhh.manadynamics.proxy.CommonProxy";

    private static Logger logger = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = CLIENT_PORXY_CLASS, serverSide = COMMON_PORXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
