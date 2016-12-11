package com.rebelkeithy.endermelon.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEnderMelonSeed extends Item implements net.minecraftforge.common.IPlantable
{
    private final Block crops;
    /** BlockID of the block the seeds can be planted on. */
    private final Block soilBlockID;

    public ItemEnderMelonSeed(Block crops, Block soil)
    {
        this.crops = crops;
        this.soilBlockID = soil;
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY)
    {
        ItemStack itemstack = stack.getHeldItem(pos);
        net.minecraft.block.state.IBlockState state = playerIn.getBlockState(worldIn);
        if (hand == EnumFacing.UP && stack.canPlayerEdit(worldIn.offset(hand), hand, itemstack) && state.getBlock().canSustainPlant(state, playerIn, worldIn, EnumFacing.UP, this) && playerIn.isAirBlock(worldIn.up()))
        {
            playerIn.setBlockState(worldIn.up(), this.crops.getDefaultState());
            itemstack.func_190918_g(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return this.crops == net.minecraft.init.Blocks.NETHER_WART ? net.minecraftforge.common.EnumPlantType.Nether : net.minecraftforge.common.EnumPlantType.Crop;
    }

    @Override
    public net.minecraft.block.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return this.crops.getDefaultState();
    }
}
