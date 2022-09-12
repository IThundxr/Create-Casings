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
            GOLD_CASING = casingblock("gold_casing", () -> new SimpleCTBehaviour(ModSpriteShifts.GOLD_CASING));

    public static final BlockEntry<CasingEncasedShaftBlock>
            GOLD_ENCASED_SHAFT = REGISTRATE
            .block("gold_encased_shaft", CasingEncasedShaftBlock::gold)
            .transform(BlockBuilders.casingencasedShaft("gold", () -> ModSpriteShifts.GOLD_CASING))
            .transform(axeOrPickaxe())
            .register();


    public static void register() {}
}
