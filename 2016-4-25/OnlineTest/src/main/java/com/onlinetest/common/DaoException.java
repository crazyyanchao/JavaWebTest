package com.onlinetest.common;

/**
 * @author ∂°≈Ù
 *
 */
public class DaoException extends Exception {
	
	private static final long serialVersionUID = 3407115791109845878L;

	public DaoException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public DaoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DaoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}