package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.*;
import com.tterrag.registrate.Registrate;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(CreateBrokenBad.MOD_ID)
public class CreateBrokenBad {
    public static final String MOD_ID = "createbb";

    public CreateBrokenBad() {
        Registrate registrate = Registrate.create(MOD_ID);


        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AllSoundEvents.register(eventBus);
        CBBEffects.register(eventBus);
        CBBItems.register(registrate);
        CBBBlocks.register(registrate);
        CBBFluids.register(registrate);
        CBBBlockEntityTypes.register(registrate);
        Tab.register(registrate, eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA_SEEDS.get(), 0.65f);
        });
        
        AllCustomTriggerAdvancements.register();
    }
}