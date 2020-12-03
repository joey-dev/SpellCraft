package com.joey.spellcraft.utility;

import com.joey.spellcraft.SpellCraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class SpellCraftKeyBindings {
    // KeyCode can be found in: javafx.scene.input.KeyCode
    public static final KeyBinding PREVIOUS_SPELL = new KeyBinding("key." + SpellCraft.MOD_ID + ".previous_spell", 0x4E,  "key.categories." + SpellCraft.MOD_ID);
    public static final KeyBinding NEXT_SPELL = new KeyBinding("key." + SpellCraft.MOD_ID + ".next_spell", 0x4D,  "key.categories." + SpellCraft.MOD_ID);

    public SpellCraftKeyBindings() {
        ClientRegistry.registerKeyBinding(PREVIOUS_SPELL);
        ClientRegistry.registerKeyBinding(NEXT_SPELL);
    }
}
