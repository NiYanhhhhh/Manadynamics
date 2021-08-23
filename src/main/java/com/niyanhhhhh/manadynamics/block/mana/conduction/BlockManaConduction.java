package com.niyanhhhhh.manadynamics.block.mana.conduction;

import com.niyanhhhhh.manadynamics.block.mana.ManaBlockBase;
import com.niyanhhhhh.manadynamics.tile.TileManaConduction;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class BlockManaConduction extends ManaBlockBase {

    private static final String NAME = "mana_conduction";

    public BlockManaConduction() {
        super(Material.IRON, NAME);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileManaConduction();
    }
}
