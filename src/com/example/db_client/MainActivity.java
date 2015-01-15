package com.example.db_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        Intent i=new Intent(MainActivity.this,DeptActivity.class);
        startActivity(i);
        
        /*    String listItem []={"Departments"};
        ListView lvMain=(ListView)findViewById(R.id.lv_main);
        lvMain.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItem));
 
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
                  @Override
                  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                              long arg3) {
 
                        if(arg2==0)
                        {
                        	Intent i=new Intent(MainActivity.this,DeptActivity.class);
                            startActivity(i);
                        	 
                        	// Intent i=new Intent(MainActivity.this,LoginActivity.class);
                             // startActivity(i);
                        }
                     /*   else if(arg2==1)
                        {
                              Intent i=new Intent(MainActivity.this,CreateUserActivity.class);
                              startActivity(i);
                        } 
                        else if(arg2==2)
                        {
                        	
                        }
                  }
            }); */
    }  
 
   
}