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

public class BasicWand extends Item {
    public BasicWand() {
        super(
            new Item.Properties()
                .group(SpellCraft.TAB)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItemMainhand();

        if (!worldIn.isRemote && itemStack.getItem() == ModItems.BASIC_WAND.get()) {
//            FlameThrowerSpell.run(playerIn, worldIn);
            ToggleDoorSpell.run(playerIn, worldIn);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
