package com.violacase.ocm.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import java.util.Random;

public class SunGemItem extends Item {

    public SunGemItem(Properties pProperties) {
        super(pProperties);
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundTag fireworksTag = new CompoundTag();
        CompoundTag explosion = new CompoundTag();

        if(pPlayer instanceof Player) {

            pPlayer.sendSystemMessage(Component.literal("YOU DID IT!"));
            //pPlayer.getServer().getPlayerList().broadcastSystemMessage(Component.literal(pPlayer.getName().getString() + " has fixed the Sun!"), true);
            for (int i = 0; i < 32; i++) {
                Random randomNumber = new Random();
                explosion.putByte("Type", (byte) (randomNumber.nextInt() % 5));
                explosion.putIntArray("Colors", new int[]{randomNumber.nextInt() % 0xffffff,randomNumber.nextInt() % 0xffffff});
                explosion.putIntArray("FadeColors", new int[] {randomNumber.nextInt() % 0xffffff});
                explosion.putBoolean("Flicker", randomNumber.nextBoolean());
                explosion.putBoolean("Trail", randomNumber.nextBoolean());

                fireworksTag.put("Explosions", new ListTag());
                fireworksTag.getList("Explosions", 10).add(explosion);
                fireworksTag.putByte("Flight", (byte) 1);

                firework.getOrCreateTagElement("Fireworks").merge(fireworksTag);

                FireworkRocketEntity fireworkEntity = new FireworkRocketEntity(pPlayer.level(), pPlayer.getX(), pPlayer.getY()+ 1.0, pPlayer.getZ(), firework);
                pPlayer.level().addFreshEntity(fireworkEntity);

            }
        }
        ItemStack stack =  pPlayer.getItemInHand(pUsedHand);
        stack.setCount(stack.getCount()-1);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    String tooltip = "";

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        tooltip = Component.translatable("tooltip.ocm.sun_gem.tooltip").toString();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, java.util.List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(tooltip));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



}
