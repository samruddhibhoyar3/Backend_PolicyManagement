package com.policymanagement.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UserNominee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNominee {
	public UserNominee() {
		super();
		
	}

	public UserNominee(Long id, String fullName, LocalDate dateOfBirth, @Pattern(regexp = "[MFO]") String gender,
			@Pattern(regexp = "Passport|Aadhar|PAN|DrivingLicence") String idProofType,
			@Size(max = 20) String idProofDocument, @Size(max = 10) String nationality, NomineeRelation nomineeRelation,
			Userprofile userProfile) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.idProofType = idProofType;
		this.idProofDocument = idProofDocument;
		this.nationality = nationality;
		this.nomineeRelation = nomineeRelation;
		this.userProfile = userProfile;
	}

	public UserNominee(String fullName2, LocalDate dateOfBirth2, String gender2, String idProofType2,
			String idProofDocument2, String nationality2, NomineeRelation nomineeRelation2, Userprofile userprofile2) {
		// TODO Auto-generated constructor stub
		super();
		this.fullName = fullName2;
		this.dateOfBirth = dateOfBirth2;
		this.gender = gender2;
		this.idProofType = idProofType2;
		this.idProofDocument = idProofDocument2;
		this.nationality = nationality2;
		this.nomineeRelation = nomineeRelation2;
		this.userProfile = userprofile2;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProofDocument() {
		return idProofDocument;
	}

	public void setIdProofDocument(String idProofDocument) {
		this.idProofDocument = idProofDocument;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public NomineeRelation getNomineeRelation() {
		return nomineeRelation;
	}

	public void setNomineeRelation(NomineeRelation string) {
		this.nomineeRelation = string;
	}
	// public void setNomineeRelation(String nomineeRelation) {
	//Create a new NomineeRelation object based on the provided String
	//this.nomineeRelation = setNomineeRelation(nomineeRelation);
	//}
	// private NomineeRelation convertToNomineeRelation(String nomineeRelation) {
	// Implement the conversion logic here
	// You can create a new NomineeRelation object based on the provided String or
	// retrieve it from a repository

	// Example: Creating a new NomineeRelation object
	// return new NomineeRelation(nomineeRelation);
	//}

	public Userprofile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Userprofile userProfile) {
		this.userProfile = userProfile;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Fullname", length = 50)
	@Size(max = 50)
	private String fullName;

	@Column(name = "DateOfBirth")
	private LocalDate dateOfBirth;

	@Column(name = "Gender", length = 1)
	@Pattern(regexp = "[MFO]")
	private String gender;

	@Column(name = "Idprooftype", length = 10)
	@Pattern(regexp = "Passport|Aadhar|PAN|DrivingLicence")
	private String idProofType;

	@Column(name = "IDProofdocument", length = 20)
	@Size(max = 20)
	private String idProofDocument;

	@Column(name = "Nationality", length = 10)
	@Size(max = 10)
	private String nationality;

	@ManyToOne
	@JoinColumn(name = "Nomineerelationid", referencedColumnName = "id")
	private NomineeRelation nomineeRelation;

	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "username")
	private Userprofile userProfile;

}
