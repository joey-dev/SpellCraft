package com.joey.spellcraft.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public abstract class Spell {
    public static final String SPELLS_ON_WAND = "spellsOnWand";
    public static final String SELECTED_SPELL_ID = "selectedSpellId";

    public static final String RANGE = "range";
    public static final String DURATION = "duration";

    public String name;
    public String className;
    public int id;

    Spell(String name, String className, int id) {
        this.name = name;
        this.className = className;
        this.id = id;
    }

    public abstract void run(PlayerEntity player, World world);
}
