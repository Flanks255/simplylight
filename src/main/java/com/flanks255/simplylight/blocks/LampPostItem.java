package com.flanks255.simplylight.blocks;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;

public class LampPostItem extends BaseBlockItem {
    public LampPostItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 4.0f, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.0f, AttributeModifier.Operation.ADDITION));

        attributes = builder.build();
    }

    private static Multimap<Attribute, AttributeModifier> attributes;

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlotType.MAINHAND? attributes: super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
