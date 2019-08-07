package com.example.midterm1;

import android.net.Uri;

public class Contact {

	private Uri imageUri; //address in the phone's image gallery folder, usual in the SD card
	private String name,phone;
	public Contact(Uri imageUri, String name, String phone) {
		super();
		this.imageUri = imageUri;
		this.name = name;
		this.phone = phone;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Uri getImageUri() {
		return imageUri;
	}
	public void setImageUri(Uri imageUri) {
		this.imageUri = imageUri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	  
	
	

}
