package dev.theatricalmod.theatrical.items;

import dev.theatricalmod.theatrical.block.light.BlockGenericFixture;
import dev.theatricalmod.theatrical.tiles.lights.TileEntityGenericFixture;
import net.minecraft.block.BlockState;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import javax.annotation.Nonnull;

public class ItemGelFrame extends Item implements IDyeableArmorItem {
    public ItemGelFrame(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        if (!context.getWorld().isRemote && state.getBlock() instanceof BlockGenericFixture) {
            TileEntityGenericFixture tile = (TileEntityGenericFixture) context.getWorld().getTileEntity(context.getPos());
            if(tile != null && context.getPlayer() != null) {
                if (context.getPlayer().isSneaking() && tile.getGel() != null) {
                    context.getPlayer().addItemStackToInventory(tile.getGel());
                    tile.removeGel();
                    return ActionResultType.SUCCESS;
                } else if (tile.setGel(context.getItem())) {
                    context.getItem().shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }
}
