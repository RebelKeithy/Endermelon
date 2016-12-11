package com.rebelkeithy.endermelon.client;

import com.rebelkeithy.endermelon.EnderMelonMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRenderingRegistry {
	
	public static void registerBlockRenderers() {
		registerBlockRenderer(EnderMelonMod.instance.enderMelon);
		registerBlockRenderer(EnderMelonMod.instance.enderMelonStem);
	}

    private static void registerBlockRenderer(Block block) {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
    
}
