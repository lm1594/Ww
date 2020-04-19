package lkm.ww.mntInfo.domain;

import lkm.ww.comn.vo.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 산정보 목록 DVO
 * @author 이경민
 * @since 2020.04.05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MntInfoVO extends PageVO{
	
	//------------------
	// 산 정보 목록
	//------------------
	private String mntilistno;			// 산정보코드
	private String mntitop;				// 100대명산선정이유
	private String mntiname;			// 산명
	private String mntisname;			// 산정보부제
	private String mntiadd;				// 소재지
	private String mntihigh;			// 높이
	private String mntiadmin;			// 관리주체
	private String mntiadminnum;		// 관리자전화번호
	private String mntisummary;			// 산정보상세정보내용
	private String mntidetails;			// 산정보설명
	private String resultCode;			// 결과코드
	private String resultMsg;			// 결과메세지
	
	//------------------
	// 숲길 구간명
	//------------------
	private String frtrlSectnNm;		// 숲길구간명
	
	//------------------
	// 산 이미지 정보
	//------------------
	private String imgno;				// 이미지순번
	private String imgname;				// 이미지 명
	private String imgfilename;			// 이미지 파일경로
	
	//------------------
	// 숲길 지점 검색
	//------------------
	private String mngmeSpotSeq;		// 관리지점순번
	private String mngmeSpotTpcd;		// 관리지점구분코드
	private String mngmeSpotXcrdVal;	// 관리지점 X좌표값
	private String mngmeSpotYcrdVal;	// 관리지점 Y좌표값
	private String mngmeSpotHaslv;		// 관리지점 해발고도

//  관리지점 코드
//	mngmeSpotTpcd	A	노선내분기
//	mngmeSpotTpcd	B	노선과노선의접점
//	mngmeSpotTpcd	C	정자
//	mngmeSpotTpcd	D	화장실
//	mngmeSpotTpcd	E	벤치
//	mngmeSpotTpcd	F	음수대
//	mngmeSpotTpcd	G	시설물(운동기구등)
//	mngmeSpotTpcd	H	가로등
//	mngmeSpotTpcd	I	기타건물
//	mngmeSpotTpcd	J	이정표
//	mngmeSpotTpcd	K	안내판또는지도
//	mngmeSpotTpcd	L	위험지역
//	mngmeSpotTpcd	M	유적(문화,역사)
//	mngmeSpotTpcd	O	조망점
//	mngmeSpotTpcd	P	훼손지
//	mngmeSpotTpcd	Q	주요나무

}
