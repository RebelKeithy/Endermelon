package com.rebelkeithy.endermelon.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MelonTeleportPacket implements IMessage {

	public static class Handler implements IMessageHandler<MelonTeleportPacket, IMessage> {

		@Override
		public IMessage onMessage(final MelonTeleportPacket message, MessageContext ctx) {
			System.out.println("Teleport at " + message.pos.getX() + " " + message.pos.getY() + " " + message.pos.getZ());
			
			IThreadListener mainThread = Minecraft.getMinecraft(); // or Minecraft.getMinecraft() on the client
            
			mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    
                    
                    for (int j = 0; j < 64; ++j)
                    {
                        //double d0 = worldIn.rand.nextDouble() * 0;
                        double d0 = 1;
                    	float speedX = (Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.5F) * 0.2F;
                        float speedY = (Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.5F) * 0.2F;
                        float speedZ = (Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.5F) * 0.2F;
                        double d1 = message.pos.getX() + (Minecraft.getMinecraft().theWorld.rand.nextDouble() * 1.5D - 0.75D) + 0.5D;
                        double d2 = message.pos.getY() + Minecraft.getMinecraft().theWorld.rand.nextDouble() * 1.5D - 0.75D;
                        double d3 = message.pos.getZ() + (Minecraft.getMinecraft().theWorld.rand.nextDouble() * 1.5D - 0.75D) + 0.5D;
                        Minecraft.getMinecraft().theWorld.spawnParticle(EnumParticleTypes.PORTAL, d1, d2, d3, (double)speedX, (double)speedY, (double)speedZ, new int[0]);
                    }
                    
                    Minecraft.getMinecraft().theWorld.playSound(message.pos.getX(), message.pos.getY(), message.pos.getZ(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
                    //this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                }
            });

			
            
			return null;
		}
	}
	
	private BlockPos pos;

	public MelonTeleportPacket()
	{		
	}
	
	public MelonTeleportPacket(BlockPos pos)
	{
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		
		pos = new BlockPos(x, y, z);
	}

	@Override
	public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());		
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
	}

}
