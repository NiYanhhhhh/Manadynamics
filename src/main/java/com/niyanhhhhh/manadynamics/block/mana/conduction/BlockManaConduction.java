package com.niyanhhhhh.manadynamics.block.mana.conduction;

import com.niyanhhhhh.manadynamics.block.mana.BlockTileFlower;
import com.niyanhhhhh.manadynamics.tile.TileManaConduction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class BlockManaConduction extends BlockTileFlower {

    private static final String NAME = "mana_conduction";

    public BlockManaConduction() {
        super(NAME);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileManaConduction();
    }
}
