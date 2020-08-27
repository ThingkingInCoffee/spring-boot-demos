package com.hzy.file.service.impl;

import com.hzy.file.service.FilePDFService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FilePDFServiceImpl implements FilePDFService {

    private String DEST = "D://demo";

    @Override
    public void createSimpleTable() throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST + System.currentTimeMillis() + ".pdf"));
        document.open();
        PdfPTable table = new PdfPTable(5);
        for (int aw = 0; aw < 10; aw++) {
            // 构建每一格
            table.addCell("cell" + aw);
        }
        document.add(table);
        document.close();
    }

    /**
     * 表格各种属性综合使用
     *
     * @throws IOException
     * @throws DocumentException
     */
    public void createTablePdf() throws IOException, DocumentException {
        Document document = new Document();
        // 创建PdfWriter对象
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST + System.currentTimeMillis() + ".pdf"));
        // 打开文档
        document.open();

        // 添加表格，4列
        PdfPTable table = new PdfPTable(4);
        //// 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        // 设置表格的宽度
        table.setTotalWidth(500);
        // 也可以每列分别设置宽度
        table.setTotalWidth(new float[]{160, 70, 130, 100});
        // 锁住宽度
        table.setLockedWidth(true);
        // 设置表格上面空白宽度
        table.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table.setSpacingAfter(10f);
        // 设置表格默认为无边框
        table.getDefaultCell().setBorder(0);
        PdfContentByte cb = writer.getDirectContent();

        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
        // 边框颜色
        cell1.setBorderColor(BaseColor.BLUE);
        // 设置背景颜色
        cell1.setBackgroundColor(BaseColor.ORANGE);
        // 设置跨两行
        cell1.setRowspan(2);
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(20);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
        cell2.setBorderColor(BaseColor.GREEN);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
        cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        // 设置无边框
        cell3.setBorder(Rectangle.NO_BORDER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell3);

        // 在表格添加图片
        Image cellImg = Image.getInstance("D://sss.png");
        PdfPCell cell4 = new PdfPCell(cellImg, true);
        cell4.setBorderColor(BaseColor.RED);
        cell4.setPaddingLeft(10);
        cell4.setFixedHeight(30);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell4);

        // 增加一个条形码到表格
        Barcode128 code128 = new Barcode128();
        code128.setCode("http://www.baidu.com");
        code128.setCodeType(Barcode128.CODE128);
        // 生成条形码图片
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        // 加入到表格
        PdfPCell cellcode = new PdfPCell(code128Image, true);
        cellcode.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellcode.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellcode.setFixedHeight(30);
        table.addCell(cellcode);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Cell 5"));
        cell5.setPaddingLeft(10);
        // 设置占用列数
        cell5.setColspan(2);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell5);
        document.add(table);
        // 关闭文档
        document.close();
    }


    public void createPICCPdf() throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Document document = new Document();
        // 创建PdfWriter对象
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://PICC" + System.currentTimeMillis() + ".pdf"));
        // 打开文档
        document.open();
        //头部表格
        PdfPTable tableHead = new PdfPTable(2);
        // 设置表格宽度比例为%100
        tableHead.setWidthPercentage(100);
        // 设置表格的宽度
        tableHead.setTotalWidth(500);
        // 锁住宽度
        tableHead.setLockedWidth(true);
        // 设置表格上面空白宽度
        tableHead.setSpacingBefore(10f);
        // 设置表格下面空白宽度
//        tableHead.setSpacingAfter(10f);
        // 设置表格默认为无边框
//        tableHead.getDefaultCell().setBorder(0);
        // 头部的PICC图标
        // 在表格添加图片
        Image headImg = Image.getInstance("D://head.png");
        PdfPCell headCell01 = new PdfPCell(headImg, true);
//        headCell01.setBorderColor(BaseColor.RED);
        headCell01.setBorder(0);
        headCell01.setPaddingLeft(10);
        headCell01.setFixedHeight(30);
        headCell01.setHorizontalAlignment(Element.ALIGN_CENTER);
        headCell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHead.addCell(headCell01);

        PdfPCell headCell02 = new PdfPCell(new Paragraph("14位单证流水号",new Font(baseFont)));
//        headCell02.setBorderColor(BaseColor.GREEN);
        headCell02.setBorder(0);
        headCell02.setPaddingRight(10);
        headCell02.setHorizontalAlignment(Element.ALIGN_RIGHT);
        headCell02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHead.addCell(headCell02);

        Image lineImg = Image.getInstance("D://line.png");
        PdfPCell headCell03 = new PdfPCell(lineImg, true);
