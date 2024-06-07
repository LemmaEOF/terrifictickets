package gay.lemmaeof.terrifictickets.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

public record PasscardComponent(int tokens, int tickets) implements TooltipAppender {
	public static final PasscardComponent EMPTY = new PasscardComponent(0, 0);
	public static final Codec<PasscardComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codecs.NONNEGATIVE_INT.fieldOf("tokens").forGetter(PasscardComponent::tokens),
			Codecs.NONNEGATIVE_INT.fieldOf("tickets").forGetter(PasscardComponent::tickets)
	).apply(instance, PasscardComponent::new));

	public boolean isEmpty() {
		return tokens == 0 && tickets == 0;
	}

	public boolean hasTokens(int tokenCount) {
		return tokens >= tokenCount;
	}

	public boolean hasTickets(int ticketCount) {
		return tickets >= ticketCount;
	}

	public PasscardComponent withTokens(int tokens) {
		return new PasscardComponent(tokens, this.tickets);
	}

	public PasscardComponent withTickets(int tickets) {
		return new PasscardComponent(this.tokens, tickets);
	}

	public PasscardComponent addTokens(int tokens) {
		return new PasscardComponent(this.tokens+tokens, this.tickets);
	}

	public PasscardComponent addTickets(int tickets) {
		return new PasscardComponent(this.tokens, this.tickets+tickets);
	}

	public Pair<PasscardComponent, Integer> removeTokens(int tokens) {
		int tokensRemoved = Math.min(this.tokens, tokens);
		return new Pair<>(new PasscardComponent(this.tokens-tokensRemoved, this.tickets), tokensRemoved);
	}

	public Pair<PasscardComponent, Integer> removeTickets(int tickets) {
		int ticketsRemoved = Math.min(this.tickets, tickets);
		return new Pair<>(new PasscardComponent(this.tokens, this.tickets-ticketsRemoved), ticketsRemoved);
	}

	@Override
	public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
		tooltip.accept(Text.translatable("tooltip.terrifictickets.token_display", tokens).formatted(Formatting.GRAY));
		tooltip.accept(Text.translatable("tooltip.terrifictickets.ticket_display", tickets).formatted(Formatting.GRAY));
	}
}
