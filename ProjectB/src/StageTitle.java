import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StageTitle {

	private BufferedImage castle1;
	private BufferedImage logo;
	public StageTitle() {
		loadImage();
	}
	
	private void loadImage() {
		try {
			this.logo = ImageIO.read(new File("res/logo.png"));
			this.castle1 = ImageIO.read(new File("res/castle1.png"));
			this.logo = resizeImage(logo, 450);
			this.castle1 = resizeImage(castle1, 1500);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.logo = TransformColorToTransparency(logo, new Color(60, 60, 160));
	}
	public boolean getStageEnd() {
		return  castle1_y >= -10;
	}
	
	private int castle1_y = 0;
	private int logo_x= 535;
	private int logo_y= 820;
	private int time_step = 8;
			
	public void draw(Graphics g, Screen screen) {
		g.drawImage(this.castle1, 0, castle1_y, screen);
		g.drawImage(this.logo, logo_x, logo_y, screen);
		if(screen.getCount() % time_step == 0) {
			if(castle1_y >= -430) {
				castle1_y -= 2;
				logo_y -= 2;
				
			}
		}
	}
	
	private BufferedImage resizeImage(BufferedImage image, int newWidth) {
	    int imageWidth = image.getWidth(null);
	    int imageHeight = image.getHeight(null);

	    double ratio = (double)newWidth/(double)imageWidth;
	    int w = (int)(imageWidth * ratio);
	    int h = (int)(imageHeight * ratio);
	    Image resizeImage = image.getScaledInstance(w, h, 
	                    Image.SCALE_SMOOTH);
	    BufferedImage newImage = new BufferedImage(w, h,  
	                                         BufferedImage.TYPE_INT_RGB);
	    Graphics g = newImage.getGraphics();
	    g.drawImage(resizeImage, 0, 0, null);
	    g.dispose();
	    return newImage;
	}
	
	protected BufferedImage TransformColorToTransparency(BufferedImage image, Color c1) {
		  final int r1 = c1.getRed();
		  final int g1 = c1.getGreen();
		  final int b1 = c1.getBlue();
		 
		  ImageFilter filter = new RGBImageFilter() {
				public int filterRGB(int x, int y, int rgb) {
					int r = ( rgb & 0xFF0000 ) >> 16;
					int g = ( rgb & 0xFF00 ) >> 8;
					int b = ( rgb & 0xFF );
					if( r == r1 && g == g1 && b == b1) {
						return rgb & 0xFFFFFF;
					}
					return rgb;
				}
			};
		 
			ImageProducer ip = new FilteredImageSource( image.getSource(), filter );
			Image img = Toolkit.getDefaultToolkit().createImage(ip);
			BufferedImage dest = new BufferedImage(img.getWidth(null), 
					img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = dest.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			return dest;
	}
}
