package com.example.kbd6.backend.generatePdf.service;

import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.service.VendorService;
import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;

@Service
public class PdfService {

    private final VendorService vendorService;

    public PdfService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // dont open on browser we need to change the route and pass
    // purchaseId then we can get data and see without this it will give error
    public ByteArrayInputStream createPdf(PurchaseItemModel purchaseItemModel){

        VendorEntity vendor = vendorService.getVendorByVendoruniquename(purchaseItemModel.getVendoruniquename());


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, outputStream);
        doc.open();

        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        headerTable.setSpacingAfter(10f);

        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(Color.RED);
        headerCell.setPadding(10f);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setPhrase(new Phrase("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.WHITE)));
        headerTable.addCell(headerCell);
        doc.add(headerTable);

        Paragraph companyDetails = new Paragraph();
        companyDetails.setAlignment(Element.ALIGN_RIGHT);
        companyDetails.add(new Phrase("Stock Control System\n"));
        companyDetails.add(new Phrase("47 landseer road\n"));
        companyDetails.add(new Phrase("UK, Le2 3ef\n"));
        companyDetails.add(new Phrase("Phone: 785-243-7890\n"));
        companyDetails.add(new Phrase("Email: SCS@admin.com\n"));
        companyDetails.add(new Phrase("Website: www.SCS.com\n\n"));
        doc.add(companyDetails);

//         Add sender details
        Paragraph senderDetails = new Paragraph();
        senderDetails.setAlignment(Element.ALIGN_LEFT);
        senderDetails.add(new Phrase(vendor.getFirstname() +" " +vendor.getLastname()+"\n"));
        senderDetails.add(new Phrase(vendor.getLocation()+ "\n"));
        senderDetails.add(new Phrase("Email:" + vendor.getEmail() +" \n\n"));
        senderDetails.add(new Phrase("Country, Postal Code\n"));
        senderDetails.add(new Phrase("Phone: 987-654-3210\n"));

        doc.add(senderDetails);

        // Add design elements
        PdfPTable designTable = new PdfPTable(1);
        designTable.setWidthPercentage(100);
        designTable.setSpacingBefore(10f);
        designTable.setSpacingAfter(10f);

        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(2f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderColor(Color.GRAY);
        designTable.addCell(cell);

        doc.add(designTable);

        Paragraph invoiceText = new Paragraph();
        invoiceText.setAlignment(Element.ALIGN_LEFT);
        invoiceText.add(new Phrase("Invoice Number: INV-001\n"));
        invoiceText.add(new Phrase("Invoice Date: July 13, 2023\n"));
        invoiceText.add(new Phrase("Invoice Status: Unpaid \n\n"));
        invoiceText.add(new Phrase("Dear Customer,\n\n"));
        invoiceText.add(new Phrase("Thank you for your business. Please find below the details of your invoice:\n\n"));
        doc.add(invoiceText);

        // Create a table for product information
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Add table headers
        table.addCell("Product Name");
        table.addCell("Category");
        table.addCell("Buy Price");
        table.addCell("Quantity");
        table.addCell("Amount");

        // Add table rows with product information
        table.addCell("Product 1");
        table.addCell("Category 1");
        table.addCell("$10.00");
        table.addCell("2");
        table.addCell("$20.00");

        table.addCell("Product 2");
        table.addCell("Category 2");
        table.addCell("$15.00");
        table.addCell("1");
        table.addCell("$15.00");

        // Add table to the document
        doc.add(table);

        // Create a table for additional calculations
        PdfPTable calculationsTable = new PdfPTable(2);
        calculationsTable.setWidthPercentage(50);
        calculationsTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        calculationsTable.setSpacingBefore(10f);

        calculationsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        // Add subtotals
        calculationsTable.addCell("Subtotal1");
        calculationsTable.addCell("$35");

        // Add tax information
        calculationsTable.addCell("Tax (5%)");
        calculationsTable.addCell("$1.75");

        // Add taxable amount
        calculationsTable.addCell("Taxable Amount");
        calculationsTable.addCell("$36.75");

        // Add total amount
        calculationsTable.addCell("Total Amount");
        calculationsTable.addCell("$36.75");

        // Add calculations table to the document
        doc.add(calculationsTable);

        // Add invoice text
        Paragraph footerText = new Paragraph();
        footerText.setAlignment(Element.ALIGN_LEFT);

        footerText.add(new Phrase("\n\n\n"));
        footerText.add(new Phrase("Please review the invoice and let us know if you have any questions or concerns. We appreciate your timely remittance.\n\n"));
        footerText.add(new Phrase("Thank you once again for your business. We look forward to serving you in the future.\n\n"));
        footerText.add(new Phrase("Sincerely,\n"));
        footerText.add(new Phrase("Stock Control System\n"));
        doc.add(footerText);
        doc.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    // when admin adds all the information and click save and send then only this function
    // will be called and new pdf will be generated and saved in D folder
    // attach this pdf in email and send.
    public void generatePdf(PurchaseItemModel purchaseItemModel) {

        try{
            VendorEntity vendor = vendorService.getVendorByVendoruniquename(purchaseItemModel.getVendoruniquename());
            Document doc = new Document(PageSize.A4);

            String filepath ="D:\\" + purchaseItemModel.getPurchaseid()+ ".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(filepath));

            doc.open();
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            headerTable.setSpacingAfter(10f);

            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(Color.RED);
            headerCell.setPadding(10f);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPhrase(new Phrase("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.WHITE)));
            headerTable.addCell(headerCell);
            doc.add(headerTable);

            Paragraph companyDetails = new Paragraph();
            companyDetails.setAlignment(Element.ALIGN_RIGHT);
            companyDetails.add(new Phrase("Stock Control System\n"));
            companyDetails.add(new Phrase("47 landseer road\n"));
            companyDetails.add(new Phrase("UK, Le2 3ef\n"));
            companyDetails.add(new Phrase("Phone: 785-243-7890\n"));
            companyDetails.add(new Phrase("Email: SCS@admin.com\n"));
            companyDetails.add(new Phrase("Website: www.SCS.com\n\n"));
            doc.add(companyDetails);

//         Add sender details
            Paragraph senderDetails = new Paragraph();
            senderDetails.setAlignment(Element.ALIGN_LEFT);
            senderDetails.add(new Phrase(vendor.getTitles()+" "+vendor.getFirstname() +" " +vendor.getLastname()+"\n"));
            senderDetails.add(new Phrase("Brand:" + vendor.getBrandname() +"\n"));
            senderDetails.add(new Phrase("Email:" + vendor.getEmail() +"\n"));
            senderDetails.add(new Phrase("Phone:- "+vendor.getPhonenumber()+"\n"));
            senderDetails.add(new Phrase("Location:- "+ vendor.getLocation()+ "\n"));
            senderDetails.add(new Phrase("Country:- "+vendor.getCountry()+ "\n\n"));

            doc.add(senderDetails);

            // Add design elements
            PdfPTable designTable = new PdfPTable(1);
            designTable.setWidthPercentage(100);
            designTable.setSpacingBefore(10f);
            designTable.setSpacingAfter(10f);

            PdfPCell cell = new PdfPCell();
            cell.setFixedHeight(2f);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderColor(Color.GRAY);
            designTable.addCell(cell);

            doc.add(designTable);

            Paragraph invoiceText = new Paragraph();
            invoiceText.setAlignment(Element.ALIGN_LEFT);
            invoiceText.add(new Phrase("Invoice Number:- "+ purchaseItemModel.getPurchaseid()+"\n"));
            invoiceText.add(new Phrase("Invoice Date:- "+ purchaseItemModel.getCurrentdate().substring(0,10)+"\n"));
            invoiceText.add(new Phrase("Invoice Status: Unpaid \n\n"));
            invoiceText.add(new Phrase("Dear Customer,\n\n"));
            invoiceText.add(new Phrase("Thank you for your business. Please find below the details of your invoice:\n\n"));
            doc.add(invoiceText);

            // Create a table for product information
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            // Add table headers
            table.addCell("Product Name");
            table.addCell("Category");
            table.addCell("Buy Price");
            table.addCell("Quantity");
            table.addCell("Amount");




            // Add table rows with product information
            table.addCell(purchaseItemModel.getProductname());
            table.addCell("asd");
            table.addCell(purchaseItemModel.getBuyprice());
            table.addCell(purchaseItemModel.getQuantity());
            table.addCell(purchaseItemModel.getAmount());



            // Add table to the document
            doc.add(table);

            // Create a table for additional calculations
            PdfPTable calculationsTable = new PdfPTable(2);
            calculationsTable.setWidthPercentage(40);
            calculationsTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            calculationsTable.setSpacingBefore(10f);

            calculationsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add subtotals
            calculationsTable.addCell("Subtotal1");
            calculationsTable.addCell(purchaseItemModel.getSubtotal());

            // Add tax information
            calculationsTable.addCell("Tax");
            calculationsTable.addCell(purchaseItemModel.getTax());

            // Add taxable amount
            calculationsTable.addCell("Taxable Amount");
            calculationsTable.addCell(purchaseItemModel.getTaxamount());

            // Add total amount
            calculationsTable.addCell("Total Amount");
            calculationsTable.addCell(purchaseItemModel.getTotal());

            // Add calculations table to the document
            doc.add(calculationsTable);

            // Add invoice text
            Paragraph footerText = new Paragraph();
            footerText.setAlignment(Element.ALIGN_LEFT);

            footerText.add(new Phrase("\n\n\n"));
            footerText.add(new Phrase("Please review the invoice and let us know if you have any questions or concerns. We appreciate your timely remittance.\n\n"));
            footerText.add(new Phrase("Thank you once again for your business. We look forward to serving you in the future.\n\n"));
            footerText.add(new Phrase("Sincerely,\n"));
            footerText.add(new Phrase("Stock Control System\n"));
            doc.add(footerText);
            doc.close();
            System.out.println("pdf generated");



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
