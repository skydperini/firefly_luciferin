/*
  GrabberSingleton.java

  Firefly Luciferin, very fast Java Screen Capture software designed
  for Glow Worm Luciferin firmware.

  Copyright © 2020 - 2023  Davide Perini  (https://github.com/sblantipodi)

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package org.dpsoftware.grabber;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dpsoftware.LEDCoordinate;
import org.dpsoftware.managers.dto.AudioDevice;
import org.freedesktop.gstreamer.Pipeline;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Grabber singleton used to share common data
 */
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("all")
public class GrabberSingleton {

    @Getter
    private final static GrabberSingleton instance;

    static {
        instance = new GrabberSingleton();
    }

    public volatile boolean RUNNING_AUDIO = false;
    public int AUDIO_BRIGHTNESS = 255;
    public Map<String, AudioDevice> audioDevices = new LinkedHashMap<>();
    public float rainbowHue = 0;
    public boolean CHECK_ASPECT_RATIO = true;
    // Only one instace must be used, Java Garbage Collector will not be fast enough in freeing memory with more instances
    public BufferedImage screen;
    // LED Matrix Map
    public LinkedHashMap<Integer, LEDCoordinate> ledMatrix;
    // Screen capture rectangle
    public Rectangle rect;
    // GStreamer Rendering pipeline
    public Pipeline pipe;
    // There is a known issue that prevents to correcly scale the captured image with some resolutions/GPUs.
    // for example 3440x1440 on NVIDIA, 1920x1080 on AMD. Scale the image on the CPU if this is the case.
    public boolean fallbackPipeline;
    float maxPeak, maxRms = 0;
    float maxPeakLeft, maxRmsLeft = 0;
    float maxPeakRight, maxRmsRight = 0;
    int runNumber = 0;
    float lastRmsRun = 0, lastRmsRunLeft = 0, lastRmsRunRight = 0;
    float lastPeackRun = 0, lastPeackRunLeft = 0, lastPeackRunRight = 0;
    // Custom JNA Class for GDI32Util
    CustomGDI32Util customGDI32Util;

}

