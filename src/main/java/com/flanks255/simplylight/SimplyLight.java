package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.LampBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplyLight implements ModInitializer {
	public static final String MODID = "simplylight";
	public static final Logger LOGGER = LoggerFactory.getLogger("Simply Light");

	public static final TagKey<Item> ANY_ON_LAMP = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "any_lamp_on"));
	public static final TagKey<Item> ANY_OFF_LAMP = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "any_lamp_off"));

	public static final TagKey<Item> ANY_STONE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "any_stone"));

	@Override
	public void onInitialize() {
		SLBlocks.register();



	}
}
