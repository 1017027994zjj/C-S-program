package Toolclass;

/**
 *
 */

import javax.swing.*;
import java.io.*;

/**
 * @author Administrator
 *
 */
public class ImageUtil {

    // 读取本地图片获取输入流
    public static FileInputStream readImage(String path) throws IOException {
        return new FileInputStream(new File(path));
    }

    // 读取表中图片获取输出流
    public static ImageIcon readBin2Image(InputStream in) {
        File file = new File("D:\\java图标.jpg");
        if (!file.exists()) {
            new File("D:\\java图标.jpg").mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            ImageIcon imageicon = new ImageIcon("D:\\\\java图标.jpg");
            return imageicon;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {

                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}