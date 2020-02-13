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
package io.github.reflxction.betrayedcapes;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import io.github.reflxction.betrayedcapes.cache.CacheImpl;
import io.github.reflxction.betrayedcapes.cache.ICache;
import io.github.reflxction.betrayedcapes.proxy.IProxy;
import io.github.reflxction.betrayedcapes.utils.Reference;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * BetrayedCapes: A mod which gives special capes to Betrayed members.
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class BetrayedCapes {

    // Config for saving data
    private static Configuration config;

    // If the mod is enabled
    private static boolean enabled;

    // API key to work on
    private static String key;

    // Cache
    private static List<UUID> cache;

    // Cache implementation instance
    private static ICache c = new CacheImpl();
    /*
     * Initialize variables here
     */
    static {
        config = new Configuration(new File("config/betrayed-capes.cfg"));
        enabled = config.get("Enabled", "Enabled", true).getBoolean();
        key = config.get("Key", "Key", "").getString();
        cache = c.createCache();
    }

    /**
     * Set the mod proxies
     */
    @SidedProxy(
            clientSide = Reference.CLIENT_PROXY,
            serverSide = Reference.SERVER_PROXY
    )
    private static IProxy proxy;

    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Register events and client commands here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * Called after the mod is initialized
     */
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    /**
     * Register server commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    /**
     * @return If the mod is enabled
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the cached enabled value
     *
     * @param enabled Boolean to set
     */
    public static void setEnabled(boolean enabled) {
        BetrayedCapes.enabled = enabled;
    }

    /**
     * Sets the cached key
     *
     * @param key Key to set
     */
    public static void setKey(String key) {
        BetrayedCapes.key = key;
    }

    /**
     * Returns the cached key
     *
     * @return ^
     */
    public static String getKey() {
        return key;
    }

    /**
     * The config
     *
     * @return Config which contains all the data the mod saves
     */
    public static Configuration getConfig() {
        return config;
    }

    /**
     * The cache singleton
     *
     * @return The latest cache singleton
     */
    public static List<UUID> getCache() {
        return cache;
    }

    /**
     * Set the cache singleton
     *
     * @param cache Cache to set
     */
    public static void setCache(List<UUID> cache) {
        BetrayedCapes.cache = cache;
    }
}
