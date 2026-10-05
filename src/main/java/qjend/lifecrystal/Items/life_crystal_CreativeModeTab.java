package qjend.lifecrystal.Items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import qjend.lifecrystal.LifeCrystal;

public class life_crystal_CreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> ITEM_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LifeCrystal.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MY_TAB =
            ITEM_TAB.register("life_crystal_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.life_crystal_tab"))
                    .icon(() -> new ItemStack(life_crystal_Item.LIFE_CRYSTAL.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(life_crystal_Item.LIFE_CRYSTAL.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        ITEM_TAB.register(eventBus);
    }
}
