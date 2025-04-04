package com.direwolf20.justdirethings.common.blocks.soil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class GooSoilTier2 extends GooSoilBase {
    public GooSoilTier2() {
        super();
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        for (int i = 0; i < 2; i++) {
            bonemealMe(pLevel, pPos);
        }
        autoHarvest(pLevel, pPos);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && level instanceof ServerLevel serverLevel) {
            autoHarvest(serverLevel, currentPos);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }
}
