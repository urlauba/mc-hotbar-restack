package io.github.urlauba.hotbarrestack;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

/**
 * Keeps the player's selected hotbar stack at capacity by pulling matching
 * items from the rest of the inventory, so an item in active use never runs
 * out while the player still owns more of it.
 */
public final class HotbarTopUp {
	private HotbarTopUp() {
	}

	public static void topUp(ServerPlayer player) {
		if (player.isSpectator() || player.isCreative()) {
			return;
		}

		Inventory inventory = player.getInventory();
		ItemStack held = inventory.getSelectedItem();

		// Only ever touch plain stackables
		// damageable items (weapons, tools, armor) and other non-stackables must never be moved automatically.
		if (held.isEmpty() || !held.isStackable() || held.isDamageableItem()) {
			return;
		}

		int missing = held.getMaxStackSize() - held.getCount();

		while (missing > 0) {
			int donorSlot = findDonorSlot(inventory, held);
			if (donorSlot == Inventory.NOT_FOUND_INDEX) {
				return;
			}

			ItemStack donor = inventory.getItem(donorSlot);
			int moved = Math.min(missing, donor.getCount());
			donor.shrink(moved);
			if (donor.isEmpty()) {
				inventory.setItem(donorSlot, ItemStack.EMPTY);
			}
			held.grow(moved);
			missing -= moved;
			inventory.setChanged();
		}
	}

	/**
	 * Returns the first slot holding a matching stack. Hotbar slots (0-8)
	 * precede main-inventory slots (9-35), so other hotbar stacks are
	 * consolidated before reserves are pulled from the main inventory.
	 */
	private static int findDonorSlot(Inventory inventory, ItemStack held) {
		int selected = inventory.getSelectedSlot();

		for (int slot = 0; slot < inventory.getNonEquipmentItems().size(); slot++) {
			if (slot == selected) {
				continue;
			}

			ItemStack candidate = inventory.getItem(slot);
			if (!candidate.isEmpty() && ItemStack.isSameItemSameComponents(held, candidate)) {
				return slot;
			}
		}

		return Inventory.NOT_FOUND_INDEX;
	}
}
