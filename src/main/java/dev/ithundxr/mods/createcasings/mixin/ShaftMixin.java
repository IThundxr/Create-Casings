package dev.ithundxr.mods.createcasings.mixin;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;
import dev.ithundxr.mods.createcasings.block.ModBlocks;
import dev.ithundxr.mods.createcasings.data.CasingEncasedShaftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

@Mixin(ShaftBlock.class)
public class ShaftMixin {


    @Inject(method = "use", at = @At(value ="INVOKE", target = "Lcom/simibubi/create/foundation/utility/placement/PlacementHelpers;get(I)Lcom/simibubi/create/foundation/utility/placement/IPlacementHelper;"), cancellable = true)
    private void Inject(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray, CallbackInfoReturnable<InteractionResult> cir){
        ItemStack heldItem = player.getItemInHand(hand);
        for (CasingEncasedShaftBlock glassEncasedShaftBlock : new CasingEncasedShaftBlock[]
                {
                        ModBlocks.GOLD_ENCASED_SHAFT.get()
                }) {

            if (!glassEncasedShaftBlock.getCasing()
                    .isIn(heldItem))
                continue;

            if (world.isClientSide)
                cir.setReturnValue(InteractionResult.SUCCESS);

            KineticTileEntity.switchToBlockState(world, pos, glassEncasedShaftBlock.defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS)));
            cir.setReturnValue(InteractionResult.SUCCESS);
        }

    }
}
