package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn = null;
	
	private static String sql = null;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Department obj) {

		PreparedStatement st = null;
		
		try {
			sql = "INSERT into department "
				+ "(Name) "
				+ " VALUES (?)";
			
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
		} 
		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}
		finally {
			
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department obj) {

		PreparedStatement st = null;
		
		try {
			
			sql = "UPDATE department "
				+ "   SET Name = ? "
				+ " WHERE Id = ?";
			
			st = conn.prepareStatement(sql);

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();	
		} 
		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement st = null;
		try {
			sql = "DELETE from department "
					+ " WHERE id = ?";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			st.executeUpdate();
				

		} 
		catch (SQLIntegrityConstraintViolationException e){
			throw new DbIntegrityException("Departamento não pode ser excluído! Há vendedores associados a ele.");
		}
		catch (SQLException e) {

//			throw new DbException(e.getMessage());
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			sql = "SELECT * FROM department "
				+ " WHERE id = ?";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			} else {
				throw new DbException("Error! Department not exist!");
//				return null;
			}
//			return null;
		} 
		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));		
		return dep;
	}


	@Override
	public List<Department> findAll(boolean order) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			if (order == false) {
				sql = "SELECT * FROM department "
					+ " ORDER BY id";
			} else {
				
				sql = "SELECT * FROM department "
						+ " ORDER BY name";
			}
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			
			return list;
			
		} 
		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	@Override
	public List<Department> findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			sql = "SELECT * FROM department "
				+ " WHERE Name LIKE ?";

			st = conn.prepareStatement(sql);
			st.setString(1, "%" + name.toString().toLowerCase() + "%");
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			
			return list;
			
		} 
		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
