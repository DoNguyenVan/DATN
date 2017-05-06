/**
 * 
 */
package com.nguyenvando.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="SCORE")
public class Score {

	private Integer scoreId;
	private Integer lanthi;
	private float score;
	private Examination exam;

	public Score() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="scoreId")
	public Integer getScoreId() {
		return scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	@Column(name="lanThi",nullable=true)
	public Integer getLanthi() {
		return lanthi;
	}

	public void setLanthi(Integer lanthi) {
		this.lanthi = lanthi;
	}

	@Column(name="score",nullable=true)
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "examId", nullable = false)
	public Examination getExam() {
		return exam;
	}

	public void setExam(Examination exam) {
		this.exam = exam;
	}
		
}
