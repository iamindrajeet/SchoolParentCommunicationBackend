package com.cg.spc.entities;

/**
 * Enumeration for Subject
 * 
 */

public enum Subject {

	ENGLISH("ENG"),HINDI("HIN"),MATHS("MAT"),SOCIAL_STUDIES("SST"),SCIENCE("SCI"),HISTORY_CIVICS("HCV"),GEOGRAPHY("GEO");
	
	public final String subjectCode;
	
	Subject(String subjectCode)
	{
		this.subjectCode=subjectCode;
	}
	
	public String getSubjectCode()
	{
		return this.subjectCode;
	}
}
