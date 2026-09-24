package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.network.ExplosionSourcePacket;
import dev.xylonity.explosiveenhancement.network.ExplosiveNetwork;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID)
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

        final Vec3 center = event.getExplosion().getPosition();
        final ExplosionSourcePacket packet = new ExplosionSourcePacket(center.x, center.y, center.z, BuiltInRegistries.ENTITY_TYPE.getKey(source.getType()));
        for (final ServerPlayer player : level.players()) {
            // Same range the vanilla explosion packet uses
            if (player.distanceToSqr(center) < 4096) {
                ExplosiveNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
            }

        }

    }

}
