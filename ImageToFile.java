import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToFile {
	BufferedImage imag;
	int alpha,red,green,blue,average,total;
	int width,height,totalRed,totalGreen,totalBlue;
	String file;
	String color="";
	public ImageToFile() throws IOException{
		imag = ImageIO.read(this.getClass().getResource("unicorn.jpg"));
		width = imag.getWidth();
		height = imag.getHeight();
	}
	
	public void getpixel(int pixels){
		int count = 0;
		file ="";
		for(int j=0;j<height;j++){
			for(int i=0;i<width;i++){
				int pixel = imag.getRGB(i, j);
				printPixelRGB(pixel);
				chooseAscii();
				//total += average;
				/*if(count==pixels){
					average = total/pixels;
					chooseAscii();
					count=0;
					total=0;
					
				}*/
				//count++;
				
			}
			file+="\n";
		}
	}
	
	public void printPixelRGB(int pixel){
		alpha = (pixel >> 24) & 0xff;
	    red = (pixel >> 16) & 0xff;
	    green = (pixel >> 8) & 0xff;
	    blue = (pixel) & 0xff;
	    average = (red+green+blue)/3;
	}
	public void chooseAscii(){
		if(average > 0 && average <32)
			file+="&";
		else if(average>32 && average<64)
			file+="@";
		else if(average >64 && average<96 )
			file+="%";
		else if(average>96 && average <128)
			file+="/";
		else if(average>128 && average<192)
			file+=".";
		else if(average >192 && average<224)
			file+="-";
		else if(average>192 && average<220)
			file+="`";
		else
			file+=" ";
	}
	public void wirteToFile(String fileName) throws IOException{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(file);
		bw.close();
		
	}
	public void writeToImage(){
		String lines[] = file.split("\n");
		int fontSize = 12;
		int y=0;
		int imgWidth = width*fontSize;
		int imgHeight = height*fontSize*2;
        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Monospaced", Font.PLAIN, fontSize);
        FontMetrics fm = g2d.getFontMetrics();
        g2d = img.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, imgWidth, imgHeight);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        for(String line:lines){
        	g2d.drawString(line, 0, y);
        	y+=fm.getAscent()+12;
        }
        g2d.dispose();
        System.out.println(height+" "+lines.length);
        System.out.println(img.getWidth()+" "+img.getHeight());
        System.out.println(img.getWidth()+" "+img.getHeight());
        try {
            ImageIO.write(img, "png", new File("txt.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}	
}