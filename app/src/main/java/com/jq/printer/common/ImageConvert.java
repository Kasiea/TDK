package com.jq.printer.common;

import java.io.File;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/// <summary>
/// </summary>
public class ImageConvert
{
    private int width;
    /// <summary>
    /// </summary>
    public int getWidth()
    {
        return width;
    }
    private int height;
    /// <summary>
    /// </summary>
    public int getHeight()
    {
        return height;
    }
    /// <summary>
    /// </summary>
    public ImageConvert()
    {
        width = 0;
        height = 0;
    }


    private boolean PixelIsBlack(int color, int gray_threshold) {
		int red = ((color & 0x00FF0000) >> 16);
		int green = ((color & 0x0000FF00) >> 8);
		int blue = color & 0x000000FF;
		int grey = (int) ((float) red * 0.299 + (float) green * 0.587 + (float) blue * 0.114);
		return (grey < gray_threshold);
	}


    public byte[] CovertImageVertical(Bitmap bitmap, int gray_threshold,int column_dots)
    {
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        int count = (height - 1) / column_dots + 1;
        int column_bytes = column_dots/8;
        byte[] data = new byte[width * column_bytes * count];

        int index = 0;

        int sx = 0, sy = 0;
        for (int i = 0; i < count; i++)
        {
            for (int j = 0; j < width; j++)
            {
                sx = j;
                for (int k = 0; k < 8; k++)
                {
                    sy = (i << 3) + k;
                    if (sy >= height)
                    {
                        continue;
                    }
                    else
                    {
                        if (PixelIsBlack(bitmap.getPixel(sx, sy), gray_threshold))
                        {
                            data[index] |= (byte)(0x01 << (7 - k));
                        }
                    }
                }
                index++;
            }
        }
        return data;
    }


    public byte[] CovertImageVertical(String image_path, int gray_threshold)
    {
    	if(new File(image_path).exists())
        {
    		Bitmap bitmap = BitmapFactory.decodeFile(image_path);
    	    byte[] data = CovertImageVertical(bitmap, gray_threshold,8);
    	    if (data == null)
    	    	Log.e("JQ","fail");
            return data;
        }
    	else
    	{
    		Log.e("JQ","fail:" + image_path);
    		return null;
    	}
    }


    public byte[] CovertImageHorizontal(Bitmap bitmap, int gray_threshold)
    {
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        int BytesPerLine = (width - 1) / 8 + 1;

        byte[] data = new byte[BytesPerLine * height];

        int index = 0;

        int x = 0, y = 0;
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < BytesPerLine; j++)
            {
                for (int k = 0; k < 8; k++)
                {
                    x = (j << 3) + k;
                    y = i;
                    if (x >= width)
                    {
                        continue;
                    }
                    else
                    {
                        if (PixelIsBlack(bitmap.getPixel(x, y), gray_threshold))
                        {
                            data[index] |= (byte)(0x01 << k);
                        }
                    }
                }
                index++;
            }
            x = 0;
        }

        return data;
    }


    public byte[] CovertImageHorizontal(String image_path, int gray_threshold)
    {
    	if(new File(image_path).exists())
        {
    		Bitmap bitmap = BitmapFactory.decodeFile(image_path);
    		byte[] data = CovertImageHorizontal(bitmap, gray_threshold);
    	    if (data == null)
    	    	Log.e("JQ","fail");
            return data;
        }
    	else
    	{
    		Log.e("JQ","fail:" + image_path);
    		return null;
    	}
    }
}