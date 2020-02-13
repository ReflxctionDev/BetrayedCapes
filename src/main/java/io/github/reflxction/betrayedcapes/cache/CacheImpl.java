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
package io.github.reflxction.betrayedcapes.cache;

import io.github.reflxction.betrayedcapes.BetrayedCapes;
import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.hypixelapi.guild.Guild;
import me.kbrewster.hypixelapi.guild.Member;
import me.kbrewster.mojangapi.MojangAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link ICache}
 */
public class CacheImpl implements ICache {

    private static final String GUILD_ID = "5b5dd7340cf2a7d9577a3dba";

    private List<UUID> playersCache = new ArrayList<>();

    /**
     * Set the current cached data singleton
     */
    @Override
    public List<UUID> createCache() {
        try {
            playersCache.clear(); // For safety to avoid memory leaks

            HypixelAPI api = new HypixelAPI(BetrayedCapes.getKey());

            Guild betrayed = api.getGuild(GUILD_ID);

            for (Member m : betrayed.getMembers()) {
                playersCache.add(UUID.fromString(MojangAPI.addDashes(m.getUuid())));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return playersCache;
    }

}
