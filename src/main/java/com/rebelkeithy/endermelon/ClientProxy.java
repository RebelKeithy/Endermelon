package com.rebelkeithy.endermelon;

import com.rebelkeithy.endermelon.client.BlockRenderingRegistry;
import com.rebelkeithy.endermelon.client.ItemRenderingRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init(FMLInitializationEvent e) {
	    super.init(e);

	    BlockRenderingRegistry.registerBlockRenderers();
	    ItemRenderingRegistry.registerItemRenderers();
	}

}
