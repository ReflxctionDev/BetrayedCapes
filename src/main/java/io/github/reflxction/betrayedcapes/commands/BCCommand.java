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
package io.github.reflxction.betrayedcapes.commands;

import io.github.reflxction.betrayedcapes.BetrayedCapes;
import io.github.reflxction.betrayedcapes.cache.ICache;
import io.github.reflxction.betrayedcapes.events.EventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import io.github.reflxction.betrayedcapes.cache.CacheImpl;
import io.github.reflxction.betrayedcapes.cache.CacheManager;
import io.github.reflxction.betrayedcapes.utils.ChatColor;
import io.github.reflxction.betrayedcapes.utils.ConfigManager;
import io.github.reflxction.betrayedcapes.utils.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class which handles command input for "/bc" and "/betrayedcapes"
 */
public class BCCommand implements ICommand {

    private ConfigManager config = new ConfigManager();

    private ICache cache = new CacheImpl();
    private CacheManager cacheManager = new CacheManager();

    @Override
    public String getCommandName() {
        return "betrayedcapes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/betrayedcapes <toggle / key / cache>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("bc");
    }

    @Override
    public void processCommand(ICommandSender executor, String[] args) {
        switch (args.length) {
            case 0:
                sendMessage("&cIncorrect command usage. Try " + getCommandUsage(executor));
                break;
            case 1:
                switch (args[0]) {
                    case "toggle":
                        config.setEnabled(!BetrayedCapes.isEnabled());
                        break;
                    case "cache":
                        BetrayedCapes.setCache(cache.createCache());
                        cacheManager.saveCacheToConfig();
                        break;
                }
                break;
            case 2:
                if (args[0].equalsIgnoreCase("key")) {
                    EventFactory.onKeySet(args[1]);
                }
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return new ArrayList<>(Arrays.asList("cache", "key", "toggle"));
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    /**
     * Sends a message to the client, chat-formatted
     *
     * @param message Message to send
     */
    private static void sendMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(message)));
        }
    }

}
