package dev.ithundxr.mods.createcasings.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTileEntities;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.AbstractEncasedShaftBlock;
import com.simibubi.create.content.schematics.ISpecialBlockItemRequirement;
import com.simibubi.create.content.schematics.ItemRequirement;
import com.simibubi.create.foundation.block.ITE;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.ithundxr.mods.createcasings.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;


public class CasingEncasedShaftBlock extends AbstractEncasedShaftBlock implements ITE<KineticTileEntity>, ISpecialBlockItemRequirement {

    private BlockEntry<CasingBlock> CasingBlock;

    public static CasingEncasedShaftBlock andesiteglass(Properties properties) {
        return new CasingEncasedShaftBlock(properties, ModBlocks.ANDESITE_GLASS_CASING);
    }
    /*public static CasingEncasedShaftBlock andesiteclearglass(Properties properties) {
        return new CasingEncasedShaftBlock(properties, ModBlocks.ANDESITE_CLEAR_GLASS_CASING);
    }
    public static CasingEncasedShaftBlock brassglass(Properties properties) {
        return new CasingEncasedShaftBlock(properties, ModBlocks.BRASS_GLASS_CASING);
    }
    public static CasingEncasedShaftBlock brassclearglass(Properties properties) {
        return new CasingEncasedShaftBlock(properties, ModBlocks.BRASS_CLEAR_GLASS_CASING);
    }*/



    public CasingEncasedShaftBlock(Properties properties, BlockEntry<CasingBlock> CasingBlock) {
        super(properties);
        this.CasingBlock = CasingBlock;
    }

    public BlockEntry<CasingBlock> getCasing() {
        return CasingBlock;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {}

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;
        context.getLevel()
                .levelEvent(2001, context.getClickedPos(), Block.getId(state));
        KineticTileEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
                AllBlocks.SHAFT.getDefaultState()
                        .setValue(AXIS, state.getValue(AXIS)));
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult)
            return ((BlockHitResult) target).getDirection()
                    .getAxis() == getRotationAxis(state) ? AllBlocks.SHAFT.asStack() : getCasing().asStack();
        return super.getCloneItemStack(state, target, world, pos, player);
    }

    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity te) {
        return ItemRequirement.of(AllBlocks.SHAFT.getDefaultState(), te);
    }

    @Override
    public Class<KineticTileEntity> getTileEntityClass() {
        return KineticTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return AllTileEntities.ENCASED_SHAFT.get();
    }
}
