package Toolclass;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

import org.springframework.jdbc.core.RowMapper;

import Dateclass.Goods;
/**
 * 用来获取商品的自定义RowMapper
 * */
public class MyGoodsRowMapper implements RowMapper<Goods>{
		int k=0;//图片地址
//	        获取结果集中的数据
        	public Goods mapRow(ResultSet rs, int i) throws SQLException {
                Goods good = new Goods();
                int store_id = rs.getInt("store_id");
                int good_id = rs.getInt("good_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                int salesnum = rs.getInt("salesnum");    
                Blob photo=rs.getBlob("imageicon");//读取数据库中图片的Blob流	
				InputStream in = photo.getBinaryStream();
				String pic = "./"+k+".jpg";//为图片设置不同的地址				
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(pic);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024];
				int len = 0;
				try {
					while((len = in.read(buffer))!=-1) {//将图片写入本地硬盘中
						out.write(buffer,0,len);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				k++;
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("pic"+pic);//测试	
				ImageIcon icon = new ImageIcon(pic);     
                good.setImageicon(icon);
                good.setStore_id(store_id);
                good.setGood_id(good_id);
                good.setName(name);
                good.setType(type);
                good.setPrice(price);
                good.setSalesnum(salesnum);             
                return good;
	    }
	}

