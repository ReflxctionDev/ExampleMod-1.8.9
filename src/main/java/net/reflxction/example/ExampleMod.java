/*
 * * Copyright 2018 github.com/ReflxctionDev
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
package net.reflxction.example;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.reflxction.example.proxy.IProxy;
import net.reflxction.example.updater.UpdateManager;
import net.reflxction.example.updater.VersionChecker;
import net.reflxction.example.utils.Reference;

import java.io.File;

/**
 * ExampleMod: The mod template used by all of my mods
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class ExampleMod {

    public static final ExampleMod INSTANCE = new ExampleMod();

    // Config for saving data
    private Configuration config = new Configuration(new File("config" + File.separator + "example-config.cfg"));

    // Assign proxies of the mod
    @SidedProxy(

            // Client side proxy
            clientSide = Reference.CLIENT_PROXY,

            // Server side proxy
            serverSide = Reference.SERVER_PROXY
    )
    private static IProxy proxy;

    // The update manager
    private UpdateManager updateManager = new UpdateManager(true);

    // The version checker
    private VersionChecker checker = new VersionChecker();

    /**
     * Called before the mod is fully initialized
     * <p>
     * Registries: Initiate variables and client command registries
     *
     * @param event Forge's pre-init event
     */
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Called when the mod has been fully initialized
     * <p>
     * Registries: Events and client-server command registries
     *
     * @param event Forge's init event
     */
    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * Called after the mod has been successfully initialized
     * <p>
     * Registries: Nothing
     *
     * @param event Forge's post init event
     */
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    /**
     * The mod config
     *
     * @return The config file used to store all the mod data and HTTP caches if any
     */
    public Configuration getConfig() {
        return config;
    }

    /**
     * The mod update manager
     *
     * @return An instance of the mod update manager
     */
    public UpdateManager getUpdateManager() {
        return updateManager;
    }

    public VersionChecker getChecker() {
        return checker;
    }

}