//        headCell01.setBorderColor(BaseColor.RED);
        headCell03.setPaddingLeft(5);
        headCell03.setPaddingRight(5);
        headCell03.setFixedHeight(10);
        headCell03.setBorder(0);
        headCell03.setColspan(2);
        headCell03.setHorizontalAlignment(Element.ALIGN_CENTER);
        headCell03.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHead.addCell(headCell03);

        PdfPCell headCell04 = new PdfPCell(new Paragraph("中国人民财产保险股份有限公司  展览会责任保险  投保单",new Font(baseFont)));
        headCell04.setFixedHeight(20);
        headCell04.setColspan(2);
        headCell04.setBorder(0);
        headCell04.setHorizontalAlignment(Element.ALIGN_CENTER);
        headCell04.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHead.addCell(headCell04);

        Paragraph paragraph = new Paragraph("        尊敬的投保人：在您填写本投保单前请先详细阅读《展览会责任保险条款》，阅读条款时请您特别注意条款中的保险责任、责任免除、投保人被保险人义务、赔偿处理等内容并听取保险人就条款（包括前述需特别注意的内容）所作的说明。",new Font(baseFont));
//        paragraph.setIndentationLeft(8);
//        paragraph.setFirstLineIndent(8);
//        paragraph.setAlignment(Element.ALIGN_LEFT);
        PdfPCell headCell05 = new PdfPCell(paragraph);
        headCell05.setFixedHeight(60);
        headCell05.setColspan(2);
        headCell05.setBorder(0);
        headCell05.setHorizontalAlignment(Element.ALIGN_LEFT);
        headCell05.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHead.addCell(headCell05);
        document.add(tableHead);
        //头部表格
        PdfPTable tableTitle = new PdfPTable(12);
        // 设置表格宽度比例为%100
        tableTitle.setWidthPercentage(100);
        // 设置表格的宽度
        tableTitle.setTotalWidth(500);
        // 锁住宽度
        tableTitle.setLockedWidth(true);
        // 设置表格上面空白宽度
