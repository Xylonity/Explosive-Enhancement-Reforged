package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.network.ExplosionSourcePayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID)
public class ExplosiveCommonEvents {

    @SubscribeEvent
    public static void sendExplosionSource(final ExplosionEvent.Detonate event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        final Entity source = event.getExplosion().getDirectSourceEntity();
        if (source == null) {
            return;
        }

        final Vec3 center = event.getExplosion().center();
        final ExplosionSourcePayload payload = new ExplosionSourcePayload(center.x, center.y, center.z, BuiltInRegistries.ENTITY_TYPE.getKey(source.getType()));
        for (ServerPlayer player : level.players()) {
            // Same range the vanilla explosion packet uses
            if (player.distanceToSqr(center) < 4096 && player.connection.hasChannel(payload)) {
                PacketDistributor.sendToPlayer(player, payload);
            }

        }

    }

}
