package com.joey.spellcraft.spells;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.utility.StaffUtility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class ToggleDoorSpell {
    public static void run(PlayerEntity playerIn, World worldIn) {
        int maxRange = 20;

        Block targetBlock = StaffUtility.getBlockInAimDirection(maxRange, playerIn, worldIn);
        ResourceLocation doorsResourceLocation = new ResourceLocation("minecraft", "doors");


        if (Objects.requireNonNull(BlockTags.getCollection().get(doorsResourceLocation)).contains(targetBlock)) {
            BlockPos targetBlockPosition = StaffUtility.getBlockPositionInAimDirection(maxRange, playerIn, worldIn);
            BlockState targetBlockState = worldIn.getBlockState(targetBlockPosition);

            Property<Boolean> openDoorProperty = BooleanProperty.create("open");

            BlockState newBlockState = targetBlockState.with(openDoorProperty, !targetBlockState.get(openDoorProperty));

            worldIn.setBlockState(targetBlockPosition, newBlockState);
        }
    }


}
