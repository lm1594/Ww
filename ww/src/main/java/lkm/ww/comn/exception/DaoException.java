package lkm.ww.comn.exception;

/**
 * DaoException
 * @author lkm
 * @since 2020. 04. 02
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 8397810887117433400L;

	public static final int DEFAULT_ERROR_CODE = 99999;

	/** 에러코드 */
	private final int errorCode;

	/**
	 * Constructor.
	 * @param errorCode 에러코드
	 */
	public DaoException(int errorCode) {
		this(errorCode, (String) null);
	}

	/**
	 * Constructor.
	 * @param message 메시지
	 */
	public DaoException(String message) {
		this(DEFAULT_ERROR_CODE, message);
	}

	/**
	 * Constructor.
	 * @param errorCode 에러코드
	 * @param message 메시지
	 */
	public DaoException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor.
	 * @param errorCode 에러코드
	 * @param cause 예외객체
	 */
	public DaoException(int errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	/**
	 * Constructor.
	 * @param message 메시지
	 * @param cause 예외객체
	 */
	public DaoException(String message, Throwable cause) {
		this(DEFAULT_ERROR_CODE, message, cause);
	}

	/**
	 * Constructor.
	 * @param errorCode 에러코드
	 * @param message 메시지
	 * @param cause 예외객체
	 */
	public DaoException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * 에러코드 반환.
	 * @return 에러코드
	 */
	public int getErrorCode() {
		return this.errorCode;
	}

}
