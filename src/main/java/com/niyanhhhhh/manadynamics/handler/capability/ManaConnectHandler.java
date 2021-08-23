package com.niyanhhhhh.manadynamics.handler.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public class ManaConnectHandler implements IManaConnectHandler, INBTSerializable<NBTTagCompound> {

    protected int mana;
    protected int maxLink;
    protected boolean allowInput;
    protected boolean allowOutput;

    private BlockPos extract;

    public ManaConnectHandler() {
    }

    public ManaConnectHandler(int maxLink) {
        this.maxLink = maxLink;
    }

    public ManaConnectHandler(int maxLink, boolean allowInput, boolean allowOutput) {
        this.maxLink = maxLink;
        this.allowInput = allowInput;
        this.allowOutput = allowOutput;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void addMana(int mana) {
        this.mana += mana;
    }

    public int getMaxLink() {
        return maxLink;
    }

    public void setMaxLink(int maxLink) {
        this.maxLink = maxLink;
    }

    public boolean isAllowInput() {
        return allowInput;
    }

    public void setAllowInput(boolean allowInput) {
        this.allowInput = allowInput;
    }

    public boolean isAllowOutput() {
        return allowOutput;
    }

    public void setAllowOutput(boolean allowOutput) {
        this.allowOutput = allowOutput;
    }

    public BlockPos getExtract() {
        return extract;
    }

    public void setExtract(BlockPos extract) {
        this.extract = extract;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Mana", getMana());
        nbt.setInteger("MaxLink", getMaxLink());
        nbt.setBoolean("AllowInput", isAllowInput());
        nbt.setBoolean("AllowOutput", isAllowOutput());

        if (this.extract != null) {
            NBTTagCompound extract = new NBTTagCompound();
            nbt.setTag("Extract", extract);
            extract.setInteger("x", getExtract().getX());
            extract.setInteger("y", getExtract().getY());
            extract.setInteger("z", getExtract().getZ());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setMana(nbt.getInteger("Mana"));
        setMaxLink(nbt.getInteger("MaxLink"));
        setAllowInput(nbt.getBoolean("AllowInput"));
        setAllowOutput(nbt.getBoolean("AllowOutput"));

        if (nbt.hasKey("Extract")) {
            NBTTagCompound extract = (NBTTagCompound) nbt.getTag("Extract");
            setExtract(new BlockPos(extract.getInteger("x"), extract.getInteger("y"), extract.getInteger("z")));
        }
    }

    public static class IStorageManaConnect implements IStorage<ManaConnectHandler> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<ManaConnectHandler> capability, ManaConnectHandler instance, EnumFacing side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<ManaConnectHandler> capability, ManaConnectHandler instance, EnumFacing side, NBTBase nbt) {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }

    }

}
