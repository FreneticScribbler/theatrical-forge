package com.georlegacy.general.theatrical.api.fixtures;

import com.georlegacy.general.theatrical.api.ChannelsDefinition;
import com.georlegacy.general.theatrical.util.Reference;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
import net.minecraftforge.registries.RegistryBuilder;

public class Fixture extends Impl<Fixture> {

    private static ForgeRegistry<Fixture> REGISTRY;

    public static void createRegistry() {
        if (REGISTRY == null) {
            ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, "fixtures");
            REGISTRY = (ForgeRegistry<Fixture>) new RegistryBuilder<Fixture>().setType(Fixture.class).setName(registryName).create();
            MinecraftForge.EVENT_BUS.post(new Register<>(registryName, REGISTRY));
        }
    }

    public static ForgeRegistry<Fixture> getRegistry() {
        return REGISTRY;
    }

    private ResourceLocation name;
    private FixtureType fixtureType;
    private HangableType hangableType;
    private ResourceLocation staticModelLocation;
    private ResourceLocation hookedModelLocation;
    private ResourceLocation tiltModelLocation;
    private ResourceLocation panModelLocation;
    private float[] tiltRotationPosition;
    private float[] panRotationPosition;
    private float[] beamStartPosition;
    private float defaultRotation;
    private float beamWidth;
    private float rayTraceRotation;
    private float maxLightDistance;
    private int maxEnergy;
    private int energyUse;
    private int energyUseTimer;
    private int channelCount;
    private ChannelsDefinition channelsDefinition;

    private IBakedModel staticModel;
    private IBakedModel hookedModel;
    private IBakedModel tiltModel;
    private IBakedModel panModel;

    /**
     * An instance of a fixture
     *
     * @param name Name of Fixture
     * @param fixtureType The Type of Fixture
     * @param hangableType How the fixture hangs
     * @param staticModelLocation The location of the static model
     * @param hookedModelLocation The location of the hooked model
     * @param tiltModelLocation The location of the model that tilts
     * @param panModelLocation The Location of the model that pans
     * @param tiltRotationPosition The middle of the tilt rotation area
     * @param panRotationPosition The middle of the pan rotation area
     * @param beamStartPosition The location of where the beam starts
     * @param defaultRotation The default rotation of the model
     * @param beamWidth The width of the beam
     * @param rayTraceRotation Any extra raytracing rotation
     */
    public Fixture(ResourceLocation name, FixtureType fixtureType, HangableType hangableType, ResourceLocation staticModelLocation, ResourceLocation hookedModelLocation, ResourceLocation tiltModelLocation, ResourceLocation panModelLocation, float[] tiltRotationPosition, float[] panRotationPosition, float[] beamStartPosition, float defaultRotation, float beamWidth, float rayTraceRotation, float maxLightDistance, int maxEnergy, int energyUse, int energyUseTimer, int channelCount, ChannelsDefinition channelsDefinition) {
        this.name = name;
        this.setRegistryName(new ResourceLocation(name.getNamespace(), name.getPath() + "_fixture"));
        this.fixtureType = fixtureType;
        this.hangableType = hangableType;
        this.staticModelLocation = staticModelLocation;
        this.hookedModelLocation = hookedModelLocation;
        this.tiltModelLocation = tiltModelLocation;
        this.panModelLocation = panModelLocation;
        this.tiltRotationPosition = tiltRotationPosition;
        this.panRotationPosition = panRotationPosition;
        this.beamStartPosition = beamStartPosition;
        this.defaultRotation = defaultRotation;
        this.beamWidth = beamWidth;
        this.rayTraceRotation = rayTraceRotation;
        this.maxLightDistance = maxLightDistance;
        this.energyUse = energyUse;
        this.energyUseTimer = energyUseTimer;
        this.channelCount = channelCount;
        this.channelsDefinition = channelsDefinition;
        this.maxEnergy = maxEnergy;
    }

    public ResourceLocation getName() {
        return name;
    }

    public HangableType getHangableType() {
        return hangableType;
    }

    public ResourceLocation getStaticModelLocation() {
        return staticModelLocation;
    }

    public ResourceLocation getTiltModelLocation() {
        return tiltModelLocation;
    }

    public ResourceLocation getPanModelLocation() {
        return panModelLocation;
    }

    public ResourceLocation getHookedModelLocation() {
        return hookedModelLocation;
    }

    public float[] getTiltRotationPosition() {
        return tiltRotationPosition;
    }

    public float[] getPanRotationPosition() {
        return panRotationPosition;
    }

    public float[] getBeamStartPosition() {
        return beamStartPosition;
    }

    public float getDefaultRotation() {
        return defaultRotation;
    }

    public float getBeamWidth() {
        return beamWidth;
    }

    public float getRayTraceRotation() {
        return rayTraceRotation;
    }

    public FixtureType getFixtureType() {
        return fixtureType;
    }

    public IBakedModel getStaticModel() {
        return staticModel;
    }

    public void setStaticModel(IBakedModel staticModel) {
        this.staticModel = staticModel;
    }

    public IBakedModel getTiltModel() {
        return tiltModel;
    }

    public void setTiltModel(IBakedModel tiltModel) {
        this.tiltModel = tiltModel;
    }

    public IBakedModel getPanModel() {
        return panModel;
    }

    public void setPanModel(IBakedModel panModel) {
        this.panModel = panModel;
    }

    public IBakedModel getHookedModel() {
        return hookedModel;
    }

    public void setHookedModel(IBakedModel hookedModel) {
        this.hookedModel = hookedModel;
    }

    public float getMaxLightDistance() {
        return maxLightDistance;
    }

    public int getEnergyUse() {
        return energyUse;
    }

    public int getEnergyUseTimer() {
        return energyUseTimer;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public ChannelsDefinition getChannelsDefinition() {
        return channelsDefinition;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }
}
