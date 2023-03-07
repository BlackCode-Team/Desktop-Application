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
        pdfReservation(40); }

    public static void pdfReservation(int id) throws FileNotFoundException, DocumentException, SQLException, WriterException, IOException, com.google.zxing.WriterException {
        Connection myConnex = MyConnection.getInstance().getMyconnex();

        String file_name = "C:\\Users\\Rania2\\Desktop\\pdffiles\\generatedreservation.pdf";
        String qr_path = "C:/Users/Rania2/Desktop/pdffiles/Qrcode.png";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();

        try {
            String req = "SELECT r.*, u.cin, v.matricule, i.pointdepart, i.pointarrivee "
                    + "FROM reservation r "
                    + "JOIN utilisateur u ON u.iduser = r.iduser "
                    + "JOIN vehicule v ON v.idvehicule = r.idvehicule "
                    + "JOIN itineraire i ON i.iditineraire = r.iditineraire "
                    + "where r.idreservation=? ";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            float[] columnWidths = {4f, 4f, 4f, 4f, 4f, 4f, 4f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(110f);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell(new Paragraph("Details Reservation"));
            cell.setColspan(14);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            //  table.addCell("IdRes");
            table.addCell("cin");
            table.addCell("date Debut");
            table.addCell("date fin");
            table.addCell(" Matricule");
            table.addCell("point de depart");
            table.addCell("point d'arrivé ");
            table.addCell("QR Code");

            while (rs.next()) {
                table.addCell(rs.getString("cin"));
                table.addCell(String.valueOf(rs.getDate("dateDebut")));
                table.addCell(String.valueOf(rs.getDate("dateFin")));
                table.addCell(rs.getString("matricule"));
                table.addCell(rs.getString("pointdepart"));
                table.addCell(rs.getString("pointarrivee"));


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
