package dev.xylonity.explosiveenhancement.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class EmptyParticle extends NoRenderParticle {

    public EmptyParticle(ClientLevel level, double x, double y, double z, double velX, double velY, double velZ) {
        super(level, x, y + 0.5, z, 0.0, 0.0, 0.0);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        public Provider(SpriteSet spriteSet) {
            ;;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new EmptyParticle(level, x, y, z, dx, dy, dz);
        }

    }

}