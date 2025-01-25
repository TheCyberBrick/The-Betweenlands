package thebetweenlands.common.item.misc;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import thebetweenlands.common.network.clientbound.OpenLoreScrapPacket;

public class LoreScrapItem extends Item {
	public LoreScrapItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer sp) {
			PacketDistributor.sendToPlayer(sp, new OpenLoreScrapPacket(BuiltInRegistries.ITEM.getKey(this)));
		}
		return InteractionResult.SUCCESS;
	}
}
