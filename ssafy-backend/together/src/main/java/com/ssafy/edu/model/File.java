package com.ssafy.edu.model;

public class File {
	private int file_num;
	private int review_num;
	private String file_name; // 저장할 파일
	private String file_ori_name; // 실제 파일
	private String file_url;

	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File(int file_num, int review_num, String file_name, String file_ori_name, String file_url) {
		super();
		this.file_num = file_num;
		this.review_num = review_num;
		this.file_name = file_name;
		this.file_ori_name = file_ori_name;
		this.file_url = file_url;
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_ori_name() {
		return file_ori_name;
	}

	public void setFile_ori_name(String file_ori_name) {
		this.file_ori_name = file_ori_name;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	@Override
	public String toString() {
		return "File [file_num=" + file_num + ", review_num=" + review_num + ", file_name=" + file_name
				+ ", file_ori_name=" + file_ori_name + ", file_url=" + file_url + "]";
	}

}
