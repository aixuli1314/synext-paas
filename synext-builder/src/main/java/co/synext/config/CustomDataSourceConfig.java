package co.synext.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class CustomDataSourceConfig extends DataSourceConfig {

    private DataSource dataSource;

    @Override
    public Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
