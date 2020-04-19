package Dateclass;
import JDBCUtil.JDBCUtils;
import Toolclass.MyStoresRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * 商店信息类
 **/
public class Store {
    public int store_id;
    public String name;
    public double grade;//评分
    public String address;//商店地址
    public String notice;//公告
    public int salesnum;
    public int deliverytime;//配送时间
    public String storephone;//商店联系电话
    public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public static int getStore_id(String usernum) {//获得商店编号
        String sql = "select store_id from user_store where usernum=?";
        int store_id = template.queryForObject(sql, int.class, usernum);
        return store_id;
    }
    
    public static boolean ifRegist(String usernum) {//判断用户是否注册了商店
        String sql = "select store_id from user_store where usernum=?";
        try {
            template.queryForObject(sql, int.class, usernum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void addStore(String usernum, String name, String notice, String address, String storephone) {//注册商店
        String sql = "select max(store_id)+1 from store";
        String sql1 = "insert into store values(?,?,?,?,?,?,?,?,?)";
        String sql2 = "insert into user_store values(?,?)";
        int store_id;    //自动生成商店编号
        try {
            store_id = template.queryForObject(sql, int.class);
        } catch (Exception e) {
            // TODO: handle exception
            store_id = 1;
        }
        template.update(sql1, store_id, name, notice, 5, 0, address, 40, storephone);
        template.update(sql2, usernum, store_id);
    }

    public static List<Store> getstores() {
        String sql = "select * from store ";
        List<Store> stores = template.query(sql, new MyStoresRowMapper());
        return stores;

    }

    public static List<Store> getstores(String storename) {
        String sql = "select * from store where name like ?";
        List<Store> stores = template.query(sql, new MyStoresRowMapper(), "%" + storename + "%");
        return stores;

    }

    public static List<Store> sqlSortByGrade() {//数据库查询直接按评分排序
        String sql = "select * from store order by grade DESC";
        List<Store> stores = template.query(sql, new MyStoresRowMapper());
        return stores;
    }

    public static List<Store> sqlSortByMonthsale() {//数据库查询直接按月销售量排序
        String sql = "select * from store order by salenum DESC";
        List<Store> stores = template.query(sql, new  MyStoresRowMapper());
        return stores;
    }

    public static void updateNotice(int store_id, String newnotice) {//更新公告
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update store set notice=? where store_id=?";
        template.update(sql, newnotice, store_id);
    }

    public static void updateName(int store_id, String newname) {//更新名字
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update store set name=? where store_id=?";
        template.update(sql, newname, store_id);
    }

    public static void updateSalesNum(int store_id) {//更改商店销售量（订单数量+1）
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update store set salenum=salenum+1 where store_id=?";
        template.update(sql, store_id);
    }

    public static void updatePhoneNum(int store_id, String storephone) {//更改商店联系方式
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update store set storephone=? where store_id=?";
        template.update(sql, storephone, store_id);
    }

    public static void updateAddress(int store_id, String address) {//更改商店联系方式
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update store set address=? where store_id=?";
        template.update(sql, address, store_id);
    }

    public static String getStoreName(int store_id) {//获取某商店的名称
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select name from store where store_id=?";
        String storename = template.queryForObject(sql, String.class, store_id);
        return storename;
    }

    public static String getNotice(int store_id) {//获取某商店的公告
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select notice from store where store_id=?";
        String notice = template.queryForObject(sql, String.class, store_id);
        return notice;
    }

    public static double getGrade(int store_id) {//获取某商店的评分
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select grade from store where store_id=?";
        double notice = template.queryForObject(sql, double.class, store_id);
        return notice;
    }

    public static String getStorePhone(int store_id) {//获取某商店的联系电话
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select storephone from store where store_id=?";
        String storephone = template.queryForObject(sql, String.class, store_id);
        return storephone;
    }

    public static String getAddress(int store_id) {  //获取某商店的地址
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select address from store where store_id=?";
        String address = template.queryForObject(sql, String.class, store_id);
        return address;
    }

    public static void addGoods(int store_id, String name, String type, double price,InputStream imputstream) {//为商店增加菜品
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select max(good_id)+1 from goods where store_id=?";
        String sql1 = "insert into Goods values(?,?,?,?,?,?,?)";
        int goods_id;    //自动生成商品编号
        try {
            goods_id = template.queryForObject(sql, int.class, store_id);
        } catch (Exception e) {
            // TODO: handle exception
            goods_id = 1;//如果没菜品编号为0
        }
        int salesnum = 0;
        template.update(sql1, store_id, goods_id, name, type, price, salesnum,imputstream);
    }

    public static void modifyGoods(int store_id, int good_id, Goods newgoods) {//更新商品的属性
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update goods set name = ?,type = ?,price = ?,salesnum=? where store_id=? and good_id =?";
        template.update(sql, newgoods.name, newgoods.type, newgoods.price, newgoods.salesnum, store_id, good_id);
    }
    public static void DeleteyGoods(int store_id, int good_id) {//删除指定商品
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "delete from Goods where store_id = ? and good_id = ? ";
        template.update(sql,store_id, good_id);
    }
    
    public static void upDateGoodsSaleNum(int store_id, int good_id, int buynum) {//更新商品的属性
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update goods set salesnum=salesnum+? where store_id =? and good_id=?";
        template.update(sql, buynum, store_id, good_id);
    }

    public static void main(String[] args) {
        System.out.println(ifRegist("170400327"));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStorephone() {
        return storephone;
    }

    public void setStorephone(String storephone) {
        this.storephone = storephone;
    }

    public int getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(int deliverytime) {
        this.deliverytime = deliverytime;
    }

    public int getSalesnum() {
        return salesnum;
    }

    public void setSalesnum(int monthsale) {
        this.salesnum = monthsale;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

