package qjend.lifecrystal.Items;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import qjend.lifecrystal.LifeCrystal;

public class life_crystal_Item {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(LifeCrystal.MODID);

    public static final DeferredHolder<Item, Item> LIFE_CRYSTAL = ITEMS.register("life_crystal",
            () -> new life_crystal_Item_MaxHealth(new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
