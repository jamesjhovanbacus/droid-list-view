package com.example.midterm1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
	EditText txtSearch;
	ListView lv;
	ArrayList<Contact> reflist=new ArrayList<Contact>();
	ArrayList<Contact> searchlist=new ArrayList<Contact>();
	ContactAdapter adapter;
    Uri imageUri;
	String name,phone;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        this.txtSearch=(EditText) this.findViewById(R.id.editText1);
        this.lv=(ListView) this.findViewById(R.id.listView1);
        this.adapter=new ContactAdapter(this,searchlist);
        this.lv.setAdapter(adapter);
        //
        this.lv.setOnItemClickListener(this); 
        //
        this.txtSearch.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				//clear list
				searchlist.clear();
				Pattern p=Pattern.compile(arg0.toString());
				for(int i=0;i<reflist.size();i++){
					Contact s=reflist.get(i);
					Matcher m=p.matcher(s.getName());
					if(m.find()){
						searchlist.add(s);
					}
				}
				adapter.notifyDataSetChanged();
				
			}});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent=new Intent(this,AddContact.class);
		this.startActivityForResult(intent, 0);
		
		
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==0){
				
				Bundle b=data.getExtras();
				imageUri=b.getParcelable("image");
				name=b.getString("name");
				phone=b.getString("phone");
				//
				Contact contact=new Contact(imageUri,name,phone);
				reflist.add(contact);
				searchlist.add(contact);
				adapter.notifyDataSetChanged();
			}
			
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Contact contact=(Contact) this.lv.getItemAtPosition(arg2);
		String phonenumber = contact.getPhone();
		
		Intent intent=new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+phonenumber));
		this.startActivity(intent);
	}
    
}
