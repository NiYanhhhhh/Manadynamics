package com.niyanhhhhh.manadynamics.block.mana.conduction;

import com.niyanhhhhh.manadynamics.handler.capability.CapabilityHandler;
import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class TileManaConnect extends TileEntity implements IManaReceiver {

    private static final int MAX_MANA = 1000000;
    private static final int MAX_LINK = 4;

    private final ManaConnectHandler manaConnect = new ManaConnectHandler(getMaxMana(), getMaxLink());

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        getManaConnect().deserializeNBT(compound.getCompoundTag("ManaConnect"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("ManaConnect", getManaConnect().serializeNBT());
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
            return CapabilityHandler.MANA_CONNECT_CAPABILITY.cast(getManaConnect());
        }
        return getCapability(capability, facing);
    }

    @Override
    public boolean isFull() {
        return getManaConnect().isFull();
    }

    @Override
    public void recieveMana(int mana) {
        getManaConnect().addMana(mana);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !isFull();
    }

    @Override
    public int getCurrentMana() {
        return getManaConnect().getMana();
    }

    public boolean checkExtractPosIsManaConnect(BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        return te instanceof TileManaConnect;
    }

    public boolean setExtractPos(BlockPos pos) {
        if (checkExtractPosIsManaConnect(pos)) {
            getManaConnect().setExtractPos(pos);
        }
        return checkExtractPosIsManaConnect(pos);
    }

    public TileManaConnect getTileExtract() {
        return (TileManaConnect) world.getTileEntity(getManaConnect().getExtractPos());
    }

    public boolean extractMana(int mana) {
        if (getTileExtract().getManaConnect().getMana() > mana && isExistExtractPos()) {
            getManaConnect().addMana(mana);
            getTileExtract().getManaConnect().consumeMana(mana);
            return true;
        }
        return false;
    }

    public boolean isExistExtractPos() {
        return Objects.nonNull(getManaConnect().getExtractPos());
    }

    public ManaConnectHandler getManaConnect() {
        return this.manaConnect;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public int getMaxLink() {
        return MAX_LINK;
    }
}
