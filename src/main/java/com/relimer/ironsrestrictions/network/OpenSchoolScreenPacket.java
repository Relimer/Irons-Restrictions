package com.relimer.ironsrestrictions.network;

import com.relimer.ironsrestrictions.player.RClientSpellCastHelper;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenSchoolScreenPacket {
    private final InteractionHand hand;
    private static SchoolType schoolType = null;

    public OpenSchoolScreenPacket(InteractionHand pHand, SchoolType schoolType) {
        this.hand = pHand;
        this.schoolType = schoolType;
    }

    public OpenSchoolScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            RClientSpellCastHelper.openSchoolResearchScreen(this.hand, schoolType);
        });
        return true;
    }
}
