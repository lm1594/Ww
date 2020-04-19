
/**
 * Object의 타입을 String 형태로 반환한다.
 * @param obj
 * @returns Boolean
 */
function util_GetType(obj) {
	if(obj == null) return "null";
	return (typeof obj).toLowerCase();
}

/**
 * Object가 undfined일 경우 true를 반환한다.
 * @param obj
 * @returns
 */
function util_IsUndefined(obj) {
	return (obj == undefined);
}

/**
 * 문자열이 NULL이면 true 아니면 false를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_IsNull(str) {
	var chk = false;

	if(str == null) {
		chk = true;
	}

	return chk;
}

/**
 * 문자열이 NULL 또는 비어있는 경우 true 아니면 false를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_IsNullOrBlank(str) {
	var chk = false;

	if(str == null || str == "") {
		chk = true;
	}

	return chk;
}

/**
 * 문자열의 길이가 0 이상인 경우 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_HasLength(str) {
	var chk = false;

	if(str.length > 0) {
		chk = true;
	}

	return chk;
}

/**
 * 문자열의 길이를 반환한다.
 * @param str
 * @returns Number
 */
function util_Length(str) {
	return str.length;
}

/**
 * 문자열의 앞쪽 공백을 제거한다.
 * @param str
 * @returns String
 */
function util_Ltrim(str) {
	return str.replace(/^\s*/, "");
}

/**
 * 문자열의 뒤쪽 공백을 제거한다.
 * @param str
 * @returns String
 */
function util_Rtrim(str) {
	return str.replace(/\s*$/, "");
}

/**
 * 문자열의 앞, 뒤 공백을 제거한다.
 * @param str
 * @returns String
 */
function util_Trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 문자열의 모든 공백을 제거한다.
 * @param str
 * @returns String
 */
function util_AllTrim(str) {
	return str.replace(/(\s*)/g, "");
}

/**
 * 해당 문자열(str)에서 첫 번재로 검색된 문자열(searchStr)을 다른 문자열(replaceStr)로 치환한다.
 * @param str
 * @param searchStr
 * @param replaceStr
 * @returns String
 */
function util_Replace(str, searchStr, replaceStr) {
	return str.replace(searchStr, replaceStr);
}

/**
 * 해당 문자열(str)에서 검색된 문자열(searchStr)을 다른 문자열(replaceStr)로 모두 치환한다.
 * @param str
 * @param searchStr
 * @param replaceStr
 * @returns String
 */
function util_ReplaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

/**
 * 문자열에 영문자가 포함되어 있으면 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_ContainEng(str) {
	var chk = false;
	var regx = /[A-Za-z]/;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 문자열에 숫자가 포함되어 있으면 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_ContainNum(str) {
	var chk = false;
	var regx = /[0-9]/;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 문자열에 영문자 숫자가 포함되어 있으면 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_ContainEngNum(str) {
	var chk = false;
	var regx = /[A-Za-z0-9]/;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 문자열에 한글이 포함되어 있으면 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_ContainKor(str) {
	var chk  = false;
	var regx = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 파라미터로 전달받은 문자열에 한글이 아닐 경우 false를 반환한다.
 * @param str
 * @returns
 */
function util_IsHangul(str) {
	var chk = false;

	for(var i=0; i<str.length; i++) {
		c = str.charCodeAt(i);
		if(0x1100<=c && c<=0x11FF || 0x3130<=c && c<=0x318F || 0xAC00<=c && c<=0xD7A3) chk = true;
		else return false;
	}
	return chk;
}

/**
 * 파라미터로 전달받은 문자열에 한글이 아닐 경우 false를 반환한다.
 * @param str
 * @returns
 */
function util_IsEng(str) {
	var chk = false;
	var regx = /[A-Za-z]/;

	for(var i=0; i<str.length; i++) {
		var c = str.charAt(i);

		if(regx.test(c)) chk = true;
		else return false;
	}
	return chk;
}

/**
 * 문자열에 특수문자가 포함되어 있으면 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_ContainSpecialChar(str) {
	var chk = false;
	var regx = /[~!@\#$%<>^&*\()\-=+_\’]/i;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 이메일 유효성 검사
 * @param email
 * @returns Boolean
 */
function util_IsEmail(email) {
	var chk = false;
	var regx = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	if(regx.test(email)) {
		chk = true;
	}

	return chk;
}

/**
 * 날짜(년/월/일) 유효성 검사
 * @param date
 * @returns Boolean
 */
function util_IsDate(date) {
	date = util_ReplaceAll(date, "-", "");
	date = util_ReplaceAll(date, "/", "");

	if(date.length !=  8) return false;
	if(isNaN(date)) return false;

	var year = Number(date.substring(0, 4));
    var month = Number(date.substring(4, 6));
    var day = Number(date.substring(6, 8));

    var dd = day / 0;

    if(month < 1 || month > 12) return false;

    var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var maxDay = maxDaysInMonth[month - 1];

    if(month==2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) maxDay = 29;
    if(day <= 0 || day > maxDay)  return false;

    return true;
}

/**
 * 날짜(년/월) 유효성 검사
 * @param date
 * @returns Boolean
 */
function util_IsMonth(date) {
	date = util_ReplaceAll(date, "-", "");
	date = util_ReplaceAll(date, "/", "");

	if(date.length !=  6) return false;
	if(isNaN(date)) return false;

	var year = Number(date.substring(0, 4));
	var month = Number(date.substring(4, 6));

	if(month < 1 || month > 12) return false;

	return true;
}

/**
 * 날짜(년) 유효성 검사
 * @param date
 * @returns Boolean
 */
function util_IsYear(date) {
	date = util_ReplaceAll(date, "-", "");
	date = util_ReplaceAll(date, "/", "");

	if(date.length !=  4) return false;
	if(isNaN(date)) return false;

	var year = Number(date.substring(0, 4));

	return true;
}

/**
 * 문자열이 숫자일 경우 true를 반환한다.
 * @param str
 * @returns Boolean
 */
function util_IsNumber(str) {
	var chk = false;
	var regx = /^[0-9]*$/;

	if(regx.test(str)) {
		chk = true;
	}

	return chk;
}

/**
 * 제한 바이트수와 텍스트를 파라미터로 받아 제한 수 이상유무에 따라 true, false를 반환한다.
 * @param limit
 * @param text
 * @returns
 */
function util_TextByteChk(limit, text) {
	if (parseInt(limit) < util_TextByteLength(text)) {
		return false;
	}

	return true;
}

/**
 * 텍스트 문자읠의 Byte 길이를 반환한다.
 *
 * @param text
 * @returns
 */
function util_TextByteLength(text) {
	var pattern = /[\u0000-\u007f]|([\u0080-\u07ff]|(.))/g;

	return text.replace(pattern, "$&$1$2").length;
}

/**
 * 이벤트로 넘어온 키값이 숫자인지 확인한다.
 * @param event
 * @returns
 */
function util_NumberChk(event) {
	if (event.which && (event.which <= 47 || event.which >= 58) && (event.which <= 95 || event.which >= 106) && event.which != 8 && event.which != 9) return false;
	else return false;
}

/**
 * 입력 이벤트 발생 시 해당 숫자의 포맷을 지정한다.
 * @param obj
 * @returns
 */
function util_InputFormatNumber(obj) {
	obj.value = util_FormatNumber(util_RemoveComma(obj.value));
}

/**
 * 파라미터로 넘어온 숫자를 3자리 단위 콤마 형식으로 변환해서 반환한다.
 * @param num
 * @returns String
 */
