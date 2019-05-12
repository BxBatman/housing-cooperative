package com.politechnika.housing.service.impl.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.politechnika.housing.model.Bill;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {
    public static byte[] generate(Bill bill,String lang) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(bf,12);

        if (lang.equals("en")) {
            document.add(new Paragraph("Id: " + bill.getId(), font));
            document.add(new Paragraph("Date: " + bill.getDate(), font));
            document.add(new Paragraph("Cold water " + bill.getColdWater(), font));
            document.add(new Paragraph("Electricity " + bill.getElectricity(), font));
            document.add(new Paragraph("Gas " + bill.getGas(), font));
            document.add(new Paragraph("Heating " + bill.getHeating(), font));
            document.add(new Paragraph("Hot water " + bill.getHotWater(), font));
        } else {
            document.add(new Paragraph("Id: " + bill.getId(), font));
            document.add(new Paragraph("Data: " + bill.getDate(), font));
            document.add(new Paragraph("Zimna woda " + bill.getColdWater(), font));
            document.add(new Paragraph("Elektryczno\u015B\u0107 " + bill.getElectricity(), font));
            document.add(new Paragraph("Gaz " + bill.getGas(), font));
            document.add(new Paragraph("Ogrzewanie " + bill.getHeating(), font));
            document.add(new Paragraph("Ciep\u0142a woda " + bill.getHotWater(), font));
        }

        document.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }
}
