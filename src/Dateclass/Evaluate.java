package Dateclass;
import JDBCUtil.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zjj
 */
/**
 * 用户评价类
 * */
public class Evaluate {
	public String ordernum;//订单号
    public String nickname;//用户的昵称
    public String comment;//评论
    public double grade;//评分
    public String reply;//商家回复
    public static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public static void addEvaluate(Evaluate a) {
        String sql = "insert into evaluate values(?,?,?,?,?)";
        template.update(sql, a.ordernum, a.nickname, a.comment, a.grade, null);
    }

    public static void modifyEvaluate(String ordernum, String reply) {
        String sql = "update evaluate set reply =? where ordernum=?";
        template.update(sql, reply, ordernum);
    }

    public static List<Evaluate> getEvaluate(int store_id) {    //获取某商店的评论  
        String sql = "select ordernum,nickname,comment,grade,reply from evaluate natural join userorder where store_id =?";
        List<Evaluate> storeevaluates = template.query(sql, new RowMapper<Evaluate>() {
            public Evaluate mapRow(ResultSet rs, int i) throws SQLException {
            	Evaluate evaluate = new Evaluate();
            	String ordernum = rs.getString("ordernum");
            	String nickname = rs.getString("nickname");
                String comment = rs.getString("comment");  
                double  grade = rs.getDouble("grade");
                String reply =rs.getString("reply");
                evaluate.setComment(comment);
                evaluate.setGrade(grade);
                evaluate.setOrdernum(ordernum);
                evaluate.setNickname(nickname);
                evaluate.setReply(reply);
                return evaluate;
            }
        }, store_id);
        return storeevaluates;
    }

    public static Evaluate getEvaluate(String ordernum) {
        String sql = "select * from evaluate where ordernum =?";
        Evaluate evaluate = template.queryForObject(sql, Evaluate.class, ordernum);
        return evaluate;
    }

    public static boolean ifEvaluate(String ordernum) {
        String sql = "select ordernum from evaluate where ordernum=?";
        try {
            template.queryForObject(sql, String.class, ordernum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int countEvaluateNum(int store_id) {
        String sql = "select count(*) from evaluate natural join userorder where store_id =?";
        int count = template.queryForObject(sql, int.class, store_id);
        return count;
    }

    public static String getNikename(String ordernum) {
        String sql = "select nickname from user where usernum = (select usernum from userorder where ordernum =? ) ";
        String nickname = template.queryForObject(sql, String.class, ordernum);
        return nickname;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}