package com.his.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeUtil {
	//二维码颜色  
    private static final int BLACK = 0xFF000000;  
    //二维码颜色  
    private static final int WHITE = 0xFFFFFFFF;  
  
    public static void main(String[] args) throws Exception {
		zxingCodeCreate("http://www.baidu.com", 300, 300, "D:/qrcode.jpg", "jpg");
//    	zxingCodeAnalyze("D:/qrcode.jpg");
	}
    /** 
     * 生成二维码
     * @param text    二维码内容
     * @param width    二维码宽 
     * @param height    二维码高 
     * @param outPutPath    二维码生成保存路径 
     * @param imageType     二维码生成格式 
     */  
    public static void zxingCodeCreate(String text, int width, int height, String outPutPath, String imageType){  
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();  
        //设置编码字符集  
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        try {  
            //1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);  
              
            //2、获取二维码宽高  
            int codeWidth = encode.getWidth();  
            int codeHeight = encode.getHeight();  
              
            //3、将二维码放入缓冲流  
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);  
            for (int i = 0; i < codeWidth; i++) {  
                for (int j = 0; j < codeHeight; j++) {  
                    //4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);  
                }  
            }  
            File outPutImage = new File(outPutPath);  
            //如果图片不存在创建图片  
            if(!outPutImage.exists())  
                outPutImage.createNewFile();  
            //5、将二维码写入图片  
            ImageIO.write(image, imageType, outPutImage);  
        } catch (WriterException e) {  
            e.printStackTrace();  
            System.out.println("二维码生成失败");  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("生成二维码图片失败");  
        }  
    }  
      
    /** 
     * 二维码解析 
     * @param analyzePath    二维码路径 
     * @return 
     * @throws IOException 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static String zxingCodeAnalyze(String analyzePath) throws Exception{  
        MultiFormatReader formatReader = new MultiFormatReader();  
        String resultStr = null;  
        try {  
            File file = new File(analyzePath);  
            if (!file.exists())  
            {  
                return "二维码不存在";  
            }  
            BufferedImage image = ImageIO.read(file);  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);    
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map hints = new HashMap();  
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
            Result result = formatReader.decode(binaryBitmap, hints); 
            resultStr = result.getText();
        } catch (NotFoundException e) {  
            e.printStackTrace();
        }    
        System.out.println(resultStr);
        return resultStr;  
    }  
    
    /** 
     * 解析二维码 
     * @param imagePath 二维码图片存放路径(含文件名) 
     * @param charset   解码二维码内容时采用的字符集(传null时默认采用UTF-8编码) 
     * @return 解析成功后返回二维码文本,否则返回空字符串 
     */  
    public static String decodeQRCodeImage(String imagePath, String charset) {  
        BufferedImage image = null;  
        try {  
            image = ImageIO.read(new File(imagePath));  
        } catch (IOException e) {  
            e.printStackTrace();  
            return "";  
        }  
        if(null == image){  
            System.out.println("Could not decode QRCodeImage");  
            return "";  
        }  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));  
        Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();  
        hints.put(DecodeHintType.CHARACTER_SET, charset==null ? "UTF-8" : charset);  
        Result result = null;  
        try {  
            result = new MultiFormatReader().decode(bitmap, hints);  
            return result.getText();  
        } catch (NotFoundException e) {  
            System.out.println("二维码图片[" + imagePath + "]解析失败,堆栈轨迹如下");  
            e.printStackTrace();  
            return "";  
        }  
    }

      

}

