package com.flanks255.simplylight.items

import com.flanks255.simplylight.simplylight
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

//just in case i need any pure items...
class ItemBase(private val name :String) :Item() {
    init {
        setUnlocalizedName(name)
        setRegistryName(name)
    }

    fun registerItemModel(){
        simplylight.proxy?.registerItemRenderer(this, 0, name)
    }

    override fun setCreativeTab(tab: CreativeTabs): ItemBase {
        super.setCreativeTab(tab)
        return this
    }
}