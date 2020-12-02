package com.joey.spellcraft.init;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.items.BasicWand;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpellCraft.MOD_ID);

    public static final RegistryObject<BasicWand> BASIC_WAND = ITEMS.register("basic_wand", BasicWand::new);
}
