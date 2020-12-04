package com.joey.spellcraft.utility;

import com.joey.spellcraft.items.ISpellCastingItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class WandUtility {
    public static boolean playerIsHoldingASpellCastingItem(PlayerEntity player) {
        return (player.getHeldItemMainhand().getItem() instanceof ISpellCastingItem);
    }

    public static boolean stackItemIsSpellCastingItem(ItemStack stack) {
        return (stack.getItem() instanceof ISpellCastingItem);
    }

    public static ItemStack getPlayerSpellCastingItemStack(PlayerEntity player) {
        return player.getHeldItemMainhand();
    }

    public static void nextSpell(ItemStack wand) {
        ((ISpellCastingItem) wand.getItem()).nextSpell(wand);
    }

    public static void previousSpell(ItemStack wand) {
        ((ISpellCastingItem) wand.getItem()).previousSpell(wand);
    }
}
