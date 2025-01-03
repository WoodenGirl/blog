package fun.aprilsxz.blog.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        // 将数组转换为逗号分隔的字符串
        ps.setString(i, String.join(",", parameter));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从结果集中获取字符串并转换为数组
        String data = rs.getString(columnName);
        return data == null ? new String[0] : data.split(",");
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从结果集中获取字符串并转换为数组
        String data = rs.getString(columnIndex);
        return data == null ? new String[0] : data.split(",");
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从存储过程结果中获取字符串并转换为数组
        String data = cs.getString(columnIndex);
        return data == null ? new String[0] : data.split(",");
    }
}