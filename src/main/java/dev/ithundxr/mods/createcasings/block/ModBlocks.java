package dev.ithundxr.mods.createcasings.block;

import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.ithundxr.mods.createcasings.CasingsTab;
import dev.ithundxr.mods.createcasings.CreateCasings;
import dev.ithundxr.mods.createcasings.data.BlockBuilders;
import dev.ithundxr.mods.createcasings.data.CasingBlock;
import dev.ithundxr.mods.createcasings.data.CasingEncasedShaftBlock;
import dev.ithundxr.mods.createcasings.data.ModSpriteShifts;

import static com.simibubi.create.AllTags.axeOrPickaxe;
import static dev.ithundxr.mods.createcasings.data.BlockBuilders.casingblock;

public class ModBlocks {
    private static final Registrate REGISTRATE = CreateCasings.registrate()
            .creativeModeTab(() -> CasingsTab.CASING_TAB);


    public static final BlockEntry<CasingBlock>
            DIAMOND_WARPED_CASING = casingblock("diamond_warped_casing", () -> new SimpleCTBehaviour(ModSpriteShifts.DIAMOND_WARPED_CASING));

    public static final BlockEntry<CasingEncasedShaftBlock>
            DIAMOND_WARPED_ENCASED_SHAFT = REGISTRATE
            .block("diamond_warped_encased_shaft", CasingEncasedShaftBlock::diamondwarped)
            .transform(BlockBuilders.casingencasedShaft("andesite_glass", () -> ModSpriteShifts.DIAMOND_WARPED_CASING))
            .transform(axeOrPickaxe())
            .register();


    public static void register() {}
}
