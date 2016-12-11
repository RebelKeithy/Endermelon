package com.rebelkeithy.endermelon.blocks;

import java.util.Random;

import com.rebelkeithy.endermelon.EnderMelonMod;
import com.rebelkeithy.endermelon.packets.MelonTeleportPacket;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnderMelon extends Block
{
	public EnderMelon()
	{
        super(Material.GOURD, MapColor.LIME);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.WOOD);
	}
	
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
    	//teleport(worldIn, pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	if(worldIn.rand.nextInt(100) == 0)
    	{
    		teleport(worldIn, pos);
    	}
    }
    
    private void teleport(World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            for (int i = 0; i < 1000; ++i)
            {
                BlockPos blockpos = pos.add(worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16), worldIn.rand.nextInt(8) - worldIn.rand.nextInt(8), worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16));
                BlockPos blockposdown = blockpos.add(0, -1, 0);

                IBlockState targetState = worldIn.getBlockState(blockpos);
                IBlockState downState = worldIn.getBlockState(blockposdown);
                
                if (targetState.getBlock().getMaterial(targetState) == Material.AIR && downState.getBlock().isSideSolid(downState, worldIn, blockposdown, EnumFacing.UP))
                {
                    worldIn.setBlockState(blockpos, iblockstate, 2);
                    worldIn.setBlockToAir(pos);
                    Minecraft.getMinecraft().theWorld.playSound((EntityPlayer)null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);

					EnderMelonMod.network.sendToAll(new MelonTeleportPacket(pos));

                    return;
                }
            }
        }
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state)
    {
    	if(type.equals("axe"))
    		return true;
    	
    	return super.isToolEffective(type, state);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return EnderMelonMod.instance.itemEndermelon;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3) + random.nextInt(2);
    }

    /**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return Math.min(5, this.quantityDropped(random) + random.nextInt(1 + fortune));
    }
}
