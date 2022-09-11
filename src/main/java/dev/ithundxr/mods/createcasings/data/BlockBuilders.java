package dev.ithundxr.mods.createcasings.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import dev.ithundxr.mods.createcasings.CreateCasings;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;

public class BlockBuilders {
    private static final Registrate REGISTRATE = CreateCasings.registrate();


    private static @NotNull Properties casingProperties(Properties p) {
        return p.isValidSpawn(BlockBuilders::never)
                .isRedstoneConductor(BlockBuilders::always);
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static Boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return true;
    }

    public static BlockEntry<CasingBlock> casingblock(String name, Supplier<ConnectedTextureBehaviour> behaviour) {
        return REGISTRATE.block(name, CasingBlock::new)
                .onRegister(connectedTextures(behaviour))
                .properties(BlockBuilders::casingProperties)
                .loot(BlockLoot::dropSelf)
                .blockstate((c, p) -> BlockStateGen.cubeAll(c, p, "", c.getName()))
                .tag(AllTags.AllBlockTags.CASING.tag)

                .item()
                .tag(AllTags.AllItemTags.CASING.tag)
                .model((c, p) -> p.cubeColumn(c.getName(), p.modLoc("block/" + c.getName()), p.modLoc("block/" + c.getName())))

                .build()
                .register();
    }

    public static <B extends CasingEncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> casingencasedShaft(String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return builder -> casingencasedBase(builder, AllBlocks.SHAFT::get)
                .initialProperties(AllBlocks.ANDESITE_CASING)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))))
                .initialProperties(AllBlocks.ANDESITE_CASING)
                .properties(BlockBuilders::casingProperties)
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/casing_encased_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("casing_encased_shaft", "item_" + casing))
                .build();
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> casingencasedBase(BlockBuilder<B, P> b, Supplier<ItemLike> drop) {
        return b.properties(BlockBehaviour.Properties::noOcclusion)
                .transform(BlockStressDefaults.setNoImpact())
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }
}