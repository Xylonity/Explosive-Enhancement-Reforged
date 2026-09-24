package dev.xylonity.explosiveenhancement.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class ExplosiveParticleType extends ParticleType<ExplosiveParticleOptions> {

    private final Codec<ExplosiveParticleOptions> codec = ExplosiveParticleOptions.codec(this);

    public ExplosiveParticleType() {
        super(false, ExplosiveParticleOptions.DESERIALIZER);
    }

    public ExplosiveParticleOptions withScale(float scale) {
        return new ExplosiveParticleOptions(this, scale);
    }

    @Override
    public Codec<ExplosiveParticleOptions> codec() {
        return this.codec;
    }

}