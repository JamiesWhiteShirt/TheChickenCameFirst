package com.jamieswhiteshirt.tccf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
        modid = TCCF.MODID,
        name = TCCF.NAME,
        version = TCCF.VERSION,
        acceptedMinecraftVersions = "[1.12,1.13)",
        dependencies = "required-after:forge@[14.21.1.2387,)"
)
@Mod.EventBusSubscriber
public class TCCF {
    public static final String MODID = "tccf";
	public static final String NAME = "The Chicken Came First";
    public static final String VERSION = "1.0";

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getItem();
            if (stack.getItem() == Items.EGG) {
                event.setCanceled(true);

                World world = event.getWorld();
                if (!world.isRemote) {
                    double posX = entityItem.posX;
                    double posY = entityItem.posY;
                    double posZ = entityItem.posZ;

                    for (int i = 0; i < stack.getCount(); i++) {
                        EntityChicken entityChicken = new EntityChicken(world);
                        entityChicken.setPosition(posX, posY, posZ);
                        event.getWorld().spawnEntity(entityChicken);
                    }
                }
            }
        }
    }
}
