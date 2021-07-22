package com.flanks255.simplylight;


import javax.annotation.Nonnull;
/*
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
        SimplyLight.ITEMS.getEntries().forEach((item) -> {
            String key = item.get().getDescriptionId()+".jei.info";
            if (I18n.exists(key)) {
                String langEntry = TextFormatting.stripFormatting((I18n.get(key)));
                if (langEntry != null)
                    registration.addIngredientInfo(new ItemStack(item.get()), VanillaTypes.ITEM, StringUtils.splitByWholeSeparator(langEntry, "//n"));
            }
        });
    }
}
 */ //TODO later when JEI is ported
