package com.doArtShow.listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.doArtShow.controls.exhibition.ExhibitionAddController;
import com.doArtShow.controls.exhibition.ExhibitionAddFormController;
import com.doArtShow.controls.exhibition.ExhibitionContentController;
import com.doArtShow.controls.exhibition.ExhibitionListController;
import com.doArtShow.controls.exhibition.ExhibitionMyListController;
import com.doArtShow.controls.exhibition.ExhibitionReviewFormController;
import com.doArtShow.controls.exhibition.ExhibitionUpdateController;
import com.doArtShow.controls.exhibition.searchListController;
import com.doArtShow.controls.exhibition.searchMapController;
import com.doArtShow.controls.member.FindEmailController;
import com.doArtShow.controls.member.FindPwController;
import com.doArtShow.controls.member.IndexController;
import com.doArtShow.controls.member.MemProfileUpdateController;
import com.doArtShow.controls.member.MemRevDeleteController;
import com.doArtShow.controls.member.MemRevUpdateController;
import com.doArtShow.controls.member.MemReviewListController;
import com.doArtShow.controls.member.MemVisitListController;
import com.doArtShow.controls.member.MemWishListController;
import com.doArtShow.controls.member.MemberAddController;
import com.doArtShow.controls.member.MemberDeleteController;
import com.doArtShow.controls.member.MemberDetailController;
import com.doArtShow.controls.member.MemberEmailChkController;
import com.doArtShow.controls.member.MemberLogInController;
import com.doArtShow.controls.member.MemberLogOutController;
import com.doArtShow.controls.member.MemberPageController;
import com.doArtShow.controls.member.MemberUpdateController;
import com.doArtShow.controls.member.NaverLoginController;
import com.doArtShow.dao.ExhibitionDao;
import com.doArtShow.dao.ManagerDao;
import com.doArtShow.dao.MemberDao;
import com.doArtShow.dao.ReviewDao;
import com.doArtShow.dao.VisitListDao;
import com.doArtShow.dao.WishListDao;

