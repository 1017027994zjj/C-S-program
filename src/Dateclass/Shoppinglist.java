package Dateclass;
import JDBCUtil.JDBCUtils;
import Toolclass.MyGoodsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
//���ﳵ��
public class Shoppinglist {
    public String ordernum;//������
    public int store_id;//�̵��
    public int good_id;//��Ʒ��
    public int buynum;//��������
	public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * ��ö���Ʒ�Ĺ�������
     */
    public static int getBuyNum(String usernum, int store_id, int good_id) {
        String sql = "select buynum from shoppinglist where usernum=? and store_id =? and good_id=?";
        int buynum = template.queryForObject(sql, int.class, usernum, store_id, good_id);
        return buynum;
    }

    public static void addShoppinglist(String usernum, int store_id, int good_id, int buynum) {//ĳ���̵Ĺ��ﳵ
        String sql1 = "select buynum from shoppinglist where usernum =? and store_id= ? and good_id=?";
        String sql2 = "insert into shoppinglist values(?,?,?,?)";
        String sql3 = "update shoppinglist set buynum=? where usernum =? and store_id=? and good_id=?";
        int existbuynum;
        try {
            existbuynum = template.queryForObject(sql1, int.class, usernum, store_id, good_id);
            template.update(sql3, buynum + existbuynum, usernum, store_id, good_id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("3");
            template.update(sql2, usernum, store_id, good_id, buynum);
        }


    }

    public static List<Goods> getShoppinglist(String usernum, int store_id) {//ĳ���̵Ĺ��ﳵ
        String sql = "select * from goods where (store_id,good_id) in (select store_id,good_id from shoppinglist where usernum= ? and store_id= ?) ";
        List<Goods> goods = template.query(sql, new MyGoodsRowMapper(), usernum, store_id);
        return goods;
    }

    public static void deleteFromShoppinglist(String usernum, int store_id, int good_id) {
        String sql = "delete from shoppinglist where usernum = ? and store_id = ? and good_id =?";
        template.update(sql, usernum, store_id, good_id);
    }
}

