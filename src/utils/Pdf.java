package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.MyConnection;


public class Pdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        pdfVehicule(41);    }


    public static void pdfVehicule(int id) throws FileNotFoundException, DocumentException {


        Connection myConnex = MyConnection.getInstance().getMyconnex();

        String file_name = "C:\\Users\\Rania2\\Desktop\\pdffiles\\generatePdfapi.pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        try {
            String req = "select * from vehicule ";
            PreparedStatement ps = myConnex.prepareStatement(req);

            ResultSet rs = ps.executeQuery();
            float[] columnWidths = { 4f, 4f, 4f, 4f, 4f};

            PdfPTable table = new PdfPTable(columnWidths);

            table.setWidthPercentage(110f);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell(new Paragraph("Car List"));
            cell.setColspan(14);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            //table.addCell("Image");
            table.addCell("Type");
            table.addCell("Modele");
            table.addCell("Matricule ");
            table.addCell(" Puissance");
            table.addCell("Prix de location d'une heure");
            while (rs.next()) {
               // table.addCell(rs.getString("image"));
                table.addCell(String.valueOf(rs.getString("type")));
                table.addCell(rs.getString("modele"));
                table.addCell(rs.getString("matricule"));
                table.addCell(rs.getString("puissance"));
                table.addCell(String.valueOf(rs.getInt("prix")));
            }
            document.add(table);
            document.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}