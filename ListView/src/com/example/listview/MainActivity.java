package com.example.listview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	//define the properties
	ListView lv;
	//data source
	ArrayList<String> list=new ArrayList<String>();
	//adapter
	ArrayAdapter<String> adapter;
	//
	AlertDialog.Builder builder;
	AlertDialog dialog;
	
	EditText txtName;
	AdapterView.AdapterContextMenuInfo info;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        this.txtName=new EditText(this);
        this.txtName.setHint("NAME");
        this.txtName.setPadding(5, 5, 5, 5);
        
        this.builder=new AlertDialog.Builder(this);
        this.builder.setTitle("ADD NAME");
        this.builder.setView(txtName);
        this.builder.setPositiveButton("Okey", this);
        this.builder.setNegativeButton("Cancel", this);
        //
        this.dialog=this.builder.create();
        
        //
        this.lv=(ListView) this.findViewById(R.id.listView1);
        this.adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        //attach the adapter to the listview
        this.lv.setAdapter(adapter);
        //delegate the context menu
        this.registerForContextMenu(lv);
        
    }

    //CREATE AN OPTION MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    // ASSIGN EVENT LISTENER TO THE CONTEXT MENU
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}
	
	
	//used FOR CREATING CONTEXT MENU
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		info= (AdapterContextMenuInfo) menuInfo;
		String name=this.list.get(info.position);
		menu.setHeaderTitle(name);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		this.dialog.show();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		//check which alert button is pressed
		switch(arg1){
		case DialogInterface.BUTTON_POSITIVE:
			//get the data from the textfield
			String s=this.txtName.getText().toString();
			//validate if there is data
			if(!s.equals("")){
				this.list.add(s);
				//update/refresh the listview
				this.adapter.notifyDataSetChanged();
			}
			else{
				Toast.makeText(this, "Fill Name", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			//close the dialog
			this.dialog.dismiss();
		
		}
		this.txtName.setText("");
		
	}

    
}
