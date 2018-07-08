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
package net.reflxction.impuritycapes.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.reflxction.impuritycapes.ImpurityCapes;
import net.reflxction.impuritycapes.cache.CacheImpl;
import net.reflxction.impuritycapes.cache.CacheManager;
import net.reflxction.impuritycapes.cache.ICache;
import net.reflxction.impuritycapes.events.EventFactory;
import net.reflxction.impuritycapes.utils.ChatColor;
import net.reflxction.impuritycapes.utils.ConfigManager;
import net.reflxction.impuritycapes.utils.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which handles command input for "/ic" and "/impuritycapes"
 */
public class ICCommand implements ICommand {

    private ConfigManager config = new ConfigManager();

    private ICache cache = new CacheImpl();
    private CacheManager cacheManager = new CacheManager();

    @Override
    public String getCommandName() {
        return "impuritycapes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/impuritycapes <toggle>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("ic");
        return aliases;
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
                        config.setEnabled(!ImpurityCapes.isEnabled());
                        break;
                    case "cache":
                        ImpurityCapes.setCache(cache.createCache());
                        cacheManager.saveCacheToConfig();
                        break;
                }
                break;
            case 2:
                switch (args[0]) {
                    case "key":
                        EventFactory.onKeySet(args[1]);
                        break;
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
        List<String> tabs = new ArrayList<>();
        tabs.add("impuritycapes");
        tabs.add("ic");
        return tabs;
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
    private void sendMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(message)));
        }
    }

}
