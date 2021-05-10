package com.hzy.file.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hzy.file.dto.DemoData;
import com.hzy.file.dto.ImageData;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelTest {

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "E:\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 写法2
        fileName = "E:\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 图片导出
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ImageData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void imageWrite() throws Exception {
        String fileName = "E:\\imageWrite" + System.currentTimeMillis() + ".xlsx";
        // 如果使用流 记得关闭
        InputStream inputStream = null;
        try {
            List<ImageData> list = new ArrayList<ImageData>();
            ImageData imageData = new ImageData();
            for (int i = 0; i < 1000; i++) {
                String imagePath = "E:\\test.png";
                // 放入五种类型的图片 实际使用只要选一种即可
//                imageData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
//                imageData.setFile(new File(imagePath));
//                imageData.setString(imagePath);
//                inputStream = FileUtils.openInputStream(new File(imagePath));
//                imageData.setInputStream(inputStream);
//                imageData.setUrl(new URL(
//                        "http://img-oscs.opechk.com/hub/file/d883131053c7a059419115ed54e117bf.png"));
                list.add(imageData);
            }
            EasyExcel.write(fileName, ImageData.class).sheet().doWrite(list);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static final String suffix = "?x-oss-process=image/resize,m_fixed,h_60,w_60";

    /**
     * 图片导出
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ImageData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void imageWrites() throws Exception {
        String fileName = "E:\\imageWrite" + System.currentTimeMillis() + ".xlsx";
        // 如果使用流 记得关闭
        InputStream inputStream = null;
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            List<ImageData> list = new ArrayList<ImageData>();
            ImageData imageData = new ImageData();
            for (int x = 0; x < 100; x++) {
                long start = System.currentTimeMillis();
                System.out.println("第" + x + "轮开始");
                for (int i = 0; i < 500; i++) {
//                    String imagePath = "E:\\test.png";
//                    // 放入五种类型的图片 实际使用只要选一种即可
//                    imageData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
//                    imageData.setFile(new File(imagePath));
//                    imageData.setString(imagePath);
//                    inputStream = FileUtils.openInputStream(new File(imagePath));
//                    imageData.setInputStream(inputStream);
                    imageData.setUrl(new URL(
                            "http://img-oscs.opechk.com/hub/file/d883131053c7a059419115ed54e117bf.png" + suffix));
                    list.add(imageData);
                }

                try {
                    excelWriter = EasyExcel.write(fileName, ImageData.class).build();
                    WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
                    excelWriter.write(list, writeSheet);
                    list.clear();
                } finally {
                    // 千万别忘记finish 会帮忙关闭流
                    if (excelWriter != null) {
                        excelWriter.finish();
                    }
                }
                long end = System.currentTimeMillis();
                System.out.println("第" + x + "轮结束，耗时" + (end - start));
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


}
