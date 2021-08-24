package com.niyanhhhhh.manadynamics.tile;

import com.niyanhhhhh.manadynamics.handler.capability.CapabilityHandler;
import com.niyanhhhhh.manadynamics.handler.capability.IManaConnectHandler;
import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraft.nbt.NBTTagCompound;
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

    private final ManaConnectHandler manaConnect = new ManaConnectHandler(MAX_MANA, MAX_LINK) {
        @Override
        public boolean isAllowInput() {
            return false;
        }

        @Override
        public boolean isAllowOutput() {
            return true;
        }
    };

    @Override
    public void update() {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.manaConnect.deserializeNBT(compound.getCompoundTag("ManaConnect"));
    }


    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("ManaConnect", this.manaConnect.serializeNBT());
        this.markDirty();
        return compound;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityHandler.MANA_CONNECT_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityHandler.MANA_CONNECT_CAPABILITY) {
            return CapabilityHandler.MANA_CONNECT_CAPABILITY.cast(new ManaConduction(facing));
        }
        return getCapability(capability, facing);
    }

    @Override
    public boolean isFull() {
        return manaConnect.isFull();
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

    public class ManaConduction implements IManaConnectHandler {

        private final EnumFacing facing;

        public ManaConduction(EnumFacing facing) {
            this.facing = facing;
        }

        @Override
        public int getMaxMana() {
            return manaConnect.getMaxMana();
        }

        @Override
        public int getMaxLinks() {
            return manaConnect.getMaxLinks();
        }

        @Override
        public boolean isAllowInput() {
            return facing == EnumFacing.UP;
        }

        @Override
        public boolean isAllowOutput() {
            return facing != EnumFacing.UP && facing != EnumFacing.DOWN;
        }
    }
}
