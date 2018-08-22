package com.flanks255.simplylight.blocks

import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.input.Keyboard

class BaseItemBlock(id: Block): ItemBlock(id) {
    init {
        setRegistryName(id.registryName)
    }

    private fun hasTranslation(key :String): Boolean{
        return I18n.format(key) != key
    }

    private fun fallbackString(key: String, fallback: String): String{
        return if(hasTranslation(key)) I18n.format(key) else fallback
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            tooltip.add(I18n.format(block.unlocalizedName + ".info"))
            if (hasTranslation(block.unlocalizedName + ".info2"))
                tooltip.add(I18n.format(block.unlocalizedName + ".info2"))
            if (hasTranslation(block.unlocalizedName + ".info3"))
                tooltip.add(I18n.format(block.unlocalizedName + ".info3"))
        } else {
            tooltip.add(fallbackString("simplylight.shift", "Press <§6§oShift§r> for info."))
        }
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }

}