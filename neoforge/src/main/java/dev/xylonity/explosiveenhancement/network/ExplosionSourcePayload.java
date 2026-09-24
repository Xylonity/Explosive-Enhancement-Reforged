package dev.xylonity.explosiveenhancement.network;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ExplosionSourcePayload(
        double x,
        double y,
        double z,
        ResourceLocation entityType
) implements CustomPacketPayload {

    public static final Type<ExplosionSourcePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ExplosiveEnhancement.MOD_ID, "explosion_source"));

    public static final StreamCodec<ByteBuf, ExplosionSourcePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, ExplosionSourcePayload::x,
            ByteBufCodecs.DOUBLE, ExplosionSourcePayload::y,
            ByteBufCodecs.DOUBLE, ExplosionSourcePayload::z,
            ResourceLocation.STREAM_CODEC, ExplosionSourcePayload::entityType,
            ExplosionSourcePayload::new
    );

    public void handle(IPayloadContext context) {
        ExplosionSourceTracker.add(this.x, this.y, this.z, this.entityType);
    }

    @Override
    public Type<ExplosionSourcePayload> type() {
        return TYPE;
    }

}
