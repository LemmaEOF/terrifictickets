package gay.lemmaeof.terrifictickets;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class TerrificTicketsClient implements ClientModInitializer {
	public static final Identifier STACK_SIZE_ID = Identifier.of(TerrificTickets.MODID, "stack_size");
	public static final ClampedModelPredicateProvider STACK_SIZE = ((stack, world, entity, seed) -> (float)stack.getCount() / (float)stack.getMaxCount());

	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(TerrificTickets.TICKET, STACK_SIZE_ID, STACK_SIZE);
		ModelPredicateProviderRegistry.register(TerrificTickets.TOKEN, STACK_SIZE_ID, STACK_SIZE);
	}
}
