package com.doArtShow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doArtShow.dto.ExhibitionDto;
import com.doArtShow.dto.TagDto;

// 전시회 정보 dao
public class ExhibitionDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds){
		this.ds = ds;
	}
	// 검색어로 전시 검색 실행 메소드
	public ArrayList<ExhibitionDto> searchExhibition(String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<ExhibitionDto> list =null;
		
		
		try {
			conn = ds.getConnection();
			System.out.println("일단 들어왔음");
			sql  = 	" SELECT ExhID, ExhPlaceAddr1, ExhName , ArtistName , ExhPlace,  ExhStartDate , ExhEndDate , ImageFile1 FROM artshowdb.artshow WHERE ( ExhName like ? OR ArtistName like ? OR ExhPlace like ? ) AND ActiveFlag='N' GROUP BY ExhID ORDER BY ExhEndDate DESC ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			System.out.println("sql문 세팅!");
			rs = pstmt.executeQuery();
			System.out.println("sql문 실행!!됨!");
			list = new ArrayList<ExhibitionDto>();
			ExhibitionDto dto =null;
			
			while(rs.next()){
				dto = new ExhibitionDto();
				dto.setExhID(rs.getInt("ExhID"));
				dto.setExhPlaceAddr1(rs.getString("ExhPlaceAddr1"));
				dto.setExhName(rs.getString("ExhName"));
				dto.setArtistName(rs.getString("ArtistName"));
				dto.setExhPlace(rs.getString("ExhPlace"));
				dto.setExhStartDate(rs.getString("ExhStartDate"));
				dto.setExhEndDate(rs.getString("ExhEndDate"));
				dto.setImageFile1(rs.getString("ImageFile1"));
				list.add(dto);
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if (rs != null) rs.close();} catch(Exception e) {}
				try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
				try {if (conn != null) conn.close();} catch(Exception e) {}
			}
		
		return list;
		
	}
	
	//지도 페이지 넘어가는 처리
	public ArrayList<ExhibitionDto> searchMapExhibition() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<ExhibitionDto> lists =null;
		
		
		try {
			conn = ds.getConnection();
			sql  = 	" SELECT ExhID, ExhPlaceAddr1, ExhName , ArtistName , ExhPlace,  ExhStartDate , ExhEndDate , ImageFile1 FROM artshowdb.artshow WHERE ActiveFlag='N' GROUP BY ExhID ORDER BY ExhEndDate DESC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			lists = new ArrayList<ExhibitionDto>();
			ExhibitionDto dto =null;
			
			while(rs.next()){
				dto = new ExhibitionDto();
				dto.setExhID(rs.getInt("ExhID"));
				dto.setExhPlaceAddr1(rs.getString("ExhPlaceAddr1"));
				dto.setExhName(rs.getString("ExhName"));
				dto.setArtistName(rs.getString("ArtistName"));
				dto.setExhPlace(rs.getString("ExhPlace"));
				dto.setExhStartDate(rs.getString("ExhStartDate"));
				dto.setExhEndDate(rs.getString("ExhEndDate"));
				dto.setImageFile1(rs.getString("ImageFile1"));
				lists.add(dto);
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if (rs != null) rs.close();} catch(Exception e) {}
				try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
				try {if (conn != null) conn.close();} catch(Exception e) {}
			}
		
		return lists;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//전시목록을 출력하는 메서드(전체보기, 내림차순)
	public List<ExhibitionDto> selectList() throws Exception{
		System.out.println("##4번 ExhibitionDao실행 - selectList()");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT exhID, imageFile1, exhName, exhPlace, exhStartDate, exhEndDate FROM artshow ORDER BY exhID ASC";
		ArrayList<ExhibitionDto> lists = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			lists = new ArrayList<ExhibitionDto>();
			ExhibitionDto art = null;
			
			while(rs.next()) {
				art = new ExhibitionDto();
				
				art.setExhID(rs.getInt("exhID"));
				art.setImageFile1(rs.getString("imageFile1"));
				art.setExhName(rs.getString("exhName"));
				art.setExhPlace(rs.getString("exhPlace"));
				art.setExhStartDate(rs.getString("exhStartDate"));
				art.setExhEndDate(rs.getString("exhEndDate"));
				
				lists.add(art);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
		    try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
		    try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
		return lists;
		
	}//end - public List<ExhibitionDto> selectList() throws Exception{
	
	//전시 총 갯수를 구하는 메서드(artShow테이블의 레코드 건수를 구함)
	public int getListCount() throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCnt = 0;
		String sql = "SELECT count(*) FROM artshow";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				listCnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
		    try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
		    try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
		return listCnt;
		
	}//end - public int getListCount() throws Exception{
	
	//리스트 목록의 content를 불러오는 메서드
	public ExhibitionDto selectOne(int exhID) throws Exception{
		System.out.println("##4-2번 ExhibitionDao실행 - selectOne()");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ExhibitionDto content = null;
		String sql = "SELECT * FROM artshow WHERE exhID=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, exhID);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				content = new ExhibitionDto();
				
				content.setExhID(rs.getInt("exhID"));
				content.setExhGubun1(rs.getString("exhGubun1"));
				content.setExhGubun2(rs.getString("exhGubun2"));
				//for문으로 배열에 있는 exhGubun3 빼주기
//				for(int i=0;i<rs.getString("exhGubun3").length();i++){
//					content.setExhGubun3();
//				}
				content.setExhGubun4(rs.getString("exhGubun4"));
				content.setExhName(rs.getString("exhName"));
				content.setExhGubun1(rs.getString("exhGubun1"));
				content.setArtistName(rs.getString("artistName"));
				content.setArtistInfo(rs.getString("artistInfo"));
				content.setExhContent(rs.getString("exhContent"));
				content.setExhPlace(rs.getString("exhPlace"));
				content.setExhUrl(rs.getString("exhUrl"));
				content.setExhGubun1(rs.getString("exhGubun1"));
				content.setExhPlaceAddr1(rs.getString("exhPlaceAddr1"));
				content.setExhStartDate(rs.getString("exhStartDate"));
				content.setExhEndDate(rs.getString("exhEndDate"));
				content.setOpTime(rs.getString("opTime"));
				content.setTel(rs.getString("tel"));
				content.setAdmFee(rs.getString("admFee"));
				content.setImageFile1(rs.getString("imageFile1"));
				content.setImageFile2(rs.getString("imageFile2"));
				//imageFile3과 4는 null값일 경우를 처리해줘야 함 -> 데이터를 뽑을 때 할것!!
				content.setImageFile3(rs.getString("imageFile3"));
				content.setImageFile4(rs.getString("imageFile4"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {if (rs != null) rs.close();} catch(Exception e) {}
		    try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
		    try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
		return content;
		
	}//end - public ExhibitionDto selectOne(int exhID) throws Exception{
	
	//조회수 증가하는 메서드
	public void updateReadCount(int exhID){
		System.out.println("##4-1번 ExhibitionDao실행 - updateReadCount()");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE artshow SET exhReadCount=exhReadCount+1 WHERE exhID=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setInt(1, exhID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
		    try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
	}//end - public void updateReadCount(int exhID){
	
	
//-------------------------------------------------------------------------------------------------------------
//	programmed by Hojeong - begin
//-------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
//	public void insertExhibition(ExhibitionDto exhibition) - begin
// 	exhibition insert - 전시회 등록	
//-------------------------------------------------------------------------------------------------------------	
	public void insertExhibition(ExhibitionDto exhibition) {
		System.out.println("insertExhibition - Dao");
		Connection 			conn 		= null;
		PreparedStatement 	pstmt 	= null;
		ResultSet 				rs 			= null;
		int							num 		= 0;
		String 					sql 		= null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(exhID) from artshow");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}else{
				num = 1;
			}
			
			sql = "INSERT INTO artshow(	exhID, "
					+ "							memberID, "
					+ "							exhGubun1, "
					+ "							exhGubun2, "
					+ "							exhGubun4, "
					+ "							exhName, "
					+ "							artistName, "
					+ "							artistInfo, "
					+ "							exhContent, "
					+ "							exhPlace, "
					+ "						  	exhPlaceZip, "
					+ "							exhPlaceAddr1, "
					+ "							exhPlaceAddr2, "
					+ "							exhUrl, "
					+ "							exhStartDate, "
					+ "							exhEndDate, "
					+ "							opTime, "
					+ "							tel, "
					+ "							admFee, "
					+ "							imageFile1, "
					+ "							imageFile2, "
					+ "							imageFile3, "
					+ "							imageFile4, "
					+ "							exhReadCount, "
					+ "							registerDate, "
					+ "							activeFlag) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?, "
				+ "			?,?,?,?,?,?,?,?,?,?, "
				+ "			?,?,?,?,?,? ) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, exhibition.getMemberID());
			pstmt.setString(3, exhibition.getExhGubun1());
			pstmt.setString(4, exhibition.getExhGubun2());
			pstmt.setString(5, exhibition.getExhGubun4());
			pstmt.setString(6, exhibition.getExhName());
			pstmt.setString(7, exhibition.getArtistName());
			pstmt.setString(8, exhibition.getArtistInfo());
			pstmt.setString(9, exhibition.getExhContent());
			pstmt.setString(10, exhibition.getExhPlace());
			//pstmt.setString(11, exhibition.getExhPlaceZip());
			pstmt.setString(12, exhibition.getExhPlaceAddr1());
			//pstmt.setString(13, exhibition.getExhPlaceAddr2());
			pstmt.setString(11, "001-001");
			//pstmt.setString(12, "addr1");
			pstmt.setString(13, "addr2");
			pstmt.setString(14, exhibition.getExhUrl());
			pstmt.setString(15, exhibition.getExhStartDate());
			pstmt.setString(16, exhibition.getExhEndDate());
			pstmt.setString(17, exhibition.getOpTime());
			pstmt.setString(18,  exhibition.getTel());
			pstmt.setString(19, exhibition.getAdmFee());
			pstmt.setString(20, exhibition.getImageFile1());
			pstmt.setString(21, exhibition.getImageFile2());
			pstmt.setString(22, exhibition.getImageFile3());
			pstmt.setString(23, exhibition.getImageFile4());
			pstmt.setInt(24, 0);
			pstmt.setTimestamp(25, exhibition.getRegisterDate());
			pstmt.setString(26,  "N");
	
			pstmt.executeUpdate();
			
			sql = "INSERT INTO artshowtag ( exhid, tagname ) values ( ?,? ) ";
			for (int i=0; i<exhibition.getExhGubun3().length; i++) {
				System.out.println("dao%%% "+exhibition.getExhGubun3()[i]);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, exhibition.getExhGubun3()[i]);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(rs!=null)rs.close();} catch (Exception e) {}
			try {if(pstmt!=null)pstmt.close();} catch (Exception e) {}
			try {if(conn!=null)conn.close();} catch (Exception e) {}
		}
	}
