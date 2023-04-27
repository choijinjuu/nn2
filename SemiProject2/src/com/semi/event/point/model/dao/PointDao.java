package com.semi.event.point.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.product.model.dao.ProductDao;

public class PointDao {
	
	private Properties prop = new Properties();

	public PointDao() {
			
			String filePath = ProductDao.class.getResource("/sql/event/event-mapper.xml").getPath();
			
			try {
				
				prop.loadFromXML(new FileInputStream(filePath));
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//적립금 올려주기
	public int upPoint(Connection conn, int memberNo, int addP) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("upPoint");
		try {
			
			 pstmt = conn.prepareStatement(sql);
			 	pstmt.setInt(1, addP);
			 	pstmt.setInt(2,memberNo);
			 
			 result = pstmt.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