//        tableTitle.setSpacingBefore(10f);
//        // 设置表格下面空白宽度
//        tableTitle.setSpacingAfter(10f);

        PdfPCell titleCell01 = new PdfPCell(new Paragraph("投保人：金阖科技（北京）有限公司",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell01.setBorder(0);
        titleCell01.setColspan(7);
        titleCell01.setFixedHeight(20);
        titleCell01.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell01);

        PdfPCell titleCell02 = new PdfPCell(new Paragraph("统一社会信用代码：91110101317953774G",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell02.setBorder(0);
        titleCell02.setColspan(5);
        titleCell02.setFixedHeight(20);
        titleCell02.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell02);

        PdfPCell titleCell03 = new PdfPCell(new Paragraph("联系地址：金阖科技（北京）有限公司",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell03.setBorder(0);
        titleCell03.setColspan(7);
        titleCell03.setFixedHeight(20);
        titleCell03.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell03.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell03);

        PdfPCell titleCell04 = new PdfPCell(new Paragraph("邮政编码：100000",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell04.setBorder(0);
        titleCell04.setColspan(5);
        titleCell04.setFixedHeight(20);
        titleCell04.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell04.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell04);

        PdfPCell titleCell05 = new PdfPCell(new Paragraph("电话：18888888888",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell05.setBorder(0);
        titleCell05.setColspan(7);
        titleCell05.setFixedHeight(20);
        titleCell05.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell05.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell05);

        PdfPCell titleCell06 = new PdfPCell(new Paragraph("传真：86053188888888",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
        titleCell06.setBorder(0);
        titleCell06.setColspan(5);
        titleCell06.setFixedHeight(20);
        titleCell06.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell06.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell06);


        PdfPCell titleCell07 = new PdfPCell(new Paragraph("被保险人名称：金阖科技（北京）有限公司",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell07.setBorder(0);
        titleCell07.setColspan(7);
        titleCell07.setFixedHeight(20);
        titleCell07.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell07.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell07);

        PdfPCell titleCell08 = new PdfPCell(new Paragraph("统一社会信用代码：91110101317953774G",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell08.setBorder(0);
        titleCell08.setColspan(5);
        titleCell08.setFixedHeight(20);
        titleCell08.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell08.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell08);

        PdfPCell titleCell09 = new PdfPCell(new Paragraph("被保险人地址：金阖科技（北京）有限公司",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell09.setBorder(0);
        titleCell09.setColspan(7);
        titleCell09.setFixedHeight(20);
        titleCell09.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell09.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell09);

        PdfPCell titleCell10 = new PdfPCell(new Paragraph("邮政编码：100000",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell10.setBorder(0);
        titleCell10.setColspan(5);
        titleCell10.setFixedHeight(20);
        titleCell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell10);

        PdfPCell titleCell11 = new PdfPCell(new Paragraph("电话：18888888888",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell11.setBorder(0);
        titleCell11.setColspan(7);
        titleCell11.setFixedHeight(20);
        titleCell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell11);

        PdfPCell titleCell12 = new PdfPCell(new Paragraph("传真：86053188888888",new Font(baseFont)));
//        titleCell01.setBorderColor(BaseColor.GREEN);
//        titleCell12.setBorder(0);
        titleCell12.setColspan(5);
        titleCell12.setFixedHeight(20);
        titleCell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(titleCell12);

//        //头部表格
//        PdfPTable tableExhibit = new PdfPTable(12);
//        // 设置表格宽度比例为%100
//        tableTitle.setWidthPercentage(100);
//        // 设置表格的宽度
//        tableTitle.setTotalWidth(500);
//        // 锁住宽度
//        tableTitle.setLockedWidth(true);
//        // 设置表格上面空白宽度
////        tableTitle.setSpacingBefore(10f);
////        // 设置表格下面空白宽度
////        tableTitle.setSpacingAfter(10f);

        PdfPCell exhibitCell01 = new PdfPCell(new Paragraph("展会名称/类型",new Font(baseFont)));
        exhibitCell01.setColspan(4);
        exhibitCell01.setFixedHeight(20);
        exhibitCell01.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell01);

        PdfPCell exhibitCell02 = new PdfPCell(new Paragraph("中国农机展览会",new Font(baseFont)));
        exhibitCell02.setColspan(8);
        exhibitCell02.setFixedHeight(20);
        exhibitCell02.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell02);

        PdfPCell exhibitCell03 = new PdfPCell(new Paragraph("展馆名称",new Font(baseFont)));
        exhibitCell03.setColspan(4);
        exhibitCell03.setFixedHeight(20);
        exhibitCell03.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell03.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell03);

        PdfPCell exhibitCell04 = new PdfPCell(new Paragraph("中国农机展览会",new Font(baseFont)));
        exhibitCell04.setColspan(8);
        exhibitCell04.setFixedHeight(20);
        exhibitCell04.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell04.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell04);

        PdfPCell exhibitCell05 = new PdfPCell(new Paragraph("展会/展馆地址",new Font(baseFont)));
        exhibitCell05.setColspan(4);
        exhibitCell05.setFixedHeight(20);
        exhibitCell05.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell05.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell05);

        PdfPCell exhibitCell06 = new PdfPCell(new Paragraph("中国农机展览会",new Font(baseFont)));
        exhibitCell06.setColspan(8);
        exhibitCell06.setFixedHeight(20);
        exhibitCell06.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell06.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell06);
        document.add(tableTitle);

        for (int i = 0; i < 3; i++) {
            //头部表格
            PdfPTable tableBooth = new PdfPTable(12);
            // 设置表格宽度比例为%100
            tableBooth.setWidthPercentage(100);
            // 设置表格的宽度
            tableBooth.setTotalWidth(500);
            // 锁住宽度
            tableBooth.setLockedWidth(true);

            PdfPCell cell01 = new PdfPCell(new Paragraph("展位信息",new Font(baseFont)));
            cell01.setColspan(4);
            cell01.setRowspan(3);
            cell01.setFixedHeight(40);
            cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell01);

            PdfPCell cell02 = new PdfPCell(new Paragraph("展出面积(m²)：",new Font(baseFont)));
            cell02.setColspan(4);
            cell02.setFixedHeight(20);
            cell02.setBorderWidthRight(0);
            cell02.setBorderWidthBottom(0);
            cell02.setBorderWidthTop(0);
            cell02.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell02.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell02);

            PdfPCell cell03 = new PdfPCell(new Paragraph("展出天数：",new Font(baseFont)));
            cell03.setColspan(4);
            cell03.setFixedHeight(20);
            cell03.setBorderWidthBottom(0);
            cell03.setBorderWidthLeft(0);
            cell03.setBorderWidthTop(0);
            cell03.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell03.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell03);

            PdfPCell cell04 = new PdfPCell(new Paragraph("工作人员人数：",new Font(baseFont)));
            cell04.setColspan(4);
            cell04.setFixedHeight(20);
            cell04.setBorderWidthTop(0);
            cell04.setBorderWidthRight(0);
            cell04.setBorderWidthBottom(0);
            cell04.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell04.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell04);

            PdfPCell cell05 = new PdfPCell(new Paragraph("预计日均参观人数：",new Font(baseFont)));
            cell05.setColspan(4);
            cell05.setFixedHeight(20);
            cell05.setBorderWidthTop(0);
            cell05.setBorderWidthLeft(0);
            cell05.setBorderWidthBottom(0);
            cell05.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell05.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell05);

            PdfPCell cell06 = new PdfPCell(new Paragraph("参展商名称：",new Font(baseFont)));
            cell06.setColspan(4);
            cell06.setFixedHeight(20);
            cell06.setBorderWidthTop(0);
            cell06.setBorderWidthRight(0);
            cell06.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell06.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell06);

            PdfPCell cell07 = new PdfPCell(new Paragraph("搭建商名称：",new Font(baseFont)));
            cell07.setColspan(4);
            cell07.setFixedHeight(20);
            cell07.setBorderWidthTop(0);
            cell07.setBorderWidthLeft(0);
            cell07.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell07.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableBooth.addCell(cell07);

            document.add(tableBooth);
        }



        PdfPCell exhibitCell07 = new PdfPCell(new Paragraph("展位信息",new Font(baseFont)));
        exhibitCell07.setColspan(4);
        exhibitCell07.setFixedHeight(20);
        exhibitCell07.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell07.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell07);

        PdfPCell exhibitCell08 = new PdfPCell(new Paragraph("中国农机展览会",new Font(baseFont)));
        exhibitCell08.setColspan(8);
        exhibitCell08.setFixedHeight(20);
        exhibitCell08.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell08.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableTitle.addCell(exhibitCell08);

        //头部表格
        PdfPTable tableExhibit = new PdfPTable(12);
        // 设置表格宽度比例为%100
        tableExhibit.setWidthPercentage(100);
        // 设置表格的宽度
        tableExhibit.setTotalWidth(500);
        // 锁住宽度
        tableExhibit.setLockedWidth(true);

        PdfPCell exhibitCell09 = new PdfPCell(new Paragraph("以往损失情况",new Font(baseFont)));
        exhibitCell09.setColspan(4);
        exhibitCell09.setFixedHeight(50);
        exhibitCell09.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell09.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell09);

        PdfPCell exhibitCell10 = new PdfPCell(new Paragraph("无",new Font(baseFont)));
        exhibitCell10.setColspan(8);
        exhibitCell10.setFixedHeight(50);
        exhibitCell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell10);

        PdfPCell exhibitCell11 = new PdfPCell(new Paragraph("责任限额及免赔额\n" +
                "（方案）",new Font(baseFont)));
        exhibitCell11.setColspan(4);
        exhibitCell11.setFixedHeight(60);
        exhibitCell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell11);

        PdfPCell exhibitCell12 = new PdfPCell(new Paragraph("每人责任限额（万元）：100\n" +
                "每次事故责任限额（万元）：100\n" +
                "累计责任限额（万元）：100\n" +
                "每次事故免赔额（元）：100",new Font(baseFont)));
        exhibitCell12.setColspan(8);
        exhibitCell12.setFixedHeight(60);
        exhibitCell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        exhibitCell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell12);

        PdfPCell exhibitCell13 = new PdfPCell(new Paragraph("保险费",new Font(baseFont)));
        exhibitCell13.setColspan(4);
        exhibitCell13.setFixedHeight(20);
        exhibitCell13.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell13);

        PdfPCell exhibitCell14 = new PdfPCell(new Paragraph("5000",new Font(baseFont)));
        exhibitCell14.setColspan(8);
        exhibitCell14.setFixedHeight(20);
        exhibitCell14.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell14);

        PdfPCell exhibitCell15 = new PdfPCell(new Paragraph("保险期间",new Font(baseFont)));
        exhibitCell15.setColspan(4);
        exhibitCell15.setFixedHeight(20);
        exhibitCell15.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell15);

        PdfPCell exhibitCell16 = new PdfPCell(new Paragraph("自2019年07月08日零时起，至2019年07月17日二十四时止",new Font(baseFont)));
        exhibitCell16.setColspan(8);
        exhibitCell16.setFixedHeight(20);
        exhibitCell16.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell16);

        PdfPCell exhibitCell17 = new PdfPCell(new Paragraph("保险期间",new Font(baseFont)));
        exhibitCell17.setColspan(4);
        exhibitCell17.setFixedHeight(20);
        exhibitCell17.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell17);

        PdfPCell exhibitCell18 = new PdfPCell(new Paragraph("自2019年07月08日零时起，至2019年07月17日二十四时止",new Font(baseFont)));
        exhibitCell18.setColspan(8);
        exhibitCell18.setFixedHeight(20);
        exhibitCell18.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell18);

        PdfPCell exhibitCell19 = new PdfPCell(new Paragraph("保险合同争议\n" +
                "解决方式",new Font(baseFont)));
        exhibitCell19.setColspan(4);
        exhibitCell19.setFixedHeight(40);
        exhibitCell19.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell19);

        Paragraph p2 = new Paragraph("提交",new Font(baseFont));
        p2.add(new Chunk("北京市劳动仲裁").setUnderline(0.5f, -4f));
        p2.add(new Chunk("仲裁委员会仲裁"));
        PdfPCell exhibitCell20 = new PdfPCell(p2);
        exhibitCell20.setColspan(8);
        exhibitCell20.setFixedHeight(40);
        exhibitCell20.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell20);

        PdfPCell exhibitCell21 = new PdfPCell(new Paragraph("特别约定",new Font(baseFont)));
        exhibitCell21.setColspan(4);
        exhibitCell21.setFixedHeight(40);
        exhibitCell21.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell21);

        PdfPCell exhibitCell22 = new PdfPCell(new Paragraph("",new Font(baseFont)));
        exhibitCell22.setColspan(8);
        exhibitCell22.setFixedHeight(40);
        exhibitCell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        exhibitCell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableExhibit.addCell(exhibitCell22);

        document.add(tableExhibit);

        //头部表格
        PdfPTable tableBottom = new PdfPTable(2);
        // 设置表格宽度比例为%100
        tableBottom.setWidthPercentage(100);
        // 设置表格的宽度
        tableBottom.setTotalWidth(500);
        // 锁住宽度
        tableBottom.setLockedWidth(true);
        // 设置表格上面空白宽度
