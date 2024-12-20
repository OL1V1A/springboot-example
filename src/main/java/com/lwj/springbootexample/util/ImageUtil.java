package com.lwj.springbootexample.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    /**
     *
     * @param inputStream 文件流
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @return
     */
    public static byte[] resizeAndCropImage(InputStream inputStream, int maxWidth,int maxHeight){
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            double aspectRatio = (double) originalWidth / originalHeight;

            int newWidth;
            int newHeight;

            if (originalWidth > originalHeight) {
                newWidth = maxWidth;
                newHeight = (int) (newWidth / aspectRatio);
            } else {
                newHeight = maxHeight;
                newWidth = (int) (newHeight * aspectRatio);
            }

            // Ensure the new dimensions do not exceed the maximum dimensions
            if (newWidth > maxWidth) {
                newWidth = maxWidth;
                newHeight = (int) (newWidth / aspectRatio);
            }
            if (newHeight > maxHeight) {
                newHeight = maxHeight;
                newWidth = (int) (newHeight * aspectRatio);
            }

            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos); // 可以指定其他格式，如png
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
