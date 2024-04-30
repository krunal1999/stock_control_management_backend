package com.example.kbd6.backend.excel.helper;

import com.example.kbd6.backend.entity.BillEnity;
import com.example.kbd6.backend.entity.CategoriesEntity;
import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.model.ProductModel;
import com.example.kbd6.backend.user.entity.Address;
import com.example.kbd6.backend.user.entity.OrderSuceess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {
//    https://www.youtube.com/watch?v=9fwHA9s3QGs&ab_channel=LearnCodeWithDurgesh

    public static ByteArrayInputStream PoductdataToExcel(List<ProductEntity> list) throws IOException {
        String[] HEADER={
                "id","productid","vendoruniquename",
                "receieveItem","productname","brand","categories",
                "about","title","buyprice",
                "sellingPrice","quantity","remainingquantity","soldquantity","minimumQuantityAlert","autoReorderEnabled",
                "length","breadth","height",
                "volume","activestatus","productstatus"
        };
        String SHEET_NAME = "productdata";

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);


            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            //values row
            int rowIndex = 1;
            for(ProductEntity pm:list){
//                System.out.println(pm.getId());
                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(pm.getId());
                dataRw.createCell(1).setCellValue(pm.getProductid());
                dataRw.createCell(2).setCellValue(pm.getVendoruniquename());
                dataRw.createCell(3).setCellValue(pm.getReceieveItem());
                dataRw.createCell(4).setCellValue(pm.getProductname());
                dataRw.createCell(5).setCellValue(pm.getBrand());
                dataRw.createCell(6).setCellValue(pm.getCategories());
                dataRw.createCell(7).setCellValue(pm.getAbout());

                dataRw.createCell(8).setCellValue(pm.getTitle());
                dataRw.createCell(9).setCellValue(pm.getBuyprice());
                dataRw.createCell(10).setCellValue(pm.getSellingPrice());
                dataRw.createCell(11).setCellValue(pm.getQuantity());
                dataRw.createCell(12).setCellValue(pm.getRemainingquantity());
                dataRw.createCell(13).setCellValue(pm.getSoldquantity());
                dataRw.createCell(14).setCellValue(pm.getMinimumQuantityAlert());
                dataRw.createCell(15).setCellValue(pm.getAutoReorderEnabled());
                dataRw.createCell(16).setCellValue(pm.getLength());
                dataRw.createCell(17).setCellValue(pm.getBreadth());

                dataRw.createCell(18).setCellValue(pm.getHeight());
                dataRw.createCell(19).setCellValue(pm.getVolume());
                dataRw.createCell(20).setCellValue(pm.getActivestatus());
                dataRw.createCell(21).setCellValue(pm.getProductstatus());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

         }finally {
            workbook.close();

        }
        out.close();
        return null;
    }

    public static ByteArrayInputStream CategorydataToExcel(List<CategoriesEntity> categoriesEntities) throws IOException {

        String[] HEADER={
                "id","type","activestatus"

        };
        String SHEET_NAME = "categorydata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            //values row
            int rowIndex = 1;
            for(CategoriesEntity cn:categoriesEntities){

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(cn.getId());
                dataRw.createCell(1).setCellValue(cn.getType());
                dataRw.createCell(2).setCellValue(cn.getActivestatus());

            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

        }finally {
            workbook.close();
            out.close();
        }
        return null;
    }

    public static ByteArrayInputStream OrderSuccessdataToExcel(List<OrderSuceess> orderSuceessList) throws IOException {
        String[] HEADER={
                "id","userid","productid",
                "orderid","username","productname","quantity",
                "sellingPrice","totalprice","clientIntent",
                "clientIntentRefund","orderStatus","deliveryStatus","productTrackStatus",
                "returnProduct","infoMessage"
        };
        String SHEET_NAME = "ordersuccessdata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            //values row
            int rowIndex = 1;
            for(OrderSuceess os:orderSuceessList){

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(os.getId());
                dataRw.createCell(1).setCellValue(os.getUserid());
                dataRw.createCell(2).setCellValue(os.getProductid());
                dataRw.createCell(3).setCellValue(os.getOrderid());

                dataRw.createCell(4).setCellValue(os.getUsername());
                dataRw.createCell(5).setCellValue(os.getProductname());
                dataRw.createCell(6).setCellValue(os.getQuantity());
                dataRw.createCell(7).setCellValue(os.getSellingPrice());

                dataRw.createCell(8).setCellValue(os.getTotalprice());
                dataRw.createCell(9).setCellValue(os.getClientIntent());
                dataRw.createCell(10).setCellValue(os.getClientIntentRefund());
                dataRw.createCell(11).setCellValue(os.getOrderStatus());

                dataRw.createCell(12).setCellValue(os.getDeliveryStatus());
                dataRw.createCell(13).setCellValue(os.getProductTrackStatus());
                dataRw.createCell(14).setCellValue(os.getReturnProduct());
                dataRw.createCell(15).setCellValue(os.getInfoMessage());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

        }finally {
            workbook.close();
            out.close();
        }
        return null;

    }

    public static ByteArrayInputStream BillsDatadataToExcel(List<BillEnity> billEnityList) throws IOException {
        String[] HEADER = {
                "id", "bill_id", "amount",
                "productname", "vendorname", "billstatus", "paidstatus",
                "paymenttype", "paymentdate"
        };
        String SHEET_NAME = "billsdata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADER.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }
            //values row
            int rowIndex = 1;
            for (BillEnity pm : billEnityList) {

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(pm.getId());
                dataRw.createCell(1).setCellValue(pm.getBill_id());
                dataRw.createCell(2).setCellValue(pm.getAmount());
                dataRw.createCell(3).setCellValue(pm.getProductname());
                dataRw.createCell(4).setCellValue(pm.getVendorname());
                dataRw.createCell(5).setCellValue(pm.getBillstatus());
                dataRw.createCell(6).setCellValue(pm.getPaidstatus());
                dataRw.createCell(7).setCellValue(pm.getPaymenttype());
                dataRw.createCell(8).setCellValue(pm.getPaymentdate());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            workbook.close();
            out.close();
        }
        return null;
    }


    public static ByteArrayInputStream PurchaseItemDataToExcel(List<PurchaseItemEntity> purchaseItemEntityList)throws IOException{
        String[] HEADER={
                "po_id","purchaseid","vendoruniquename",
                "currentdate","expectdate","productname","categories",
                "buyprice","quantity","amount",

                "subtotal","tax","taxamount","total","orderstatus",
                "billstatus",
                "productStatus","reorderproductid"
        };
        String SHEET_NAME = "purchaseItemdata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            //values row
            int rowIndex = 1;
            for(PurchaseItemEntity pm:purchaseItemEntityList){

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(pm.getPo_id());
                dataRw.createCell(1).setCellValue(pm.getPurchaseid());
                dataRw.createCell(2).setCellValue(pm.getVendoruniquename());
                dataRw.createCell(3).setCellValue(pm.getCurrentdate());
                dataRw.createCell(4).setCellValue(pm.getExpectdate());

                dataRw.createCell(5).setCellValue(pm.getProductname());
                dataRw.createCell(6).setCellValue(pm.getCategories());
                dataRw.createCell(7).setCellValue(pm.getBuyprice());
                dataRw.createCell(8).setCellValue(pm.getQuantity());
                dataRw.createCell(9).setCellValue(pm.getAmount());

                dataRw.createCell(10).setCellValue(pm.getSubtotal());
                dataRw.createCell(11).setCellValue(pm.getTax());
                dataRw.createCell(12).setCellValue(pm.getTaxamount());
                dataRw.createCell(13).setCellValue(pm.getTotal());
                dataRw.createCell(14).setCellValue(pm.getOrderstatus());

                dataRw.createCell(15).setCellValue(pm.getBillstatus());
                dataRw.createCell(16).setCellValue(pm.getProductStatus());
                dataRw.createCell(17).setCellValue(pm.getReorderproductid());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

        }finally {
            workbook.close();
            out.close();
        }
        return null;
    }

    public static ByteArrayInputStream VendorDataToExcel(List<VendorEntity> vendorEntityList) throws IOException {
        String[] HEADER={
                "vendor_id","titles","firstname",
                "lastname","vendoruniquename","brandname","email",
                "location","phonenumber","country",
                "activestatus"
        };
        String SHEET_NAME = "vendordata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }
            //values row
            int rowIndex = 1;
            for(VendorEntity pm:vendorEntityList){

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(pm.getVendor_id());
                dataRw.createCell(1).setCellValue(pm.getTitles());
                dataRw.createCell(2).setCellValue(pm.getFirstname());
                dataRw.createCell(3).setCellValue(pm.getLastname());
                dataRw.createCell(4).setCellValue(pm.getVendoruniquename());
                dataRw.createCell(5).setCellValue(pm.getBrandname());
                dataRw.createCell(6).setCellValue(pm.getEmail());
                dataRw.createCell(7).setCellValue(pm.getLocation());

                dataRw.createCell(8).setCellValue(pm.getPhonenumber());
                dataRw.createCell(9).setCellValue(pm.getCountry());
                dataRw.createCell(10).setCellValue(pm.getActivestatus());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

        }finally {
            workbook.close();
            out.close();
        }
        return null;
    }

    public static ByteArrayInputStream UserDataToExcel(List<Address> addressList) throws IOException {

        String[] HEADER={
                "userId","username","firstname",
                "lastname","addressline1","addressline2","city",
                "state","country","postcode"

        };

        String SHEET_NAME = "userdata";
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);


            for (int i=0;i < HEADER.length;i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            //values row
            int rowIndex = 1;
            for(Address pm:addressList){

                Row dataRw = sheet.createRow(rowIndex);
                rowIndex++;
                dataRw.createCell(0).setCellValue(pm.getUserId());
                dataRw.createCell(1).setCellValue(pm.getUsername());
                dataRw.createCell(2).setCellValue(pm.getFirstname());
                dataRw.createCell(3).setCellValue(pm.getLastname());
                dataRw.createCell(4).setCellValue(pm.getAddressline1());
                dataRw.createCell(5).setCellValue(pm.getAddressline2());
                dataRw.createCell(6).setCellValue(pm.getCity());
                dataRw.createCell(7).setCellValue(pm.getState());
                dataRw.createCell(8).setCellValue(pm.getCountry());
                dataRw.createCell(9).setCellValue(pm.getPostcode());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch(IOException e){
            e.printStackTrace();

        }finally {
            workbook.close();
            out.close();
        }
        return null;
    }
}