//-------------------------------------------------------------------------------------------------------------
//	public void insertExhibition(ExhibitionDto exhibition) - end
//-------------------------------------------------------------------------------------------------------------		
//-------------------------------------------------------------------------------------------------------------
//	public void updateExhibition(ExhibitionDto exhibition) - begin
//	exhibition update - 내가 등록한 전시회 수정	
//-------------------------------------------------------------------------------------------------------------			
	public void updateExhibition(ExhibitionDto exhibition) {
		System.out.println("updateExhibition - Dao");
		Connection 			conn 		= null;
		PreparedStatement 	pstmt 	= null;
		ResultSet 				rs 			= null;
		int							num 		= 0;
		String 					sql 		= null;
		
		try {
			conn = ds.getConnection();
			sql = "UPDATE artshow set	 	exhGubun1 	=?, "
					+ "							exhGubun2 	=?, "
					+ "							exhGubun4 	=?, "
					+ "							exhName 		=?, "
					+ "							artistName 		=?, "
					+ "							artistInfo 		=?, "
					+ "							exhContent 	=?, "
					+ "							exhPlace 		=?, "
					+ "						  	exhPlaceZip 	=?, "
					+ " 							exhPlaceAddr1 =?, "
					+ "							exhPlaceAddr2 =?, "
					+ "							exhUrl 			=?, "
					+ "							exhStartDate 	=?, "
					+ "							exhEndDate 	=?, "
					+ "							opTime 			=?, "
					+ "							tel 				=?, "
					+ "							admFee 			=?, "
					+ "							imageFile1 		=?, "
					+ "							imageFile2 		=?, "		// modified by Hojeong (20/01/03) ; 맨끝에 쉼표 추가함
					+ "							imageFile3 		=?, "
					+ "							imageFile4 		=? "
					+ "							WHERE exhID 	=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exhibition.getExhGubun1());
			pstmt.setString(2, exhibition.getExhGubun2());
			pstmt.setString(3, exhibition.getExhGubun4());
			pstmt.setString(4, exhibition.getExhName());
			pstmt.setString(5, exhibition.getArtistName());
			pstmt.setString(6, exhibition.getArtistInfo());
			pstmt.setString(7, exhibition.getExhContent());
			pstmt.setString(8, exhibition.getExhPlace());
			pstmt.setString(9, "001-001");
			//pstmt.setString(9, exhibition.getExhPlaceZip());
			pstmt.setString(10, exhibition.getExhPlaceAddr1());
			//pstmt.setString(10, "addr1");
			//pstmt.setString(11, exhibition.getExhPlaceAddr2());
			pstmt.setString(11, "addr2");
			pstmt.setString(12, exhibition.getExhUrl());
			pstmt.setString(13, exhibition.getExhStartDate());
			pstmt.setString(14, exhibition.getExhEndDate());
			pstmt.setString(15, exhibition.getOpTime());
			pstmt.setString(16,  exhibition.getTel());
			pstmt.setString(17, exhibition.getAdmFee());
			pstmt.setString(18, exhibition.getImageFile1());
			pstmt.setString(19, exhibition.getImageFile2());
			pstmt.setString(20, exhibition.getImageFile3());
			pstmt.setString(21, exhibition.getImageFile4());
			pstmt.setInt(22, exhibition.getExhID());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement( " DELETE FROM artshowtag WHERE exhID = ? ");
			pstmt.setInt(1, exhibition.getExhID());
			pstmt.executeUpdate();
			
			sql = "INSERT INTO artshowtag ( exhid, tagname ) values ( ?,? ) ";
			for (int i=0; i<exhibition.getExhGubun3().length; i++) {
				System.out.println("dao%%% "+exhibition.getExhGubun3()[i]);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, exhibition.getExhID());
				pstmt.setString(2, exhibition.getExhGubun3()[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
				try {if(pstmt!=null)pstmt.close();} catch (Exception e) {}
				try {if(conn!=null)conn.close();} catch (Exception e) {}
		}
	}
//-------------------------------------------------------------------------------------------------------------
//	public void updateExhibition(ExhibitionDto exhibition) - end
//-------------------------------------------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------
//	public ExhibitionDto selectExhibition(Integer exhID) - begin
//  내가 등록한 전시회 상세 정보 	
//-------------------------------------------------------------------------------------------------------------		
	public ExhibitionDto selectExhibition(Integer exhID) {
		System.out.println("selectExhibition - Dao");
		Connection 			conn 		= null;
		PreparedStatement 	pstmt 	= null;
		ResultSet 				rs 			= null;
		int							num 		= 0;
		String 					sql 		= null;
		ExhibitionDto exhibition = null;
		
		try {
			conn = ds.getConnection();
			pstmt =	conn.prepareStatement(" select * from artshow where exhID = ? and activeFlag = 'N'");
			pstmt.setInt(1, exhID);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("rs.next()");
				exhibition = new ExhibitionDto();
				exhibition.setExhID(rs.getInt("exhID"));
				exhibition.setMemberID(rs.getString("memberID"));
				exhibition.setExhGubun1(rs.getString("exhGubun1"));
				exhibition.setExhGubun2(rs.getString("exhGubun2"));
				exhibition.setExhGubun4(rs.getString("exhGubun4"));
				exhibition.setExhName(rs.getString("exhName"));
				exhibition.setArtistName(rs.getString("artistName"));
				exhibition.setArtistInfo(rs.getString("artistInfo"));
				exhibition.setExhContent(rs.getString("exhContent"));
				exhibition.setExhPlace(rs.getString("exhPlace"));
				exhibition.setExhPlaceAddr1(rs.getString("exhPlaceAddr1"));
				exhibition.setExhUrl(rs.getString("exhUrl"));
				exhibition.setExhStartDate(rs.getString("exhStartDate"));
				exhibition.setExhEndDate(rs.getString("exhEndDate"));
				exhibition.setOpTime(rs.getString("opTime"));
				exhibition.setTel(rs.getString("tel"));
				exhibition.setAdmFee(rs.getString("admFee"));
				exhibition.setImageFile1(rs.getString("imageFile1"));
				exhibition.setImageFile2(rs.getString("imageFile2"));
				exhibition.setImageFile3(rs.getString("imageFile3"));	//added by Hojeong 20/01/03
				exhibition.setImageFile4(rs.getString("imageFile4"));	//added by Hojeong 20/01/03
				exhibition.setRegisterDate(rs.getTimestamp("registerDate"));
				exhibition.setActiveFlag(rs.getString("activeFlag"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(rs!=null)rs.close();} catch (Exception e) {}
			try {if(pstmt!=null)pstmt.close();} catch (Exception e) {}
			try {if(conn!=null)conn.close();} catch (Exception e) {}
		}
		return exhibition;	
	}
//-------------------------------------------------------------------------------------------------------------
//	public ExhibitionDto selectExhibition(Integer exhID) - end
//-------------------------------------------------------------------------------------------------------------		
//-------------------------------------------------------------------------------------------------------------
//	public ArrayList<TagDto> getTagList(Integer exhID) - begin
//	내가 등록한 전시회 tagList 가져오기	
//-------------------------------------------------------------------------------------------------------------		
	public ArrayList<TagDto> getTagList(Integer exhID) {
		System.out.println("getTags - Dao");
		Connection 			conn 		= null;
		PreparedStatement 	pstmt 	= null;
		ResultSet 				rs 			= null;
		int							count		= 0;
		String 					sql 		= null;
		String[] tags = null;
		ExhibitionDto exhibition = new ExhibitionDto();
		ArrayList<TagDto> tagList = new ArrayList<TagDto>();
		TagDto tag = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(" select count(*) from artshow, artshowTag "
					+ " where artshow.exhID = artshowTag.exhID and artshow.exhID=? ");
			pstmt.setInt(1, exhID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) count = rs.getInt(1);
	
			pstmt =	conn.prepareStatement(" select * from artshow, artshowTag "
					+ " where artshow.exhID = artshowTag.exhID and artshow.exhID=? ");
			pstmt.setInt(1, exhID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				tag = new TagDto();
				tag.setExhID(rs.getInt("artshow.exhID"));
				tag.setExhTagName(rs.getString("tagName"));
				tagList.add(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(rs!=null)rs.close();} catch (Exception e) {}
			try {if(pstmt!=null)pstmt.close();} catch (Exception e) {}
			try {if(conn!=null)conn.close();} catch (Exception e) {}
		}
		
		return tagList;
	}
//-------------------------------------------------------------------------------------------------------------
//	public ArrayList<TagDto> getTagList(Integer exhID) - end
//	내가 등록한 전시회 tagList 가져오기	
//-------------------------------------------------------------------------------------------------------------		
//-------------------------------------------------------------------------------------------------------------
//	public List<ExhibitionDto> selectExhibitionMyList(String id) - begin
//	마이 페이지에서 내가 등록한 전시회 리스트 보기 
//-------------------------------------------------------------------------------------------------------------
	public List<ExhibitionDto> selectExhibitionMyList(String id) {
		System.out.println("selectExhibitionMyList - Dao" + id);
		Connection 			conn 		= null;
		PreparedStatement 	pstmt 	= null;
		ResultSet 				rs 			= null;
		int							count		= 0;
		String 					sql 		= null;
		List<ExhibitionDto> exhibitionList = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(" select * from artshow where memberID=? ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				exhibitionList = new ArrayList<ExhibitionDto>();
				do {
					ExhibitionDto exhibition = new ExhibitionDto();
					exhibition.setExhID(rs.getInt("exhID"));
					exhibition.setMemberID(rs.getString("memberID"));
					exhibition.setExhGubun1(rs.getString("exhGubun1"));
					exhibition.setExhGubun2(rs.getString("exhGubun2"));
					exhibition.setExhGubun4(rs.getString("exhGubun4"));
					exhibition.setExhName(rs.getString("exhName"));
					exhibition.setArtistName(rs.getString("artistName"));
					exhibition.setArtistInfo(rs.getString("artistInfo"));
					exhibition.setExhContent(rs.getString("exhContent"));
					exhibition.setExhPlace(rs.getString("exhPlace"));
					exhibition.setExhPlaceAddr1(rs.getString("exhPlaceAddr1"));
					exhibition.setExhUrl(rs.getString("exhUrl"));
					exhibition.setExhStartDate(rs.getString("exhStartDate"));
					exhibition.setExhEndDate(rs.getString("exhEndDate"));
					exhibition.setOpTime(rs.getString("opTime"));
					exhibition.setTel(rs.getString("tel"));
					exhibition.setAdmFee(rs.getString("admFee"));
					exhibition.setImageFile1(rs.getString("imageFile1"));
					exhibition.setImageFile2(rs.getString("imageFile2"));
					exhibition.setImageFile3(rs.getString("imageFile3"));	//added by Hojeong 20/01/03
					exhibition.setImageFile4(rs.getString("imageFile4"));	//added by Hojeong 20/01/03
					exhibition.setRegisterDate(rs.getTimestamp("registerDate"));
					exhibition.setActiveFlag(rs.getString("activeFlag"));
					System.out.println("dao:"+exhibition.toString());
					exhibitionList.add(exhibition);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(rs!=null)rs.close();} catch (Exception e) {}
			try {if(pstmt!=null)pstmt.close();} catch (Exception e) {}
			try {if(conn!=null)conn.close();} catch (Exception e) {}
		}

		return exhibitionList;
	}
//-------------------------------------------------------------------------------------------------------------
//public List<ExhibitionDto> selectExhibitionMyList(String id) - end
//마이 페이지에서 내가 등록한 전시회 리스트 보기 
//-------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
//programmed by Hojeong - end
//-------------------------------------------------------------------------------------------------------------
	
	
// 메인페이지에서 슬라이드에 전시 리스트 보냄.
	public ArrayList<ExhibitionDto> indexExhibition() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<ExhibitionDto> list =null;
		
		try {
			conn = ds.getConnection();
			//최신등록순 9개
			sql  = 	" SELECT ExhID, ExhPlaceAddr1, ExhName , ArtistName , ExhPlace,  ExhStartDate , ExhEndDate , ImageFile1 FROM artshow ORDER BY RegisterDate DESC LIMIT 9 ";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, "%"+search+"%");
			//pstmt.setString(2, "%"+search+"%");
			//pstmt.setString(3, "%"+search+"%");
			rs = pstmt.executeQuery();
			list = new ArrayList<ExhibitionDto>();
			ExhibitionDto dto =null;
			
			while(rs.next()){
				dto = new ExhibitionDto();
				dto.setExhID(rs.getInt("ExhID"));
				dto.setExhPlaceAddr1(rs.getString("ExhPlaceAddr1"));
				dto.setExhName(rs.getString("ExhName"));
				dto.setArtistName(rs.getString("ArtistName"));
				dto.setExhPlace(rs.getString("ExhPlace"));
				dto.setExhStartDate(rs.getString("ExhStartDate"));
				dto.setExhEndDate(rs.getString("ExhEndDate"));
				dto.setImageFile1(rs.getString("ImageFile1"));
				list.add(dto);
				}
			pstmt.close();
			rs.close();
			
			sql="";
			dto=null;
			// 인기 조회순 9개
			sql = " SELECT ExhID, ExhPlaceAddr1, ExhName , ArtistName , ExhPlace,  ExhStartDate , ExhEndDate , ImageFile1 FROM artshowdb.artshow ORDER BY ExhReadCount DESC LIMIT 9 ";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				dto = new ExhibitionDto();
				dto.setExhID(rs.getInt("ExhID"));
				dto.setExhPlaceAddr1(rs.getString("ExhPlaceAddr1"));
				dto.setExhName(rs.getString("ExhName"));
				dto.setArtistName(rs.getString("ArtistName"));
				dto.setExhPlace(rs.getString("ExhPlace"));
				dto.setExhStartDate(rs.getString("ExhStartDate"));
				dto.setExhEndDate(rs.getString("ExhEndDate"));
				dto.setImageFile1(rs.getString("ImageFile1"));
				list.add(dto);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if (rs != null) rs.close();} catch(Exception e) {}
				try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
				try {if (conn != null) conn.close();} catch(Exception e) {}
			}
		
		return list;
		
	}
	
	
	
	//by jungmi
		//-------------------------------------------------------------------
		// 회원페이지(memberPage.jsp) 등록한 전시의 개수
		//-------------------------------------------------------------------
		public int countMyExh(String email) {
			Connection 			conn 	= null;
			PreparedStatement 	pstmt 	= null;
			ResultSet 			rs 		= null;
			String 				sql 	= "";
			
			int myExhCount = 0;
			
			try {
				conn 	= ds.getConnection();
				sql  	=  "SELECT  count(*) ";
				sql		+= "FROM	artshow ";
				sql		+= "WHERE   memberID=?	 ";
				
				pstmt 	= conn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs 		= pstmt.executeQuery();
				
				if(rs.next()) {
					myExhCount = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				if(rs    != null) try {rs.close();   } catch(SQLException ex) {}
				if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
				if(conn  != null) try {conn.close(); } catch(SQLException ex) {}
			}
			return myExhCount;
		}
	
	
	
	
}



