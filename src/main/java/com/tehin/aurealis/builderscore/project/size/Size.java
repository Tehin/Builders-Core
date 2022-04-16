package com.tehin.aurealis.builderscore.project.size;

import java.io.Serializable;

public class Size implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -658288803542738679L;
	
	private int length, width;
	
	public Size(int length, int width) {
		this.length = length;
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}
	
	

}
