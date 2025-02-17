package org.vanharneveldt.tinted_glass;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.vanharneveldt.tinted_glass.block.ModBlocks;
import org.vanharneveldt.tinted_glass.command.TeleportCommand;
import org.vanharneveldt.tinted_glass.component.ModDataComponents;
import org.vanharneveldt.tinted_glass.effect.ModEffects;
import org.vanharneveldt.tinted_glass.enchantment.ModEnchantmentEffects;
import org.vanharneveldt.tinted_glass.enchantment.ModEnchantments;
import org.vanharneveldt.tinted_glass.entity.ModEntities;
import org.vanharneveldt.tinted_glass.entity.client.FacelessModel;
import org.vanharneveldt.tinted_glass.entity.client.FacelessRenderer;
import org.vanharneveldt.tinted_glass.entity.custom.FacelessEntity;
import org.vanharneveldt.tinted_glass.item.ModCreativeModeTabs;
import org.vanharneveldt.tinted_glass.item.ModItems;
import org.vanharneveldt.tinted_glass.potion.ModPotions;
import org.vanharneveldt.tinted_glass.sound.ModSounds;
import org.vanharneveldt.tinted_glass.registry.DimensionRegistry;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TintedGlass.MOD_ID)
public class TintedGlass
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "revampedglass";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TintedGlass(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModDataComponents.register(modEventBus);

        DimensionRegistry.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Register the command when the server starts
        TeleportCommand.register(event.getServer().getCommands().getDispatcher());
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.FACELESS.get(), FacelessRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(FacelessModel.LAYER_LOCATION, FacelessModel::createBodyLayer);
        }
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntities.FACELESS.get(), FacelessEntity.createAttributes().build());
        }
    }
}
