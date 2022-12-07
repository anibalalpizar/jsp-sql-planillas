/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controller.Reporte;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;

/**
 *
 * @author anibal
 */
public class ReportePDF implements Reporte {

    private Connection con;
    private final String SQL_CONSULTA = "select * from CategoriaPago";

    public ReportePDF() {
    }

    public ReportePDF(Connection c) {
        this.con = c;
    }

    @Override
    public void generarReporte(String DES, String titulo) throws SQLException, FileNotFoundException, IOException {
        // path del archivo pdf en el desktop
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + DES;

        
        PdfWriter writer = new PdfWriter(desktopPath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());

        EventoPDF evento = new EventoPDF(document, titulo);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);

        document.setMargins(75, 36, 75, 36);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        String tituloTabla[] = {"IDCategoriaPago", "Descripcion", "Precio", "Borrado"};
        float[] anchoColumn = {3, 3, 3, 2};

        Table table = new Table(anchoColumn);
        table.setWidth(UnitValue.createPercentValue(100));

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = (this.con != null) ? this.con : Conexion.conectar();
            ps = conn.prepareStatement(SQL_CONSULTA);
            rs = ps.executeQuery();
            for (String encabezadoTabla : tituloTabla) {
                process(table, encabezadoTabla, bold, true);
            }
            while (rs.next()) {

                String line = "";
                for (int i = 1; i <= 4; i++) {
                    String valor = rs.getString(i);
                    if (i == 4) {
                        if (valor == null || valor.trim().length() == 0) {
                            line += "-";

                        } else {
                            line += valor;
                        }
                    } else {
                        if (valor == null || valor.trim().length() == 0) {
                            line += "-;";
                        } else {
                            line += valor + ";";
                        }
                    }
                }
                process(table, line, font, false);
            }
            document.add(table);
            document.close();

        } finally {

        }
    }

    public void process(Table table, String line, PdfFont font, boolean isHeader) {

        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }

    }

}
