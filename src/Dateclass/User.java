package Dateclass;
import JDBCUtil.JDBCUtils;
import Toolclass.ImageDemo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * �û���
 */
public class User {
    public String type;//�û�����
    public String usernum;//�û���
    public String password;//����
    public String nickname;//�ǳ�
    public String phonenum;//�ֻ���
    public ImageIcon head_sculpture;//ͷ��
	public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public static void addUserfollow(String usernum, int store_id) {//Ϊ�ͻ���ӹ��ĵĵ���
        String sql = "insert into focused_store values(?,?)";
        template.update(sql, usernum, store_id);
    }

    public static void deleteUserfollow(String usernum, int store_id) {//ȡ�����ĵĵ���
        String sql = "delete from focused_store where usernum =? and store_id=?";
        template.update(sql, usernum, store_id);
    }

    public static boolean ifFollow(String usernum, int store_id) {     //�ж��Ƿ������ĳ���̵ĺ���
        String sql = "select usernum from focused_store where usernum =? and store_id =?";
        String rs = null;
        try {
            rs = template.queryForObject(sql, String.class, usernum, store_id);
        } catch (EmptyResultDataAccessException e) {
            rs = null;
        }
        if (rs == null)
            return false;
        else
            return true;
    }

    public static List<Store> getYourFollwStores(String usernum) {
        String sql = "select * from store where store_id in (select store_id from focused_store where usernum =?)";
        List<Store> stores = template.query(sql, new RowMapper<Store>() {
            public Store mapRow(ResultSet rs, int i) throws SQLException {
                Store store = new Store();
                int store_id = rs.getInt("store_id");
                String name = rs.getString("name");
                double grade = rs.getDouble("grade");
                String notice = rs.getString("notice");
                int monthsale = rs.getInt("salenum");
                String address = rs.getString("address");
                int deliverytime = rs.getInt("deliverytime");
                String storephone = rs.getString("storephone");
                store.setStore_id(store_id);
                store.setName(name);
                store.setGrade(grade);
                store.setNotice(notice);
                store.setSalesnum(monthsale);
                store.setDeliverytime(deliverytime);
                store.setStorephone(storephone);
                store.setAddress(address);
                return store;
            }
        }, usernum);
        return stores;
    }


    public static String getNickname(String usernum) {//����ǳ�
        String sql = "select nickname from user where usernum=?";
        String nickname = template.queryForObject(sql, String.class, usernum);
        return nickname;

    }

    public static String getPhonenum(String usernum) {//��õ绰
        String sql = "select phonenum from user where usernum=?";
        String nickname = template.queryForObject(sql, String.class, usernum);
        return nickname;

    }

    public static String getUserType(String usernum) {//����û�����
        String sql = "select type from user where usernum=?";
        String type = template.queryForObject(sql, String.class, usernum);
        return type;

    }

    public static void updatePersonalInformation(String usernum, String phonenum, String nickname, Image icon) {//�޸ĸ�����Ϣ
        String sql = "update user set phonenum=?,nickname=? where usernum= ?";
        template.update(sql, phonenum, nickname, usernum);
        ImageDemo.readImage2DB(usernum, icon);
    }

}
