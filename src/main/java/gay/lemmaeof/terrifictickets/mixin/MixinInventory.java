package gay.lemmaeof.terrifictickets.mixin;

import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public interface MixinInventory {
	@Inject(method = "getMaxCountPerStack", at = @At("RETURN"), cancellable = true)
	private void changeMaxCount(CallbackInfoReturnable<Integer> info) {
		if (info.getReturnValueI() == 99) info.setReturnValue(256);
	}
}
