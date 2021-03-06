package com.doArtShow.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

//전시정보
public class ExhibitionDto {
	private int 			exhID; //전시회 등록 ID
	private String			memberID; //신청자
	private String			exhGubun1; //분류
	private String			exhGubun2; //장르
	private String			exhGubun4; //전시관위치
	private String 			exhName; //전시회명
	private String			artistName; //작가명
	private String			artistInfo; //작가정보
	private String 			exhContent; //전시내용
	private String 			exhPlace; //전시관명
	private String 			exhPlaceZip; //전시관우편번호
	private String 			exhPlaceAddr1; //전시관주소1
	private String			exhPlaceAddr2; //전시관주소2
	private String 			exhUrl; //전시관홈페이지주소
	private String			exhStartDate; //전시시작일
	private String			exhEndDate; //전시종료일
	private String			opTime; //운영시간
	private String 			tel; //전시관전화번호
	private String 			admFee; //입장료
	private String			imageFile1; //전시회 포스터
	private String 			imageFile2; //작품, 전시전경1
	private String 			imageFile3; //작품, 전시전경2 *NULL가능
	private String 			imageFile4; //작품, 전시전경3 *NULL가능
	private int				exhReadCount; //전시조회수
	private Timestamp		registerDate; //전시등록일
	private String			activeFlag; //관리자컨펌여부
	private String 			exhGubun3[]; //태그
	
	public int getExhID() {
		return exhID;
	}
	public void setExhID(int exhID) {
		this.exhID = exhID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getExhGubun1() {
		return exhGubun1;
	}
	public void setExhGubun1(String exhGubun1) {
		this.exhGubun1 = exhGubun1;
	}
	public String getExhGubun2() {
		return exhGubun2;
	}
	public void setExhGubun2(String exhGubun2) {
		this.exhGubun2 = exhGubun2;
	}
	public String getExhGubun4() {
		return exhGubun4;
	}
	public void setExhGubun4(String exhGubun4) {
		this.exhGubun4 = exhGubun4;
	}
	public String getExhName() {
		return exhName;
	}
	public void setExhName(String exhName) {
		this.exhName = exhName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getArtistInfo() {
		return artistInfo;
	}
	public void setArtistInfo(String artistInfo) {
		this.artistInfo = artistInfo;
	}
	public String getExhContent() {
		return exhContent;
	}
	public void setExhContent(String exhContent) {
		this.exhContent = exhContent;
	}
	public String getExhPlace() {
		return exhPlace;
	}
	public void setExhPlace(String exhPlace) {
		this.exhPlace = exhPlace;
	}
	public String getExhPlaceZip() {
		return exhPlaceZip;
	}
	public void setExhPlaceZip(String exhPlaceZip) {
		this.exhPlaceZip = exhPlaceZip;
	}
	public String getExhPlaceAddr1() {
		return exhPlaceAddr1;
	}
	public void setExhPlaceAddr1(String exhPlaceAddr1) {
		this.exhPlaceAddr1 = exhPlaceAddr1;
	}
	public String getExhPlaceAddr2() {
		return exhPlaceAddr2;
	}
	public void setExhPlaceAddr2(String exhPlaceAddr2) {
		this.exhPlaceAddr2 = exhPlaceAddr2;
	}
	public String getExhUrl() {
		return exhUrl;
	}
	public void setExhUrl(String exhUrl) {
		this.exhUrl = exhUrl;
	}
	public String getExhStartDate() {
		return exhStartDate;
	}
	public void setExhStartDate(String exhStartDate) {
		this.exhStartDate = exhStartDate;
	}
	public String getExhEndDate() {
		return exhEndDate;
	}
	public void setExhEndDate(String exhEndDate) {
		this.exhEndDate = exhEndDate;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdmFee() {
		return admFee;
	}
	public void setAdmFee(String admFee) {
		this.admFee = admFee;
	}
	public String getImageFile1() {
		return imageFile1;
	}
	public void setImageFile1(String imageFile1) {
		this.imageFile1 = imageFile1;
	}
	public String getImageFile2() {
		return imageFile2;
	}
	public void setImageFile2(String imageFile2) {
		this.imageFile2 = imageFile2;
	}
	public String getImageFile3() {
		return imageFile3;
	}
	public void setImageFile3(String imageFile3) {
		this.imageFile3 = imageFile3;
	}
	public String getImageFile4() {
		return imageFile4;
	}
	public void setImageFile4(String imageFile4) {
		this.imageFile4 = imageFile4;
	}
	public int getExhReadCount() {
		return exhReadCount;
	}
	public void setExhReadCount(int exhReadCount) {
		this.exhReadCount = exhReadCount;
	}
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public String[] getExhGubun3() {
		return exhGubun3;
	}
	public void setExhGubun3(String exhGubun3[]) {
		this.exhGubun3 = exhGubun3;
	}
	@Override
	public String toString() {
		return "ExhibitionDto [exhID=" + exhID + ", memberID=" + memberID + ", exhGubun1=" + exhGubun1 + ", exhGubun2="
				+ exhGubun2 + ", exhGubun4=" + exhGubun4 + ", exhName=" + exhName + ", artistName=" + artistName
				+ ", artistInfo=" + artistInfo + ", exhContent=" + exhContent + ", exhPlace=" + exhPlace
				+ ", exhPlaceZip=" + exhPlaceZip + ", exhPlaceAddr1=" + exhPlaceAddr1 + ", exhPlaceAddr2="
				+ exhPlaceAddr2 + ", exhUrl=" + exhUrl + ", exhStartDate=" + exhStartDate + ", exhEndDate=" + exhEndDate
				+ ", opTime=" + opTime + ", tel=" + tel + ", admFee=" + admFee + ", imageFile1=" + imageFile1
				+ ", imageFile2=" + imageFile2 + ", imageFile3=" + imageFile3 + ", imageFile4=" + imageFile4
				+ ", exhReadCount=" + exhReadCount + ", registerDate=" + registerDate + ", activeFlag=" + activeFlag
				+ ", exhGubun3=" + Arrays.toString(exhGubun3) + "]";
	}
}

