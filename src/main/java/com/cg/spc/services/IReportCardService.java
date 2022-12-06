package com.cg.spc.services;


import com.cg.spc.entities.ReportCard;

public interface IReportCardService {
	
	public ReportCard addDetails(ReportCard reportCard,int studentId);
	public ReportCard getDetailsById(int id);
	public ReportCard updateDetails(ReportCard reportCard,int studentId);
	public ReportCard deleteDetailsById(int id);
	public ReportCard getReportCardByStudentId(int id);
}
