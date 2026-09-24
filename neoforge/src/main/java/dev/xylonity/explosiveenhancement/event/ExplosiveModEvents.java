package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.network.ExplosionSourcePayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;

@EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ExplosiveModEvents {

    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        event.registrar("1").optional().executesOn(HandlerThread.NETWORK)
                .playToClient(ExplosionSourcePayload.TYPE, ExplosionSourcePayload.STREAM_CODEC, ExplosionSourcePayload::handle);
    }

}
