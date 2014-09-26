/*
 * Copyright (C) 2012 CyberAgent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage.util;

import jp.co.cyberagent.android.gpuimage.Rotation;
import android.graphics.PointF;

public class TextureRotationUtil {
	
	public static final float TEXTURE_NO_ROTATION[] = {
	        0.0f, 1.0f,
	        1.0f, 1.0f,
	        0.0f, 0.0f,
	        1.0f, 0.0f,
	};

    private TextureRotationUtil() {
    }
    
    public static float[] getRotation(final Rotation rotation, final boolean flipHorizontal, final boolean flipVertical) {
    	return getRotation(new PointF(0f, 0f), new PointF(1f, 0f), new PointF(0f, 1f), new PointF(1f, 1f), rotation, flipHorizontal, flipVertical);
    }

    public static float[] getRotation(PointF topLeft, PointF topRight, PointF bottomLeft, PointF bottomRight, final Rotation rotation, final boolean flipHorizontal, final boolean flipVertical) {
    	
        float[] rotatedTex;
        switch (rotation) {
            case ROTATION_90:
                rotatedTex = new float[]{
                		bottomRight.x, bottomRight.y, //bottomRight 
                		topRight.x, topRight.y, //topRight
        	            bottomLeft.x, bottomLeft.y, //bottomLeft 
        	            topLeft.x, topLeft.y, //topLeft
        	    };
                break;
            case ROTATION_180:
                rotatedTex = new float[]{
                		topRight.x, topRight.y, //topRight
                		topLeft.x, topLeft.y, //topLeft 
        	            bottomRight.x, bottomRight.y, //bottomRight 
        	            bottomLeft.x, bottomLeft.y, //bottomLeft 
        	    };
                break;
            case ROTATION_270:
                rotatedTex = new float[]{
                		topLeft.x, topLeft.y, //topLeft
                		bottomLeft.x, bottomLeft.y, //bottomLeft 
        	            topRight.x, topRight.y, //topRight
        	            bottomRight.x, bottomRight.y, //bottomRight 
        	    };
                break;
            case NORMAL:
            default:
                rotatedTex = new float[]{
                		bottomLeft.x, bottomLeft.y, //bottomLeft 
                		bottomRight.x, bottomRight.y, //bottomRight 
                		topLeft.x, topLeft.y, //topLeft 
        	            topRight.x, topRight.y, //topRight
        	    };
                break;
        }
        if (flipHorizontal) {
            rotatedTex = new float[]{
                    flip(rotatedTex[0]), rotatedTex[1],
                    flip(rotatedTex[2]), rotatedTex[3],
                    flip(rotatedTex[4]), rotatedTex[5],
                    flip(rotatedTex[6]), rotatedTex[7],
            };
        }
        if (flipVertical) {
            rotatedTex = new float[]{
                    rotatedTex[0], flip(rotatedTex[1]),
                    rotatedTex[2], flip(rotatedTex[3]),
                    rotatedTex[4], flip(rotatedTex[5]),
                    rotatedTex[6], flip(rotatedTex[7]),
            };
        }
        return rotatedTex;
    }


    private static float flip(final float i) {
        if (i == 0.0f) {
            return 1.0f;
        }
        return 0.0f;
    }
}
