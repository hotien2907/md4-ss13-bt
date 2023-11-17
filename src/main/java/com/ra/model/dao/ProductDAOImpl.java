package com.ra.model.dao;


import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> list = new ArrayList<>();

        connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_GET_ALL_PRODUCT()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);
                list.add(product);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(Product product) {
        Connection connection = null;
        // mở kết nối
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_INSERT_PRODUCT(?,?,?,?)}");
            callableStatement.setString(1, product.getNameProduct());
            callableStatement.setDouble(2, product.getPrice());
            callableStatement.setString(3,product.getImage());
            callableStatement.setInt(4,product.getCategory().getCategoryId());

            // thực thi
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        Product product = null;
        Connection conn = null;
        try {
            // mỏ kết nối
            conn = ConnectionDB.openConnection();
            // chuẩn bị câu lệnh
            CallableStatement callableStatement = conn.prepareCall("{CALL PRODUCT_BY_ID(?)}");
            // truyền đối số
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return product;

    }

    @Override
    public Boolean updateById(Product product, Integer id) {

        Connection conn = null;
        conn = ConnectionDB.openConnection();

        try {

            CallableStatement callableStatement = conn.prepareCall("{CALL PROC_UPDATE_PRODUCT_BY_ID(?,?,?,?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, product.getNameProduct());
            callableStatement.setDouble(3, product.getPrice());
            callableStatement.setString(4,product.getImage());
            callableStatement.setInt(5,product.getCategory().getCategoryId());
            // thực thi
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_PRODUCT_BY_ID(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

    }
}
