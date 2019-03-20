package kr.co.seoulit.common.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final int ERR_CODE; // 생성자를 통해 초기화 한다.

	public CustomException(int errCode, String msg) { // 생성자

		super(msg);

		ERR_CODE = errCode;

	}

	public CustomException(String msg) { // 생성자

		this(-99, msg); // ERR_CODE를 -99(기본값)으로 초기화한다.

	}

	public int getErrCode() {// 에러 코드를 얻을 수 있는 메서드도 추가한다.

		return ERR_CODE;// 이 메서드는 주로 getMessage()와 함께 사용될 것이다.

	}

}
