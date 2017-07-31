package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.JdbcUtil;
import util.Criteria;

public class ShopDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ShopDAO() {
		// TODO Auto-generated constructor stub
		try{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:/comp/env/jdbc/oracle");
		}catch(NamingException e){
			System.err.println("����Ŭ ���� ����!!");
			e.printStackTrace();
		}
	}
	
	
	public ShopDTO getPDList(String id){
		  
		   ShopDTO dtos = new ShopDTO();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from pdinfo where pdnum = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				
				return dtos;
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dtos;
		
	}
	
	
	public ArrayList<ShopDTO> getAllPDList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");
		
			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
			
		
			rs = pstmt.executeQuery();
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	public ArrayList<ShopDTO> getAllPDList1(){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from pdinfo order by pdnum desc");
		
		
			rs = pstmt.executeQuery();
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	public int addPD(String pdname, int countpd, int category,int price, String img, String info, String color, String pdsize ){
			int b=0;
			int result = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("insert into pdinfo values(pdinfo_num.nextval,?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1, pdname);
			pstmt.setInt(2, countpd);
			pstmt.setInt(3, category);
			pstmt.setInt(4, price);
			pstmt.setString(5, img);
			pstmt.setString(6, info);
			pstmt.setString(7, color);
			pstmt.setString(8, pdsize);

			b =  pstmt.executeUpdate();
			if(b == 1){
				result = 1;
			}else if(b != 1){
				result = 0;
			}
			System.out.println("��ǰ �߰�.....");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return result;
	}
	
	public ArrayList<ShopDTO> getTopList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo where category = 2 order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");
			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	
	public ArrayList<ShopDTO> getHatList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo where category = 1 order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");

			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
					rs = pstmt.executeQuery();
			
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	
	public ArrayList<ShopDTO> getShoesList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo where category = 4 order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");
			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	public ArrayList<ShopDTO> getPantsList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo where category = 3 order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");
			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	
	public ArrayList<ShopDTO> getAccList(Criteria cri){
		  
		   ShopDTO dtos = new ShopDTO();
		   ArrayList<ShopDTO> dto = new ArrayList<ShopDTO>();
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select pdinfo.* from (select rownum as rnum, pdinfo.* from pdinfo where category = 5 order by rnum asc)pdinfo where rnum >=? and rnum <= ? order by pdnum desc");
			pstmt.setInt(1,cri.getPageStart()+cri.getPerPageNum()-9);
			pstmt.setInt(2,cri.getPageStart()+cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int pdnum = rs.getInt("pdnum");
				String pdname = rs.getString("pdname");
				int countpd = rs.getInt("countpd");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				String img = rs.getString("img");
				String info = rs.getString("info");
				String color = rs.getString("color");
				String pdsize = rs.getString("pdsize");
				
				dtos= new ShopDTO(pdnum, pdname, countpd, category, price, img, info, color, pdsize);
				dto.add(dtos);
				
				


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return dto;
		
	}
	
	
	
	
	public int totalShop(){
		String sql = "select count(*) from pdinfo";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public int totalHat(){
		String sql = "select count(*) from pdinfo where category = 1";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public int totalTop(){
		String sql = "select count(*) from pdinfo where category = 2";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public int totalPants(){
		String sql = "select count(*) from pdinfo where category = 3";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public int totalShoes(){
		String sql = "select count(*) from pdinfo where category = 4";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public int totalAcc(){
		String sql = "select count(*) from pdinfo where category = 5";
		int result=0;
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			if(rs.next()){
			result = rs.getInt(1);
			}
		}catch(SQLException e){
			System.err.println("listBoard �޼ҵ� ���� �� ���� �߻�!!");
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
}
