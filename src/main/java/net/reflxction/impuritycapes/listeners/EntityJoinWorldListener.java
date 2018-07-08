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
package net.reflxction.impuritycapes.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.impuritycapes.ImpurityCapes;
import net.reflxction.impuritycapes.cache.CacheManager;
import net.reflxction.impuritycapes.cape.CapeLayer;

/**
 * Class which handles cape rendering
 * <p>
 * unchecked since the player's parts map doesn't have generics
 */
@SuppressWarnings("unchecked")
public class EntityJoinWorldListener {

    // Minecraft instance
    private Minecraft mc = Minecraft.getMinecraft();

    private CacheManager manager = new CacheManager();

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (ImpurityCapes.isEnabled()) {
            if (mc.thePlayer.getGameProfile().getId().equals(event.entity.getUniqueID())) {
                renderCape();
            }
            if (event.entity instanceof EntityPlayer) {
                if (manager.isCreated()) {
                    try {
                        final EntityPlayerMP playerMP = ((EntityPlayerMP) event.entity);

                        if (ImpurityCapes.getCache().contains(playerMP.getUniqueID())) {
                            renderCape();
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    private void renderCape() {
        Minecraft.getMinecraft().gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
        for (RenderPlayer render : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            render.addLayer(new CapeLayer(render));
        }
    }

}
