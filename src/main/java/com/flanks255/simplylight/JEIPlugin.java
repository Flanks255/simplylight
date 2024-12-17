package com.flanks255.simplylight;


import com.flanks255.simplylight.blocks.BaseBlockItem;
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
    public static final ResourceLocation ID = SimplyLight.SLRes("jei_plugin");
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
        SLBlocks.ITEMS.getEntries().forEach((itemSupplier) -> {
            var item = itemSupplier.get();
            String key = item.getDescriptionId()+".jei.info";
            if (item instanceof BaseBlockItem baseBlockItem)
                key = baseBlockItem.getTooltipBase()+".jei.info";
            if (I18n.exists(key)) {
                registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM_STACK, Component.translatable(key));
            }
        });
    }
}
