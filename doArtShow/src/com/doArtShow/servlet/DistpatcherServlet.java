package com.doArtShow.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.doArtShow.controls.Controller;
import com.doArtShow.controls.exhibition.ExhibitionListController;
import com.doArtShow.dao.MemberDao;
import com.doArtShow.dto.ExhibitionDto;
import com.doArtShow.dto.MemberDto;
import com.doArtShow.dto.ReviewDto;
import com.doArtShow.util.UploadUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet({"*.do", "/start","/NaverCallback"})
public class DistpatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service메소드는 doPost와 doGet을 모두 처리합니다.
		 	response.setContentType("text/html; charset=UTF-8");
		    String servletPath = request.getServletPath();
		    System.out.println("##1번 웹브라우저에서 DistpatcherServlet(프런트컨트롤러)에 요청");
		    System.out.println(servletPath);
		    
		    try {
		      ServletContext sc = this.getServletContext();
		      
		      HashMap<String,Object> model = new HashMap<String,Object>();
		      HttpSession session = request.getSession();
		      model.put("session", request.getSession());
		      
		      //로그인 후 이전 페이지로 가기 위해 Header를 사용
              model.put("Referer",request.getHeader("Referer"));
		      
		      //페이지컨트롤러는 ServletContext보관소에 저장되어있으므로 이 보관소에서 페이지컨트롤러를 꺼낼때 서블릿 URL을 사용한다
		      Controller pageController = (Controller) sc.getAttribute(servletPath);
		      
		      //--------------------------------------------------------------------------------------
			  //각자 추가하는 Controller에 따라서 수정될 수 있습니다.
		      
		      //--------------------------------------------------------------------------------------
		      //jungmi-start
		      //--------------------------------------------------------------------------------------
		      //회원 가입 시 이메일 중복확인
		      if("/client/auth/checkEmail.do".equals(servletPath)){
		             if(request.getParameter("email")!=null){
		                JSONObject jsonObj = new JSONObject();
		                model.put("jsonObj", jsonObj);
		                
		                model.put("checkEmailInfo", request.getParameter("email"));
		                 
		             }
		      //회원가입
		      } else if ("/client/auth/memberAdd.do".equals(servletPath)) {
		    	  if(request.getParameter("email")!=null){
		    		  model.put("member", new MemberDto()
		    				  .setEmail(request.getParameter("email"))
		    				  .setName(request.getParameter("name"))
		                      .setBirth(request.getParameter("birth"))
		                      .setGender(request.getParameter("gender"))
		                      .setPw(request.getParameter("pw")));
		    	  }
		      //회원 로그인   
		      } else if("/client/auth/memberLogIn.do".equals(servletPath)){
		    	  if(request.getParameter("email")!=null){
	                  model.put("loginInfo", new MemberDto()
	                        .setEmail(request.getParameter("email"))
	                        .setPw(request.getParameter("pw"))); 
	                                    
	               }
		      //회원정보 불러오기
		      } else if("/client/auth/memberDetail.do".equals(servletPath)){
		    	  if(session.getAttribute("member")!=null){
		    		  MemberDto member = (MemberDto)session.getAttribute("member");
		              model.put("email", member.getEmail()); 
		          }
		      //회원정보수정
		      } else if("/client/auth/memberUpdate.do".equals(servletPath)){
		    	  if(request.getParameter("birth")!=null){
		    		  model.put("member", new MemberDto()
		    				  .setBirth(request.getParameter("birth"))
		    				  .setGender(request.getParameter("gender"))
		    				  .setPw(request.getParameter("pw")));
		    	  }
		          MemberDto member = (MemberDto)session.getAttribute("member");
	              model.put("email", member.getEmail());
	    
	          //회원 프로필 사진 수정
		      } else if("/client/auth/profileImgUpdate.do".equals(servletPath)){    	  
		    	  String saveFolder = "/memberProfileImages";											 
		    	  int fileSize = 7 * 1024 * 1024;	
					
				  String[] filename = new String[1];
				  filename = UploadUtil.filesUpload(saveFolder, fileSize, request);
				  
				  model.put("profileImg", filename[0]);
		    	  
		      //이메일찾기
		      } else if("/client/auth/findEmail.do".equals(servletPath)){
		               if(request.getParameter("name")!=null) {
		                  model.put("findEmailInfo", new MemberDto()
		                        .setName(request.getParameter("name"))
		                        .setBirth(request.getParameter("birth")));
		               }
		      //비밀번호찾기     
		      } else if("/client/auth/findPw.do".equals(servletPath)){
		               if(request.getParameter("email")!=null) {
		                  model.put("findPwInfo", new MemberDto()
		                        .setEmail(request.getParameter("email"))
		                        .setBirth(request.getParameter("birth")));
		               }
		               
		      //리뷰 수정하기     
		      } else if("/client/auth/revUpdate.do".equals(servletPath)){
		    	  if(request.getParameter("exhID")!=null) {
		    		  MemberDto member = (MemberDto)session.getAttribute("member");
		    		  
		    		  model.put("revUpdateInfo", new ReviewDto()
		    				  .setEmail(member.getEmail())
		    				  .setExhID(Integer.parseInt(request.getParameter("exhID")))
		    				  .setRevContent(request.getParameter("revContent")));
		    	  }
		      //리뷰 삭제하기    
		      } else if("/client/auth/revDelete.do".equals(servletPath)){
		    	  if(request.getParameter("exhName")!=null) {
		    		  ExhibitionDto exhibitionDto = new ExhibitionDto();
		    		  System.out.println(request.getParameter("exhName"));
		    		  exhibitionDto.setExhName(request.getParameter("exhName"));
		    		  model.put("exhName",exhibitionDto);
		    		  
		    		  MemberDto member = (MemberDto)session.getAttribute("member");
		    		  model.put("logInInfo",member.getEmail());
		    	  }
		      	  
		      //--------------------------------------------------------------------------------------
		 	  //jungmi-end
		 	  //--------------------------------------------------------------------------------------
		 		      
		      } else if("/search.do".equals(servletPath)){
			    	  if(request.getParameter("search")!=null) {
			    		  
			    		  model.put("search", request.getParameter("search"));
			    	  }
			    	  
			  //--------------------------------------------------------------------------------------
			  //seran
			  //--------------------------------------------------------------------------------------
			  } else if("/client/exhibition/ExContentView.do".equals(servletPath)){ //전시정보 상세페이지 보기
		    	  if(request.getParameter("exhID") != null){
		    		  int exhID = Integer.parseInt(request.getParameter("exhID"));

		    		  model.put("exhID", exhID);
		    	  }
			  }else if("/client/exhibition/review.do".equals(servletPath)){ //리뷰 등록
				  if(request.getParameter("email") != null){
					  model.put("exhreview", new ReviewDto()
							  .setEmail(request.getParameter("email"))
							  .setExhID(Integer.parseInt(request.getParameter("exhID")))
							  .setRevContent(request.getParameter("revContent")));
				  }
		      //--------------------------------------------------------------------------------------
		  	  // begin - modified by Hojeong 20/01/03(yy/mm/dd)	
	    	  //--------------------------------------------------------------------------------------
		      // 전시회 등록 페이지 열기 	
		      } else if ("/client/exhibition/addForm.do".equals(servletPath)) {
	
	    	  // 전시회 등록 	  
		  	  } else if ("/client/exhibition/add.do".equals(servletPath)) {
		  		  
					String saveFolder = "/exhibitionImages";											 
					int fileSize = 7 * 1024 * 1024;	
					
					String[] filename = new String[4];
					// multipartRequest를 위한 filesUpload(다중 파일 upload) method를 호출한다.  		
					filename = UploadUtil.filesUpload(saveFolder, fileSize, request);
									
	  				ExhibitionDto exhibitionDto = new ExhibitionDto();
	  				// exhibitionDto.setExhID(Integer.parseInt(multi.getParameter("exhID")));
	  				exhibitionDto.setMemberID(UploadUtil.multi.getParameter("memberID"));
	  				exhibitionDto.setExhGubun1(UploadUtil.multi.getParameter("exhGubun1"));
	  				exhibitionDto.setExhGubun2(UploadUtil.multi.getParameter("exhGubun2"));
	  				exhibitionDto.setExhGubun4(UploadUtil.multi.getParameter("exhGubun4"));
	  				exhibitionDto.setExhName(UploadUtil.multi.getParameter("exhName"));
	  				exhibitionDto.setArtistName(UploadUtil.multi.getParameter("artistName"));
	  				exhibitionDto.setArtistInfo(UploadUtil.multi.getParameter("artistInfo"));
	  				exhibitionDto.setExhContent(UploadUtil.multi.getParameter("exhContent"));
	  				exhibitionDto.setExhPlace(UploadUtil.multi.getParameter("exhPlace"));
	  				exhibitionDto.setExhPlaceAddr1(UploadUtil.multi.getParameter("exhPlaceAddr1"));
	  				exhibitionDto.setExhUrl(UploadUtil.multi.getParameter("exhUrl"));
	  				exhibitionDto.setExhStartDate(UploadUtil.multi.getParameter("exhStartDate"));
	  				exhibitionDto.setExhEndDate(UploadUtil.multi.getParameter("exhEndDate"));
	  				exhibitionDto.setOpTime(UploadUtil.multi.getParameter("opTime"));
	  				exhibitionDto.setTel(UploadUtil.multi.getParameter("tel1") + "-" + UploadUtil.multi.getParameter("tel2") + "-"
	  						+ UploadUtil.multi.getParameter("tel3"));
	  				exhibitionDto.setAdmFee(UploadUtil.multi.getParameter("admFee"));
	  				exhibitionDto.setRegisterDate(new Timestamp(System.currentTimeMillis()));
	  				
	  				String exhGubun3[] = UploadUtil.multi.getParameterValues("exhGubun3");
	  				for (int i = 0; i < exhGubun3.length; i++) {
	  					System.out.println(exhGubun3[i]);
	  				}
	  				exhibitionDto.setExhGubun3(exhGubun3);
	  				for (int i = 0; i < exhibitionDto.getExhGubun3().length; i++) {
	  					System.out.println("exhGubun3%%% " + exhibitionDto.getExhGubun3()[i]);
	  				}
	
	  				// if(filename == null) filename = "nothing.jpg";
	  				exhibitionDto.setImageFile1(filename[3]);
	  				exhibitionDto.setImageFile2(filename[2]);
	  				exhibitionDto.setImageFile3(filename[1]);
	  				exhibitionDto.setImageFile4(filename[0]);
	
	  				System.out.println("###" + exhibitionDto.toString());
	
	  				model.put("exhibition", exhibitionDto);
	  				
	  			// 전시회 수정 	
	  			} else if ("/client/exhibition/update.do".equals(servletPath)) {
	  				
	  				if (request.getParameter("exhID") != null) {
	  					System.out.println("$$$" + request.getParameter("exhID"));
	  					model.put("exhID", request.getParameter("exhID"));
	  				} else {
						String saveFolder = "/exhibitionImages";											 
						int fileSize = 7 * 1024 * 1024;													 
	
						String[] filename = new String[4];
						// multipartRequest를 위한 filesUpload(다중 파일 upload) method를 호출한다.  
						filename = UploadUtil.filesUpload(saveFolder, fileSize, request);
						
	  					ExhibitionDto exhibitionDto = new ExhibitionDto();
	  					// if(request.getParameter("id")!=null){
	  					exhibitionDto.setExhID(Integer.parseInt(UploadUtil.multi.getParameter("exhID")));
	  					exhibitionDto.setMemberID(UploadUtil.multi.getParameter("memberID"));
	  					exhibitionDto.setExhGubun1(UploadUtil.multi.getParameter("exhGubun1"));
	  					exhibitionDto.setExhGubun2(UploadUtil.multi.getParameter("exhGubun2"));
	  					exhibitionDto.setExhGubun4(UploadUtil.multi.getParameter("exhGubun4"));
	  					exhibitionDto.setExhName(UploadUtil.multi.getParameter("exhName"));
	  					exhibitionDto.setArtistName(UploadUtil.multi.getParameter("artistName"));
	  					exhibitionDto.setArtistInfo(UploadUtil.multi.getParameter("artistInfo"));
	  					exhibitionDto.setExhContent(UploadUtil.multi.getParameter("exhContent"));
	  					exhibitionDto.setExhPlace(UploadUtil.multi.getParameter("exhPlace"));
	  					exhibitionDto.setExhPlaceAddr1(UploadUtil.multi.getParameter("exhPlaceAddr1"));
	  					exhibitionDto.setExhUrl(UploadUtil.multi.getParameter("exhUrl"));
	  					exhibitionDto.setExhStartDate(UploadUtil.multi.getParameter("exhStartDate"));
	  					exhibitionDto.setExhEndDate(UploadUtil.multi.getParameter("exhEndDate"));
	  					exhibitionDto.setOpTime(UploadUtil.multi.getParameter("opTime"));
	  					exhibitionDto.setTel(UploadUtil.multi.getParameter("tel1") + "-" + UploadUtil.multi.getParameter("tel2") + "-"
	  							+ UploadUtil.multi.getParameter("tel3"));
	  					exhibitionDto.setAdmFee(UploadUtil.multi.getParameter("admFee"));
	  					// exhibitionDto.setRegisterDate(new
	  					// Timestamp(System.currentTimeMillis()));
	  					String exhGubun3[] = UploadUtil.multi.getParameterValues("exhGubun3");
	
	  					for (int i = 0; i < exhGubun3.length; i++) {
	  						System.out.println(exhGubun3[i]);
	  					}
	  					exhibitionDto.setExhGubun3(exhGubun3);
	  					for (int i = 0; i < exhibitionDto.getExhGubun3().length; i++) {
	  						System.out.println("exhGubun3%%% " + exhibitionDto.getExhGubun3()[i]);
	  					}
	
	  					// if(filename == null) filename = "nothing.jpg";
	  					exhibitionDto.setImageFile1(filename[3]);
	  					exhibitionDto.setImageFile2(filename[2]);
	  					exhibitionDto.setImageFile3(filename[1]);
	  					exhibitionDto.setImageFile4(filename[0]);
	
	  					System.out.println("###" + exhibitionDto.toString());
	
	  					model.put("exhibition", exhibitionDto);
					}
  			// 마이(회원)페이지에서 내가 등록한 전시회 목록 보기 	
  			} else if ("/client/exhibition/myList.do".equals(servletPath)) {
	  				model.put("id", "id01");
		    }
	      //--------------------------------------------------------------------------------------
		  // end - modified by Hojeong 20/01/03(yy/mm/dd)	
	      //--------------------------------------------------------------------------------------
		      
		      
		      //--------------------------------------------------------------------------------------

		      
		      // 페이지 컨트롤러를 실행한다.
		      System.out.println("##2번 페이지컨트롤러 호출");
		      String viewUrl = pageController.execute(model); //페이지 컨트롤러의 execute()메서드로 이동하며 데이터를 주고받을 바구니 역할을 하는 Map객체(model)를 넘긴다
		      System.out.println(viewUrl);
		      //viewUrl은 execute()의 반환값으로 화면에 출력을 수행할 JSP의 URL이다
		      
		      // Map 객체에 저장된 값을 ServletRequest에 복사한다.
		      for (String key : model.keySet()) {
		        request.setAttribute(key, model.get(key));
		      }
		      
		      //view(JSP)로 실행을 위임하거나 리다이렉트한다
		      if (viewUrl.startsWith("redirect:")) {
		        response.sendRedirect(viewUrl.substring(9));
		        return;
		        
		      } else if (viewUrl.startsWith("json:")) {
		    	  response.setContentType("json:application/json");
		    	  response.getWriter().print(viewUrl.substring(5));
			      return;
			      
			  } else {
		        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
		        rd.include(request, response);
		      }
		      
		    } catch(Exception e){
		    	 e.printStackTrace();
		         request.setAttribute("error", e);
		         RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
		         rd.forward(request, response);
		    }
	
}
}

