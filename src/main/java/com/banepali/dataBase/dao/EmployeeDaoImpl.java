package com.banepali.dataBase.dao;

import dataBase.utils.SQLConnectionUtils;
import dataBase.utils.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.banepali.dataBase.dao.entity.EmployeeEntity;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String save(EmployeeEntity entity) {

		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.INSERT_IN_EMP_TBL)) {

			System.out.println(entity);// confirming the entity which this function just got, after which we will be

			// setting those data to the specific places...

			pstmt.setInt(1, entity.geteID());
			pstmt.setString(2, entity.getUserId());
			pstmt.setString(3, entity.getPassword());
			pstmt.setString(4, entity.getName());
			pstmt.setString(5, entity.getEmail());
			pstmt.setDate(6, new java.sql.Date(entity.getDate().getTime()));
			pstmt.setLong(7, entity.getMobile());
			pstmt.setDouble(8, entity.getSalary());
			pstmt.setLong(9, entity.getSsn());
			pstmt.setTimestamp(10, entity.getCreateDate());
			pstmt.setTimestamp(11, entity.getUpdateDate());

			rowCount = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rowCount > 0 ? "Rows Updated." : "Rows not Updated.";

	}

	@Override
	public List<EmployeeEntity> findAll() {
		List<EmployeeEntity> employeeEntities = new ArrayList<EmployeeEntity>();

		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_ALL_EMPLOYEES)) {
			java.sql.ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int eID = resultSet.getInt(1);
				String userId = resultSet.getString(2);
				String password = resultSet.getString(3);
				String name = resultSet.getString(4);
				String email = resultSet.getString(5);
				Date date = resultSet.getDate(6);
				Long mobile = resultSet.getLong(7);
				double salary = resultSet.getDouble(8);
				Long ssn = resultSet.getLong(9);

				Timestamp createDate = resultSet.getTimestamp(10);
				Timestamp updateDate = resultSet.getTimestamp(11);
				String role = resultSet.getString(12);
				Time sTime = resultSet.getTime(13);
				Time eTime = resultSet.getTime(14);
				String active = resultSet.getString(15);

				EmployeeEntity employeeEntity = new EmployeeEntity(eID, userId, password, name, email, date, mobile,
						salary, ssn, createDate, updateDate, role, sTime, eTime, active);

				employeeEntities.add(employeeEntity);
			}

			// printing all the employees from the list
			employeeEntities.forEach(System.out::println);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return employeeEntities;
	}

	@Override
	public List<String> findAllUserId() {
		List<String> userIds = new ArrayList<String>();

		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_ALL_USERID)) {
			java.sql.ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				userIds.add(resultSet.getString(1));
			}

			// printing all the employees from the list

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userIds;
	}

	@Override
	public int deleteById(int eID) {
		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.DELETE_EMP_BY_EID)) {
			pstmt.setInt(1, eID);
			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount);

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public int deleteByEmail(String email) {
		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.DELETE_EMP_BY_EMAIL)) {
			pstmt.setString(1, email);
			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount);

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public EmployeeEntity employeeById(int eID) {
		EmployeeEntity employeeEntities = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_EMP_BY_EID)) {
			pstmt.setInt(1, eID);
			java.sql.ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employeeEntities = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						resultSet.getLong(7), resultSet.getDouble(8), resultSet.getLong(9), resultSet.getTimestamp(10),
						resultSet.getTimestamp(11), resultSet.getString(12), resultSet.getTime(13),
						resultSet.getTime(14), resultSet.getString(15));
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return employeeEntities;
	}

	@Override
	public EmployeeEntity employeeByUserId(String userId) {
		EmployeeEntity employeeEntities = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_EMP_BY_USERID)) {
			pstmt.setString(1, userId);
			java.sql.ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employeeEntities = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						resultSet.getLong(7), resultSet.getDouble(8), resultSet.getLong(9), resultSet.getTimestamp(10),
						resultSet.getTimestamp(11), resultSet.getString(12), resultSet.getTime(13),
						resultSet.getTime(14), resultSet.getString(15));
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return employeeEntities;
	}

	@Override
	public EmployeeEntity employeeByEmail(String email) {
		EmployeeEntity employeeEntities = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_EMP_BY_EMAIL)) {
			pstmt.setString(1, email);
			java.sql.ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employeeEntities = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						resultSet.getLong(7), resultSet.getDouble(8), resultSet.getLong(9), resultSet.getTimestamp(10),
						resultSet.getTimestamp(11), resultSet.getString(12), resultSet.getTime(13),
						resultSet.getTime(14), resultSet.getString(15));
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return employeeEntities;
	}

	@Override
	public Optional<EmployeeEntity> optionalEmployeeByEmail(String email) {
		EmployeeEntity employeeEntities = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_EMP_BY_EMAIL)) {
			pstmt.setString(1, email);
			java.sql.ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employeeEntities = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						resultSet.getLong(7), resultSet.getDouble(8), resultSet.getLong(9), resultSet.getTimestamp(10),
						resultSet.getTimestamp(11), resultSet.getString(12), resultSet.getTime(13),
						resultSet.getTime(14), resultSet.getString(15));
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return Optional.ofNullable(employeeEntities);
	}

	@Override
	public Optional<EmployeeEntity> employeeLogin(String email, String password) { // Optional<> because if there is not
																					// userid associated there, then no
																					// output.
		EmployeeEntity employeeEntities = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.CHECK_LOGIN_USER)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			java.sql.ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employeeEntities = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						resultSet.getLong(7), resultSet.getDouble(8), resultSet.getLong(9), resultSet.getTimestamp(10),
						resultSet.getTimestamp(11), resultSet.getString(12), resultSet.getTime(13),
						resultSet.getTime(14), resultSet.getString(15));
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return Optional.ofNullable(employeeEntities);
	}

	/*
	 * @Override public String upload(EmployeeEntity entity) { InputStream
	 * inputStream = null; // input stream of the upload file
	 * 
	 * // obtains the upload file part in this multipart request Part filePart =
	 * request.getPart("photo"); if (filePart != null) { // prints out some
	 * information for debugging System.out.println(filePart.getName());
	 * System.out.println(filePart.getSize());
	 * System.out.println(filePart.getContentType());
	 * 
	 * // obtains input stream of the upload file inputStream =
	 * filePart.getInputStream(); }
	 */

	@Override
	public String update(EmployeeEntity entity) {
		EmployeeDao employeeDao = null;
		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.UPDATE_EMP_TBL_BY_EID)) {

			EmployeeEntity employeeEntity = employeeById(entity.geteID());
			/*
			 * First check if the given field values(inputted) from "entity" are good or
			 * not(null or not), then put those field value gotten from the entity to the
			 * Java class EmployeeEntity, and from the Java EmployeeEntity, you can go ahead
			 * and update the data into the DataBase. >> employeeEntity.set... --> part for
			 * the EmployeeEntity & >> pstmt.set.... --> part for the DataBase
			 */
			/*
			 * String UPDATE_EMP_TBL = "update employee_tbl set eid = ? ,userid = ?,
			 * password =?,name = ?, email = ?, DOB = ?, mobile = ?, salary = ?, ssn = ?,
			 * createdate = ?, updatedate = ? where eid = ?";
			 */
			employeeDao = new EmployeeDaoImpl();

			if (entity.geteID() != 0) {
				// here instead of "0" get the list of eIds and compare with the eids in that
				// list.
				employeeEntity.seteID(entity.geteID());
				pstmt.setInt(1, entity.geteID());
				pstmt.setInt(12, entity.geteID());

			} else {
				return "User not in the DataBase.";
			}
			if (entity.getUserId() != null) {
				employeeEntity.setUserId(entity.getUserId());
				pstmt.setString(2, entity.getUserId());
			} else {
				employeeDao.employeeById(entity.geteID()).getUserId();
				pstmt.setString(2, employeeDao.employeeById(entity.geteID()).getUserId());
			}

			if (entity.getPassword() != null) {
				employeeEntity.setPassword(entity.getPassword());
				pstmt.setString(3, entity.getPassword());
			} else {
				employeeDao.employeeById(entity.geteID()).getPassword();
				pstmt.setString(3, employeeDao.employeeById(entity.geteID()).getPassword());

			}

			if (entity.getName() != null) {
				employeeEntity.setName(entity.getName());
				pstmt.setString(4, entity.getName());
			} else {
				employeeDao.employeeById(entity.geteID()).getName();
				pstmt.setString(4, employeeDao.employeeById(entity.geteID()).getName());

			}

			if (entity.getEmail() != null) {
				employeeEntity.setEmail(entity.getEmail());
				pstmt.setString(5, entity.getEmail());
			} else {
				employeeDao.employeeById(entity.geteID()).getEmail();
				pstmt.setString(5, employeeDao.employeeById(entity.geteID()).getEmail());

			}

			if (entity.getDate() != null) {
				employeeEntity.setDate(entity.getDate());
				pstmt.setDate(6, new java.sql.Date(entity.getDate().getTime()));
			} else {
				employeeDao.employeeById(entity.geteID()).getDate();
				pstmt.setDate(6, new java.sql.Date(employeeDao.employeeById(entity.geteID()).getDate().getTime()));

			}

			if (entity.getMobile() != 0) {
				employeeEntity.setMobile(entity.getMobile());
				pstmt.setLong(7, entity.getMobile());
			} else {
				employeeDao.employeeById(entity.geteID()).getMobile();
				pstmt.setLong(7, employeeDao.employeeById(entity.geteID()).getMobile());

			}

			if (entity.getSalary() != 0) {
				employeeEntity.setSalary(entity.getSalary());
				pstmt.setDouble(8, entity.getSalary());
			} else {
				employeeDao.employeeById(entity.geteID()).getSalary();
				pstmt.setDouble(8, employeeDao.employeeById(entity.geteID()).getSalary());

			}

			if (entity.getSsn() != 0) {
				employeeEntity.setSsn(entity.getSsn());
				pstmt.setLong(9, entity.getSsn());
			} else {
				employeeDao.employeeById(entity.geteID()).getSsn();
				pstmt.setLong(9, employeeDao.employeeById(entity.geteID()).getSsn());

			}

			if (entity.getCreateDate() != null) {
				employeeEntity.setCreateDate(entity.getCreateDate());
				pstmt.setTimestamp(10, entity.getCreateDate());
			} else {
				employeeDao.employeeById(entity.geteID()).getCreateDate();
				pstmt.setTimestamp(10, employeeDao.employeeById(entity.geteID()).getCreateDate());

			}

			if (entity.getUpdateDate() != null) {
				employeeEntity.setUpdateDate(entity.getUpdateDate());
				pstmt.setTimestamp(11, entity.getUpdateDate());
			} else {
				employeeDao.employeeById(entity.geteID()).getUpdateDate();
				pstmt.setTimestamp(11, employeeDao.employeeById(entity.geteID()).getUpdateDate());

			}
			System.out.println(employeeEntity);
			rowCount = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}

		return rowCount > 0 ? "Update Successful" : "Not updated yet. ";
	}
	
	

	@Override
	public Time getStartTime() {
		Time startTime = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_STARTTIME)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				startTime = resultSet.getTime(1);
				//startTimeInMillis = LocalTimeUtil.getTimeInMillis(startTime);
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return startTime;
	}
	
	
	

	@Override
	public Time getEndTime() {
		Time endTime = null;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_ENDTIME)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				endTime = resultSet.getTime(1);
				//endTimeInMillis = LocalTimeUtil.getTimeInMillis(endTime);
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return endTime;
	}

	
	
	
	@Override
	public int getIncrementedEId() {
		int eId = 1;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.SELECT_MAX_EID)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				eId = resultSet.getInt(1);
				eId++;
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}

		return eId;

	}

	@Override
	public String updateEmployeeByUserId(EmployeeEntity entity) {
		EmployeeDao employeeDao = null;
		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.UPDATE_EMP_TBL_BY_USERID)) {

			EmployeeEntity employeeEntity = employeeByUserId(entity.getUserId());
			/*
			 * First check if the given field values(inputted) from "entity" are good or
			 * not(null or not), then put those field value gotten from the entity to the
			 * Java class EmployeeEntity, and from the Java EmployeeEntity, you can go ahead
			 * and update the data into the DataBase. >> employeeEntity.set... --> part for
			 * the EmployeeEntity & >> pstmt.set.... --> part for the DataBase
			 */
			/*
			 * String UPDATE_EMP_TBL = "update employee_tbl set eid = ? ,userid = ?,
			 * password =?,name = ?, email = ?, DOB = ?, mobile = ?, salary = ?, ssn = ?,
			 * createdate = ?, updatedate = ? where eid = ?";
			 */
			employeeDao = new EmployeeDaoImpl();
			String eId = Integer.toString(entity.geteID());
			if (!eId.isEmpty()) {
				// here instead of "0" get the list of eIds and compare with the eids in that
				// list.
				employeeEntity.seteID(entity.geteID());
				pstmt.setInt(1, entity.geteID());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).geteID();
				pstmt.setInt(1, employeeDao.employeeByUserId(entity.getUserId()).geteID());

			}

			if (entity.getUserId() != null) {
				employeeEntity.setUserId(entity.getUserId());
				pstmt.setString(2, entity.getUserId());
				pstmt.setString(12, entity.getUserId());
			} else {
				return "User not in the DataBase.";
			}

			if (entity.getPassword() != null) {
				employeeEntity.setPassword(entity.getPassword());
				pstmt.setString(3, entity.getPassword());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getPassword();
				pstmt.setString(3, employeeDao.employeeByUserId(entity.getUserId()).getPassword());

			}

			if (entity.getName() != null) {
				employeeEntity.setName(entity.getName());
				pstmt.setString(4, entity.getName());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getName();
				pstmt.setString(4, employeeDao.employeeByUserId(entity.getUserId()).getName());

			}

			if (entity.getEmail() != null) {
				employeeEntity.setEmail(entity.getEmail());
				pstmt.setString(5, entity.getEmail());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getEmail();
				pstmt.setString(5, employeeDao.employeeByUserId(entity.getUserId()).getEmail());

			}

			if (entity.getDate() != null) {
				employeeEntity.setDate(entity.getDate());
				pstmt.setDate(6, new java.sql.Date(entity.getDate().getTime()));
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getDate();
				pstmt.setDate(6,
						new java.sql.Date(employeeDao.employeeByUserId(entity.getUserId()).getDate().getTime()));

			}

			if (entity.getMobile() != 0) {
				employeeEntity.setMobile(entity.getMobile());
				pstmt.setLong(7, entity.getMobile());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getMobile();
				pstmt.setLong(7, employeeDao.employeeByUserId(entity.getUserId()).getMobile());

			}

			if (entity.getSalary() != 0) {
				employeeEntity.setSalary(entity.getSalary());
				pstmt.setDouble(8, entity.getSalary());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getSalary();
				pstmt.setDouble(8, employeeDao.employeeByUserId(entity.getUserId()).getSalary());

			}

			if (entity.getSsn() != 0) {
				employeeEntity.setSsn(entity.getSsn());
				pstmt.setLong(9, entity.getSsn());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getSsn();
				pstmt.setLong(9, employeeDao.employeeByUserId(entity.getUserId()).getSsn());

			}

			if (entity.getCreateDate() != null) {
				employeeEntity.setCreateDate(entity.getCreateDate());
				pstmt.setTimestamp(10, entity.getCreateDate());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getCreateDate();
				pstmt.setTimestamp(10, employeeDao.employeeByUserId(entity.getUserId()).getCreateDate());

			}

			if (entity.getUpdateDate() != null) {
				employeeEntity.setUpdateDate(entity.getUpdateDate());
				pstmt.setTimestamp(11, entity.getUpdateDate());
			} else {
				employeeDao.employeeByUserId(entity.getUserId()).getUpdateDate();
				pstmt.setTimestamp(11, employeeDao.employeeByUserId(entity.getUserId()).getUpdateDate());

			}
			System.out.println(employeeEntity);
			rowCount = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}

		return rowCount > 0 ? "Update Successful" : "Not updated yet. ";
	}

	@Override
	public String updatePassword(String email, String password) {
		// EmployeeDao employeeDao = null;
		int rowCount = 0;
		try (Connection conn = SQLConnectionUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLQueries.UPDATE_EMP_PASSWORD)) {
			pstmt.setString(1, password);
			pstmt.setString(2, email);

			rowCount = pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		return rowCount > 0 ? "Update Successful" : "Not updated yet. ";
	}

	@Override
	public String updateEmployeeActiveStatus(String userId) {

		String status = employeeByUserId(userId).getActive();

		System.out.println(status);
		if (status.equals("YES")) { // the "Active" status of the employee was "YES"
			int rowCount = 0;

			try (Connection conn = SQLConnectionUtils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(SQLQueries.UPDATE_EMP_ACTIVE_STATUS_OFF);) {
				pstmt.setString(1, userId);
				rowCount = pstmt.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" \t\t\t\t UPDATE_EMP_ACTIVE_STATUS_OFF  was called");
			return rowCount > 0 ? "Update Successful" : "Not updated Yet";
		}

		else { // the "Active" status of the employee was "NO"
			int rowCountOn = 0;
			int rowCountOff = 0;

			try (Connection conn = SQLConnectionUtils.getConnection();
					PreparedStatement pstmtSetOn = conn.prepareStatement(SQLQueries.UPDATE_EMP_ACTIVE_STATUS_ON);
					PreparedStatement pstmtSetOff = conn
							.prepareStatement(SQLQueries.UPDATE_REMAIN_EMP_ACTIVE_STATUS_OFF)) {
				pstmtSetOn.setString(1, userId);
				pstmtSetOff.setString(1, userId);
				rowCountOn = pstmtSetOn.executeUpdate();
				rowCountOff = pstmtSetOff.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\t\t\t\t UPDATE_EMP_ACTIVE_STATUS_ON  was called");
			if (rowCountOn > 0) {
				if (rowCountOff > 0) {
					return "Update Successful";
				} else {
					return "Not updated Yet due to \"UPDATE_REMAIN_EMP_ACTIVE_STATUS_OFF\"";
				}
			} else {
				if (rowCountOff > 0) {
					return "Update Successful";
				} else {
					return "Not updated Yet due to \"UPDATE_EMP_ACTIVE_STATUS_ON\"";
				}
			}

		}

	}
}