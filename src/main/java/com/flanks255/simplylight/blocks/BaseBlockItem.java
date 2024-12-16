package com.flanks255.simplylight.blocks;

import com.google.common.base.Suppliers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class BaseBlockItem extends BlockItem implements Equipable {
    public BaseBlockItem(Block blockIn, Item.Properties builder)
    {
        super(blockIn, builder);
        this.block = blockIn;
        this.alternateTooltipBlock = null;
    }

    public BaseBlockItem(Block block, Properties properties, Supplier<Block> alternateTooltipBlock) {
        super(block, properties);
        this.block = block;
        this.alternateTooltipBlock = alternateTooltipBlock.get();
    }

    private final Supplier<ResourceLocation> lazyRes = Suppliers.memoize(() -> BuiltInRegistries.ITEM.getKey(this));

    public ResourceLocation getRegistryName() {
        return lazyRes.get();
    }

    private final Block block;
    private final Block alternateTooltipBlock;

/*    @Override
    public boolean canEquip(@Nonnull ItemStack stack, @Nonnull EquipmentSlot armorType, @Nonnull Entity entity) {
        return armorType == EquipmentSlot.HEAD || super.canEquip(stack, armorType, entity);
    }*/

    public String getTooltipBase() {
        if (alternateTooltipBlock != null)
            return alternateTooltipBlock.getDescriptionId();
        return block.getDescriptionId();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        String tooltipBase = getTooltipBase();

        boolean redstoneFlag = block instanceof LampBlock;
        if (Screen.hasShiftDown() && I18n.exists(tooltipBase + ".info")) {
            tooltip.add(Component.translatable(tooltipBase + ".info").withStyle(ChatFormatting.GRAY));
            if (I18n.exists(tooltipBase+".info2")) {
                if (redstoneFlag)
                    tooltip.add(Component.translatable(tooltipBase + ".info2", Component.translatable("simplylight.redstone").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.GRAY));
                else
                    tooltip.add(Component.translatable(tooltipBase + ".info2").withStyle(ChatFormatting.GRAY));
            }
        }
        else {
            tooltip.add(Component.translatable("simplylight.shift", Component.translatable("simplylight.key.shift").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC)).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
