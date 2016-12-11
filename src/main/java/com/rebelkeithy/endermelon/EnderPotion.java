package com.rebelkeithy.endermelon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnderPotion extends Potion
{
	private final ResourceLocation iconTexture;

	protected EnderPotion() 
	{
		super(true, 0);
		setIconIndex(6, 0);
		iconTexture = new ResourceLocation(EnderMelonMod.MODID, "textures/potions/endersickness.png");
	}
	

    public void performEffect(EntityLivingBase entityLivingBaseIn, int intensity)
    {        
        if(entityLivingBaseIn.worldObj.rand.nextInt(4) == 0)
        	return;

        double d0 = entityLivingBaseIn.posX + (entityLivingBaseIn.worldObj.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = entityLivingBaseIn.posY + (double)(entityLivingBaseIn.worldObj.rand.nextInt(64) - 32);
        double d2 = entityLivingBaseIn.posZ + (entityLivingBaseIn.worldObj.rand.nextDouble() - 0.5D) * 64.0D;
        
        boolean flag = entityLivingBaseIn.attemptTeleport(d0, d1, d2);

        if (flag)
        {
        	entityLivingBaseIn.worldObj.playSound((EntityPlayer)null, entityLivingBaseIn.prevPosX, entityLivingBaseIn.prevPosY, entityLivingBaseIn.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
        	entityLivingBaseIn.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
        }
    }

    
    public boolean isReady(int duration, int amplifier)
    {
        int ticks = 35 >> amplifier;
        return ticks > 0 ? duration % ticks == 0 : true;
    }

	@Override
	public boolean hasStatusIcon() {
		return false;
	}
	
    @SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		if (mc.currentScreen != null) {
			mc.getTextureManager().bindTexture(iconTexture);
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
		}
	}
    
	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
	}
}
