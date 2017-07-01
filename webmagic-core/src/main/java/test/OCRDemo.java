package test;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

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

    public static void main(String[] args) throws TesseractException {
        ITesseract instance = new Tesseract();
        File imgFile = new File("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa\\0.jpg");
        //对img_data文件夹中的每个验证码进行识别
        //文件名即正确的结果
             instance.setDatapath("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa\\tessdata");
             instance.setLanguage("eng");
            //该例子输入的是文件，也可输入BufferedImage           
            String ocrResult = instance.doOCR(imgFile);
            //输出图片文件名，即正确识别结果
            System.out.println("ImgFile: "+imgFile.getAbsolutePath());
            //输出识别结果
            System.out.println("OCR Result: " + ocrResult);
          
    }
}
