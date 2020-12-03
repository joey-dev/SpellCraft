package com.joey.spellcraft.items;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.init.ModItems;
import com.joey.spellcraft.spells.FlameThrowerSpell;
import com.joey.spellcraft.spells.ToggleDoorSpell;
import net.minecraft.command.arguments.NBTTagArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BasicWand extends Item {
    public int maxAmountOfSpells = 10;

    public BasicWand() {
        super(
            new Item.Properties()
                .group(SpellCraft.TAB)
                .maxStackSize(1)
        );
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        if (!worldIn.isRemote) {
            SpellCraft.LOGGER.info("Basic wand has been created.");

            if (!stack.hasTag()) {
                stack.setTag(new CompoundNBT());
            }

            int[] spells = {};
            setSpells(stack, spells);
            setCurrentSpellIndex(stack, 0);

            tempSetSpells(stack);
        }

        super.onCreated(stack, worldIn, playerIn);
    }

    private void tempSetSpells(ItemStack stack) {
        int[] spellsOnWand = {1, 0};

        setSpells(stack, spellsOnWand);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && playerIn.getHeldItemMainhand().getItem() == ModItems.BASIC_WAND.get()) {
            ItemStack wand = playerIn.getHeldItemMainhand();

            int currentSpellIndex = getCurrentSpellIndex(wand);

            SpellCraft.LOGGER.info("Current spell index: " + currentSpellIndex);
            switch (currentSpellIndex) {
                case 0:
                    FlameThrowerSpell.run(playerIn, worldIn);
                    break;
                case 1:
                    ToggleDoorSpell.run(playerIn, worldIn);
                    break;
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private static int[] getSpells(ItemStack wand) {
        return wand.getOrCreateTag().getIntArray("spellsOnWand");
    }

    private static void setSpells(ItemStack wand, int[] spells) {
        wand.getOrCreateTag().putIntArray("spellsOnWand", spells);
    }

    private static int getCurrentSpellIndex(ItemStack wand) {
        return wand.getOrCreateTag().getInt("selectedSpellIndex");
    }

    private static void setCurrentSpellIndex(ItemStack wand, int spellIndex) {
        wand.getOrCreateTag().putInt("selectedSpellIndex", spellIndex);
    }

    public static void nextSpell(ItemStack wand) {
        int currentSpellIndex = getCurrentSpellIndex(wand);
        int[] spells = getSpells(wand);

        if (currentSpellIndex + 1 >= spells.length) {
            setCurrentSpellIndex(wand, 0);
        } else {
            setCurrentSpellIndex(wand, currentSpellIndex + 1);
        }
    }

    public static void previousSpell(ItemStack wand) {
        int currentSpellIndex = getCurrentSpellIndex(wand);
        int[] spells = getSpells(wand);

        if (currentSpellIndex - 1 < 0) {
            setCurrentSpellIndex(wand, spells.length - 1);
        } else {
            setCurrentSpellIndex(wand, currentSpellIndex - 1);
        }
    }

}
