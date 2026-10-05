package qjend.lifecrystal.Items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import qjend.lifecrystal.LifeCrystal;

public class life_crystal_Item_MaxHealth extends Item {

    public life_crystal_Item_MaxHealth(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        if (!level.isClientSide) {

            var attribute = player.getAttribute(
                    Attributes.MAX_HEALTH
            );

            if (attribute != null) {

                attribute.setBaseValue(
                        attribute.getBaseValue() + 2
                );

                player.setHealth(
                        Math.min(
                                player.getHealth() + 2,
                                player.getMaxHealth()
                        )
                );

                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.playNotifySound(
                            SoundEvents.PLAYER_LEVELUP,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.2F
                    );
                }

                player.getItemInHand(hand).shrink(1);

                if (player instanceof ServerPlayer serverPlayer) {

                    var advancement = level.getServer()
                            .getAdvancements()
                            .get(
                                    ResourceLocation.fromNamespaceAndPath(
                                            LifeCrystal.MODID,
                                            "life_crystal_advancement"
                                    )
                            );

                    if (advancement != null) {

                        var progress =
                                serverPlayer.getAdvancements()
                                        .getOrStartProgress(advancement);

                        if (!progress.isDone()) {

                            serverPlayer.getAdvancements()
                                    .award(
                                            advancement,
                                            "use_life_crystal"
                                    );
                        }
                    }
                }
            }
        }

        return InteractionResultHolder.consume(
                player.getItemInHand(hand)
        );
    }
}