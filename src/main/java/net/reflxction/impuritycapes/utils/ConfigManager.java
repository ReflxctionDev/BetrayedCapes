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
package net.reflxction.impuritycapes.utils;

import net.reflxction.impuritycapes.ImpurityCapes;

public class ConfigManager {

    /**
     * Sets if the mod is enabled or not
     *
     * @param b Flag if it should be enabled or not
     */
    public void setEnabled(boolean b) {
        ImpurityCapes.setEnabled(b);
        ImpurityCapes.getConfig().get("Enabled", "Enabled", true).set(b);
        ImpurityCapes.getConfig().save();
    }

    /**
     * Sets the Hypixel API key
     *
     * @param key Key to set
     */
    public void setKey(String key) {
        ImpurityCapes.setKey(key);
        ImpurityCapes.getConfig().get("Key", "Key", "").set(key);
        ImpurityCapes.getConfig().save();
    }

}
