package com.joey.spellcraft.spells;

import com.joey.spellcraft.utility.StaffUtility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class FlameThrowerSpell {
    public static void run(PlayerEntity playerIn, World worldIn) {
        int maxRange = 10;
        int burnDuration = 5;

        StaffUtility.getEntitiesInAimDirection(maxRange, playerIn, worldIn, entity -> entity instanceof LivingEntity)
                .forEach(entity ->
                        entity.setFire(burnDuration));
    }
}
