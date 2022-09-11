package dev.ithundxr.mods.createcasings;

import com.tterrag.registrate.Registrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class CasingsTab {
    public static final CreativeModeTab CASING_TAB = new CreativeModeTab("create-casings") {
        @Override
        public ItemStack makeIcon() {return Blocks.DIRT.asItem().getDefaultInstance();} //ModBlocks.MYBLOCK.asStack();}
    };
    private static final Registrate REGISTRATE = CreateCasings.registrate().creativeModeTab(() -> CASING_TAB, "Create: Casings");
}