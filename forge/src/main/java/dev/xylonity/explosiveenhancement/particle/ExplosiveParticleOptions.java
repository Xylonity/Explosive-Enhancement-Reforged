package dev.xylonity.explosiveenhancement.particle;

import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

public record ExplosiveParticleOptions(
        ParticleType<ExplosiveParticleOptions> type,
        float scale
) implements ParticleOptions {

    public static MapCodec<ExplosiveParticleOptions> codec(ParticleType<ExplosiveParticleOptions> type) {
        // 4f is the default vanilla tnt explosion
        return ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("scale", 4f)
                .xmap(scale -> new ExplosiveParticleOptions(type, scale), ExplosiveParticleOptions::scale);
    }

    public static StreamCodec<ByteBuf, ExplosiveParticleOptions> streamCodec(ParticleType<ExplosiveParticleOptions> type) {
        return ByteBufCodecs.FLOAT.map(scale -> new ExplosiveParticleOptions(type, scale), ExplosiveParticleOptions::scale);
    }

    @Override
    public ParticleType<ExplosiveParticleOptions> getType() {
        return this.type;
    }

}