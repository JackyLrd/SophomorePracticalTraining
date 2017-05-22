import imagereader.IImageProcessor;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;

public class ImplementImageProcessor implements IImageProcessor
{
	// get chanel r
	public Image showChanelR(Image sourceImage)
	{
		BufferedImage bimage = toBufferedImage(sourceImage);
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		int[] rgb = new int [4];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				// get the rgb of every value
				int pixel = bimage.getRGB(i, j);
				// shift right to compute the rgb value
				rgb[0] = (pixel & 0xff000000) >> 24;
				rgb[1] = (pixel & 0xff0000) >> 16;
				// set green to 0
				rgb[2] = 0;
				// set blue to 0
				rgb[3] = 0;
				// rewrite the rgb to the pixel
				bimage.setRGB(i, j, ((rgb[0] << 24) | (rgb[1] << 16) | (rgb[2] << 8) | (rgb[3])));
			}
		}
		return bimage;
	}

	// get chanel g
	public Image showChanelG(Image sourceImage)
	{
		BufferedImage bimage = toBufferedImage(sourceImage);
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		int[] rgb = new int [4];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				// get the rgb of every value
				int pixel = bimage.getRGB(i, j);
				// shift right to compute the rgb value
				rgb[0] = (pixel & 0xff000000) >> 24;
				rgb[2] = (pixel & 0xff00) >> 8;
				// set red to 0
				rgb[1] = 0;
				// set blue to 0
				rgb[3] = 0;
				// rewrite the rgb to the pixel
				bimage.setRGB(i, j, ((rgb[0] << 24) | (rgb[1] << 16) | (rgb[2] << 8) | (rgb[3])));
			}
		}
		return bimage;
	}

	// get chanel b
	public Image showChanelB(Image sourceImage)
	{
		BufferedImage bimage = toBufferedImage(sourceImage);
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		int[] rgb = new int [4];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				// get the rgb of every value
				int pixel = bimage.getRGB(i, j);
				// shift right to compute the rgb value
				rgb[0] = (pixel & 0xff000000) >> 24;
				rgb[3] = (pixel & 0xff);
				// set red to 0
				rgb[1] = 0;
				// set green to 0
				rgb[2] = 0;
				// rewrite the rgb the the pixel
				bimage.setRGB(i, j, ((rgb[0] << 24) | (rgb[1] << 16) | (rgb[2] << 8) | (rgb[3])));
			}
		}
		return bimage;
	}

	// get gray image
	public Image showGray(Image sourceImage)
	{
		BufferedImage bimage = toBufferedImage(sourceImage);
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		int[] rgb = new int [4];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				// get the rgb of every value
				int pixel = bimage.getRGB(i, j);
				// shift right to compute the rgb value
				rgb[0] = (pixel & 0xff000000) >> 24;
				rgb[1] = (pixel & 0xff0000) >> 16;
				rgb[2] = (pixel & 0xff00) >> 8;
				rgb[3] = (pixel & 0xff);
				// compute the gray value
				int gray = (int)(0.299 * rgb[1] + 0.587 * rgb[2] + 0.114 * rgb[3]);
				int newPixel = colorToRgb(255, gray, gray, gray);
				bimage.setRGB(i, j, newPixel);
			}
		}
		return bimage;
	}

	public static BufferedImage toBufferedImage(Image img)
	{
		if(img instanceof BufferedImage)
		{
			return (BufferedImage)img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}

	// get the value of the gray rgb
	public static int colorToRgb(int alpha, int red, int green, int blue)
	{
		int newPixel = 0;
		newPixel += alpha;
		// shift 8 bits
		newPixel = newPixel << 8;
		newPixel += red;
		// shift 8 bits
		newPixel = newPixel << 8;
		newPixel += green;
		// shift 8 bits
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
	}
}