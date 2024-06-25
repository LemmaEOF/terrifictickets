package gay.lemmaeof.terrifictickets.block;

import gay.lemmaeof.terrifictickets.TerrificTickets;
import gay.lemmaeof.terrifictickets.api.TerrificTicketsApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TicketAcceptorBlock extends Block {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
	public static final IntProperty COST = IntProperty.of("cost", 1, 16);

	public TicketAcceptorBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVATED, false).with(COST, 1));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(FACING, ACTIVATED, COST);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (state.get(ACTIVATED)) {
			return ItemActionResult.CONSUME;
		} else if (TerrificTicketsApi.getTickets(stack) >= state.get(COST)) {
			if (!player.isCreative()) TerrificTicketsApi.removeTickets(stack, state.get(COST));
			world.setBlockState(pos, state.with(ACTIVATED, true));
			world.scheduleBlockTick(pos, this, 10);
			world.playSound(null, pos, TerrificTickets.TICKET_ACCEPT, SoundCategory.BLOCKS, 1f, world.getRandom().nextFloat() * 0.4f + 0.8f);
			return ItemActionResult.SUCCESS;
		} else if (stack.isEmpty() && player.isCreative()) {
			int newValue = cycle(COST.getValues(), state.get(COST), player.isSneaking());
			world.setBlockState(pos, state.with(COST, newValue));
			player.sendMessage(Text.translatable("text.terrifictickets.cycle_ticket", newValue), true);
			return ItemActionResult.SUCCESS;
		}
		return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
	}

	@Override
	protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.scheduledTick(state, world, pos, random);
		world.setBlockState(pos, state.with(ACTIVATED, false));
	}

	@Override
	protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(ACTIVATED)? 16 : 0;
	}

	@Override
	protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(ACTIVATED)? 16 : 0;
	}

	@Override
	protected boolean emitsRedstonePower(BlockState state) {
		return state.get(ACTIVATED);
	}

	private static <T> T cycle(Iterable<T> elements, @Nullable T current, boolean inverse) {
		return (T)(inverse ? Util.previous(elements, current) : Util.next(elements, current));
	}
}
