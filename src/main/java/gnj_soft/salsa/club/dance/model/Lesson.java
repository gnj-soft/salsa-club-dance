package gnj_soft.salsa.club.dance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a database mapping for Lesson table. It is part of salsa-club
 * project. For now I use {@link javax.persistence} instead of Hibernate.
 * 
 * @author Ghislain N.
 */
@Entity
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long lessonId;
	@Column
	private String lessonName;
	@Column(nullable = false)
	@JsonProperty("level")
	private Integer lessonLevel;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	@Column(nullable = false)
	private Integer duration;

	public Lesson() {
		// Empty constructor
	}

	public Lesson(Long lessonId, String lessonName, Integer lessonLevel, Date startDate, Date endDate,
			Integer duration) {
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.lessonLevel = lessonLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getLessonLevel() {
		return lessonLevel;
	}

	public void setLessonLevel(Integer lessonLevel) {
		this.lessonLevel = lessonLevel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
}