// datasource 주입, 컨트롤러 객체에 dao주입
@WebListener
public class ContextLoaderListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextLoaderListener");
	    try {
	    	ServletContext sc = event.getServletContext();
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/artshowdb");
			
			// 각 dao마다 datasource를 주입합니다.
			ExhibitionDao exhibitionDao = new ExhibitionDao();
			exhibitionDao.setDataSource(ds);
			
			ManagerDao managerDao = new ManagerDao();
			managerDao.setDataSource(ds);
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			
			ReviewDao reviewDao = new ReviewDao();
			reviewDao.setDataSource(ds);
			
			VisitListDao visitListDao = new VisitListDao();
			visitListDao.setDataSource(ds);
			
			WishListDao wishListDao = new WishListDao();
			wishListDao.setDataSource(ds);
			
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by jungmi - begin
			//-------------------------------------------------------------------------------------------------------------			
			sc.setAttribute("/client/auth/checkEmail.do", 	new MemberEmailChkController().setMemberDao(memberDao) );
			
			//회원 가입(추가)
			sc.setAttribute("/client/auth/memberAdd.do", 	new MemberAddController().setMemberDao(memberDao) );
			
			//회원 로그인
			sc.setAttribute("/client/auth/memberLogIn.do", 	new MemberLogInController().setMemberDao(memberDao));
			
			//회원 계정 보여주기
			sc.setAttribute("/client/auth/memberDetail.do", new MemberDetailController().setMemberDao(memberDao));
			
			//회원 로그아웃
			sc.setAttribute("/client/auth/memberLogOut.do", new MemberLogOutController().setMemberDao(memberDao));
			
			//회원 정보 수정
			sc.setAttribute("/client/auth/memberUpdate.do", new MemberUpdateController().setMemberDao(memberDao));
			
			//회원 정보 수정
			sc.setAttribute("/client/auth/profileImgUpdate.do", new MemProfileUpdateController().setMemberDao(memberDao));
			
			//이메일 찾기
			sc.setAttribute("/client/auth/findEmail.do", 	new FindEmailController().setMemberDao(memberDao));
			
			//비밀번호 찾기
			sc.setAttribute("/client/auth/findPw.do", 		new FindPwController().setMemberDao(memberDao));
			
			//회원 탈퇴 하기
			sc.setAttribute("/client/auth/memberDelete.do", new MemberDeleteController().setMemberDao(memberDao));
						
			//회원페이지 구성
			sc.setAttribute("/client/auth/memberPage.do", 	new MemberPageController().setMemberDao(memberDao));
			
			//회원 리뷰 목록 구성
			sc.setAttribute("/client/auth/reviewList.do", 	new MemReviewListController().setMemberDao(memberDao));
			
			//회원 가고 싶은 전시 목록 구성
			sc.setAttribute("/client/auth/wishList.do", 	new MemWishListController().setMemberDao(memberDao));
			
			//회원 다녀온 전시 목록 구성
			sc.setAttribute("/client/auth/visitList.do", 	new MemVisitListController().setMemberDao(memberDao));
									
			//회원 다녀온 전시 목록 구성
			sc.setAttribute("/client/auth/revUpdate.do", 	new MemRevUpdateController().setMemberDao(memberDao));
			
			//회원 다녀온 전시 목록 구성
			sc.setAttribute("/client/auth/revDelete.do", 	new MemRevDeleteController().setMemberDao(memberDao));
			
			// 검색기능
			sc.setAttribute("/search.do", new searchListController().setExhibitionDao(exhibitionDao));

			// 지도 페이지
			sc.setAttribute("/searchMap.do", new searchMapController().setExhibitionDao(exhibitionDao));
			
			sc.setAttribute("/start", new IndexController().setExhibitionDao(exhibitionDao));
						 
			sc.setAttribute("/NaverCallback", new NaverLoginController());
			
			
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by jungmi- end
			//-------------------------------------------------------------------------------------------------------------						
			
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by seran - begin
			//-------------------------------------------------------------------------------------------------------------
			
			//전시 목록 
			sc.setAttribute("/client/exhibition/ExListView.do", 		new ExhibitionListController().setExhibitionDao(exhibitionDao));

			//전시글 상세내용 보기 
			sc.setAttribute("/client/exhibition/ExContentView.do", 	new ExhibitionContentController().setExhibitionDao(exhibitionDao));

			//가고싶어요 체크
			//sc.setAttribute("", new WishChkController().setWishListDao(wishListDao));
			
			//다녀왔어요 체크
			//sc.setAttribute("", new VisitChkController().setVisitListDao(visitListDao));
			
			//리뷰 작성폼
			sc.setAttribute("/client/exhibition/review.do", new ExhibitionReviewFormController().setReviewDao(reviewDao));
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by seran - end
			//-------------------------------------------------------------------------------------------------------------
			
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by Hojeong - begin
			//-------------------------------------------------------------------------------------------------------------			
			//전시회 등록폼
			sc.setAttribute("/client/exhibition/addForm.do", new ExhibitionAddFormController().setExhibitionDao(exhibitionDao));
			
			//전시회 등록
			sc.setAttribute("/client/exhibition/add.do", new ExhibitionAddController().setExhibitionDao(exhibitionDao));
			
			//전시회 수정
			sc.setAttribute("/client/exhibition/update.do", new ExhibitionUpdateController().setExhibitionDao(exhibitionDao));
				
			//마이 전시회 리스트 
			sc.setAttribute("/client/exhibition/myList.do", new ExhibitionMyListController().setExhibitionDao(exhibitionDao));
			//-------------------------------------------------------------------------------------------------------------
			//			programmed by Hojeong - end
			//-------------------------------------------------------------------------------------------------------------			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
