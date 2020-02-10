package com.ssafy.edu.model;

public class File {
	private int fno;
	private String budget_email;
	private String budget_title;
	private String fileName; // 저장할 파일
	private String fileOriName; // 실제 파일
	private String fileUrl;

	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File(int fno, String budget_email, String budget_title, String fileName, String fileOriName,
			String fileUrl) {
		super();
		this.fno = fno;
		this.budget_email = budget_email;
		this.budget_title = budget_title;
		this.fileName = fileName;
		this.fileOriName = fileOriName;
		this.fileUrl = fileUrl;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getBudget_email() {
		return budget_email;
	}

	public void setBudget_email(String budget_email) {
		this.budget_email = budget_email;
	}

	public String getBudget_title() {
		return budget_title;
	}

	public void setBudget_title(String budget_title) {
		this.budget_title = budget_title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String toString() {
		return "File [fno=" + fno + ", budget_email=" + budget_email + ", budget_title=" + budget_title + ", fileName="
				+ fileName + ", fileOriName=" + fileOriName + ", fileUrl=" + fileUrl + "]";
	}

}
