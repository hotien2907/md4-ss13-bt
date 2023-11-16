package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> list = new ArrayList<>();

        connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_GET_ALL_CATEGORY()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(Category category) {
        Connection connection = null;
        // mở kết nối
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_INSERT_CATEGORY(?,?)}");
            callableStatement.setString(1, category.getCategoryName());
            callableStatement.setBoolean(2, category.isStatus());

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
    public Category findById(Integer id) {

        Category category = null;
        Connection conn = null;
        try {
            // mỏ kết nối
            conn = ConnectionDB.openConnection();
            // chuẩn bị câu lệnh
            CallableStatement callableStatement = conn.prepareCall("{CALL CATEGORY_BY_ID(?)}");
            // truyền đối số
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return category;
    }

    @Override
    public Boolean updateById(Category category, Integer id) {

        Connection conn = null;
        conn = ConnectionDB.openConnection();

        try {

            CallableStatement callableStatement = conn.prepareCall("{CALL PROC_UPDATE_CATEGORY_BY_ID(?,?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, category.getCategoryName());
            callableStatement.setBoolean(3, category.isStatus());

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
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_CATEGORY_BY_ID(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
