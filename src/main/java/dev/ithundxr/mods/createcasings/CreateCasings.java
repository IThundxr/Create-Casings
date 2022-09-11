package dev.ithundxr.mods.createcasings;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import dev.ithundxr.mods.createcasings.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.jarjar.util.Lazy;
import org.slf4j.Logger;

@Mod(CreateCasings.MOD_ID)
public class CreateCasings {
    public static final String MOD_ID = "createcasings";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MOD_ID));

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static Registrate registrate() {
        return REGISTRATE.get();
    }


    public CreateCasings() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        ModBlocks.register();

        modEventBus.addListener(this::setup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {}
}