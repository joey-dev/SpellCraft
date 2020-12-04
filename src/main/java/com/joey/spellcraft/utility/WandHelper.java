package com.joey.spellcraft.utility;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.spells.Spell;
import com.joey.spellcraft.spells.Spells;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class WandHelper {
    public static void onCreateCastingItem(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            if (!stack.hasTag()) {
                stack.setTag(new CompoundNBT());
            }

            int[] spells = {};
            setSpells(stack, spells);
            setCurrentSpellId(stack, 0);

            tempSetSpells(stack);
        }
    }

    private static void tempSetSpells(ItemStack stack) {
        int[] spellsOnWand = {1, 0};

        setSpells(stack, spellsOnWand);
    }

    public static void onCastSpell(World world, PlayerEntity player) {

        if (!world.isRemote && WandUtility.playerIsHoldingASpellCastingItem(player)) {
            ItemStack wand = WandUtility.getPlayerSpellCastingItemStack(player);

            SpellCraft.LOGGER.info("Spell id: " + getCurrentSpellId(wand));
            getCurrentSpell(wand).run(player, world);
        }
    }

    public static int[] getSpellIds(ItemStack wand) {
        return wand.getOrCreateTag().getIntArray(Spell.SPELLS_ON_WAND);
    }

    public static Spell[] getSpells(ItemStack wand) {
        int[] spellIds = getSpellIds(wand);
        return Spells.getSpellsById(spellIds);
    }

    public static void setSpells(ItemStack wand, int[] spells) {
        wand.getOrCreateTag().putIntArray(Spell.SPELLS_ON_WAND, spells);
    }

    public static Spell getCurrentSpell(ItemStack wand) {
        int currentSpellId = getCurrentSpellId(wand);
        return Spells.getSpellById(currentSpellId);
    }

    public static int getCurrentSpellId(ItemStack wand) {
        return wand.getOrCreateTag().getInt(Spell.SELECTED_SPELL_ID);
    }

    public static void setCurrentSpellId(ItemStack wand, int spellId) {
        wand.getOrCreateTag().putInt(Spell.SELECTED_SPELL_ID, spellId);
    }

    public static void nextSpell(ItemStack wand) {
        int currentSpellId = getCurrentSpellId(wand);
        Spell[] spells = getSpells(wand);

        if (currentSpellId + 1 >= spells.length) {
            setCurrentSpellId(wand, 0);
        } else {
            setCurrentSpellId(wand, currentSpellId + 1);
        }
    }

    public static void previousSpell(ItemStack wand) {
        int currentSpellId = getCurrentSpellId(wand);
        Spell[] spells = getSpells(wand);

        if (currentSpellId - 1 < 0) {
            setCurrentSpellId(wand, spells.length - 1);
        } else {
            setCurrentSpellId(wand, currentSpellId - 1);
        }
    }
}
