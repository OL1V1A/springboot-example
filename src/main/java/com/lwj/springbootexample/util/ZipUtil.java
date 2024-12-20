package com.lwj.springbootexample.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    /**
     * 将多个图片文件压缩成一个ZIP文件，并在压缩前裁剪和调整分辨率
     *
     * @param imagePaths 图片文件路径列表
     * @param zipFilePath 输出的ZIP文件路径
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     */
    public static void compressImagesToZip(List<String> imagePaths, String zipFilePath, int targetWidth, int targetHeight) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String imagePath : imagePaths) {
                Path path = Paths.get(imagePath);
                FileInputStream fis = new FileInputStream(path.toFile());

                // 处理图片
                byte[] processedImageData = ImageUtil.resizeAndCropImage(fis, targetWidth, targetHeight);

                ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
                zos.putNextEntry(zipEntry);

                zos.write(processedImageData, 0, processedImageData.length);

                fis.close();
                zos.closeEntry();
            }

            System.out.println("图片已成功压缩到 " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
