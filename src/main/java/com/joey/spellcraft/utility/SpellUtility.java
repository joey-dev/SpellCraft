package com.joey.spellcraft.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class SpellUtility {

    public static List<Entity> getEntitiesInAimDirection(int range, PlayerEntity player, World world,
                                                         @Nullable Predicate<? super Entity> predicate) {
        final ArrayList<Entity> res = new ArrayList<>();

        final Predicate<? super Entity> nonNullPredicate = predicate != null ? predicate : (v) -> true;

        iterateLinearAimDirection(range, player, (vec) -> {
            final AxisAlignedBB section = new AxisAlignedBB(
                    vec.x - 1, vec.y - 1, vec.z - 1,
                    vec.x + 1, vec.y + 1, vec.z + 1);

            if (!world.getBlockState(new BlockPos(vec.x, vec.y, vec.z)).getBlock().equals(Blocks.AIR)) {
                return false;
            }

            res.addAll(world.getEntitiesInAABBexcluding(player, section, nonNullPredicate));

            return true;
        });

        return res;
    }

    public static Block getBlockInAimDirection(int range, PlayerEntity player, World world) {
        final AtomicReference<Block> res = new AtomicReference<>();

        iterateLinearAimDirection(range, player, (vec) -> {
            final Block cBlock = world.getBlockState(new BlockPos(vec.x, vec.y, vec.z)).getBlock();

            if (cBlock.equals(Blocks.AIR)) {
                return true;
            }

            res.set(cBlock);
            return false;
        });

        return res.get();
    }

    public static BlockPos getBlockPositionInAimDirection(int range, PlayerEntity player, World world) {
        final AtomicReference<BlockPos> res = new AtomicReference<>();

        iterateLinearAimDirection(range, player, (vec) -> {
            final BlockPos blockPosition = new BlockPos(vec.x, vec.y, vec.z);

            if (world.getBlockState(blockPosition).getBlock().equals(Blocks.AIR)) {
                return true;
            }

            res.set(blockPosition);
            return false;
        });

        return res.get();
    }

    public static void iterateLinearAimDirection(int range, PlayerEntity player,
                                                 Predicate<Vector3d> callback) {
        final Vector3d playerPos = player.getEyePosition(1);
        final Vector3d aim = player.getLookVec();

        for (int i = 0; i < range; i++) {
            final Vector3d cPos = new Vector3d(
                    playerPos.x + (aim.x * i),
                    playerPos.y + (aim.y * i),
                    playerPos.z + (aim.z * i));

            if (!callback.test(cPos))
                break;
        }
    }
}
