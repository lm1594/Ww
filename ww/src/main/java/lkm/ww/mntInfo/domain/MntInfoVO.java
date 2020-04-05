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
}
