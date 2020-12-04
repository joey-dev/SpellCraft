package com.joey.spellcraft.items;

import com.joey.spellcraft.spells.Spell;
import net.minecraft.item.ItemStack;

public interface ISpellCastingItem {
    int maxAmountOfSpells = 5;
    int addedRange = 0;

    Spell[] getSpells(ItemStack wand);
    void setSpells(ItemStack wand, int[] spells);
    int getCurrentSpellId(ItemStack wand);
    void setCurrentSpellId(ItemStack wand, int spellIndex);
    void nextSpell(ItemStack wand);
    void previousSpell(ItemStack wand);
}
