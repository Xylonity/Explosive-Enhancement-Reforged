package dev.xylonity.explosiveenhancement.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.network.CustomPayloadEvent;

public record ExplosionSourcePacket(
        double x,
        double y,
        double z,
        ResourceLocation entityType
) {

    public static ExplosionSourcePacket decode(FriendlyByteBuf buf) {
        return new ExplosionSourcePacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readResourceLocation());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeResourceLocation(this.entityType);
    }

    public void handle(CustomPayloadEvent.Context context) {
        ExplosionSourceTracker.add(this.x, this.y, this.z, this.entityType);
        context.setPacketHandled(true);
    }

}