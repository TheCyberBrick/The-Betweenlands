package thebetweenlands.common.item.shield;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DentrothystShieldItem extends BaseShieldItem {
	public DentrothystShieldItem(ToolMaterial material, Properties properties) {
		super(material, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.thebetweenlands.dentrothyst_shield.desc").withStyle(ChatFormatting.GRAY));
	}
}
