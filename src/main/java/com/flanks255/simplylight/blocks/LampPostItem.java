package com.flanks255.simplylight.blocks;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;


public class LampPostItem extends BaseBlockItem {
    public LampPostItem(Block pBlock) {
        super(pBlock, new Item.Properties().attributes(createAttributes()));
    }

    private static ItemAttributeModifiers createAttributes() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,4.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        return builder.build();
    }
}
