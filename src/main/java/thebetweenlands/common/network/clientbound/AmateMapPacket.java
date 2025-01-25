package thebetweenlands.common.network.clientbound;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.item.misc.AmateMapItem;
import thebetweenlands.common.world.storage.AmateMapData;

public record AmateMapPacket(ClientboundMapItemDataPacket inner) implements CustomPacketPayload {

	public static final Type<AmateMapPacket> TYPE = new Type<>(TheBetweenlands.prefix("amate_map"));

	public static final StreamCodec<RegistryFriendlyByteBuf, AmateMapPacket> STREAM_CODEC = StreamCodec.composite(ClientboundMapItemDataPacket.STREAM_CODEC, p -> p.inner, AmateMapPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	@SuppressWarnings("Convert2Lambda")
	public static void handle(AmateMapPacket message, IPayloadContext ctx) {
		//ensure this is only done on clients as this uses client only code
		if (ctx.flow().isClientbound()) {
			ctx.enqueueWork(new Runnable() {
				@Override
				public void run() {
					Level level = ctx.player().level();
					// [VanillaCopy] ClientPacketListener#handleMapItemData with our own mapdatas
					MapId mapid = message.inner().mapId();
					MapItemSavedData mapdata = level.getMapData(mapid);
					if (mapdata == null) {
						mapdata = AmateMapData.createForClient(message.inner.locked());
						Minecraft.getInstance().level.overrideMapData(mapid, mapdata);
					}

					message.inner.applyToMap(mapdata);
					Minecraft.getInstance().getMapTextureManager().update(mapid, mapdata);
				}
			});
		}
	}
}
