package app.community.mapper.handler;

import app.community.model.enums.UserRoleType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleTypeEnumHandler extends BaseTypeHandler<UserRoleType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserRoleType parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.getValue());
    }

    @Override
    public UserRoleType getNullableResult(ResultSet resultSet, String columnLabel) throws SQLException {
        return UserRoleType.of(resultSet.getString(columnLabel));
    }

    @Override
    public UserRoleType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return UserRoleType.valueOf(resultSet.getString(columnIndex));
    }

    @Override
    public UserRoleType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return UserRoleType.valueOf(callableStatement.getString(columnIndex));
    }
}