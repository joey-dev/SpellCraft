package com.joey.spellcraft.spells;

import com.joey.spellcraft.items.ISpellCastingItem;
import com.joey.spellcraft.utility.SpellUtility;
import com.joey.spellcraft.utility.WandUtility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlameThrowerSpell extends Spell {

    FlameThrowerSpell(String name, String className, int id) {
        super(name, className, id);
    }

    public void run(PlayerEntity player, World world) {
        int maxRange = 10;
        int burnDuration = 5;

        ItemStack wandStack =  WandUtility.getPlayerSpellCastingItemStack(player);
        ISpellCastingItem wand = (ISpellCastingItem) wandStack.getItem();

        int maxRangeWithAddedRange = maxRange + wand.addedRange;

        SpellUtility.getEntitiesInAimDirection(maxRangeWithAddedRange, player, world, entity -> entity instanceof LivingEntity)
                .forEach(entity ->
                        entity.setFire(burnDuration));
    }
}
