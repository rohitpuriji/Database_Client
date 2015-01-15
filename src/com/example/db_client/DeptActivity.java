package com.example.db_client;

import java.util.ArrayList;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DeptActivity extends Activity{


	ArrayAdapter<String> adapter;
	ListView listv;
	Context context;
	ArrayList<String> data;
	DBHelper sqlite;
	int id = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dept);

		sqlite = new DBHelper(DeptActivity.this);

		setupActionBar();
		data = new ArrayList<String>();
		listv = (ListView) findViewById(R.id.lv_dept);
		context = this;

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
		listv.setAdapter(adapter);
		Toast.makeText(this,"Loading Please Wait..",Toast.LENGTH_SHORT).show();
		new AsyncLoadDeptDetails().execute();

	}

	public class AsyncLoadDeptDetails extends AsyncTask<Void, JSONObject, ArrayList<DeptTable>> {
		ArrayList<DeptTable> deptTable = null;
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {

			progressDialog = new ProgressDialog(DeptActivity.this);
			progressDialog.setMessage("Loading...");
			progressDialog.setCancelable(false);
			progressDialog.show();
			Log.e( "pre execute","yee"); 

		}

		@Override
		public ArrayList<DeptTable> doInBackground(Void... params) {

			RestAPI api = new RestAPI();
			try {
				Log.e( "In background try block","yee"); 

				JSONObject jsonObj = api.GetDepartmentDetails();
				JSONParser parser = new JSONParser();
				Log.e( "department list", jsonObj.toString()); 
				deptTable = parser.parseDepartment(jsonObj);
				sqlite();

			} catch (Exception e) {
				Log.e( "bg error", e.getMessage()); 

			}
			return deptTable;
		}


		private void sqlite() {
			// TODO Auto-generated method stub

			for(int i=0; i<deptTable.size(); i++) {
				sqlite.insertContact(deptTable.get(i).getName(),id+"");
				id++;
			}

			sqlite.close();
		}   

		@Override
		protected void onPostExecute(ArrayList<DeptTable> result) {
			// TODO Auto-generated method stub

			for (int i = 0; i < result.size(); i++) {
				data.add(result.get(i).getNo() + " " + result.get(i).getName());
			}

			adapter.notifyDataSetChanged();
			if(progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	public Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}




}
