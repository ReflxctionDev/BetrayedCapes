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
package net.reflxction.impuritycapes.cape;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Class which downloads capes from the URL
 */
class CapeDownloader {

    private ResourceLocation capeTexture = capeTexture(); // Cache for the cape texture to avoid excessive HTTP requests

    /**
     * Cape image
     *
     * @return A BufferedImage of the cape image
     */
    private BufferedImage getCapeImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL("https://i.imgur.com/Vh1uLrX.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * ResourceLocation of the cape
     *
     * @return ResourceLocation of the cape texture from a buffered image
     */
    private ResourceLocation capeTexture() {
        return Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("impuritycape", new DynamicTexture(getCapeImage()));
    }

    /**
     * The cape texture cache
     *
     * @return The cape texture cache
     */
    public ResourceLocation getCapeTexture() {
        return capeTexture;
    }

}
