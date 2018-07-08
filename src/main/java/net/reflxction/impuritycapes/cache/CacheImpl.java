/*
 *
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
 *
 */
package net.reflxction.impuritycapes.cache;

import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.hypixelapi.guild.Guild;
import me.kbrewster.hypixelapi.guild.Member;
import me.kbrewster.mojangapi.MojangAPI;
import net.reflxction.impuritycapes.ImpurityCapes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link net.reflxction.impuritycapes.cache.ICache}
 */
public class CacheImpl implements ICache {

    private List<UUID> playersCache = new ArrayList<>();

    /**
     * Set the current cached data singleton
     */
    @Override
    public List<UUID> createCache() {
        try {
            playersCache.clear(); // For safety to avoid memory leaks

            HypixelAPI api = new HypixelAPI(ImpurityCapes.getKey());

            String impurityID = api.getGuildID("Scindra");

            Guild impurity = api.getGuild(impurityID);

            for (Member m : impurity.getMembers()) {
                playersCache.add(UUID.fromString(MojangAPI.addDashes(m.getUuid())));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return playersCache;
    }

}
