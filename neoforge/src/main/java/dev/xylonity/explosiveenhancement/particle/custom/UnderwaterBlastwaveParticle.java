package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class UnderwaterBlastwaveParticle extends BlastWaveParticle {

    UnderwaterBlastwaveParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y, z, sprites, velX, velY, velZ);
    }

    @Override
    protected int getLightCoords(float a) {
        BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
        return ExplosiveValues.emissiveWaterExplosion ? 15728880 : this.level.hasChunkAt(blockPos) ? LevelRenderer.getLightCoords(this.level, blockPos) : 0;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new UnderwaterBlastwaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}