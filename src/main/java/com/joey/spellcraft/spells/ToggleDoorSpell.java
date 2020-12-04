package com.joey.spellcraft.spells;

import com.joey.spellcraft.items.ISpellCastingItem;
import com.joey.spellcraft.utility.SpellUtility;
import com.joey.spellcraft.utility.WandUtility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class ToggleDoorSpell extends Spell {

    ToggleDoorSpell(String name, String className, int id) {
        super(name, className, id);
    }

    public void run(PlayerEntity player, World world) {
        int maxRange = 20;

        ItemStack wandStack =  WandUtility.getPlayerSpellCastingItemStack(player);
        ISpellCastingItem wand = (ISpellCastingItem) wandStack.getItem();

        int maxRangeWithAddedRange = maxRange + wand.addedRange;

        Block targetBlock = SpellUtility.getBlockInAimDirection(maxRangeWithAddedRange, player, world);
        ResourceLocation doorsResourceLocation = new ResourceLocation("minecraft", "doors");


        if (Objects.requireNonNull(BlockTags.getCollection().get(doorsResourceLocation)).contains(targetBlock)) {
            BlockPos targetBlockPosition = SpellUtility.getBlockPositionInAimDirection(maxRangeWithAddedRange, player, world);
            BlockState targetBlockState = world.getBlockState(targetBlockPosition);

            Property<Boolean> openDoorProperty = BooleanProperty.create("open");

            BlockState newBlockState = targetBlockState.with(openDoorProperty, !targetBlockState.get(openDoorProperty));

            world.setBlockState(targetBlockPosition, newBlockState);
        }
    }
}
