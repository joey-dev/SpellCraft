package com.joey.spellcraft.items;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.init.ModItems;
import com.joey.spellcraft.spells.FlameThrowerSpell;
import com.joey.spellcraft.spells.ToggleDoorSpell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BasicWand extends Item {
    public int maxAmountOfSpells = 10;
    public int currentSpellIndex = 0;
    public List<Integer> spellsOnWand = new ArrayList<Integer>();

    public BasicWand() {
        super(
            new Item.Properties()
                .group(SpellCraft.TAB)
        );

        spellsOnWand.add(0);
        spellsOnWand.add(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItemMainhand();

        if (!worldIn.isRemote && itemStack.getItem() == ModItems.BASIC_WAND.get()) {
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

    public void nextSpell() {
        if (currentSpellIndex + 1 >= spellsOnWand.size()) {
            currentSpellIndex = 0;
        } else {
            currentSpellIndex++;
        }
    }

    public void previousSpell() {
        if (currentSpellIndex - 1 < 0) {
            currentSpellIndex = spellsOnWand.size() - 1;
        } else {
            currentSpellIndex--;
        }
    }

}
