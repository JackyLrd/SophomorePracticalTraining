import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;


public class ImplementTest
{
	ImplementImageIO imageioer = new ImplementImageIO();
	ImplementImageProcessor processor = new ImplementImageProcessor();

	// test the width
	@Test
	public void testWidth() throws IOException
	{
		String[] color = {"red", "green", "blue", "gray"};
		for(int x = 1; x <= 2; x++)
		{
			for(int i = 0; i < 4; i++)
			{
				FileInputStream fis = new FileInputStream("bmptest/goal/" + (x + "") + "_" +  color[i] + "_goal.bmp");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedImage image = ImageIO.read(bis);
				BufferedImage img;
				if(i == 0)
				{
					img = (BufferedImage)processor.showChanelR(image);
					assertEquals(image.getWidth(null), img.getWidth());
				}
				else if(i == 1)
				{
					img = (BufferedImage)processor.showChanelG(image);
					assertEquals(image.getWidth(null), img.getWidth());
				}
				else if(i == 2)
				{
					img = (BufferedImage)processor.showChanelB(image);
					assertEquals(image.getWidth(null), img.getWidth());
				}
				else if(i == 3)
				{
					img = (BufferedImage)processor.showGray(image);
					assertEquals(image.getWidth(null), img.getWidth());
				}
			}
		}
	}

	// test the height
	@Test
	public void testHeight() throws IOException
	{
		String[] color = {"red", "green", "blue", "gray"};
		for(int x = 1; x <= 2; x++)
		{
			for(int i = 0; i < 4; i++)
			{
				FileInputStream fis = new FileInputStream("bmptest/goal/" + (x + "") + "_" +  color[i] + "_goal.bmp");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedImage image = ImageIO.read(bis);
				BufferedImage img;
				if(i == 0)
				{
					img = (BufferedImage)processor.showChanelR(image);
					assertEquals(image.getHeight(null), img.getHeight());
				}
				else if(i == 1)
				{
					img = (BufferedImage)processor.showChanelG(image);
					assertEquals(image.getHeight(null), img.getHeight());
				}
				else if(i == 2)
				{
					img = (BufferedImage)processor.showChanelB(image);
					assertEquals(image.getHeight(null), img.getHeight());
				}
				else if(i == 3)
				{
					img = (BufferedImage)processor.showGray(image);
					assertEquals(image.getHeight(null), img.getHeight());
				}
			}
		}
	}

	//test the pixels
	@Test
	public void testPixel() throws IOException
	{
		String[] color = {"red", "green", "blue", "gray"};
		for(int x = 1; x <= 2; x++)
		{
			for(int i = 0; i < 4; i++)
			{
				FileInputStream fis = new FileInputStream("bmptest/goal/" + (x + "") + "_" +  color[i] + "_goal.bmp");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedImage image = ImageIO.read(bis);
				BufferedImage img;
				if(i == 0)
				{
					img = (BufferedImage)processor.showChanelR(image);
					for(int w = 0; w < img.getWidth(); w++)
					{
						for(int h = 0; h < img.getHeight(); h++)
						{
							assertEquals(image.getRGB(w, h), img.getRGB(w, h));
						}
					}			
				}
				else if(i == 1)
				{
					img = (BufferedImage)processor.showChanelR(image);
					for(int w = 0; w < img.getWidth(); w++)
					{
						for(int h = 0; h < img.getHeight(); h++)
						{
							assertEquals(image.getRGB(w, h), img.getRGB(w, h));
						}
					}
				}
				else if(i == 2)
				{
					img = (BufferedImage)processor.showChanelR(image);
					for(int w = 0; w < img.getWidth(); w++)
					{
						for(int h = 0; h < img.getHeight(); h++)
						{
							assertEquals(image.getRGB(w, h), img.getRGB(w, h));
						}
					}
				}
				else if(i == 3)
				{
					img = (BufferedImage)processor.showChanelR(image);
					for(int w = 0; w < img.getWidth(); w++)
					{
						for(int h = 0; h < img.getHeight(); h++)
						{
							assertEquals(image.getRGB(w, h), img.getRGB(w, h));
						}
					}
				}
			}
		}
	}
}