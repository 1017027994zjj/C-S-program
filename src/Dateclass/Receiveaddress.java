package Dateclass;

import JDBCUtil.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * * 收货地址类
 **/
public class Receiveaddress {
    public String usernum;
    public String contact;//联系人;
    public String address;//收货地址
    public String phonenum;//联系电话
    public String sex;//性别

    public static void addReceiveAddress(Receiveaddress newaddress) {//添加收获地址
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into receiveaddress values(?,?,?,?,?)";
        template.update(sql, newaddress.usernum, newaddress.contact, newaddress.address, newaddress.phonenum, newaddress.sex);
    }

    public static List<Receiveaddress> getReceiveAddress(String usernum) {//获得收获地址
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from receiveaddress where usernum = ? ";
        List<Receiveaddress> list = template.query(sql, new RowMapper<Receiveaddress>() {
            public Receiveaddress mapRow(ResultSet rs, int i) throws SQLException {
                Receiveaddress receiveaddress = new Receiveaddress();
                String usernum = rs.getString("usernum");
                String contact = rs.getString("contact");
                String address = rs.getString("address");
                String phonenum = rs.getString("phonenum");
                String sex = rs.getString("sex");
                receiveaddress.address = address;
                receiveaddress.contact = contact;
                receiveaddress.phonenum = phonenum;
                receiveaddress.sex = sex;
                receiveaddress.usernum = usernum;
                return receiveaddress;
            }
        }, usernum);
        return list;
    }

    public static void updateAddress(Receiveaddress newaddress) {//更新地址
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update receiveaddress set address=?,phonenum=?,sex=? where usernum=? and contact=?";
        template.update(sql, newaddress.address, newaddress.phonenum, newaddress.sex, newaddress.usernum, newaddress.contact);
    }

    public static void deleteAddress(String usernum, String contact) {//删除地址按钮
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "delete from receiveaddress where usernum=? and contact =?";
        template.update(sql, usernum, contact);
    }

    public static void main(String[] args) {
        String usernum = "1232";
        List<Receiveaddress> list = Receiveaddress.getReceiveAddress(usernum);
        System.out.println(list.get(0).usernum);
    }

}
