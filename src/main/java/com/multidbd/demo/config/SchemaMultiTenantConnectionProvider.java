//package com.multidbd.demo.config;
//
//
//
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@Component
//public class SchemaMultiTenantConnectionProvider 
//    implements MultiTenantConnectionProvider<String> {
//
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private final DataSource dataSource;
//
//    public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public Connection getAnyConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
//
//    @Override
//    public void releaseAnyConnection(Connection connection) throws SQLException {
//        connection.close();
//    }
//
//    @Override
//    public Connection getConnection(String tenantIdentifier) throws SQLException {
//        final Connection connection = getAnyConnection();
//        connection.setSchema(tenantIdentifier);
//        return connection;
//    }
//
//    @Override
//    public void releaseConnection(String tenantIdentifier, Connection connection) 
//        throws SQLException {
//        connection.setSchema("public");
//        releaseAnyConnection(connection);
//    }
//
//    @Override
//    public boolean supportsAggressiveRelease() {
//        return false;
//    }
//
//    @Override
//    public boolean isUnwrappableAs(Class<?> unwrapType) {
//        return false;
//    }
//
//    @Override
//    public <T> T unwrap(Class<T> unwrapType) {
//        return null;
//    }
//}







package com.multidbd.demo.config;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider<String> {

    private static final long serialVersionUID = 1L;
    private final DataSource dataSource;

    public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        connection.setSchema(tenantIdentifier);
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.setSchema("public");
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
