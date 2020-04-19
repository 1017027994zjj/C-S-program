package Dateclass;
import JDBCUtil.JDBCUtils;
import Toolclass.MyUserOrderMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * 订单类
 * */
public class Userorder {
    public String ordernum;//订单号唯一
    public int store_id;//商家编号唯一标识
    public String usernum;//订单所属的用户的账号，唯一
    public String contact;//联系人
    public String phonenum;//电话号
    public String address;//联系人地址
    public String note;//备注
    public String buytime;//下单时间
    public String orderstatus;//订单状态
	public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
    public static List<Userorder> getAllUserorder(String usernum) {  //获得某用户的全部订单
        String sql = "select * from userorder where usernum = ?";
        List<Userorder> userorderlist = template.query(sql.toString(), new BeanPropertyRowMapper<Userorder>(Userorder.class), usernum);
        return userorderlist;
    }

    public static List<Userorder> getStoreOrder(int store_id) {  //获得某商店的全部订单
        String sql = "select * from userorder where store_id = ? ";
        List<Userorder> userorderlist = template.query(sql.toString(), new BeanPropertyRowMapper<Userorder>(Userorder.class), store_id);
        return userorderlist;
    }

    public static List<Userorder> getStoreOneDayOrder(int store_id) {  //获得商店某天的全部订单
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataStr = dateFormat.format(new Date());
        String sql = "select * from userorder where store_id = ? and ordernum like ?";
        List<Userorder> userorderlist = template.query(sql, new  MyUserOrderMapper(), store_id, "%" + dataStr + "%");
        return userorderlist;
    }

    public static List<Userorder> getUserorder(String usernum, String X, String Y) {  //用户获得指定的订单
        String sql = "select * from userorder where usernum = ? and " + X + " = ?";
        List<Userorder> userorderlist = template.query(sql, new  MyUserOrderMapper(), usernum, Y);
        return userorderlist;
    }

    public static List<Userorder> getStoreOrder(int store_id, String X, String Y) {  //店主获得指定的订单
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from userorder where store_id = ? and " + X + " = ?";
        List<Userorder> userorderlist = template.query(sql.toString(), new BeanPropertyRowMapper<Userorder>(Userorder.class), store_id, Y);
        return userorderlist;
    }

    public static List<Userorder> getStoreOrderSortByData(int store_id) {  //店主获得按日期排序的订单
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from userorder where store_id = ? order by ordernum DESC";
        List<Userorder> userorderlist = template.query(sql.toString(), new BeanPropertyRowMapper<Userorder>(Userorder.class), store_id);
        return userorderlist;
    }

    public static int getDayAllOrderSaleNum(int store_id) {  //店主获得某天店内的订单数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataStr = dateFormat.format(new Date());
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select count(ordernum) from userorder where ordernum like ?";
        int daysalesnum;
        try {
            daysalesnum = template.queryForObject(sql, int.class, "%" + dataStr + "%");
        } catch (NullPointerException e) {
            daysalesnum = 0;
        }
        return daysalesnum;
    }

    public static int getDayFinishOrderSaleNum(int store_id) {  //店主获得某天店内已完成的订单数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataStr = dateFormat.format(new Date());
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select count(ordernum) from userorder where store_id = ? and ordernum like ? and orderstatus in(?,?)";
        int daysalesnum;
        try {
            daysalesnum = template.queryForObject(sql, int.class, store_id, "%" + dataStr + "%", "订单已完成", "订单已评价");
        } catch (NullPointerException e) {
            daysalesnum = 0;
        }
        return daysalesnum;
    }

    public static double getDayIncome(int store_id) {  //店主获得某天店内的收入
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataStr = dateFormat.format(new Date());
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select sum(price*buynum) "
                + "from O_G where store_id =? "
                + "and ordernum like ? "
                + "and ordernum in (select ordernum from userorder where orderstatus in (?,?))";

        double dayincome;
        try {
            dayincome = template.queryForObject(sql, Double.class, store_id, "%" + dataStr + "%", "订单已完成", "订单已评价");
        } catch (NullPointerException e) {
            dayincome = 0;
        }
        return dayincome;
    }

    public static List<Userorder> getToBeEvaluatedUserorder(String usernum) {  //用户获得待评价订单
        String sql = "select * from userorder where usernum = ? and orderstatus = ?";
        List<Userorder> userorderlist = template.query(sql, new  MyUserOrderMapper() , usernum, "订单已完成");
        return userorderlist;
    }

    public static void addUserorder(Userorder a) {  //添加订单
        String sql = "insert into userorder values(?,?,?,?,?,?,?,?,?)";
        template.update(sql, a.ordernum, a.usernum, a.store_id, a.contact, a.phonenum, a.address, a.note, a.buytime, a.orderstatus);
    }

    public static void updateOrderstatus(String ordernum, String neworderstatus) {//更新订单配送状态
        String sql = "update userorder set orderstatus = ? where ordernum = ?";
        template.update(sql, neworderstatus, ordernum);

    }

    public static String makeOrdernum() {            //生成订单号
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(4);
        numberFormat.setMinimumIntegerDigits(4);
        String numStr = numberFormat.format(new Random().nextInt(9999));
        String dataStr = dateFormat.format(new Date());
        String Ordernum = dataStr + numStr;
        return Ordernum;
    }

    public static String makeGoodsname(String ordernum) {//给出所买的所有商品的名称
        String goodsnamelist = "";
        List<Goods> goods = Goods.getOrdergoods(ordernum);
        int size = goods.size();
        for (int i = 0; i < size; i++) {
            if (i != size - 1)
                goodsnamelist += (goods.get(i).name + ',');
            else
                goodsnamelist += (goods.get(i).name);
        }
        return goodsnamelist;
    }

    public static String makeBuytime() {    //生成下单时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String buytime = dateFormat.format(date);
        return buytime;
    }

    public static String makeServicetime(String buytime, int store_id) {//生成预计送达时间
        String sql = "select deliverytime from store where store_id = ?";
        int deliverytime = template.queryForObject(sql, int.class, store_id);
        String servicetime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            Date date = formatter.parse(buytime);
            servicetime = formatter.format(new Date(date.getTime() + deliverytime * 60 * 1000));
            //servicetime=formatter.format(new Date(date.getTime()+deliverytime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return servicetime;
    }

    public static double countTotalprice(String ordernum) {//计算某订单的总价
        String sql = "select sum(price*buynum) from O_G where ordernum=?";
        double totalprice = template.queryForObject(sql, double.class, ordernum);
        return totalprice;
    }

    public static String Getstorephone(String ordernum) {//获得商店电话
        String sql = "select storephone from store where store_id =(select store_id from userorder where ordernum =?)";
        String storephone = template.queryForObject(sql, String.class, ordernum);
        return storephone;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
}