function util_FormatNumber(num) {
	return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/**
 * 파라미터로 넘어온 숫자에 콤마를 제거ㅏ한다.
 * @param num
 * @returns
 */
function util_RemoveComma(num) {
	return num.toString().replace(/[^\d]+/g, "");
}

/**
 * 문자열이 숫자인 경우 한글금액으로 변경한다. (10000 -> 일만원)
 * @param amt
 * @returns String
 */
function util_FormatKrMoney(amt) {
	var orgAmt = String(amt);
	var strAmt = "0000000000000000" + orgAmt;
	var retAmt = "";
	var idx = 0;
	var len = strAmt.length;
	for(var ii = len; ii > 0; --ii) {
		if (++idx > orgAmt.length) { break; };
		var number = strAmt.substring(ii - 1, ii);
		if (number != "0") {
			if      (idx % 4 == 2)            { retAmt = "십" + retAmt; }
			else if (idx % 4 == 3)            { retAmt = "백" + retAmt; }
			else if (idx > 1 && (idx % 4)==0) { retAmt = "천" + retAmt; }
		}
		if (idx == 5  && parseInt(strAmt.substring(len - 8,  len - 4), 10)  > 0) { retAmt = "만" + retAmt; }
		if (idx == 9  && parseInt(strAmt.substring(len - 12, len - 8), 10)  > 0) { retAmt = "억" + retAmt; }
		if (idx == 13 && parseInt(strAmt.substring(len - 16, len - 12), 10) > 0) { retAmt = "조" + retAmt; }
		if      (number == "1") { retAmt = "일" + retAmt; }
		else if (number == "2") { retAmt = "이" + retAmt; }
		else if (number == "3") { retAmt = "삼" + retAmt; }
		else if (number == "4") { retAmt = "사" + retAmt; }
		else if (number == "5") { retAmt = "오" + retAmt; }
		else if (number == "6") { retAmt = "육" + retAmt; }
		else if (number == "7") { retAmt = "칠" + retAmt; }
		else if (number == "8") { retAmt = "팔" + retAmt; }
		else if (number == "9") { retAmt = "구" + retAmt; }
	}
	return retAmt + "원";
}

/**
 * 문자열에 포함된 특수문자를 이스케이프 문자로 변환하여 반환한다.
 * @param str
 * @returns
 */
function util_XSSEscape(str) {
	str = str.replace(/\</g, "&lt;");
	str = str.replace(/\>/g, "&gt;");
	str = str.replace(/\&/g, "&amp;");
	str = str.replace(/\\/g, "&#039;");
	str = str.replace(/\"/g, "&#034;");
	return str;
}

/**
 * 문자열에 포함된 이스케이프 문자를 특수문자로 변환하여 반환한다.
 * @param str
 * @returns
 */
function util_XSSUnEscape(str) {
	if(!util_IsNullOrBlank(str)) {
		str = str.replace(/&amp;/g, "&");
		str = str.replace(/&lt;/g, "<");
		str = str.replace(/&gt;/g, ">");
		str = str.replace(/\&#034;/g, "\"");
		str = str.replace(/\&#039;/g, "\\");
	}
	return str;
}

/**
 * 해당 일자의 년을 반환한다. (YYYY)
 * @returns
 */
function util_ToYear() {
	var date = new Date();
	return date.getFullYear();
}

/**
 * 해당 일자의 월을 반환한다. (MM)
 * @returns
 */
function util_ToMonth() {
	var date = new Date();
	var month = date.getMonth() + 1;

	if(parseInt(month) < 10) {
		month = "0" + month;
	}

	return month;
}

/**
 * 해당 일자의 일을 반환한다. (DD)
 * @returns
 */
function util_ToDate() {
	var date = new Date();
	var day = date.getDate();

	if(parseInt(day) < 10) {
		day = "0" + day;
	}

	return day;
}

/**
 * 오늘날짜를 YYYYMMDD 형식으로 반환한다.
 * @returns String
 */
function util_Today() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

/**
 * 오늘날짜를 특정형식으로 반환한다. (단, null 입력 시 YYYY/MM/DD로 반환)
 * @param format
 * @returns String
 */
function util_FormatToday(format) {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;
	if(util_IsNullOrBlank(format) || format == "undefined") format = "/";

	return year + format + month + format + day;
}

/**
 * 오늘 날짜및 시간을 YYYYMMDDHH24MISS 형태로 반환한다.
 * @returns String
 */
function util_DateTime() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;
	if(parseInt(hour) < 10) hour = "0" + hour;
	if(parseInt(minute) < 10) minute = "0" + minute;
	if(parseInt(second) < 10) second = "0" + second;

	return year + "" + month + "" + day + "" + hour + "" + minute + "" + second;
}

/**
 * 오늘 날짜 및 시간을 포맷팅하여 반환한다. (날짜, 시간 포맷이 NULL인 경우 YYYY/MM/DD HH24:MI:SS로 반환)
 * @param dateFormat
 * @param timeFormat
 * @returns String
 */
function util_FormatDateTime(dateFormat, timeFormat) {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;
	if(parseInt(hour) < 10) hour = "0" + hour;
	if(parseInt(minute) < 10) minute = "0" + minute;
	if(parseInt(second) < 10) second = "0" + second;
	if(util_IsNullOrBlank(dateFormat) || dateFormat == "undefined") dateFormat = "/";
	if(util_IsNullOrBlank(timeFormat) || timeFormat == "undefined") timeFormat = ":";

	return year + dateFormat + month + dateFormat + day + " " + hour + timeFormat + minute + timeFormat + second;
}

/**
 * 현재 시간을 포맷팅하여 반환한다. (시간 포맷이 NULL인 경우 HH24MISS로 반환)
 * @param timeFormat("",":")
 * @returns String
 */
function util_FormatTime(timeFormat) {
	var date = new Date();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();

	if(parseInt(hour) < 10) hour = "0" + hour;
	if(parseInt(minute) < 10) minute = "0" + minute;
	if(parseInt(second) < 10) second = "0" + second;

	return hour + timeFormat + minute + timeFormat + second;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 일자를 더하여 YYYYMMDD 형식으로 반환한다.
 * @param addDate
 * @returns String
 */
function util_AddDate(addDate) {
	var date = new Date();
	date.setDate(date.getDate() + parseInt(addDate));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 월을 더하여 YYYYMMDD 형식으로 반환한다.
 * @param addMonth
 * @returns String
 */
function util_AddMonth(addMonth) {
	var date = new Date();
	date.setDate(date.getDate() + (30 * parseInt(addMonth)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 년을 더하여 YYYYMMDD 형식으로 반환한다.
 * @param addYear
 * @returns String
 */
function util_AddYear(addYear) {
	var date = new Date();
	date.setDate(date.getDate() + (365 * parseInt(addYear)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 일자를 빼 YYYYMMDD 형식으로 반환한다.
 * @param diffDate
 * @returns String
 */
function util_DiffDate(diffDate) {
	var date = new Date();
	date.setDate(date.getDate() - parseInt(diffDate));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

function util_DiffFormatDate(diffDate) {
	var date = new Date();
	date.setDate(date.getDate() - parseInt(diffDate));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "/" + month + "/" + day;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 월을 빼 YYYYMMDD 형식으로 반환한다.
 * @param diffMonth
 * @returns String
 */
function util_DiffMonth(diffMonth) {
	var date = new Date();
	date.setDate(date.getDate() - (30 * parseInt(diffMonth)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

function util_DiffFormatMonth(diffMonth) {
	var date = new Date();
	date.setDate(date.getDate() - (30 * parseInt(diffMonth)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "/" + month + "/" + day;
}

/**
 * 오늘 날짜에 파라미터로 넘어온 년을 빼 YYYYMMDD 형식으로 반환한다.
 * @param diffYear
 * @returns String
 */
function util_DiffYear(diffYear) {
	var date = new Date();
	date.setDate(date.getDate() - (365 * parseInt(diffYear)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "" + month + "" + day;
}

function util_DiffFormatYear(diffYear) {
	var date = new Date();
	date.setDate(date.getDate() - (365 * parseInt(diffYear)));

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if(parseInt(month) < 10) month = "0" + month;
	if(parseInt(day) < 10) day = "0" + day;

	return year + "/" + month + "/" + day;
}

/**
 * 이번 달의 첫 날짜를 YYYYMMDD 형식으로 반환한다.
 * @param month
 * @returns String
 */
function util_MonthFirstDate() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = "01";

	if(parseInt(month) < 10) month = "0" + month;

	return year + "" + month + "" + day;
}

/**
 * 이번 달의 첫 날짜를 포맷팅한 형식으로 반환한다.
 * @param month
 * @returns String
 */
function util_MonthFormatFirstDate(format) {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = "01";

	if(parseInt(month) < 10) month = "0" + month;
	if(util_IsNullOrBlank(format) || format == "undefined") format = "/";

	return year + format + month + format + day;
}

/**
 * 이번 달의 마지막 날짜를 YYYYMMDD 형식으로 반환한다.
 * @param month
 * @returns String
 */
function util_MonthLastDate() {
	var date = new Date();

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = "";
	var dayArr = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if(month != 2) {
		day = dayArr[Number(month) - 1];
	} else {
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) day = "29";
		else day = "28";
	}
	if(parseInt(month) < 10) month = "0" + month;
	return year + "" + month + "" + day;
}

/**
 * 이번 달의 마지막 날짜를 포맷팅한 형식으로 반환한다.
 * @param month
 * @returns String
 */
function util_MonthFormatLastDate(format) {
	var date = new Date();

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = "";
	var dayArr = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if(month != 2) {
		day = dayArr[Number(month) - 1];
	} else {
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) day = "29";
		else day = "28";
	}
	if(parseInt(month) < 10) month = "0" + month;
	if(util_IsNullOrBlank(format) || format == "undefined") format = "/";
	return year + format + month + format + day;
}

/**
 * 이번 년의 첫 날짜를 YYYYMMDD 형식으로 반환한다.
 * @returns String
 */
function util_YearFirstDate() {
	var date = new Date();
	return date.getFullYear() + "0101";
}

/**
 * 이번 년의 첫 날짜를 YYYY/MM/DD 형식으로 반환한다.
 * @returns
 */
function util_YearFirstFormatDate() {
	var date = new Date();
	return date.getFullYear() + "/01/01";
}

/**
 * 이번 년의 마지막 날짜를 YYYYMMDD 형식으로 반환한다.
 * @returns
 */
function util_YearLastDate() {
	var date = new Date();
	return date.getFullYear() + "1231";
}

/**
 * 이번 년의 마지막 날짜를 YYYY/MM/DD 형식으로 반환한다
 * @returns
 */
function util_YearLastFormatDate() {
	var date = new Date();
	return date.getFullYear() + "/12/31";
}

/**
 * 입력받은 문자열의 데이터 중 숫자만 반환한다.
 * @param str
 * @returns
 */
function util_NumberOnly(str) {
	var num = str.replace(/[^0-9]/g, "");
	return num
}

/**
 * null 또는 undefined 치환한다.
 * @param val
 * @param def
 * @returns
 */
function util_Nvl(val, def) {
	if(!val){
		return !def ? "" : def;
	}
	return val;
}

/**
 * 시작일과 종료일을 비교하여 시작일이 클 경우 경고창 표시 및 데이터를 지운다.
 * @param from
 * @param to
 * @returns
 */
function util_ValidateFromToDate(from, to) {
	var fromDate = $("#" + from).val();
	var toDate = $("#" + to).val();

	if(!util_IsNullOrBlank(fromDate) && !util_IsNullOrBlank(toDate)) {
		if(fromDate != "undefined") {
			if(!util_IsDate(fromDate.replace(/[^0-9]/g,""))) {
				alert("날짜 형식에 맞지 않습니다.");
				$("#"+from).focus();
				return false;
			}

			if(toDate != "undefined") {
				if(!util_IsDate(toDate.replace(/[^0-9]/g,""))) {
					alert("날짜 형식에 맞지 않습니다.");
					$("#"+to).focus();
					return false;
				}
				var date1 = new Date(fromDate);
				var date2 = new Date(toDate);

				if(date1 > date2) {
					alert("시작일이 종료일보다 클 수 없습니다.", "경고");
					//$("#" + from).val("");
					$("#"+to).focus();
					return false;
				}
			}
		}
	}

	return true;
}

/**
 * 시작월과 종료월을 비교하여 시작일이 클 경우 경고창 표시 및 데이터를 지운다.
 * @param from
 * @param to
 * @returns
 */
function util_ValidateFromToMonth(from, to) {
	var fromDate = $("#" + from).val();
	var toDate = $("#" + to).val();

	if(!util_IsNullOrBlank(fromDate) && !util_IsNullOrBlank(toDate)) {
		if(fromDate != "undefined") {
			if(!util_IsMonth(fromDate.replace(/[^0-9]/g,""))) {
				alert("날짜 형식에 맞지 않습니다.");
				$("#"+from).focus();
				return false;
			}

			if(toDate != "undefined") {
				if(!util_IsMonth(toDate.replace(/[^0-9]/g,""))) {
					alert("날짜 형식에 맞지 않습니다.");
					$("#"+to).focus();
					return false;
				}
				var date1 = new Date(fromDate);
				var date2 = new Date(toDate);

				if(date1 > date2) {
					alert("시작월이 종료월보다 클 수 없습니다.", "경고");
					$("#" + from).val("");
					return false;
				}
			}
		}
	}

	return true;
}
/**
 * 시작년과 종료년을 비교하여 시작일이 클 경우 경고창 표시 및 데이터를 지운다.
 * @param from
 * @param to
 * @returns
 */
function util_ValidateFromToYear(from, to) {
	var fromDate = $("#" + from).val();
	var toDate = $("#" + to).val();

	if(!util_IsNullOrBlank(fromDate) && !util_IsNullOrBlank(toDate)) {
		if(fromDate != "undefined") {
			if(!util_IsYear(fromDate.replace(/[^0-9]/g,""))) {
				alert("날짜 형식에 맞지 않습니다.");
				$("#"+from).focus();
				return false;
			}

			if(toDate != "undefined") {
				if(!util_IsYear(toDate.replace(/[^0-9]/g,""))) {
					alert("날짜 형식에 맞지 않습니다.");
					$("#"+to).focus();
					return false;
				}
				var date1 = new Date(fromDate);
				var date2 = new Date(toDate);

				if(date1 > date2) {
					alert("시작년이 종료년보다 클 수 없습니다.", "경고");
					$("#" + from).val("");
					return false;
				}
			}
		}
	}

	return true;
}

/**
 * 만료일자를 지정하여 그 이전 날짜를 선택하는 경우 경고창과 해당 datepicker의 데이터를 지운다.
 * @param date
 * @param id
 * @returns
 */
function util_ExpireDate(date, id) {
	var date1 = new Date(date);
	var date2 = new Date($("#" + id).val());

	if(date2 < date1) {
		alert("만료일은 " + date + " 부터 시작입니다.\n다시 지정해 주세요.", "경고");
		$("#" + id).val("");
		return false;
	}

	return true;
}

/**
 * 파라미터로 받은 파일명과 확장자 배열로 허용 가능한 파일인지 확인한다.
 * @param fileVal
 * @param extArr
 * @returns
 */
function util_FileExtension(fileVal, extArr) {
	if(util_IsNullOrBlank(fileVal)) return false;
	if(extArr.length == 0) return false;

	var fileNm = fileVal;
	var ext = fileNm.substring(fileNm.lastIndexOf(".") + 1, fileNm.length).toLowerCase();

	for(var i=0; i<extArr.length; i++) {
		if(extArr[i].toLowerCase() == ext) {
			return true;
		}
	}
	return false;
}

/**
 * 파라미터로 받은 태그 아이디와 사이즈(MB)로 허용 가능한 크기인지 확인한다.
 * @param fileId
 * @param maxSz
 * @returns
 */
function util_FileMaxSize(fileId, maxSz) {
	if(util_IsNullOrBlank(fileId)) return false;
	if(util_IsNullOrBlank(maxSz)) return false;

	var fileSz = document.getElementById(fileId).files[0].size;
	var maxSz = parseFloat(maxSz) * 1024 * 1024;
	//parseInt->parseFloat로 변경 : KB크기 확인하기 위해서는 소수점으로 들어오기 떄문

	if(fileSz > maxSz) {
		return false;
	}
	return true;
}

/**
 * uuid를 생성한다
 * @param
 * @param uuid
 * @returns
 */
function util_getUUID() { // UUID v4 generator in JavaScript (RFC4122 compliant)
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 3 | 8);
		return v.toString(16);
		});
}

/**
 * 한글을 영문으로 변경한다.
 * @param strKor 변경할 전체 문자열
 * @returns 영문으로 변환된 문자열
 */
function util_ConvertKor2Eng(strKor) {
	var strResult = "";
	var temp_strResult = "";

	for (var i = 0; i < strKor.length; i++) {
		// 공백 무시
		if (strKor.charAt(i) == " ") {
			continue;
		}

		// 특수 케이스 처리 : 성
		if (i == 0) {
			var strFN = strKor.charAt(i) + "";
			switch (strFN) {
				case "김":
					strResult += "Kim ";
					continue;
				case "이":
					strResult += "Lee ";
					continue;
				case "박":
					strResult += "Park ";
					continue;
				case "정":
					strResult += "Jung ";
					continue;
				case "최":
					strResult += "Choi ";
					continue;
				case "조":
					strResult += "Cho ";
					continue;
				case "강":
					strResult += "Kang ";
					continue;
				case "임":
					strResult += "Lim ";
					continue;
				case "신":
					strResult += "Shin ";
					continue;
				case "안":
					strResult += "Ahn ";
					continue;
				case "권":
					strResult += "Kwon ";
					continue;
				case "고":
					strResult += "Ko ";
					continue;
				case "심":
					strResult += "Shim ";
					continue;
				case "주":
					strResult += "Joo ";
					continue;
			}
		}

		// 일반 케이스 처리
		temp_strResult = util_ConvertKor2EngHelper(strKor.charAt(i));
		if (temp_strResult != null) {
			strResult += temp_strResult.substring(0, 1).toUpperCase() + temp_strResult.substring(1, 10) + " ";
		}

		// converting 가능하지 않은 문자가 섞여 있는 경우 그냥 둔다.
		else {
			strResult = "";
			break;
		}
	}

	return strResult.substring(0, strResult.length-1);
}

/**
 * 한글을 영문으로 변환한다.
 * @param strKorChar
 * @returns
 */
function util_ConvertKor2EngHelper(strKorChar) {
	var strResult = "";
	strKorChar = strKorChar + "";

	if (strKorChar >= "가" && strKorChar < "나") {
		var strConvertInfo = "|가|ga|각|gak|간|gan|갇|gat|갈|gal|갉|gal|갊|gam|감|gam|갑|gap|값|gap|갓|gat|갔|gat|강|gang|갖|gat|같|gat|갛|gat|개|gae|객|gaek|갠|gaen|갤|gael|갬|gaem|갭|gaep|갯|gaet|갰|gaet|갱|gaeng|갸|gya|갹|gyak|갼|gyan|걀|gyal|걈|gyam|걍|gyang|걐|gyak|걔|gyae|거|geo|걱|geok|건|geon|걵|geon|걸|geol|검|geom|겁|geop|것|geot|겄|geot|겅|geong|겉|geot|게|ge|겐|gen|겔|gel|겜|gem|겟|get|겠|get|겡|geng|겨|gyeo|격|gyeok|겪|gyeok|견|gyeon|겯|gyeot|결|gyeol|겸|gyeom|겹|gyeop|겻|gyeot|겼|gyeot|경|gyeong|곁|gyeot|계|gye|곌|gyel|고|go|곡|gok|곤|gon|곧|got|골|gol|곪|gom|곰|gom|곱|gop|곳|got|공|gong|곶|got|곷|got|과|gwa|곽|gwak|관|gwan|괁|gwan|괄|gwal|괆|gwam|괌|gwam|괏|gwat|광|gwang|괘|gwae|괜|gwaen|괭|gwaeng|괴|goe|괵|goek|괸|goen|굉|goeng|교|gyo|굔|gyon|굡|gyop|굥|gyong|굫|gyot|구|gu|국|guk|군|gun|굳|gut|굴|gul|굵|gul|굶|gum|굻|gul|굼|gum|굽|gup|굿|gut|궁|gung|궈|gwo|궉|gwok|권|gwon|궍|gwon|궎|gwon|궐|gwol|궘|gwom|궜|gwot|궝|gwong|궤|gwe|귀|gwi|귄|gwin|귈|gwil|귐|gwim|귑|gwip|귓|gwit|규|gyu|균|gyun|귤|gyul|귭|gyup|귱|gyung|그|geu|극|geuk|근|geun|귽|geun|글|geul|긁|geul|금|geum|급|geup|긋|geut|긍|geung|긔|gui|긜|guil|기|gi|긱|gik|긴|gin|긷|git|길|gil|긹|gil|긻|gil|김|gim|깁|gip|깃|git|깅|ging|깈|gik|깉|git|깊|gip|까|kka|깍|kkak|깎|kkak|깐|kkan|깔|kkal|깜|kkam|깝|kkap|깟|kkat|깠|kkat|깡|kkang|깥|kkat|깨|kkae|깬|kkaen|깰|kkael|깸|kkaem|깻|kkaet|깽|kkaeng|꺄|kkya|꺅|kkyak|꺌|kkyal|꺼|kkeo|꺽|kkeok|꺾|kkeok|껀|kkeon|껄|kkeol|껌|kkeom|껍|kkeop|껏|kkeot|껑|kkeong|껕|kkeot|께|kke|껙|kkek|껨|kkem|껫|kket|껭|kkeng|껴|kkyeo|껸|kkyeon|껼|ggyeol|꼇|kkyeot|꼈|kkyeot|꼉|kkyeong|꼐|kkye|꼬|kko|꼭|kkok|꼰|kkon|꼴|kkol|꼼|kkom|꼽|kkop|꼿|kkot|꽁|kkong|꽂|kkot|꽃|kkot|꽈|kkwa|꽉|kkwak|꽐|kkwal|꽝|kkwang|꽥|kkwaek|꾀|kkoe|꾜|kkyo|꾳|kkyot|꾸|kku|꾹|kkuk|꾼|kkun|꿀|kkul|꿇|kkul|꿈|kkum|꿉|kkup|꿋|kkut|꿍|kkung|꿔|kkwo|꿘|kkwon|꿨|kkwot|꿰|kkwe|꿿|kkwel|뀌|kkwi|뀔|kkwil|뀨|kkyu|끄|kkeu|끈|kkeun|끊|kkeun|끌|kkeul|끓|kkeul|끔|kkeum|끕|kkeup|끗|kkeut|끙|kkeung|끝|kkeut|끠|kkui|끼|kki|낀|kkin|낄|kkil|낆|kkil|낌|kkim|낑|kking|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "나" && strKorChar < "다") {
		var strConvertInfo = "|나|na|낙|nak|낚|nak|난|nan|낟|nat|날|nal|남|nam|납|nap|낫|nat|났|nat|낭|nang|낮|nat|낯|nat|내|nae|낵|naek|낸|naen|낼|nael|냄|naem|냅|naep|냇|naet|냈|naet|냉|naeng|냐|nya|냘|nyal|냠|nyam|냥|nyang|너|neo|넉|neok|넋|neok|넌|neon|널|neol|넒|neom|넓|neol|넘|neom|넙|neop|넛|neot|넝|neong|넣|neot|네|ne|넥|nek|넨|nen|넬|nel|넴|nem|넵|nep|넷|net|넹|neng|녀|nyeo|녁|nyeok|년|nyeon|녈|nyeol|념|nyeom|녑|nyeop|녕|nyeong|녘|nyeok|녜|nye|녠|nyen|녤|nyel|노|no|녹|nok|논|non|놀|nol|놈|nom|놋|not|농|nong|높|nop|놓|not|놔|nwa|놘|nwan|놜|nwal|놤|nwam|놰|nwae|뇌|noe|뇨|nyo|뇩|nyok|뇬|nyon|뇽|nyong|누|nu|눅|nuk|눈|nun|눋|nut|눌|nul|눔|num|눕|nup|눙|nung|눚|nut|눠|nwo|눤|nwon|눰|nwom|눼|nwe|눽|nwek|뉘|nwi|뉜|nwin|뉠|nwil|뉨|nwim|뉩|nwip|뉫|nwit|뉭|nwing|뉴|nyu|뉸|nyun|늄|nyum|늉|nyung|느|neu|늑|neuk|는|neun|늘|neul|늙|neul|늠|neum|늣|neut|능|neung|늦|neut|늬|nui|늰|nuin|늴|nuil|늼|nuim|닄|nuik|니|ni|닉|nik|닌|nin|닐|nil|님|nim|닙|nip|닛|nit|닝|ning|닞|nit|닠|nik|닢|nip|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "다" && strKorChar < "라") {
		var strConvertInfo = "|다|da|닥|dak|닦|dak|단|dan|닫|dat|달|dal|닭|dal|닮|dam|닰|dal|닳|dal|담|dam|답|dap|닷|dat|당|dang|닻|dat|닼|dak|닿|dat|대|dae|댁|daek|댄|daen|댈|dael|댐|daem|댑|daep|댓|daet|댕|daeng|댜|dya|댯|dyat|댱|dyang|댴|dyak|댸|dyae|더|deo|덕|deok|던|deon|덛|deot|덜|deol|덞|deom|덟|deol|덤|deom|덥|deop|덧|deot|덩|deong|덮|deop|데|de|덱|dek|덴|den|델|del|뎀|dem|뎁|dep|뎃|det|뎅|deng|뎌|dyeo|뎍|dyeok|뎐|dyeon|뎔|dyeol|뎜|dyeom|뎡|dyeong|뎨|dye|뎵|dyel|도|do|독|dok|돈|don|돋|dot|돌|dol|돔|dom|돕|dop|돗|dot|동|dong|돛|dot|돠|dwa|돤|dwan|돨|dwal|돰|dwam|돳|dwat|돵|dwang|돸|dwak|돼|dwae|됌|dwaem|됐|dwaet|됑|dwaeng|되|doe|된|doen|될|doel|됨|doem|됫|doet|됬|doet|됭|doeng|됴|dyo|둉|dyong|두|du|둑|duk|둔|dun|둘|dul|둠|dum|둡|dup|둥|dung|둬|dwo|둰|dwon|뒈|dwe|뒐|dwel|뒙|dwep|뒝|dweng|뒤|dwi|뒥|dwik|뒨|dwin|뒬|dwil|뒴|dwim|뒵|dwip|뒷|dwit|뒹|dwing|뒼|dwik|듀|dyu|듄|dyun|듈|dyul|듐|dyum|듓|dyut|듕|dyung|드|deu|득|deuk|든|deun|듣|deut|들|deul|듬|deum|듭|deup|듯|deut|등|deung|듸|dui|듼|duin|딈|duim|딉|duip|딍|duing|딐|duik|디|di|딕|dik|딘|din|딜|dil|딤|dim|딥|dip|딧|dit|딨|dit|딩|ding|따|tta|딱|ttak|딲|ttak|딴|ttan|딸|ttal|딹|ttal|땀|ttam|땁|ttap|땃|ttat|땄|ttat|땅|ttang|때|ttae|땍|ttaek|땐|ttaen|땔|ttael|땜|ttaem|땟|ttaet|땡|ttaeng|땩|ttyak|땹|ttyap|땽|ttyang|떄|ttyae|떙|ttyaeng|떠|tteo|떡|tteok|떢|tteok|떤|tteon|떨|tteol|떰|tteom|떱|tteop|떳|tteot|떴|tteot|떵|tteong|떻|tteot|떼|tte|떽|ttek|뗀|tten|뗄|ttel|뗅|ttel|뗈|ttel|뗌|ttem|뗍|ttep|뗑|tteng|뗘|ttyeo|뗭|ttyeong|또|tto|똑|ttok|똔|tton|똘|ttol|똥|ttong|똬|ttwa|똴|ttwal|뙒|ttwaem|뙔|ttwael|뙗|ttwael|뚕|ttyong|뚜|ttu|뚝|ttuk|뚠|ttun|뚤|ttul|뚫|ttul|뚬|ttum|뚱|ttung|뛔|ttwe|뛣|ttwel|뛰|ttwi|뛱|ttwik|뛴|ttwin|뛸|ttwil|뜀|ttwim|뜁|ttwip|뜅|ttwing|뜌|ttyu|뜔|ttyul|뜨|tteu|뜩|tteuk|뜬|tteun|뜯|tteut|뜰|tteul|뜸|tteum|뜹|tteup|뜻|tteut|뜽|tteung|띀|tteuk|띄|ttui|띅|ttuik|띕|ttuip|띠|tti|띡|ttik|띤|ttin|띨|ttil|띰|ttim|띱|ttip|띳|ttit|띵|tting|띸|ttik|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "라" && strKorChar < "마") {
		var strConvertInfo = "|라|ra|락|rak|란|ran|랄|ral|람|ram|랍|rap|랏|rat|랐|rat|랑|rang|랗|rat|래|rae|랙|raek|랜|raen|랠|rael|램|raem|랩|raep|랫|raet|랬|raet|랭|raeng|랴|rya|략|ryak|랸|ryan|랼|ryal|럄|ryam|럇|ryat|량|ryang|러|reo|럭|reok|런|reon|럴|reol|럼|reom|럽|reop|럿|reot|렀|reot|렁|reong|렇|reot|레|re|렉|rek|렌|ren|렐|rel|렘|rem|렙|rep|렛|ret|렝|reng|려|ryeo|력|ryeok|련|ryeon|렫|ryeot|렬|ryeol|렴|ryeom|렵|ryeop|렷|ryeot|렸|ryeot|령|ryeong|례|rye|롄|ryen|롑|ryep|로|ro|록|rok|론|ron|롤|rol|롬|rom|롭|rop|롯|rot|롱|rong|롲|rot|롷|rot|롸|rwa|롹|rwak|롼|rwan|뢀|rwal|뢈|rwam|뢍|rwang|뢔|rwae|뢰|roe|룃|roet|료|ryo|룐|ryon|룔|ryol|룝|ryop|룟|ryot|룡|ryong|루|ru|룩|ruk|룬|run|룰|rul|룸|rum|룹|rup|룻|rut|룽|rung|뤄|rwo|뤈|rwon|뤘|rwot|뤠|rwe|뤼|rwi|뤽|rwik|륀|rwin|륌|rwim|륍|rwip|륏|rwit|륑|rwing|류|ryu|륙|ryuk|륜|ryun|률|ryul|륨|ryum|륫|ryut|륭|ryung|륳|ryut|르|reu|륵|reuk|른|reun|를|reul|름|reum|릅|reup|릇|reut|릉|reung|릊|reut|릐|rui|릔|ruin|리|ri|릭|rik|릮|rik|린|rin|릴|ril|림|rim|립|rip|릿|rit|맀|rit|링|ring|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "마" && strKorChar < "바") {
		var strConvertInfo = "|마|ma|막|mak|만|man|많|man|맏|mat|말|mal|맑|mal|맘|mam|맙|map|맛|mat|망|mang|맞|mat|맡|mat|매|mae|맥|maek|맨|maen|맬|mael|맴|maem|맵|maep|맷|maet|맹|maeng|맺|maet|먀|mya|먄|myan|먈|myal|먕|myang|머|meo|먹|meok|먼|meon|멀|meol|멈|meom|멉|meop|멋|meot|멌|meot|멍|meong|멎|meot|메|me|멕|mek|멘|men|멜|mel|멤|mem|멧|met|멨|met|멩|meng|며|myeo|멱|myeok|면|myeon|멷|myeot|멸|myeol|몀|myeom|몁|myeop|명|myeong|몇|myeot|몋|myeot|몌|mye|모|mo|목|mok|몫|mok|몬|mon|몮|mon|몰|mol|몸|mom|몹|mop|못|mot|몽|mong|뫄|mwa|뫈|mwan|뫙|mwang|뫼|moe|묘|myo|묜|myon|묠|myol|묭|myong|무|mu|묵|muk|묶|muk|문|mun|묹|mun|묻|mut|물|mul|뭄|mum|뭅|mup|뭇|mut|뭉|mung|뭍|mut|뭐|mwo|뭔|mwon|뭘|mwol|뭣|mwot|뭬|mwe|뮈|mwi|뮌|mwin|뮐|mwil|뮤|myu|뮨|myun|뮬|myul|뮹|myung|므|meu|믁|meuk|믄|meun|믈|meul|믐|meum|믓|meut|믜|mui|믠|muin|믤|muil|미|mi|믹|mik|민|min|믽|min|믾|min|믿|mit|밀|mil|밈|mim|밉|mip|밋|mit|밌|mit|밍|ming|및|mit|밐|mik|밑|mit|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "바" && strKorChar < "사") {
		var strConvertInfo = "|바|ba|박|bak|밖|bak|밗|bak|반|ban|받|bat|발|bal|밝|bal|밞|bam|밟|bal|밣|bal|밤|bam|밥|bap|밧|bat|방|bang|밫|bat|밭|bat|밯|bat|배|bae|백|baek|밴|baen|밸|bael|뱀|baem|뱁|baep|뱃|baet|뱄|baet|뱅|baeng|뱉|baet|뱌|bya|뱍|byak|뱐|byan|뱝|byap|뱡|byang|뱢|byat|버|beo|벅|beok|번|beon|벋|beot|벌|beol|범|beom|법|beop|벗|beot|벙|beong|벚|beot|베|be|벡|bek|벤|ben|벧|bet|벨|bel|벰|bem|벳|bet|벵|beng|벼|byeo|벽|byeok|변|byeon|볃|byeot|별|byeol|볆|byeom|볇|byeol|볌|byeom|볍|byeop|볏|byeot|병|byeong|볕|byeol|보|bo|복|bok|볶|bok|본|bon|볻|bot|볼|bol|봄|bom|봅|bop|봇|bot|봉|bong|봌|bok|봐|bwa|봔|bwan|봘|bwal|봚|bwam|봤|bwat|봴|bwael|뵈|boe|뵐|boel|뵜|boet|뵤|byo|뵨|byon|뵬|byol|뵹|byong|부|bu|북|buk|분|bun|붇|but|불|bul|붉|bul|붐|bum|붑|bup|붓|but|붕|bung|붙|but|붜|bwo|붠|bwon|붤|bwol|붬|bwom|붸|bwe|붼|bwen|뷔|bwi|뷕|bwik|뷘|bwin|뷜|bwil|뷤|bwim|뷩|bwing|뷰|byu|뷱|byuk|뷴|byun|뷸|byul|븅|byung|브|beu|븍|beuk|븐|beun|블|beul|븜|beum|븡|beung|븨|bui|븬|buin|븽|buing|븿|buit|빀|buik|비|bi|빅|bik|빈|bin|빌|bil|빔|bim|빕|bip|빗|bit|빙|bing|빚|bit|빛|bit|빠|ppa|빡|ppak|빤|ppan|빨|ppal|빪|ppam|빰|ppam|빱|ppap|빳|ppat|빵|ppang|빻|ppat|빼|ppae|빽|ppaek|뺀|ppaen|뺄|ppael|뺌|ppaem|뺍|ppaep|뺏|ppaet|뺑|ppaeng|뺘|ppya|뺙|ppyak|뺨|ppyam|뺴|ppyae|뺵|ppyaek|뻐|ppeo|뻑|ppeok|뻔|ppeon|뻘|ppeol|뻠|ppeom|뻥|ppeong|뻬|ppe|뼁|ppeng|뼈|ppyeo|뼉|ppyeok|뼐|ppyeol|뼘|ppyeom|뼛|ppyeot|뼝|ppyeong|뽀|ppo|뽁|ppok|뽄|ppon|뽇|ppot|뽈|ppol|뽐|ppom|뽑|ppop|뽕|ppong|뽜|ppwa|뽱|ppwang|뾰|ppyo|뿅|ppyong|뿌|ppu|뿍|ppuk|뿐|ppun|뿔|ppul|뿜|ppum|뿡|ppung|쀅|ppwek|쀓|ppwel|쀠|ppwi|쀤|ppwin|쀼|ppyu|쁑|ppyung|쁘|ppeu|쁜|ppeun|쁠|ppeul|쁨|ppeum|쁭|ppeung|쁸|ppuin|삐|ppi|삑|ppik|삔|ppin|삘|ppil|삣|ppit|삥|pping|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "사" && strKorChar < "아") {
		var strConvertInfo = "|사|sa|삭|sak|삯|sak|산|san|삳|sat|살|sal|삵|sal|삶|sam|삻|sal|삼|sam|삽|sap|삿|sat|상|sang|새|sae|색|saek|샌|saen|샐|sael|샘|saem|샙|saep|샛|saet|생|saeng|샤|sya|샥|syak|샨|syan|샬|syal|샴|syam|샵|syap|샷|syat|샹|syang|섀|syae|섕|syaeng|서|seo|석|seok|섟|seok|선|seon|섡|seon|섢|seon|섣|seot|설|seol|섬|seom|섭|seop|섯|seot|섰|seot|성|seong|섶|seop|세|se|섹|sek|센|sen|셀|sel|셈|sem|셉|sep|셋|set|셍|seng|셔|syeo|셕|syeok|션|syeon|셜|syeol|셤|syeom|셥|syeop|셧|syeot|셨|syeot|셩|syeong|셰|sye|셴|syen|셸|syel|소|so|속|sok|손|son|솓|sot|솔|sol|솜|som|솝|sop|솟|sot|송|song|솥|sot|솨|swa|솩|swak|솬|swan|솰|swal|솸|swam|솽|swang|쇄|swae|쇈|swaen|쇌|swael|쇔|swaem|쇗|swaet|쇠|soe|쇤|soen|쇼|syo|쇽|syok|숀|syon|숄|syol|숍|syop|숏|syot|숑|syong|수|su|숙|suk|순|sun|숝|sun|숞|sun|숟|sut|술|sul|숨|sum|숩|sup|숫|sut|숭|sung|숮|sut|숯|sut|숱|sut|숲|sup|숴|swo|숸|swon|쉐|swe|쉑|swek|쉔|swen|쉘|swel|쉠|swem|쉥|sweng|쉬|swi|쉭|swik|쉰|swik|쉴|swil|쉼|swim|쉽|swip|쉿|swit|슁|swing|슈|syu|슉|syuk|슌|syun|슐|syul|슘|syum|슛|syut|슝|syung|스|seu|슥|seuk|슨|seun|슬|seul|슭|seul|슴|seum|습|seup|승|seung|싀|sui|싁|suik|싄|suin|시|si|식|sik|신|sin|싡|sin|싣|sit|실|sil|싦|sim|싫|sil|심|sim|십|sip|싯|sit|싱|sing|싲|sit|싶|sip|싸|ssa|싹|ssak|싼|ssan|쌀|ssal|쌈|ssam|쌉|ssap|쌌|ssat|쌍|ssang|쌓|ssat|쌔|ssae|쌕|ssaek|쌘|ssaen|쌤|ssaem|쌥|ssaep|쌧|ssaet|쌩|ssaeng|쌰|ssya|쌱|ssyak|썀|ssyam|썁|ssyap|썅|ssyang|썌|ssyae|썜|ssyaem|써|sseo|썩|sseok|썪|sseok|썬|sseon|썰|sseol|썸|sseom|썹|sseop|썼|sseot|썽|sseong|쎄|sse|쎅|ssek|쎆|ssek|쎈|ssen|쎌|ssel|쎔|ssem|쎠|ssyeo|쎤|ssyeon|쎵|ssyeong|쏘|sso|쏙|ssok|쏜|sson|쏟|ssot|쏠|ssol|쏨|ssom|쏩|ssop|쏭|ssong|쏴|sswa|쏸|sswan|쏼|sswal|쐉|sswang|쐐|sswae|쐑|sswaek|쐬|ssoe|쑈|ssyo|쑐|ssyol|쑝|ssyong|쑤|ssu|쑥|ssuk|쑨|ssun|쑬|ssul|쑴|ssum|쑵|ssup|쑹|ssung|쒀|sswo|쒜|sswe|쒝|sswek|쒫|sswel|쒸|sswi|쒼|sswin|쓉|sswip|쓔|ssyu|쓩|ssyung|쓰|sseu|쓱|sseuk|쓴|sseun|쓸|sseul|씀|sseum|씁|sseup|씅|sseung|씌|ssui|씜|ssuim|씝|ssuip|씨|ssi|씩|ssik|씬|ssin|씰|ssil|씸|ssim|씹|ssip|씻|ssit|씽|ssing|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "아" && strKorChar < "자") {
		var strConvertInfo = "|아|ah|악|ak|안|an|앉|an|않|an|알|al|앍|al|앎|am|앓|al|암|am|압|ap|앗|at|았|at|앙|ang|앜|ak|앞|ap|앟|at|애|ae|액|aek|앤|aen|앨|ael|앰|aem|앱|aep|앳|aet|앵|aeng|야|ya|약|yak|얀|yan|얄|yal|얌|yam|얍|yap|얏|yat|얐|yat|양|yang|얔|yak|얗|yat|얘|yae|얜|yaen|얩|yaep|어|eo|억|eok|언|eon|얻|eot|얼|eol|얽|eol|엃|eol|엄|eom|업|eop|없|eop|엇|eot|었|eot|엉|eong|엊|eot|엌|eok|엎|eop|엏|eot|에|e|엑|ek|엔|en|엘|el|엠|em|엡|ep|엣|et|엥|eng|여|yeo|역|yeok|연|yeon|엳|yeot|열|yeol|염|yeom|엽|yeop|엿|yeot|였|yeot|영|young|옂|yeot|옅|yeot|옆|yeop|옇|yeot|예|ye|옌|yen|옐|yel|옘|yem|옙|yep|옛|yet|오|oh|옥|ok|온|on|옫|ot|올|ol|옭|ol|옳|ol|옴|om|옵|op|옷|ot|옹|ong|옿|ot|와|wa|왁|wak|완|wan|왈|wal|왐|wam|왓|wat|왔|wat|왕|wang|왜|wae|왝|waek|왠|waen|왯|waet|왱|waeng|외|oe|왼|oen|요|yo|욕|yok|욘|yon|욜|yol|욤|yom|욥|yop|욧|yot|용|yong|우|woo|욱|uk|운|un|욷|ut|울|ul|움|um|웁|up|웃|ut|웅|ung|워|wo|원|won|웒|won|월|wol|웜|wom|웠|wot|웡|wong|웥|wot|웧|wot|웨|we|웩|wek|웬|wen|웰|wel|웹|wep|위|wi|윅|wik|윈|win|윌|wil|윔|wim|윗|wit|윙|wing|유|yu|육|yuk|윤|yun|윥|yun|윦|yun|율|yul|윰|yum|윳|yut|융|yung|윶|yut|윷|yut|윻|yut|으|eu|윽|euk|은|eun|읁|eun|을|eul|음|eum|읍|eup|읏|eut|응|eung|읒|eut|읔|euk|의|ui|읜|uin|읠|uil|읨|uim|읩|uip|읫|uit|읭|uing|읰|uik|이|yi|익|ik|인|in|읻|it|일|il|읽|il|읾|im|잃|il|임|im|입|ip|잇|it|있|it|잉|ing|잊|it|잌|ik|잍|it|잎|ip|잏|it|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "자" && strKorChar < "차") {
		var strConvertInfo = "|자|ja|작|jak|잔|jan|잖|jan|잗|jat|잘|jal|잠|jam|잡|jap|잣|jat|잤|jat|장|jang|잦|jat|재|jae|잭|jaek|잰|jaen|잴|jael|잼|jaem|잽|jaep|잿|jaet|쟁|jaeng|쟈|jya|쟉|jyak|쟌|jyan|쟐|jyal|쟝|jyang|쟤|jyae|쟹|jyaeng|저|jeo|적|jeok|전|jeon|젅|jeon|젇|jeot|절|jeol|젊|jeom|점|jeom|접|jeop|젓|jeot|정|jeong|젖|jeot|제|je|젝|jek|젠|jen|젤|jel|젬|jem|젯|jet|젱|jeng|져|jyeo|젼|jyeon|졀|jyeol|졈|jyeom|졌|jyeot|졍|jyeong|졔|jye|조|jo|족|jok|존|jon|졷|jot|졸|jol|좀|jom|좁|jop|좃|jot|종|jong|좆|jot|좇|jot|좋|jot|좌|jwa|좍|jwak|좔|jwal|좡|jwang|좨|jwae|좽|jwaeng|죄|joe|죈|joen|죔|joem|죠|jyo|죤|jyon|죨|jyol|죳|jyot|죵|jyong|주|ju|죽|juk|준|jun|줁|jun|줂|jun|줄|jul|줌|jum|줍|jup|줏|jut|중|jung|줗|jut|줘|jwo|줨|jwom|줬|jwot|줭|jwong|줴|jwe|쥉|jweng|쥐|jwi|쥑|jwik|쥔|jwin|쥘|jwil|쥠|jwim|쥡|jwip|쥣|jwit|쥥|jwing|쥬|jyu|쥰|jyun|쥴|jyul|쥼|jyum|즁|jyung|즈|jeu|즉|jeuk|즌|jeun|즐|jeul|즘|jeum|즙|jeup|증|jeung|즤|jui|즨|juin|지|ji|직|jik|진|jin|짅|jin|짆|jin|짇|jit|질|jil|짊|jim|짐|jim|집|jip|짓|jit|짔|jit|징|jing|짖|jit|짚|jip|짜|jja|짝|jjak|짠|jjan|짢|jjan|짤|jjal|짧|jjal|짬|jjam|짭|jjap|짱|jjang|째|jjae|짹|jjaek|짼|jjaen|쨀|jjael|쨈|jjaem|쨍|jjaeng|쨔|jjya|쨘|jjyan|쨩|jjyang|쨰|jjyae|쩌|jjeo|쩍|jjeok|쩐|jjeon|쩔|jjeol|쩜|jjeom|쩝|jjeop|쩡|jjeong|쩨|jje|쪄|jjyeo|쪈|jjyeon|쪙|jjyeong|쪼|jjo|쪽|jjok|쫀|jjon|쫄|jjol|쫌|jjom|쫍|jjop|쫏|jjot|쫑|jjong|쫓|jjot|쫘|jjwa|쫙|jjwak|쫭|jjwang|쫴|jjwae|쬃|jjwael|쬐|jjoe|쬬|jjyo|쭁|jjyong|쭈|jju|쭉|jjuk|쭌|jjun|쭐|jjul|쭙|jjup|쭝|jjung|쭤|jjwo|쭹|jjwong|쮜|jjwi|쮝|jjwik|쮠|jjwin|쮢|jjwin|쮸|jju|쯔|jjeu|쯜|jjeul|쯤|jjeum|쯧|jjeut|쯩|jjeung|찀|jjuim|찌|jji|찍|jjik|찐|jjin|찔|jjil|찜|jjim|찝|jjip|찡|jjing|찢|jjit|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "차" && strKorChar < "카") {
		var strConvertInfo = "|차|cha|착|chak|찬|chan|찮|chan|찰|chal|참|cham|찹|chap|찻|chat|찼|chat|창|chang|찾|chat|찿|chat|채|chae|책|chaek|챈|chaen|챌|chael|챔|chaem|챕|chaep|챙|chaeng|챠|chya|챤|chyan|챨|chyal|챰|chyam|챵|chyang|챺|chyap|챼|chyae|처|cheo|척|cheok|천|cheon|철|cheol|첣|cheol|첧|cheol|첨|cheom|첩|cheop|첫|cheot|첬|cheot|청|cheong|첳|cheot|체|che|첵|chek|첸|chen|첼|chel|쳇|chet|쳉|cheng|쳐|chyeo|쳑|chyeok|쳔|chyeon|쳘|chyeol|쳤|chyeot|쳥|chyeong|쳬|chye|초|cho|촉|chok|촌|chon|촐|chol|촘|chom|촙|chop|촛|chot|총|chong|촤|chwa|촨|chwan|촬|chwal|촹|chwang|쵀|chwae|최|choe|쵠|choen|쵬|choem|쵭|choep|쵯|choet|쵱|choeng|쵲|choet|쵸|chyo|춈|chyom|춍|chyong|춏|chyot|추|chu|축|chuk|춘|chun|춚|chun|출|chul|춤|chum|춥|chup|충|chung|춭|chut|춰|chwo|춸|chwol|췌|chwe|췐|chwen|취|chwi|췬|chwin|췰|chwil|췸|chwim|췽|chwing|츄|chyu|츈|chyun|츙|chyung|츠|cheu|측|cheuk|츤|cheun|츨|cheul|층|cheung|츼|chui|칀|chuin|칑|chuing|치|chi|칙|chik|친|chin|칠|chil|칡|chil|침|chim|칩|chip|칫|chit|칭|ching|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "카" && strKorChar < "타") {
		var strConvertInfo = "|카|ka|칵|kak|칸|kan|칼|kal|캄|kam|캅|kap|캇|kat|캉|kang|캐|kae|캑|kaek|캔|kaen|캘|kael|캠|kaem|캡|kaep|캣|kaet|캥|kaeng|캬|kya|캭|kyak|컁|kyang|커|keo|컥|keok|컨|keon|컬|keol|컴|keom|컵|keop|컷|keot|컸|keot|컹|keong|케|ke|켁|kek|켄|ken|켈|kel|켐|kem|켓|ket|켕|keng|켜|kyeo|켠|kyeon|켤|kyeol|켬|kyeom|켰|kyeot|켱|kyeong|켸|kye|코|ko|콕|kok|콘|kon|콜|kol|콤|kom|콥|kop|콧|kot|콩|kong|콰|kwa|콱|kwak|콴|kwan|쾅|kwang|쾌|kwae|쾡|kwaeng|쾨|koe|쿄|kyo|쿠|ku|쿡|kuk|쿤|kun|쿨|kul|쿰|kum|쿳|kut|쿵|kung|쿼|kwo|퀀|kwon|퀄|kwol|퀘|kwe|퀙|kwek|퀣|kwel|퀭|kweng|퀴|kwi|퀵|kwik|퀸|kwin|퀼|kwil|큄|kwim|큉|kwing|큐|kyu|큔|kyun|큘|kyul|크|keu|큭|keuk|큰|keun|클|keul|큼|keum|큽|keup|킁|keung|킈|kui|키|ki|킥|kik|킨|kin|킬|kil|킴|kim|킵|kip|킷|kit|킹|king|킼|kik|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "타" && strKorChar < "파") {
		var strConvertInfo = "|타|ta|탁|tak|탄|tan|탈|tal|탐|tam|탑|tap|탕|tang|태|tae|택|taek|탠|taen|탤|tael|탬|taem|탭|taep|탯|taet|탱|taeng|탸|tya|탹|tyak|턍|tyang|터|teo|턱|teok|턴|teon|털|teol|텀|teom|텁|teop|텃|teot|텅|teong|테|te|텍|tek|텐|ten|텔|tel|템|tem|텝|tep|텟|tet|텡|teng|텨|tyeo|텬|tyeon|텼|tyeot|텽|tyeong|토|to|톡|tok|톤|ton|톨|tol|톰|tom|톱|top|통|tong|톼|twa|퇄|twal|퇘|twae|퇴|toe|툉|toeng|툐|tyo|툔|tyon|투|tu|툭|tuk|툰|tun|툴|tul|퉁|tung|퉈|two|퉤|twe|퉹|tweng|튀|twi|튁|twik|튄|twin|튈|twil|튑|twip|튕|twing|튜|tyu|튠|tyun|튤|tyul|튱|tyung|트|teu|특|teuk|튼|teun|틀|teul|틈|teum|틋|teut|틍|teung|틔|tui|틘|tuin|틩|tuing|티|ti|틱|tik|틴|tin|틸|til|팀|tim|팁|tip|팅|ting|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "파" && strKorChar < "하") {
		var strConvertInfo = "|파|pa|팍|pak|판|pan|팔|pal|팜|pam|팝|pap|팟|pat|팠|pat|팡|pang|팥|pat|패|pae|팩|paek|팬|paen|팰|pael|팸|paem|팹|paep|팻|paet|팽|paeng|퍄|pya|퍼|peo|퍽|peok|펀|peon|펄|peol|펌|peom|펑|peong|페|pe|펙|pek|펜|pen|펠|pel|펨|pem|펩|pep|펫|pet|펭|peng|펴|pyeo|편|pyeon|펼|pyeol|폄|pyeom|평|pyeong|폐|pye|폥|pyeng|포|po|폭|pok|폰|pon|폴|pol|폼|pom|폿|pot|퐁|pong|퐈|pwa|퐐|pwal|퐝|pwang|푀|poe|표|pyo|푝|pyok|푠|pyon|푯|pyot|푶|pyop|푸|pu|푹|puk|푼|pun|풀|pul|풂|pum|품|pum|풋|put|풍|pung|풔|pwo|퓌|pwi|퓔|pwil|퓨|pyu|퓬|pyun|퓰|pyul|퓽|pyung|프|peu|픈|peun|플|peul|픔|peum|픨|puil|피|pi|픽|pik|핀|pin|필|pil|핅|pil|핇|pil|핌|pim|핍|pip|핏|pit|핑|ping|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else if (strKorChar >= "하" && strKorChar <= "힣") {
		var strConvertInfo = "|하|ha|학|hak|한|han|핝|han|핞|han|할|hal|핥|hal|함|ham|합|hap|핫|hat|항|hang|핳|hat|해|hae|핵|haek|핸|haen|핼|hael|햄|haem|햅|haep|햇|haet|했|haet|행|haeng|햐|hya|햑|hyak|햔|hyan|햘|hyal|햠|hyam|햣|hyat|향|hyang|햨|hyak|햫|hyat|햬|hyae|허|heo|헉|heok|헌|heon|헎|heon|헐|heol|험|heom|헙|heop|헛|heot|헝|heong|헤|he|헥|hek|헨|hen|헬|hel|헴|hem|헵|hep|헷|het|헹|heng|헺|het|혀|hyeo|혁|hyeok|현|hyeon|혅|hyeon|혈|hyeol|혐|hyeom|협|hyeop|혓|hyeot|혔|hyeot|형|hyeong|혖|hyeot|혛|hyeot|혜|hye|혠|hyen|혤|hyel|혬|hyem|혭|hyep|혯|hyet|혱|hyeng|혲|hyet|호|ho|혹|hok|혼|hon|홀|hol|홅|hol|홈|hom|홉|hop|홋|hot|홍|hong|홑|hot|홓|hot|화|hwa|확|hwak|환|hwan|홛|hwat|활|hwal|홤|hwam|홥|hwap|홧|hwat|황|hwang|홯|hwat|홰|hwae|횃|hwaet|회|hoe|획|hoek|횐|hoen|횟|hoet|횡|hoeng|효|hyo|횬|hyon|횰|hyol|횹|hyop|횻|hyot|횽|hyong|후|hu|훅|huk|훈|hun|훌|hul|훑|hul|훓|hul|훔|hum|훕|hup|훗|hut|훙|hung|훚|hut|훠|hwo|훤|hwon|훨|hwol|훼|hwe|훽|hwek|휀|hwen|휄|hwel|휑|hweng|휔|hwek|휘|hwi|휜|hwin|휠|hwil|휨|hwim|휩|hwip|휫|hwit|휭|hwing|휴|hyu|휸|hyu|휼|hyul|휿|hyul|흉|hyung|흏|hyut|흐|heu|흑|heuk|흔|heun|흘|heul|흙|heul|흛|heul|흠|heum|흡|heup|흣|heut|흥|heung|희|hui|흰|huin|흴|huil|흼|huim|흽|huip|힁|huing|힂|huit|힄|huik|히|hi|힉|hik|힌|hin|힐|hil|힘|him|힙|hip|힛|hit|힝|hing|";
		var n4Pos = strConvertInfo.indexOf(strKorChar);
		if (n4Pos == -1) return null;
		strResult = strConvertInfo.substring(n4Pos+2, strConvertInfo.indexOf("|", n4Pos+2));
	}
	else {
		strResult = null;
	}

	return strResult;
}

/**
 * 전화번호 마스킹
 * @param tel
 * @returns
 */
function util_MaskTel(tel) {
	var formatTel = "";
	var maskTel = "";
	var origTel = "";

	if(util_IsNullOrBlank(tel)) {
		return tel;
	}

	tel = tel.replace(/[^0-9]/g, "");

	if(tel.length == 8) {
		formatTel = tel.replace(/^([0-9]{4})([0-9]{4})/, "$1-$2");
	} else if(tel.length == 12) {
		formatTel = tel.replace(/(^[0-9]{4})([0-9]{4})([0-9]{4})/, "$1-$2-$3");
		maskTel = formatTel.split("-")[0] + "-****-" + formatTel.split("-")[2];
	} else {
		formatTel = tel.replace(/(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})/, "$1-$2-$3");
		maskTel = formatTel.split("-")[0] + "-";

		for(var i=0; i<formatTel.split("-")[1].length; i++) {
			maskTel += "*";
		}
		maskTel += "-" + formatTel.split("-")[2];
	}

	return maskTel;
}

/**
 * 휴대폰 번호 마스킹
 * @param phone
 * @returns
 */
function util_MaskPhone(phone) {
	if(util_IsNullOrBlank(phone)) return phone;

	var maskPhone = "";
	var origPhone = phone;
	phone = phone.replace(/[^0-9]/g, "");

	if(phone.length == 10 || phone.length == 11) {
		maskPhone = phone.substring(0,  3);

		if(phone.length == 10) {
			maskPhone += "-***-";
			maskPhone += phone.substring(6, 10);
		} else if (phone.length == 11) {
			maskPhone += "-****-";
			maskPhone += phone.substring(7, 11);
		}
	} else {
		maskPhone = origPhone;
	}

	return maskPhone;
}

/**
 * 카드번호 마스킹
 * @param cardNo
 * @returns
 */
function util_MaskCard(cardNo) {
	if(util_IsNullOrBlank(cardNo)) return cardNo;

	var maskCardNo = "";
	var origCardNo = cardNo;
	cardNo = cardNo.replace(/[^0-9]/g, "");

	if(cardNo.length() == 16) {
		maskCardNo = cardNo.substring(0, 4) + "-****-****-" + cardNo.substring(12, 16);
	} else {
		maskCardNo = origCardNo;
	}

	return maskCardNo;
}

/**
 * 계좌번호 마스킹
 * @param account
 * @returns
 */
function util_MaskAccount(account) {
	if(util_IsNullOrBlank(account)) return account;

	var maskAccount = "";
	var origAccount = account;

	account = account.replace(/[^0-9]/g, "");

	if(account.length < 9) {
		maskAccount = origAccount;
	} else {
		maskAccount = account.substring(0, 8);

		for(var i=9; i<account.length + 1; i++) {
			maskAccount += "*";
		}
	}

	return maskAccount;
}

/**
 * 주민번호 마스킹
 * @param juminNo
 * @returns
 */
function util_MaskJuminNo(juminNo) {
	if(util_IsNullOrBlank(juminNo)) return juminNo;

	var maskJuminNo = "";
	var origJuminNo = juminNo;

	juminNo = juminNo.replace(/[^0-9]/g, "");

	if(juminNo.length != 13) {
		maskJuminNo = origJuminNo;
	} else {
		maskJuminNo = juminNo.substring(0, 6) + "-" + juminNo.substring(6, 7) + "******";
	}

	return maskJuminNo;
}