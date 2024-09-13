package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UnderwaterBlastwaveParticle extends BlastWaveParticle {

    UnderwaterBlastwaveParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(world, x, y, z, sprites, velX, velY, velZ);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
        return ExplosiveValues.emissiveWaterExplosion ? 15728880 : this.level.hasChunk(blockPos.getX(), blockPos.getZ()) ? LevelRenderer.getLightColor(this.level, blockPos) : 0;
    }
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new UnderwaterBlastwaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
