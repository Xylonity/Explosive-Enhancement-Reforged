package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SmokeParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    SmokeParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float scale, double velX, double velY, double velZ) {
        super(world, x, y, z);
        this.friction = 0.6F;
        this.sprites = spriteProvider;
        this.quadSize = scale * 0.25F;
        this.lifetime = Math.max(1, (int) ((this.random.nextInt(35) + 1 + scale * this.random.nextInt(3, 22)) * ExplosiveValues.explosionDuration));
        this.alpha = (float) ExplosiveValues.explosionOpacity;
        this.xd = velX;
        this.yd = velY;
        this.zd = velZ;
        this.gravity = 3.0E-6F;
        this.hasPhysics = true;
        this.setSpriteFromAge(spriteProvider);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            this.setSpriteFromAge(this.sprites);
            if (this.age == 12) {
                this.xd = 0;
                this.yd = 0.05;
                this.zd = 0;
            }

            this.move(this.xd, this.yd, this.zd);
        }

    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float tint) {
        if (ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.12) {
            return 15728880;
        }
        else if (ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.17) {
            return Mth.clamp(super.getLightColor(tint) + this.age + 30, super.getLightColor(tint), 15728880);
        }
        else {
            return super.getLightColor(tint);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<ExplosiveParticleOptions> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ExplosiveParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new SmokeParticle(level, x, y, z, this.sprites, options.scale(), dx, dy, dz);
        }

    }

}