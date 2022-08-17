package com.flanks255.simplylight;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation ID = new ResourceLocation(SimplyLight.MODID, "jei_plugin");
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
        SLBlocks.ITEMS.getEntries().forEach((item) -> {
            String key = item.get().getDescriptionId()+".jei.info";
            if (I18n.exists(key)) {
                registration.addIngredientInfo(new ItemStack(item.get()), VanillaTypes.ITEM_STACK, Component.translatable(key));
            }
        });
    }
}
