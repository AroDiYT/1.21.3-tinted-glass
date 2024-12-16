package org.vanharneveldt.tinted_glass.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.vanharneveldt.tinted_glass.sound.ModSounds;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class LampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public LampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide())
        {
            boolean currentState = state.getValue(CLICKED);
            level.playSound(null, pos ,ModSounds.SWORD_USE.get(), SoundSource.BLOCKS);
            level.setBlockAndUpdate(pos, state.setValue(CLICKED, !currentState));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @javax.annotation.Nullable Orientation orientation, boolean movedByPiston) {
        if(!level.isClientSide())
        {
            boolean flag = level.hasNeighborSignal(pos);
            if (flag != state.getValue(POWERED)) {
                if (flag) {
                        level.playSound(null, pos ,ModSounds.SWORD_USE.get(), SoundSource.BLOCKS);
                }
            }

            level.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(flag)), 3);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED, POWERED);
    }
}
