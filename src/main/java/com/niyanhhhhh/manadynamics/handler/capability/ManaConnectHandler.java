package com.niyanhhhhh.manadynamics.handler.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public class ManaConnectHandler implements INBTSerializable<NBTTagCompound> {

    private int mana;
    private int links;
    private BlockPos extractPos;

    protected int maxMana;
    protected int maxLinks;
    protected boolean allowLink;
    protected boolean allowInput = true;
    protected boolean allowOutput = true;

    public ManaConnectHandler() {
    }

    public ManaConnectHandler(int maxMana, int maxLinks) {
        this.maxMana = maxMana;
        this.maxLinks = maxLinks;
    }

    public int getLinks() {
        return links;
    }

    public boolean addLink() {
        if (links > maxLinks) {
            links++;
            return true;
        }
        return false;
    }

    public boolean reduceLink() {
        if (links > 0) {
            links--;
            return true;
        }
        return false;
    }

    public int getMana() {
        return mana;
    }

    public void addMana(int mana) {
        this.mana = Math.min(getMaxMana(), this.mana + mana);
    }

    public void consumeMana(int mana) {
        this.mana = Math.max(0, this.mana - mana);
    }

    public boolean isFull() {
        return mana >= maxMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMaxLinks() {
        return maxLinks;
    }

    public boolean isAllowLink() {
        return allowLink;
    }

    public boolean isAllowInput() {
        return allowInput;
    }

    public boolean isAllowOutput() {
        return allowOutput;
    }

    public BlockPos getExtractPos() {
        return extractPos;
    }

    public void setExtractPos(BlockPos extractPos) {
        this.extractPos = extractPos;
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    private void setLinks(int links) {
        this.links = links;
    }

    private void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    private void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }

    private void setAllowLink(boolean allowLink) {
        this.allowLink = allowLink;
    }

    private void setAllowInput(boolean allowInput) {
        this.allowInput = allowInput;
    }

    private void setAllowOutput(boolean allowOutput) {
        this.allowOutput = allowOutput;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Mana", getMana());
        nbt.setInteger("Links", getLinks());
        nbt.setInteger("MaxMana", getMaxMana());
        nbt.setInteger("MaxLink", getMaxLinks());
        nbt.setBoolean("AllowLink", isAllowLink());
        nbt.setBoolean("AllowInput", isAllowInput());
        nbt.setBoolean("AllowOutput", isAllowOutput());

        if (this.extractPos != null) {
            NBTTagCompound extract = new NBTTagCompound();
            nbt.setTag("Extract", extract);
            extract.setInteger("x", getExtractPos().getX());
            extract.setInteger("y", getExtractPos().getY());
            extract.setInteger("z", getExtractPos().getZ());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setMana(nbt.getInteger("Mana"));
        setLinks(nbt.getInteger("Links"));
        setMaxMana(nbt.getInteger("MaxMana"));
        setMaxLinks(nbt.getInteger("MaxLink"));
        setAllowLink(nbt.getBoolean("AllowLink"));
        setAllowInput(nbt.getBoolean("AllowInput"));
        setAllowOutput(nbt.getBoolean("AllowOutput"));

        if (nbt.hasKey("Extract")) {
            NBTTagCompound extract = (NBTTagCompound) nbt.getTag("Extract");
            setExtractPos(new BlockPos(extract.getInteger("x"), extract.getInteger("y"), extract.getInteger("z")));
        }
    }

}
