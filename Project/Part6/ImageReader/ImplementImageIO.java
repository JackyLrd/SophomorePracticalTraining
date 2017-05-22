import imagereader.IImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

public class ImplementImageIO implements IImageIO
{
	public Image myRead(String filePath) throws IOException
	{
		FileInputStream fis = new FileInputStream(filePath);
		BufferedInputStream bis = new BufferedInputStream(fis);

		// skip the first 18 bytes, which is the head of the image
		bis.skip(18);
		// read the width of the image
		byte[] b = new byte[4];
		bis.read(b);
		//read the height of the image
		byte[] b2 = new byte[4];
		bis.read(b2);
		int width = byte2Int(b);
		int height = byte2Int(b2);
		int[][] data = new int[height][width];
		// the space to be add to the rear of the row
		int skipnum = 0;
		if(width * 3 % 4 != 0)
		{
			skipnum = 4 - width * 3 % 4;
		}
		// skip the color info
		bis.skip(28);
		// read the real pixels of the image
		for(int i = height - 1; i >= 0; i--)
		{
			for(int j = 0; j < width; j++)
			{
				// make the value right(not a nagative number)
				int blue = bis.read() & 0xff;
				int green = bis.read() & 0xff;
				int red = bis.read() & 0xff;
				Color c = new Color(red, green, blue);
				data[i][j] = c.getRGB();
			}
			if(skipnum != 0)
			{
				// skip the 0s added to rear
				bis.skip(skipnum);
			}
		}
		int[] pix = new int[height * width];
		int index = 0;
		// set the pixels
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				pix[index++] = data[i][j];
			}
		}
		// create the image
		Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));
		return img;
	}

	public Image myWrite(Image image, String filePath) throws IOException
	{
		File file = new File(filePath);
		// be careful of the BufferedImage.TYP_INT_RGB
		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graph = bimage.createGraphics();
		graph.drawImage(image, 0, 0, null);
		graph.dispose();
		// ouput
		ImageIO.write(bimage, "bmp", file);
		return image;
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

	// convert byte to int
	public int byte2Int(byte[] by)
	{
		int alpha = by[3] & 0xff;
		int red = by[2] & 0xff;
		int green = by[1] & 0xff;
		int blue = by[0] & 0xff;
		int num = (alpha << 24) | (red << 16) | (green << 8) | blue;
		return num;
	}
}

