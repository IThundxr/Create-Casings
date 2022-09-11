package dev.ithundxr.mods.createcasings.block;

import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.ithundxr.mods.createcasings.CasingsTab;
import dev.ithundxr.mods.createcasings.CreateCasings;
import dev.ithundxr.mods.createcasings.data.CasingBlock;
import dev.ithundxr.mods.createcasings.data.ModSpriteShifts;

import static dev.ithundxr.mods.createcasings.data.BlockBuilders.casingblock;

public class ModBlocks {
    private static final Registrate REGISTRATE = CreateCasings.registrate()
            .creativeModeTab(() -> CasingsTab.CASING_TAB);


    public static final BlockEntry<CasingBlock>
            ANDESITE_GLASS_CASING = casingblock("andesite_glass_casing", () -> new SimpleCTBehaviour(ModSpriteShifts.ANDESITE_GLASS_CASING));


    public static void register() {}
}
