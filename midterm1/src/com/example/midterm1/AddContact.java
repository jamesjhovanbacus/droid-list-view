package com.example.midterm1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddContact extends Activity implements OnClickListener {
	
	ImageView iv;
	EditText txtName,txtPhone;
	Button btnCancel,btnOkey;
	private Uri imageUri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.contact_layout);
		//
		this.iv=(ImageView) this.findViewById(R.id.imageView1);
		this.txtName=(EditText) this.findViewById(R.id.editText1);
		this.txtPhone=(EditText) this.findViewById(R.id.editText2);
		this.txtPhone=(EditText) this.findViewById(R.id.editText2);
		this.btnCancel=(Button) this.findViewById(R.id.button1);
		this.btnOkey=(Button) this.findViewById(R.id.button2);
		//assign an event listener to the elements;
		this.iv.setOnClickListener(this);
		this.btnCancel.setOnClickListener(this);
		this.btnOkey.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		//check which item is selected
		int id=arg0.getId();
		switch(id){
		case R.id.imageView1: //pick image from image gallery at the SD CARD
			Intent n= new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			this.startActivityForResult(n,100);
			break;
		case R.id.button2: //get encoded text from the EditText
			String name=this.txtName.getText().toString();
			String phone=this.txtPhone.getText().toString();
			//validation
			if(!name.equals("") && !phone.equals("") && this.imageUri!=null){
				//blind intent
				Intent intent=new Intent();
				intent.putExtra("image", this.imageUri);
				intent.putExtra("name", name);
				intent.putExtra("phone", phone);
				this.setResult(RESULT_OK, intent); //send back the data to main activity
				
			}
			
			//break; 
		case R.id.button1: //terminate this activity
			this.finish();
		}
			
		
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
			imageUri=data.getData();
			iv.setImageURI(imageUri);
			
		}
	
	
	
	}
	

}
