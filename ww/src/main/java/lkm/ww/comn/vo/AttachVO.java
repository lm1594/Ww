package lkm.ww.comn.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 첨부VO
 * @author lkm
 * @since 2020. 04. 02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttachVO extends PageVO {
	
	private String atSeq;						// 첨부시퀀스
	private String atNo;						// 첨부순번
	private String atGbn;						// 첨부구분
	private String tarTaskKey;					// 대상업무키
	private String atPath;						// 첨부경로
	private String atOrigNm;					// 원본파일명
	private String atSaveNm;					// 저장파일명
	private String atSz;						// 첨부크기
	private String remarks;						// 첨부설명
	private String regDt;						// 등록일시
	private String regEmpId;					// 등록자아이디
	private String regIp;						// 등록아이피
	private String updDt;						// 수정일시
	private String updEmpId;					// 수정자아이디
	private String updIp;						// 수정아이피
	private String useYn;						// 사용유무
	private String sortField;					// 정렬컬럼
	private String sortType;					// 정렬타입
}
