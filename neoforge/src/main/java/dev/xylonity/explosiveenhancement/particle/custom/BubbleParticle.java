package dev.xylonity.explosiveenhancement.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;

public class BubbleParticle extends SingleQuadParticle {

    private final SpriteSet sprites;
    private int startingAirTick = 0;
    private final int extraTimeBeforePopping = this.random.nextIntBetweenInclusive(1, 10);
    private boolean startAirTick = true;

    BubbleParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y, z, sprites.first());
        this.sprites = sprites;

        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 1.5F + 0.2F;

        double theta = this.random.nextDouble() * 2.0 * Math.PI;
        double phi = this.random.nextDouble() * Math.PI;

        this.xd = Math.sin(phi) * Math.cos(theta) * (this.random.nextDouble() * 0.5 + 0.5);
        this.yd = Math.abs(this.random.nextDouble() * 0.5 + 0.5);
        this.zd = Math.sin(phi) * Math.sin(theta) * (this.random.nextDouble() * 0.5 + 0.5);

        this.lifetime = 120 + this.random.nextIntBetweenInclusive(0, 40);
        this.setSpriteFromAge(sprites);

        this.age = this.lifetime;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.lifetime-- <= 0) {
            this.remove();
            this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            return;
        }

        this.yd += 0.002;
        this.move(this.xd, this.yd, this.zd);
        this.yd *= 0.8200000238418579;

        if (this.lifetime >= this.age * 0.97) {
            this.xd *= 0.8300000238418579;
            this.zd *= 0.8300000238418579;
        }
        else {
            this.xd *= 0.6200000238418579;
            this.zd *= 0.6200000238418579;
        }

        if (!this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z)).is(FluidTags.WATER)) {
            this.yd -= 0.002;

            if (this.startAirTick) {
                this.startingAirTick = this.lifetime;
                this.yd = 0.0;
                this.startAirTick = false;
            }

            if (this.lifetime == this.startingAirTick - this.extraTimeBeforePopping) {
                this.remove();
                this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, this.xd, this.yd, this.zd);
                this.level.playSound(null, this.x, this.y, this.z, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.AMBIENT, 0.5F, 1.0F);
            }

        }

    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new BubbleParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}