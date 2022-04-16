package com.tehin.aurealis.builderscore.scoreboard.score;

public class CustomScore {
	
	private String prefix, content;
	private int position;
	private boolean removePoints = false;
	
	public CustomScore(String prefix, String content, int position) {
		this.prefix = prefix;
		this.content = content;
		this.position = position;
	}
	
	public CustomScore(String prefix, String content, int position, boolean removePoints) {
		this.prefix = prefix;
		this.content = content;
		this.position = position;
		this.removePoints = removePoints;
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

	public boolean isRemovePoints() {
		return removePoints;
	}

	public void setRemovePoints(boolean removePoints) {
		this.removePoints = removePoints;
	}

}
