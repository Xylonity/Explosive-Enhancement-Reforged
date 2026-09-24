package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SparksParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    SparksParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float scale) {
        super(world, x, y, z);
        this.sprites = spriteProvider;
        final float fireballSize = scale * 1.25F;
        this.quadSize = (float) (ExplosiveValues.sparkSize * fireballSize * 0.25F);
        this.lifetime = 5 + (int) (fireballSize / 5);
        this.setParticleSpeed(0D, 0D, 0D);
        this.alpha = (float) ExplosiveValues.sparkOpacity;
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.setSpriteFromAge(this.sprites);
        }

    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightColor(pPartialTick);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<ExplosiveParticleOptions> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ExplosiveParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new SparksParticle(level, x, y, z, this.sprites, options.scale());
        }

    }

}