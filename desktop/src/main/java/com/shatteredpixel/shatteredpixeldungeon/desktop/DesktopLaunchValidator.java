/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.desktop;

import com.badlogic.gdx.utils.SharedLibraryLoader;
import org.lwjgl.system.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class DesktopLaunchValidator {

	//Validates that the launching JVM is correctly configured
	// and attempts to launch a new one if it is not
	// returns false if current JVM is invalid and should be killed.
	public static boolean verifyValidJVMState(String[] args){

		//mac computers require the -XstartOnFirstThread JVM argument
		if (SharedLibraryLoader.isMac){

			// If XstartOnFirstThread is present and enabled, we can return true
			if ("1".equals(System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" +
					ManagementFactory.getRuntimeMXBean().getName().split("@")[0]))) {
				return true;
			} else {
				if (SharedLibraryLoader.isMac) {
					Configuration.GLFW_LIBRARY_NAME.set("glfw_async");
				}
				return true;
			}

			// Check if we are the relaunched process, if so return true to avoid looping.
			// The game will likely crash, but that's unavoidable at this point.

            // Relaunch a new jvm process with the same arguments, plus -XstartOnFirstThread



		}

		return true;
	}

}
