package gay.lemmaeof.terrifictickets.api;

import gay.lemmaeof.terrifictickets.TerrificTickets;
import gay.lemmaeof.terrifictickets.component.PasscardComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

//TODO: more methods as people need
//TODO: documentation? not sure how necessary atm
public class TerrificTicketsApi {
	public static int getTokens(ItemStack stack) {
		if (stack.isOf(TerrificTickets.TOKEN)) return stack.getCount();
		else if (stack.contains(TerrificTickets.PASSCARD_COMPONENT)) return stack.get(TerrificTickets.PASSCARD_COMPONENT).tokens();
		return 0;
	}

	public static int getTickets(ItemStack stack) {
		if (stack.isOf(TerrificTickets.TICKET)) return stack.getCount();
		else if (stack.contains(TerrificTickets.PASSCARD_COMPONENT)) return stack.get(TerrificTickets.PASSCARD_COMPONENT).tickets();
		return 0;
	}

	public static int removeTokens(ItemStack stack, int amount) {
		if (stack.isOf(TerrificTickets.TOKEN)) {
			int toRemove = Math.min(stack.getCount(), amount);
			stack.decrement(toRemove);
			return toRemove;
		} else if (stack.contains(TerrificTickets.PASSCARD_COMPONENT)) {
			Pair<PasscardComponent, Integer> res = stack.get(TerrificTickets.PASSCARD_COMPONENT).removeTokens(amount);
			stack.set(TerrificTickets.PASSCARD_COMPONENT, res.getLeft());
			return res.getRight();
		}
		return 0;
	}

	public static int removeTickets(ItemStack stack, int amount) {
		if (stack.isOf(TerrificTickets.TICKET)) {
			int toRemove = Math.min(stack.getCount(), amount);
			stack.decrement(toRemove);
			return toRemove;
		} else if (stack.contains(TerrificTickets.PASSCARD_COMPONENT)) {
			Pair<PasscardComponent, Integer> res = stack.get(TerrificTickets.PASSCARD_COMPONENT).removeTickets(amount);
			stack.set(TerrificTickets.PASSCARD_COMPONENT, res.getLeft());
			return res.getRight();
		}
		return 0;
	}
}
