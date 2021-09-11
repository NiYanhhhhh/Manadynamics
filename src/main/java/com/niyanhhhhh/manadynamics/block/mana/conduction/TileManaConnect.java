package com.niyanhhhhh.manadynamics.block.mana.conduction;

import com.niyanhhhhh.manadynamics.handler.capability.CapabilityHandler;
import com.niyanhhhhh.manadynamics.handler.capability.ManaConnectHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.wand.IWandBindable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class TileManaConnect extends TileEntity implements IManaReceiver, IWandBindable {

    private static final int MAX_MANA = 1000000;
    private static final int MAX_LINK = 4;

    private int knowMana = -1;

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
        if (Objects.nonNull(getManaConnect().getExtractPos())) {
            return (TileManaConnect) world.getTileEntity(getManaConnect().getExtractPos());
        }
        return null;
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
        return Objects.nonNull(getTileExtract());
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

    public boolean onWanded(ItemStack stack, EntityPlayer player) {
        if (Objects.isNull(player))
            return false;
        if(!player.world.isRemote)
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);

        knowMana = getManaConnect().getMana();
        SoundEvent evt = ForgeRegistries.SOUND_EVENTS.getValue(SubTileEntity.DING_SOUND_EVENT);
        if (evt != null)
            player.playSound(evt, 0.1F, 1F);

        return false;
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public BlockPos getBinding() {
        return getManaConnect().getExtractPos();
    }
}
