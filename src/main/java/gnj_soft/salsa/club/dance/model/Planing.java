package gnj_soft.salsa.club.dance.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a database mapping for Planing table. It is part of salsa-club
 * project. For now I use {@link javax.persistence} instead of Hibernate.
 * 
 * @author Ghislain N.
 */
@Entity
public class Planing implements java.io.Serializable {

	private static final long serialVersionUID = -3162642490465027707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long planingId;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planing")
	private Set<Member> members = new HashSet<>(0);
	@OneToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	@Column
	private String note;
	
	public Planing() {
		// Empty constructor
	}

	public Planing(Long planingId, String note) {
		this.planingId = planingId;
		this.note = note;
	}
	
	public Planing(Long planingId, Set<Member> members, Lesson lesson, String note) {
		this.planingId = planingId;
		this.members = members;
		this.lesson = lesson;
		this.note = note;
	}

	public Long getPlaningId() {
		return planingId;
	}

	public void setPlaningId(Long planingId) {
		this.planingId = planingId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}
