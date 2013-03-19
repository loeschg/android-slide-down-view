package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.ValueAnimator;

public class MainActivity extends Activity {
	
	/** Whole slide down view. */
	private RelativeLayout slideDownView;
	
	/** View that accepts the touch events. */
	private View handle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		slideDownView = (RelativeLayout) findViewById(R.id.slide_down_view);
		handle = findViewById(R.id.handle);
	}
	
	protected void onResume() {
		super.onResume();
		
		handle.setOnTouchListener(new View.OnTouchListener() {
			/* Starting Y point (where touch started). */
			float yStart = 0;
			
			/* Default height when in the open state. */
			float closedHeight = 300;
			
			/* Default height when in the closed state. */
			float openHeight = 600;
			
			/* The height during the transition (changed on ACTION_MOVE). */
			float currentHeight;
			
			/* The last y touch that occurred. This is used to determine if the view should snap up or down on release.
			 * Used in conjunction with directionDown boolean. */
			float lastY = 0;
			boolean directionDown = false;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch(event.getAction()) {
				
					/* User tapped down on screen. */
					case MotionEvent.ACTION_DOWN:
						// User has tapped the screen
						yStart = event.getRawY();
						lastY = event.getRawY();
						currentHeight = slideDownView.getHeight();
						break;
						
					/* User is dragging finger. */
					case MotionEvent.ACTION_MOVE:
						
						// Calculate the total height change thus far.
						float totalHeightDiff = event.getRawY() - yStart;
						
						// Adjust the slide down height immediately with touch movements.
						LayoutParams params = slideDownView.getLayoutParams();
						params.height = (int)(currentHeight + totalHeightDiff);
						slideDownView.setLayoutParams(params);
						
						// Check and set which direction drag is moving.
						if (event.getRawY() > lastY) {
							directionDown = true;
						} else {
							directionDown = false;
						}
						
						// Set the lastY for comparison in the next ACTION_MOVE event.
						lastY = event.getRawY();
						break;
						
					/* User lifted up finger. */
					case MotionEvent.ACTION_CANCEL:
					case MotionEvent.ACTION_UP:
						
						/*
						 * Need to snap either up or down. Using ValueAnimator to "finish" the action. 
						 * HeightEvaluator is a custom class. 
						 * 
						 * NOTE: I'm using the nineoldandroids library for 
						 */
						if (directionDown) {
							
							// Open the sliding view.
							int startHeight = slideDownView.getHeight(); 
							
							ValueAnimator animation = ValueAnimator.ofObject(
									new HeightEvaluator(slideDownView),
									startHeight, 
									(int) openHeight).setDuration(300);
							
							// See Table 3 for other interpolator options 
							// - http://developer.android.com/guide/topics/graphics/prop-animation.html
							animation.setInterpolator(new OvershootInterpolator(1));
							animation.start();
							
						} else {
							
							// Close the sliding view.
							int startHeight = slideDownView.getHeight(); 
							ValueAnimator animation = ValueAnimator.ofObject(
									new HeightEvaluator(slideDownView),
									startHeight, 
									(int) closedHeight).setDuration(300);
							animation.setInterpolator(new OvershootInterpolator(1));
							animation.start();
						}
						break;

				}
				return true;
			}
		});
	}
}
