package dev.xylonity.explosiveenhancement.particle;

import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.StreamCodec;

public class ExplosiveParticleType extends ParticleType<ExplosiveParticleOptions> {

    private final MapCodec<ExplosiveParticleOptions> codec = ExplosiveParticleOptions.codec(this);
    private final StreamCodec<ByteBuf, ExplosiveParticleOptions> streamCodec = ExplosiveParticleOptions.streamCodec(this);

    public ExplosiveParticleType() {
        super(false);
    }

    public ExplosiveParticleOptions withScale(float scale) {
        return new ExplosiveParticleOptions(this, scale);
    }

    @Override
    public MapCodec<ExplosiveParticleOptions> codec() {
        return this.codec;
    }

    @Override
    public StreamCodec<ByteBuf, ExplosiveParticleOptions> streamCodec() {
        return this.streamCodec;
    }

}
