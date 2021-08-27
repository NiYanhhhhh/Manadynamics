package com.niyanhhhhh.manadynamics.block.mana.conduction;

import com.niyanhhhhh.manadynamics.block.mana.BlockTileFlower;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class BlockManaConduction extends BlockTileFlower {

    private static final String NAME = "mana_conduction";

    public BlockManaConduction() {
        super(NAME);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileManaConduction();
    }
}
