package com.hoshino.cti.Items;

import com.hoshino.cti.register.ctiTab;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.common.util.FakePlayer;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlatWorldTabletItem extends Item {
    public static final ResourceKey<Level> ULTRA_FLAT_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation("kubejs","ultra_flat"));
    public FlatWorldTabletItem() {
        super(new Item.Properties().tab(ctiTab.MIXC).stacksTo(1).fireResistant());
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide&&living instanceof ServerPlayer player&&!(player instanceof FakePlayer)) {
            MinecraftServer server = player.getServer();
            if (server!=null) {
                if (level.dimension().equals(Level.OVERWORLD)) {
                    player.teleportTo(server.getLevel(ULTRA_FLAT_KEY),0,324,0,0,0);
                }else if (level.dimension().equals(ULTRA_FLAT_KEY)){
                    player.teleportTo(server.getLevel(Level.OVERWORLD),0,324,0,0,0);
                }
            }
        }
        return stack;
    }

    public int getUseDuration(ItemStack p_41454_) {
        return 20;
    }
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }
    @Override
    public void appendHoverText(ItemStack p_40572_, @Nullable Level p_40573_, List<Component> list, TooltipFlag p_40575_) {
        list.add(Component.literal("长按右键时将你传送至一个只有一层基岩的永夜超平坦维度，再次长按右键传送回主世界。").withStyle(ChatFormatting.AQUA));
        list.add(Component.literal("你会被传送至高空，如果没有任何防护措施的话你会摔死！").withStyle(ChatFormatting.RED));
        super.appendHoverText(p_40572_, p_40573_, list, p_40575_);
    }
}