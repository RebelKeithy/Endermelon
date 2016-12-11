package com.rebelkeithy.endermelon;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ThrowableImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnderPearlEvent 
{	
	@SubscribeEvent
	public void impact(ThrowableImpactEvent event)
	{
		World world = event.getEntity().worldObj;
		if(event.getEntityThrowable() instanceof EntityEnderPearl && event.getRayTraceResult().typeOfHit == RayTraceResult.Type.BLOCK)
		{
			if(world.rand.nextInt(16) == 0)
			{
				BlockPos pos = event.getRayTraceResult().getBlockPos();
				
				if (world.getBlockState(pos).getBlock() == Blocks.MELON_BLOCK)
		        {
		        	System.out.println("replacing block");
		        	world.setBlockState(pos, EnderMelonMod.instance.enderMelon.getDefaultState());
		        }
			}
		}
	}
}
