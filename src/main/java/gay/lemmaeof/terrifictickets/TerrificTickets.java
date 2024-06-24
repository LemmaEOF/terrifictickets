package gay.lemmaeof.terrifictickets;

import gay.lemmaeof.terrifictickets.block.TicketAcceptorBlock;
import gay.lemmaeof.terrifictickets.block.TokenAcceptorBlock;
import gay.lemmaeof.terrifictickets.component.PasscardComponent;
import gay.lemmaeof.terrifictickets.item.PasscardItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: data for survival-friendliness Later (though this is generally supposed to be for prebuilt worlds so maybe not)
public class TerrificTickets implements ModInitializer {
	public static final String MODID = "terrifictickets";
	public static final Logger LOGGER = LoggerFactory.getLogger(FabricLoader.getInstance().getModContainer(MODID).orElseThrow().getMetadata().getName());

	public static final ComponentType<PasscardComponent> PASSCARD_COMPONENT = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MODID, "passcard"),
			ComponentType.<PasscardComponent>builder().codec(PasscardComponent.CODEC).build()
	);

	public static final Item TOKEN = register("token", new Item(new Item.Settings().maxCount(128)));
	public static final Item TICKET = register("ticket", new Item(new Item.Settings().maxCount(256)));
	public static final PasscardItem PASSCARD = register("passcard", new PasscardItem(new Item.Settings().maxCount(1).component(PASSCARD_COMPONENT, PasscardComponent.EMPTY)));
	public static final TokenAcceptorBlock TOKEN_ACCEPTOR = register("token_acceptor", new TokenAcceptorBlock(AbstractBlock.Settings.create().mapColor(MapColor.DULL_PINK).strength(3)), new Item.Settings());
	public static final TicketAcceptorBlock TICKET_ACCEPTOR = register("ticket_acceptor", new TicketAcceptorBlock(AbstractBlock.Settings.create().mapColor(MapColor.DULL_PINK).strength(3)), new Item.Settings());

	public static final ItemGroup GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(MODID, "group"), FabricItemGroup.builder()
			.displayName(Text.translatable("itemGroup.terrifictickets.group"))
			.icon(() -> new ItemStack(TICKET))
			.entries((context, entries) -> {
				entries.add(TOKEN);
				entries.add(TICKET);
				entries.add(PASSCARD);
				entries.add(TOKEN_ACCEPTOR);
				entries.add(TICKET_ACCEPTOR);
			})
			.build());

	@Override
	public void onInitialize() {
		LOGGER.info("Dispensing tickets...");
	}

	private static <T extends Item> T register(String name, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(MODID, name), item);
	}

	private static <T extends Block> T register(String name, T block, Item.Settings settings) {
		T t = Registry.register(Registries.BLOCK, Identifier.of(MODID, name), block);
		Registry.register(Registries.ITEM, Identifier.of(MODID, name), new BlockItem(t, settings));
		return t;
	}
}
