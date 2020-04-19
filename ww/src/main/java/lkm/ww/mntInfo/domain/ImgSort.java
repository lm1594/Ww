package lkm.ww.mntInfo.domain;

import java.util.Comparator;

public class ImgSort implements Comparator<MntInfoVO>{
	@Override
	public int compare(MntInfoVO o1, MntInfoVO o2) {
		int firstValue  = Integer.parseInt(o1.getImgno());
		int secondValue = Integer.parseInt(o2.getImgno());
		
		// order by ascending
		if(firstValue > secondValue) {
			return 1;
		}else if (firstValue < secondValue) {
			return -1;
		}else {
			return 0;
		}
	}
}
