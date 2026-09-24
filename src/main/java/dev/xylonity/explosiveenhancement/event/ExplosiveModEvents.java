package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.network.ExplosiveNetwork;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExplosiveModEvents {

    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ExplosiveNetwork::register);
    }

    @SubscribeEvent
    public static void onConfigLoad(final ModConfigEvent.Loading event) {
        ExplosiveValues.onConfigLoad(event);
    }

    @SubscribeEvent
    public static void onConfigReload(final ModConfigEvent.Reloading event) {
        ExplosiveValues.onConfigLoad(event);
    }

}
