package lkm.ww.comn.exception;

/**
 * 파일핸들러Exception
 * @author lkm
 * @since 2020. 04. 02
 */
public class FileHandlerException extends Exception {

	private static final long serialVersionUID = 8821963601332375236L;

	public static final String FILE_ETC_EXCEPTION_NAME = "허용하지 않는 확장자입니다.";
	public static final String FILE_EXTENSION_EXCEPTION_NAME = "허용하지 않는 확장자입니다.";
	public static final String FILE_MAX_SIZE_EXCEPTION_NAME = "최대 크기를 초과하였습니다.";

	/** 상태코드 **/
	public static final int DEFAULT_CODE = -500;

	private String msgCode;
	private Object[] format;

	public FileHandlerException(String msgCode, Object[] format) {
		this(msgCode, format, null);
	}

	public FileHandlerException(String msgCode, Object[] format, Throwable t) {
		super(msgCode, t);
		this.msgCode = msgCode;
		this.format = format;
	}

	/**
	 * 상태코드.
	 */
	public int getCode() {
		return DEFAULT_CODE;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public Object[] getFormat() {
		return format;
	}

	public boolean isMessagePriority() {
		return false;
	}

}