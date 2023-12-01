package app.community.mapper.handler;

import app.community.model.enums.UserStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusEnumHandler extends BaseTypeHandler<UserStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserStatus parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.getValue());
    }

    @Override
    public UserStatus getNullableResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return UserStatus.of(resultSet.getString(columnLabel));
    }

    @Override
    public UserStatus getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return UserStatus.valueOf(resultSet.getString(columnIndex));
    }

    @Override
    public UserStatus getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return UserStatus.valueOf(callableStatement.getString(columnIndex));
    }
}