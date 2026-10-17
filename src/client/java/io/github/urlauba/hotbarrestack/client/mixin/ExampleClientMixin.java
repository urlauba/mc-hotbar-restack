package io.github.urlauba.hotbarrestack.client.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// No-op placeholder kept from the template; will be replaced with real injections.
@Mixin(Minecraft.class)
public class ExampleClientMixin {
	@Inject(at = @At("HEAD"), method = "run")
	private void init(CallbackInfo info) {
	}
}
