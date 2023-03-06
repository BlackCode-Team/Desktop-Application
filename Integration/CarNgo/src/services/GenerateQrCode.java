/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author ychaa
 */
import java.io.File;  
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  
import java.util.Random;
public class GenerateQrCode  {  
//static function that creates QR Code  
//public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException  
//{  
//the BitMatrix class represents the 2D matrix of bits  
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
//BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
//MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
//}  
public static void generateQRcode(String data, String filePath, String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
throws WriterException, IOException {
BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
}
//main() method  
public static void main(String args[]) throws WriterException, IOException, NotFoundException  
{  
        Random random = new Random();
        int uniqueCode = random.nextInt(8999) + 1000;

//path where we want to get QR Code  
String path = "C:/Users/ychaa/QR/Qrcode";  
//Encoding charset to be used  
String charset = "UTF-8";  
Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
//generates QR code with Low level(L) error correction capability  
hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
//invoking the user-defined method that creates the QR code  
//data that we want to store in the QR code  
Integer str= uniqueCode; 
//    String[] data = {"Hello", "World", "OpenAI"};
//for (String d : data) {
generateQRcode(str.toString(), path  + ".png", charset, hashMap, 200, 200);
//}
//generateQRcode(str, path, charset, hashMap, 200, 200);//increase or decrease height and width accodingly   
//prints if the QR code is generated   
System.out.println("QR Code created successfully.");  
}  
}  