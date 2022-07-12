package com.flanks255.simplylight;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;

public class RecipeUnlocker {
    private static String modTag;
    private static int version;

    public static void register(String modId, IEventBus bus, int recipeVersion) {
        modTag = modId + "_unlocked";
        version = recipeVersion;
        bus.addListener(RecipeUnlocker::onPlayerLoggedIn);
    }

    private static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();


        CompoundTag tag = player.getPersistentData();
        if (tag.contains(modTag) && tag.getInt(modTag) >= version) {
            return;
        }

        if (player instanceof ServerPlayer) {
            MinecraftServer server = player.getServer();
            if (server != null) {
                var recipes = new ArrayList<>(server.getRecipeManager().getRecipes());
                recipes.removeIf((recipe -> !recipe.getId().getNamespace().contains(SimplyLight.MODID)));
                player.awardRecipes(recipes);
                tag.putInt(modTag, version);
            }
        }
    }
}
