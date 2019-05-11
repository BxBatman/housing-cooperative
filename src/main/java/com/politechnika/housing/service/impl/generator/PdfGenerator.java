package com.politechnika.housing.service.impl.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.politechnika.housing.model.Bill;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfGenerator {
    public static byte[] generate(Bill bill) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER,15, BaseColor.BLACK);
        document.add(new Paragraph("Id: " + bill.getId(),font));
        document.add(new Paragraph("Date: " + bill.getDate(),font));
        document.add(new Paragraph("Cold water " + bill.getColdWater(),font));
        document.add(new Paragraph("Electricity " + bill.getElectricity(),font));
        document.add(new Paragraph("Gas " + bill.getGas(),font));
        document.add(new Paragraph("Heating " + bill.getHeating(),font));
        document.add(new Paragraph("Hot water " + bill.getHotWater(),font));

        document.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return pdfBytes;
    }
}
