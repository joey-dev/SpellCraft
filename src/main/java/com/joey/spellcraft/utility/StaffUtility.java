package com.joey.spellcraft.utility;

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
import java.util.function.Predicate;

public class StaffUtility {

    public static List<Entity> getEntitiesInAimDirection(int range, PlayerEntity player, World world,
                                                         @Nullable Predicate<? super Entity> predicate) {
        final ArrayList<Entity> res = new ArrayList<>();

        final Predicate<? super Entity> nonNullPredicate = predicate != null ? predicate : (v) -> true;

        iterateLinearAimDirection(range, player, (vec) -> {
            final AxisAlignedBB section = new AxisAlignedBB(
                    vec.x - 1, vec.y - 1, vec.z - 1,
                    vec.x + 1, vec.y + 1, vec.z + 1);

            if (!world.getBlockState(new BlockPos(vec.x, vec.y, vec.z)).getBlock().equals(Blocks.AIR))
                return false;

            res.addAll(world.getEntitiesInAABBexcluding(player, section, nonNullPredicate::test));

            return true;
        });

        return res;
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
