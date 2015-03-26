package com.stallone.factorapp;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText enteredFactor;
	private TextView showFactors;
	private String numberString;

	private Vector<Integer> factorsVector = new Vector<Integer>();
	private Vector<Integer> answerVector = new Vector<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		enteredFactor = (EditText) findViewById(R.id.getNumber);
		showFactors = (TextView) findViewById(R.id.showText);

	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	}

	public void getFactors(View v) {
		
		hideKeyboard();

		numberString = enteredFactor.getText().toString();
		if (numberString.matches("")) {
			showFactors.setText("Please enter a number");
		} else {
			clear();
			int number = Integer.parseInt(numberString);

			for (int i = 1; i < number; i++) {
				if (number % i == 0) {
					if (factorsVector.contains(number / i)
							|| answerVector.contains(i)) {
						// don't duplicate numbers
					} else {
						factorsVector.add(i);
						answerVector.add(number / i);
					}
				}
			}

			for (int j = 0; j < factorsVector.size(); j++) {
				showFactors.append("{" + factorsVector.elementAt(j) + ","
						+ answerVector.elementAt(j) + "}");
				showFactors.append("  ");

			}
		}
	}

	public void clear() {
		enteredFactor.setText("");
		showFactors.setText("");
		factorsVector.clear();
		answerVector.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
