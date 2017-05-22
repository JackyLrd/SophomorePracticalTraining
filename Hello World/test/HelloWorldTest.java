import static org.junit.Assert.*;
import imagereader.IImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageProducer;

import org.junit.Test;

public class HelloWorldTest
{
	public HelloWorld helloworld = new HelloWorld();
	@Test
	public void testHello()
	{
		helloworld.hello();
		//assertEquals("Hello World!", helloworld.getStr());
	}
}