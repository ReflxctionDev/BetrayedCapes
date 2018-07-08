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
package net.reflxction.impuritycapes.cache;

import net.reflxction.impuritycapes.ImpurityCapes;

import java.util.UUID;

/**
 * Class which handles cache and updates it
 */
public class CacheManager {

    private ICache cache = new CacheImpl();

    public void saveCacheToConfig() {
        ImpurityCapes.setCache(cache.createCache());
        StringBuilder uuids = new StringBuilder();
        for (UUID uuid : ImpurityCapes.getCache()) {
            uuids.append(" ").append(uuid);
        }
        ImpurityCapes.getConfig().get("Cache", "Cache", "").set(uuids.toString().trim());
        ImpurityCapes.getConfig().get("Cache", "Done", false).set(true);
        ImpurityCapes.getConfig().save();
        System.out.println(uuids);
    }

    public boolean isCreated() {
        return ImpurityCapes.getConfig().get("Cache", "Done", false).getBoolean();
    }

}
