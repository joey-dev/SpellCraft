package com.joey.spellcraft.events;

import com.joey.spellcraft.SpellCraft;
import com.joey.spellcraft.utility.SpellCraftKeyBindings;
import com.joey.spellcraft.utility.WandUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpellCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TickEvent {
    private static boolean NextSpellKeyPressed = false;
    private static boolean PreviousSpellKeyPressed = false;

    @SubscribeEvent
    public static void onTickEvent(net.minecraftforge.event.TickEvent.PlayerTickEvent playerTickEvent) {
        if (playerTickEvent.phase == net.minecraftforge.event.TickEvent.Phase.END) {
            return;
        }

        PlayerEntity player = playerTickEvent.player;
        if (player.world.isRemote) {
            return;
        }

        if (player == null || !WandUtility.playerIsHoldingASpellCastingItem(player)) {
            return;
        }

        ItemStack wand = WandUtility.getPlayerSpellCastingItemStack(player);

        if (Minecraft.getInstance().isGameFocused() && SpellCraftKeyBindings.NEXT_SPELL.isKeyDown()) {
            if (!NextSpellKeyPressed) {
                NextSpellKeyPressed = true;

                WandUtility.nextSpell(wand);
            }
        } else if (NextSpellKeyPressed) {
            NextSpellKeyPressed = false;
        }

        if (Minecraft.getInstance().isGameFocused() && SpellCraftKeyBindings.PREVIOUS_SPELL.isKeyDown()) {
            if (!PreviousSpellKeyPressed) {
                PreviousSpellKeyPressed = true;
                WandUtility.previousSpell(wand);
            }
        } else if (PreviousSpellKeyPressed) {
            PreviousSpellKeyPressed = false;
        }
    }
}
