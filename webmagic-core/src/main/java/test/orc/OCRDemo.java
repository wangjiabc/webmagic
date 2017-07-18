package test.orc;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.recognition.software.jdeskew.ImageDeskew;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 * 本教程由 http://bbs.datahref.com/ 提供
 * Tess4j验证码识别示例
 * 工程中tessdata文件夹包含了识别英文所需的数据
 * 需要识别其他语言课到https://github.com/tesseract-ocr/tessdata下载相关数据
 * 放到tessdata文件夹中
 * @author hu
 *
 */
public class OCRDemo {
	private final static String expOCRResult = "The (quick) [brown] {fox} jumps!\nOver the $43,456.78 <lazy> #90 dog";
	static final double MINIMUM_DESKEW_THRESHOLD = 0.05d;

	
	private final String datapath = "C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa\\tessdata";
    private final static String testResourcesDataPath = "C:\\Users\\WangJing\\Desktop\\bb";

    public static void main(String[] args) throws TesseractException, IOException {

    	
    	 System.out.println("doOCR on a skewed PNG image");
         File imageFile = new File(testResourcesDataPath, "aa.jpg");
         BufferedImage bi = ImageIO.read(imageFile);
         ImageDeskew id = new ImageDeskew(bi);
         double imageSkewAngle = id.getSkewAngle(); // determine skew angle

         String expResult = expOCRResult;
         ITesseract instance = new Tesseract();
         String curDir = System.getProperty("user.dir");
         System.out.println(curDir);
         instance.setDatapath(curDir+"\\src\\main\\java\\test\\orc\\tessdata");
         String result = instance.doOCR(bi);
         System.out.println(result);

    }
}
