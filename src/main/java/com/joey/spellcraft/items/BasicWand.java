package com.joey.spellcraft.items;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.spells.Spell;
import com.joey.spellcraft.utility.WandHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class BasicWand extends Item implements ISpellCastingItem {
    public int maxAmountOfSpells = 5;
    public int addedRange = 0;

    public BasicWand() {
        super(
            new Item.Properties()
                .group(SpellCraft.TAB)
                .maxStackSize(1)
        );
    }

    @Override
    public void onCreated(ItemStack stack, World world, PlayerEntity player) {
        WandHelper.onCreateCastingItem(stack, world, player);

        super.onCreated(stack, world, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        WandHelper.onCastSpell(world, player, hand);

        return super.onItemRightClick(world, player, hand);
    }

    public Spell[] getSpells(ItemStack wand) {
        return WandHelper.getSpells(wand);
    }

    public void setSpells(ItemStack wand, int[] spells) {
        WandHelper.setSpells(wand, spells);
    }

    public int getCurrentSpellId(ItemStack wand) {
        return WandHelper.getCurrentSpellId(wand);
    }

    public void setCurrentSpellId(ItemStack wand, int spellId) {
        WandHelper.setCurrentSpellId(wand, spellId);
    }

    public void nextSpell(ItemStack wand) {
        WandHelper.nextSpell(wand);
    }

    public void previousSpell(ItemStack wand) {
        WandHelper.previousSpell(wand);
    }

}
