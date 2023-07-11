package com.flanks255.simplylight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class SimplyLight implements ModInitializer {
	public static final String MODID = "simplylight";
	public static final Logger LOGGER = LoggerFactory.getLogger("Simply Light");

	public static final TagKey<Item> ANY_ON_LAMP = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "any_lamp_on"));
	public static final TagKey<Item> ANY_OFF_LAMP = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "any_lamp_off"));

	public static final TagKey<Item> ANY_STONE = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "any_stone"));

	public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
			.title(Component.translatable("itemGroup.simplylight.group"))
			.displayItems((params, output) -> SLBlocks.TAB_ORDER.forEach(block -> output.accept(block.getItem())))
			.build();


	public SimplyLight() {
		SLBlocks.init();
		RecipeUnlocker.register();
	}

	@Override
	public void onInitialize() {
		SLBlocks.register();

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MODID, "simplylight"), ITEM_GROUP);
	}
}
