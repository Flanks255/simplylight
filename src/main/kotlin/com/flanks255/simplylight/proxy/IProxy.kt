package com.flanks255.simplylight.proxy

import net.minecraft.item.Item

interface IProxy {
    fun registerItemRenderer(item: Item, meta: Int, id: String)
}