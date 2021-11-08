package com.giayshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote extends BaseEntity{

	@Column
	private float avg;
	@Column
	private int totalUserVote;
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public int getTotalUserVote() {
		return totalUserVote;
	}
	public void setTotalUserVote(int totalUserVote) {
		this.totalUserVote = totalUserVote;
	}
	
}
