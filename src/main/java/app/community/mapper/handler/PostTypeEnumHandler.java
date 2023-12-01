package app.community.mapper.handler;

import app.community.model.enums.PostType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTypeEnumHandler extends BaseTypeHandler<PostType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PostType parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.getValue());
    }

    @Override
    public PostType getNullableResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return PostType.of(resultSet.getString(columnLabel));
    }

    @Override
    public PostType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return PostType.valueOf(resultSet.getString(columnIndex));
    }

    @Override
    public PostType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return PostType.valueOf(callableStatement.getString(columnIndex));
    }
}