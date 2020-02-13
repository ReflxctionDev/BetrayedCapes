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
package io.github.reflxction.betrayedcapes.listeners;

import io.github.reflxction.betrayedcapes.events.KeySetEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import io.github.reflxction.betrayedcapes.utils.ChatColor;
import io.github.reflxction.betrayedcapes.utils.ConfigManager;
import io.github.reflxction.betrayedcapes.utils.Reference;

/**
 * Listener for {@link KeySetEvent}
 */
public class KeySetListener {

    private ConfigManager manager = new ConfigManager();

    @SubscribeEvent
    public void onKeySet(KeySetEvent event) {
        final String key = event.getKey();
        manager.setKey(key);
        sendMessage("&aYour API key has been set to &e" + key);
    }

    /**
     * Sends a message to the client, chat-formatted
     *
     * @param message Message to send
     */
    private void sendMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(message)));
        }
    }

}
