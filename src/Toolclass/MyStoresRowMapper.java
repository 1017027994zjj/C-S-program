package Toolclass;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import Dateclass.Store;
/**
 * 用来获取商店信息的自定义RowMapper
 * */
public class MyStoresRowMapper implements RowMapper<Store> {
		      //  获取结果集中的数据
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
}
