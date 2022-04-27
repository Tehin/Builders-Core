package com.tehin.aurealis.builderscore.scoreboard.score;

public class CustomScore {
	
	private String prefix, content;
	private int position;
	private boolean removeColons = false;
	
	public CustomScore(String prefix, String content, int position) {
		this.prefix = prefix;
		this.content = content;
		this.position = position;
	}
	
	public CustomScore(String prefix, String content, int position, boolean removeColons) {
		this.prefix = prefix;
		this.content = content;
		this.position = position;
		this.removeColons = removeColons;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isRemoveColons() {
		return removeColons;
	}

	public void setRemoveColons(boolean removeColons) {
		this.removeColons = removeColons;
	}

}
