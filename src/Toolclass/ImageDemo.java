/**
 *
 */
package Toolclass;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Administrator 测试写入数据库以及从数据库中读取
 */
public class ImageDemo {

    // 将图片插入数据库
    public static void readImage2DB(String usernum, Image image) {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            File outputfile = new File("D:\\java图标.jpg");
            if (!outputfile.exists() && !outputfile.isDirectory()) {
                System.out.println("文件夹路径不存在，创建路径:");
                outputfile.mkdirs();
            } else {
                System.out.println("文件夹路径存在:");
            }
            ImageIO.write(toBufferedImage(image), "jpg", outputfile);
            in = ImageUtil.readImage("D:\\java图标.jpg");
            conn = DBUtil.getConn();
            String sql = "update user set head_sculpture=? where usernum=?";
            ps = conn.prepareStatement(sql);
            ps.setString(2, usernum);
            ps.setBinaryStream(1, in, in.available());
            // ps.setBlob(1, in);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 读取数据库中图片
    public static ImageIcon readDB2Image(String usernum) {
        // String targetPath = "G:\\java图标\\1229334[1].gif";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select head_sculpture from user where usernum =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usernum);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("head_sculpture");
                return ImageUtil.readBin2Image(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ImageIcon initIcon(String iconnum) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select icon from init_icon where iconnum =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, iconnum);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("icon");
                return ImageUtil.readBin2Image(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }


    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        //boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
           /* if (hasAlpha) {
             transparency = Transparency.BITMASK;
             }*/

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
            /*if (hasAlpha) {
             type = BufferedImage.TYPE_INT_ARGB;
             }*/
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    //测试
    public static void main(String[] args) {
        //buff a=new Image("G:\\java图标\\爱心.png");
        // readImage2DB("1017027994",a);

    }
}