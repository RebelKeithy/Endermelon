package com.rebelkeithy.endermelon;

import com.rebelkeithy.endermelon.blocks.EnderMelon;
import com.rebelkeithy.endermelon.blocks.MelonStem;
import com.rebelkeithy.endermelon.items.ItemEnderMelonSeed;
import com.rebelkeithy.endermelon.packets.MelonTeleportPacket;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = EnderMelonMod.MODID, version = EnderMelonMod.VERSION)
public class EnderMelonMod
{
	@SidedProxy(serverSide = "com.rebelkeithy.endermelon.CommonProxy", clientSide = "com.rebelkeithy.endermelon.ClientProxy")
	public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
	
    public static final String MODID = "endermelon";
    public static final String VERSION = "1.0";

	@Mod.Instance(MODID)
    public static EnderMelonMod instance;
    
	public Item enderMelonSeeds;
	public Block enderMelonStem;
    public Block enderMelon;
    public Item itemEndermelon;
    
    public Potion enderPotion;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("endermelon");
        network.registerMessage(MelonTeleportPacket.Handler.class, MelonTeleportPacket.class, 0, Side.SERVER);
        network.registerMessage(MelonTeleportPacket.Handler.class, MelonTeleportPacket.class, 0, Side.CLIENT);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	enderPotion = new EnderPotion().setPotionName("effect.endersickness");
    	Potion.REGISTRY.register(99, new ResourceLocation("endersickness"), enderPotion);
    	
    	enderMelon = new EnderMelon().setHardness(1.0F).setTickRandomly(true).setUnlocalizedName("endermelon");
    	GameRegistry.register(enderMelon.setRegistryName("endermelon"));
    	
    	ItemBlock itemBlockSimple = new ItemBlock(enderMelon);
        itemBlockSimple.setRegistryName(enderMelon.getRegistryName());
        GameRegistry.register(itemBlockSimple);
        
        enderMelonStem = new MelonStem(enderMelon).setTickRandomly(true);
        enderMelonStem.setUnlocalizedName("endermelonstem");
        GameRegistry.register(enderMelonStem.setRegistryName("endermelonstem"));
        
        enderMelonSeeds = new ItemEnderMelonSeed(enderMelonStem, Blocks.FARMLAND).setUnlocalizedName("endermelonseeds");
        GameRegistry.register(enderMelonSeeds.setRegistryName("endermelonseeds"));
        
        itemEndermelon = new ItemFood(1, 0.8f, false).setPotionEffect(new PotionEffect(enderPotion, 400, 0), 1f).setUnlocalizedName("itemendermelon");
        GameRegistry.register(itemEndermelon.setRegistryName("itemendermelon"));
        
        GameRegistry.addShapelessRecipe(new ItemStack(enderMelonSeeds), itemEndermelon);
        
    	proxy.init(event);
    	
    	MinecraftForge.EVENT_BUS.register(new EnderPearlEvent());
    }
    
}
