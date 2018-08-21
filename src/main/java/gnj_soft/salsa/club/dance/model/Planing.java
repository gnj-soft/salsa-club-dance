package gnj_soft.salsa.club.dance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a database mapping for Planing table. It is part of salsa-club
 * project. For now I use {@link javax.persistence} instead of Hibernate.
 * 
 * @author Ghislain N.
 */
@Entity
public class Planing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long planingId;
	@Column(nullable = false)
	private Long lessonId;
	@Column(nullable = false)
	private Long teacherId;
	@Column(nullable = false)
	private Long memberId;
	@Column
	private String note;
	private String lessonName;
	private String teacherFirstName;
	private String teacherLastName;

	public Planing() {
		// Empty constructor
	}

	public Planing(Long planingId, Long lessonId, Long teacherId, Long memberId, String note) {
		this.planingId = planingId;
		this.lessonId = lessonId;
		this.teacherId = teacherId;
		this.memberId = memberId;
		this.note = note;
	}

	public Long getPlaningId() {
		return planingId;
	}

	public void setPlaningId(Long planingId) {
		this.planingId = planingId;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getTeacherFirstName() {
		return teacherFirstName;
	}

	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
}
