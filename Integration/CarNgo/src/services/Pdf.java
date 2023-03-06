/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static services.GenerateQrCode.generateQRcode;
import utils.MyConnection;

/**
 *
 * @author ychaa
 */
public class Pdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException, WriterException, IOException, IOException, SQLException, com.google.zxing.WriterException {
    }

    public static void pdfReservation(int id) throws FileNotFoundException, DocumentException, SQLException, WriterException, IOException, com.google.zxing.WriterException {
        Connection myConnex = MyConnection.getInstance().getMyconnex();
        String file_name = "C:\\Users\\ychaa\\generatePdf.pdf";
        String qr_path = "C:/Users/ychaa/QR/Qrcode.png";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();

        try {
            String req = "select * from reservation where idreservation=?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            float[] columnWidths = {4f, 4f, 4f, 4f, 4f, 4f, 4f, 4f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(110f);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell(new Paragraph("Product List"));
            cell.setColspan(14);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            table.addCell("IdRes");
            table.addCell("date Debut");
            table.addCell("date fin");
            table.addCell("status ");
            table.addCell(" id Vehicule");
            table.addCell("iduser");
            table.addCell("idItineraire");
            table.addCell("QR Code");

            while (rs.next()) {
                table.addCell(String.valueOf(rs.getInt(1)));
                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                table.addCell(rs.getString(4));
                table.addCell(String.valueOf(rs.getInt(5)));
                table.addCell(String.valueOf(rs.getInt(6)));
                table.addCell(String.valueOf(rs.getInt(7)));

                Paragraph para = new Paragraph(rs.getString("iduser") + " " + rs.getString("idvehicule"));

                // generate QR code and add to table
                Random random = new Random();
                int uniqueCode = random.nextInt(8999) + 1000;
                String charset = "UTF-8";
                Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                generateQRcode(String.valueOf(uniqueCode), qr_path, charset, hashMap, 200, 200);
                Image qrImage = Image.getInstance(qr_path);
                PdfPCell qrCell = new PdfPCell(qrImage, true);
                qrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                qrCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(qrCell);
            }
            document.add(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document.close();
    }
}
    