package com.rebelkeithy.endermelon.client;

import com.rebelkeithy.endermelon.EnderMelonMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderingRegistry {

	public static void registerItemRenderers() {
		registerItemRenderer(EnderMelonMod.instance.enderMelonSeeds);
		registerItemRenderer(EnderMelonMod.instance.itemEndermelon);
	}

    private static void registerItemRenderer(Item item) {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    	//ModelLoader.setCustomModelResourceLocation(ExampleMod.instance.enderMelonSeeds, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
