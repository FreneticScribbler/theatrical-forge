/*
 * Copyright 2018 Theatrical Team (James Conway (615283) & Stuart (Rushmead)) and it's contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.theatricalmod.theatrical.handlers;

import dev.theatricalmod.theatrical.api.capabilities.WorldPipePanelNetwork;
import dev.theatricalmod.theatrical.api.capabilities.WorldSocapexNetwork;
import dev.theatricalmod.theatrical.api.capabilities.dmx.WorldDMXNetwork;
import dev.theatricalmod.theatrical.api.fixtures.Fixture;
import dev.theatricalmod.theatrical.api.fixtures.FixtureType;
import dev.theatricalmod.theatrical.blocks.fixtures.BlockIntelligentFixture;
import dev.theatricalmod.theatrical.blocks.fixtures.BlockTungstenLight;
import dev.theatricalmod.theatrical.blocks.fixtures.base.IHasTileEntity;
import dev.theatricalmod.theatrical.init.TheatricalBlocks;
import dev.theatricalmod.theatrical.init.TheatricalFixtures;
import dev.theatricalmod.theatrical.init.TheatricalItems;
import dev.theatricalmod.theatrical.init.TheatricalSoundEvents;
import dev.theatricalmod.theatrical.tiles.cables.TileCable;
import dev.theatricalmod.theatrical.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandler {
    private static final ResourceLocation LOC = new ResourceLocation("theatrical:mcmp");
    private static final ResourceLocation WORLD_CAP_ID = new ResourceLocation(Reference.MOD_ID, "dmx_world_network");
    private static final ResourceLocation PIPE_PANEL_NETWORK = new ResourceLocation(Reference.MOD_ID, "pipe_panel_network");
    private static final ResourceLocation SOCAPEX_NETWORK_ID = new ResourceLocation(Reference.MOD_ID, "socapex_network");


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TheatricalItems.ITEMS.toArray(new Item[0]));
        TheatricalBlocks.BLOCKS.forEach(block -> event.getRegistry()
            .register(new ItemBlock(block).setRegistryName(block.getRegistryName())));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(TheatricalBlocks.BLOCKS.toArray(new Block[0]));
        TheatricalBlocks.BLOCKS.forEach(block -> {
            if (block instanceof IHasTileEntity) {
                IHasTileEntity fixture = (IHasTileEntity) block;
                GameRegistry.registerTileEntity(fixture.getTileEntity(), block.getRegistryName());
            }
        });
        for (Fixture fixture : Fixture.getRegistry()) {
            if (fixture.getFixtureType() == FixtureType.TUNGSTEN) {
                event.getRegistry().register(new BlockTungstenLight(fixture));
            } else if (fixture.getFixtureType() == FixtureType.INTELLIGENT) {
                event.getRegistry().register(new BlockIntelligentFixture(fixture));
            }
            GameRegistry.registerTileEntity(fixture.getFixtureType().getTileClass().get().getClass(), fixture.getName());
        }
        event.getRegistry().register(TheatricalBlocks.BLOCK_CABLE);
        GameRegistry.registerTileEntity(TileCable.class, TheatricalBlocks.BLOCK_CABLE.getRegistryName());
    }

    @SubscribeEvent
    public static void onFixtureRegister(RegistryEvent.Register<Fixture> event) {
//        event.getRegistry().register(TheatricalFixtures.FRESNEL);
        event.getRegistry().register(TheatricalFixtures.MOVING_HEAD);
        event.getRegistry().registerAll(TheatricalFixtures.fixtures.toArray(new Fixture[0]));
    }

    @SubscribeEvent
    public static void onSoundEventRegister(RegistryEvent.Register<SoundEvent> event) {
        TheatricalSoundEvents.SOUNDS.forEach(sound -> event.getRegistry().register(sound));
    }

    @SubscribeEvent
    public static void attachWorldCap(AttachCapabilitiesEvent<World> event)
    {
        event.addCapability(WORLD_CAP_ID, new WorldDMXNetwork(event.getObject()));
        event.addCapability(PIPE_PANEL_NETWORK, new WorldPipePanelNetwork(event.getObject()));
        event.addCapability(SOCAPEX_NETWORK_ID, new WorldSocapexNetwork(event.getObject()));
    }

    @SubscribeEvent
    public static void tickServerWorld(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            WorldDMXNetwork.getCapability(event.world).tick();
            WorldPipePanelNetwork.getCapability(event.world).tick();
            WorldSocapexNetwork.getCapability(event.world).tick();
        }
    }

}