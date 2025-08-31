package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenSchoolScreenPacket {
    private final InteractionHand hand;
    private static SchoolType schoolType;

    public OpenSchoolScreenPacket(InteractionHand pHand, SchoolType schoolType) {
        this.hand = pHand;
        this.schoolType = schoolType;
    }

    public OpenSchoolScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
        ResourceLocation id = buf.readResourceLocation();
        this.schoolType = SchoolRegistry.REGISTRY.get().getValue(id);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
        buf.writeResourceLocation(this.schoolType.getId());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(this.hand, this.schoolType);
        });
        return true;
    }
}
