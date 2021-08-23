package com.niyanhhhhh.manadynamics.tile;

import com.niyanhhhhh.manadynamics.handler.capability.CapabilityHandler;
import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileManaConduction extends TileEntity implements ITickable, IManaReceiver {

    private static final int MAX_MANA = 1000000;
    private static final int MAX_LINK = 4;

    private final ManaConnectHandler manaConnect = new ManaConnectHandler(MAX_LINK, false, true);

    @Override
    public void update() {

    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityHandler.MANA_CONNECT_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityHandler.MANA_CONNECT_CAPABILITY) {
            return CapabilityHandler.MANA_CONNECT_CAPABILITY.cast(manaConnect);
        }
        return getCapability(capability, facing);
    }

    @Override
    public boolean isFull() {
        return manaConnect.getMana() >= MAX_MANA;
    }

    @Override
    public void recieveMana(int mana) {
        manaConnect.addMana(mana);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !isFull();
    }

    @Override
    public int getCurrentMana() {
        return manaConnect.getMana();
    }
}
