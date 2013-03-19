package com.example.playground;

import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.IntEvaluator;

/**
 * Custom Evaluator for changing a ViewGroup's height.
 */
public class HeightEvaluator extends IntEvaluator {
	private final View view;
	 
	public HeightEvaluator(View dashboard) {
		this.view = dashboard;
	}
 
	@Override
	public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
	  ViewGroup.LayoutParams params = view.getLayoutParams();
	  params.height = super.evaluate(fraction, startValue, endValue);
	  view.setLayoutParams(params);
	  return params.height;
	}
}
