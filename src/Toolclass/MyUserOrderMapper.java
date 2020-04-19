package Toolclass;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import Dateclass.Userorder;
/**
 * 查询订单信息的自定义RowMapper
 * */
public class MyUserOrderMapper implements RowMapper<Userorder> {
	public Userorder mapRow(ResultSet rs, int i) throws SQLException {
		 Userorder userorder = new Userorder();
         String ordernum = rs.getString("ordernum");//订单号
         String contact = rs.getString("contact");//联系人
         String phonenum = rs.getString("phonenum");//电话号
         String address = rs.getString("address");//联系人地址
         int store_id = rs.getInt("store_id");//商家名
         String note = rs.getString("note");
         String buytime = rs.getString("buytime");//下单时间
         String orderstatus = rs.getString("orderstatus");//订单状态
         String usernum = rs.getString("usernum");      //用户名
         userorder.setOrdernum(ordernum);
         userorder.setContact(contact);
         userorder.setPhonenum(phonenum);
         userorder.setAddress(address);
         userorder.setStore_id(store_id);
         userorder.setOrderstatus(orderstatus);
         userorder.setBuytime(buytime);
         userorder.setNote(note);
         userorder.setUsernum(usernum);
         return userorder;
	
	}
}
