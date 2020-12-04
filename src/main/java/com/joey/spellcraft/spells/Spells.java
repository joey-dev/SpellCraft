package com.joey.spellcraft.spells;

import com.joey.spellcraft.utility.ArrayUtility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Spells {
    private static Spell[] spells = {};

    static {
        spells = ArrayUtility.addItemToArray(spells, new FlameThrowerSpell("Flame Thrower", "FlameThrowerSpell", 0));
        spells = ArrayUtility.addItemToArray(spells, new ToggleDoorSpell("Toggle Door", "TOGGLE_DOOR_SPELL", 1));
    }

    public static Spell getSpellById(int id) {
        return spells[id];
    }

    public static Spell[] getSpellsById(int[] ids) {
        Spell[] spells = {};

        for (int id: ids) {
            spells = ArrayUtility.addItemToArray(spells, getSpellById(id));
        }

        return spells;
    }

    public void run(PlayerEntity playerIn, World worldIn) { }
}
