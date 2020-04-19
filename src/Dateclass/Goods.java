package Dateclass;
import JDBCUtil.JDBCUtils;
import Toolclass.MyGoodsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import javax.swing.ImageIcon;
public class Goods {
    public int store_id;
    public int good_id;
    public String name;
    public String type;
    public double price;
    public int salesnum;
	public ImageIcon imageicon;
	public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public static List<Goods> getgoods(int store_id) {//��ȡĳ�̵��������Ʒ
       // JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from goods where store_id=?";
        List<Goods> goods = template.query(sql, new MyGoodsRowMapper(), store_id);
        return goods;
    }

    public static Goods getOneGoods(int good_id) {//��ȡָ����Ʒ�ŵ���Ʒ
        //JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from goods where good_id =?";
        Goods goods = template.queryForObject(sql, new MyGoodsRowMapper(), good_id);
        return goods;
    }

    public static List<Goods> getgoods(int store_id, String goodname) {//�����ƶ����ʵ���Ʒ
        //JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from goods where store_id = ? and name like ?";
        List<Goods> goods = template.query(sql, new MyGoodsRowMapper(), store_id, "%" + goodname + "%");
        return goods;
    }

    public static List<Goods> getgoods(int store_id, String goodname, String type) {//����ָ�����ƻ���Ʒ�����Ʒ
       // JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from goods where store_id = ? and (name like ? or type like ?)";
        List<Goods> goods = template.query(sql, new MyGoodsRowMapper() , store_id, "%" + goodname + "%", "%" + type + "%");
        return goods;
    }

    public static List<Goods> getOrdergoods(String ordernum) {//��ȡ�����е���Ʒ
        //JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from goods where good_id in(select good_id from order_goods where ordernum = ?)";
        List<Goods> goods = template.query(sql, new MyGoodsRowMapper() , ordernum);
        return goods;
    }

    public static String getGoodsName(int store_id, int good_id) {
        //JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select name from goods where store_id=? and good_id =? ";
        String goodsname = template.queryForObject(sql, String.class, store_id, good_id);
        return goodsname;
    }

    public static double getGoodsPrice(int store_id, int good_id) {
        //JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select price from goods where store_id=? and good_id =? ";
        double price = template.queryForObject(sql, double.class, store_id, good_id);
        return price;


    }

    public static void main(String[] args) {
        List<Order_goods> ordergoodslist = Order_goods.getOrder_GoodsList("202002051459234184");
        System.out.println(ordergoodslist.get(0).store_id + ordergoodslist.get(0).good_id);

    }
    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesnum() {
        return salesnum;
    }

    public void setSalesnum(int salesnum) {
        this.salesnum = salesnum;
    }
    public ImageIcon getImageicon() {
		return imageicon;
	}

	public void setImageicon(ImageIcon imageicon) {
		this.imageicon = imageicon;
	}
}
