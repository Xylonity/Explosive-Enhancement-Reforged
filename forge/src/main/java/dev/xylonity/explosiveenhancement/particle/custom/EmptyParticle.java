package dev.xylonity.explosiveenhancement.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class EmptyParticle extends NoRenderParticle {

    public EmptyParticle(ClientLevel world, double x, double y, double z, double velX, double velY, double velZ) {
        super(world, x, y + 0.5, z, 0.0, 0.0, 0.0);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet ss) { ;; }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new EmptyParticle(level, x, y, z, dx, dy, dz);
        }
    }

}
