package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UnderwaterBlastwaveParticle extends BlastWaveParticle {

    UnderwaterBlastwaveParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, float scale) {
        super(world, x, y, z, sprites, scale);
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
    public static class Provider implements ParticleProvider<ExplosiveParticleOptions> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ExplosiveParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new UnderwaterBlastwaveParticle(level, x, y, z, this.sprites, options.scale());
        }

    }

}