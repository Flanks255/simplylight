package com.flanks255.simplylight.proxy


import com.flanks255.simplylight.simplylight
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader

class ClientProxy :IProxy{
    override fun registerItemRenderer(item: Item, meta: Int, id: String){
        ModelLoader.setCustomModelResourceLocation(item, meta, ModelResourceLocation( simplylight.MODID+":"+id, "inventory"))
    }
}