//        tableTitle.setSpacingBefore(10f);
//        // 设置表格下面空白宽度
//        tableTitle.setSpacingAfter(10f);

        Paragraph paragraph1 = new Paragraph("        投保人声明：保险人已向本人提供并详细介绍了《展览会责任保险条款》，并对其中免除保险人责任的条款（包括但不限于责任免除、投保人被保险人义务、赔偿处理、其他事项等），以及本保险合同中付费约定和特别约定的内容向本人做了明确说明，本人已充分理解并接受上述内容，同意以此作为订立保险合同的依据，自愿投保本保险。",new Font(baseFont));
        paragraph1.add(new Chunk("\n        上述所填写的内容均属实。"));
        PdfPCell bottomCell01 = new PdfPCell( paragraph1);
        bottomCell01.setColspan(2);
        bottomCell01.setFixedHeight(80);
        bottomCell01.setBorderWidthBottom(0);
        bottomCell01.setHorizontalAlignment(Element.ALIGN_LEFT);
        bottomCell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableBottom.addCell(bottomCell01);

        PdfPCell bottomCell02 = new PdfPCell(new Paragraph("投保人签名 / 签章：",new Font(baseFont)));
//        bottomCell02.setColspan(8);
        bottomCell02.setFixedHeight(20);
        bottomCell02.setBorderWidthRight(0);
        bottomCell02.setBorderWidthTop(0);
        bottomCell02.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bottomCell02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableBottom.addCell(bottomCell02);

        PdfPCell bottomCell03 = new PdfPCell(new Paragraph("2019年07月    ",new Font(baseFont)));
//        bottomCell03.setColspan(8);
        bottomCell03.setFixedHeight(20);
        bottomCell03.setBorderWidthLeft(0);
        bottomCell03.setBorderWidthTop(0);
        bottomCell03.setHorizontalAlignment(Element.ALIGN_CENTER);
        bottomCell03.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableBottom.addCell(bottomCell03);

        document.add(tableBottom);

//        document.add(tableHead);
//        document.add(tableTitle);
//        document.add(tableExhibit);
//        document.add(tableBottom);
        // 关闭文档
        document.close();
    }
}
