package test.orc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class cat {
	private final static String testResourcesDataPath = "C:\\Users\\WangJing\\Desktop\\bb";
	
	public static void main(String[] args) {
		BufferedImage bufferedImage=console(testResourcesDataPath+"\\gg.jpg");
		erZhiHua(bufferedImage);
	}
	
	public static BufferedImage console(String imgUrl) {
        BufferedImage img = null;
        try {
                img = ImageIO.read(new File(imgUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         return img;
    }
	
	 public static void erZhiHua(BufferedImage img) {
	        int sWight = img.getWidth();
	        int sHight = img.getHeight();
	        BufferedImage newImage = new BufferedImage(sWight, sHight,
	                BufferedImage.TYPE_BYTE_BINARY);
	        for (int x = 0; x < sWight; x++) {
	            for (int y = 0; y < sHight; y++) {
	                int rgb= img.getRGB(x, y);
	                newImage.setRGB(x, y, rgb);
	            }
	        }
	         try {
	            ImageIO.write(newImage, "jpg", new File(testResourcesDataPath+"\\bb.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }  
	        
	    }
	
	public static BufferedImage cat(int x, int y, int wight, int hight,
            BufferedImage img) {
        int[] simgRgb = new int[wight * hight];
        img.getRGB(x, y, wight, hight, simgRgb, 0, wight);
        BufferedImage newImage = new BufferedImage(wight, hight,
                BufferedImage.TYPE_INT_ARGB);
        newImage.setRGB(0, 0, wight, hight, simgRgb, 0, wight);
//         try {
//                ImageIO.write(newImage, "PNG", new File("aa.png"));
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }  
        return newImage;
    }
	
	 public static List<BufferedImage> splitRedSplitLineImage(BufferedImage img,int splitNum) throws Exception {
		  //得到图片的宽度和高度
		  int width = img.getWidth();
		  int height = img.getHeight();
		  int splitLine = splitNum - 1;
		  List<BufferedImage> imgList = new ArrayList<BufferedImage>();
		   
		  BufferedImage temp1 = img.getSubimage(0, 0, 28, height);
		  BufferedImage temp2 = img.getSubimage(28, 0, width-28, height);
		   imgList.add(temp1);
		   imgList.add(temp2);
		   //构造四个图形
		   
		  return imgList;
		 }
	
}
