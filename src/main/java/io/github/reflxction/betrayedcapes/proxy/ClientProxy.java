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
package io.github.reflxction.betrayedcapes.proxy;

import io.github.reflxction.betrayedcapes.commands.BCCommand;
import io.github.reflxction.betrayedcapes.listeners.EntityJoinWorldListener;
import io.github.reflxction.betrayedcapes.listeners.KeySetListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;

/**
 * Client proxy handler
 *
 * Not unused, annotated with {@link net.minecraftforge.fml.common.SidedProxy} gives it a use
 */
@SuppressWarnings("unused")
public class ClientProxy implements IProxy {

    /**
     * Called before the mod is fully initialized
     *
     * @param event Forge's pre-init event
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("preInit()");
        ClientCommandHandler.instance.registerCommand(new BCCommand());
    }

    /**
     * Called when the mod has been fully initialized
     *
     * @param event Forge's init event
     */
    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EntityJoinWorldListener());
        MinecraftForge.EVENT_BUS.register(new KeySetListener());
        System.out.println("init()");
    }

    /**
     * Called after the mod has been successfully initialized
     *
     * @param event Forge's post init event
     */
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println("post()");
    }


    /**
     * Called after {@link FMLServerAboutToStartEvent} and before {@link FMLServerStartedEvent}.
     *
     * @param event Forge's server starting event
     */
    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new BCCommand());
        System.out.println("serverStarted()");
    }

}
