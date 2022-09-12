package dev.ithundxr.mods.createcasings.data;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import dev.ithundxr.mods.createcasings.CreateCasings;

public class ModSpriteShifts {
    public static final CTSpriteShiftEntry
            GOLD_CASING = omni("gold_casing");
            //BRASS_GLASS_CASING = omni("brass_glass_casing"),
            //COPPER_GLASS_CASING = omni("copper_glass_casing"),
            //STEEL_GLASS_CASING = omni("steel_glass_casing");


    private static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateCasings.asResource("block/" + blockTextureName), CreateCasings.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }
}
