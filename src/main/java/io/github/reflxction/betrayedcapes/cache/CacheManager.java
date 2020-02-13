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

import java.util.UUID;

/**
 * Class which handles cache and updates it
 */
public class CacheManager {

    private ICache cache = new CacheImpl();

    public void saveCacheToConfig() {
        BetrayedCapes.setCache(cache.createCache());
        StringBuilder uuids = new StringBuilder();
        for (UUID uuid : BetrayedCapes.getCache()) {
            uuids.append(" ").append(uuid);
        }
        BetrayedCapes.getConfig().get("Cache", "Cache", "").set(uuids.toString().trim());
        BetrayedCapes.getConfig().get("Cache", "Done", false).set(true);
        BetrayedCapes.getConfig().save();
        System.out.println(uuids);
    }

    public boolean isCreated() {
        return BetrayedCapes.getConfig().get("Cache", "Done", false).getBoolean();
    }

}
