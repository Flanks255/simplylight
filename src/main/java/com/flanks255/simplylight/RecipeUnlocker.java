package com.flanks255.simplylight;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class RecipeUnlocker {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((player, connection, minecraftServer) -> {
            onPlayerLoggedIn(player.player);
        });
    }

    private static void onPlayerLoggedIn(Player player) {
        if (player instanceof ServerPlayer) {
            MinecraftServer server = player.getServer();
            if (server != null) {
                var recipes = new ArrayList<>(server.getRecipeManager().getRecipes());
                recipes.removeIf((recipe -> !recipe.getId().getNamespace().contains(SimplyLight.MODID)));
                player.awardRecipes(recipes);
            }
        }
    }
}
