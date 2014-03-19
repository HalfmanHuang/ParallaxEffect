package org.beifeng.parallax;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

/**
 * 主页面视图，用来选择跳转效果页面
 * @author HalfmanHuang
 * @since JDK7 SDK19
 * @version 1.0.0
 */
public class MainActivity extends Activity {
	/** 普通滚动视图效果页面启动Button */
	private Button startNormalBtn;
	/** 简单视差滚动效果页面启动Button */
	private Button startSimpleParallaxBtn;
	/** 模仿饭本的滚动视差效果页面启动Button */
	private Button startRiceBookParallaxBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前Activity的布局文件为activity_main.xml
		setContentView(R.layout.activity_main);
		// 通过过findViewById从布局实例中获取startNormalBtn
		startNormalBtn = (Button)findViewById(R.id.startNormalBtn);
		// 设置btn的点击监听
		startNormalBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 启动普通滚动效果页面NormalActivity
				Intent intent = new Intent(MainActivity.this, NormalActivity.class);
				startActivity(intent);
			}
			
		});
		// 通过过findViewById从布局实例中获取startSimpleParallaxBtn
		startSimpleParallaxBtn = (Button)findViewById(R.id.startSimpleParallaxBtn);
		// 设置btn的点击监听
		startSimpleParallaxBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 启动简单滚动视差效果页面SimpleActivity
				Intent intent = new Intent(MainActivity.this, SimpleActivity.class);
				startActivity(intent);
			}
			
		});
		// 通过过findViewById从布局实例中获取startRiceBookParallaxBtn
		startRiceBookParallaxBtn = (Button)findViewById(R.id.startRiceBookParallaxBtn);
		// 设置btn的点击监听
		startRiceBookParallaxBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 启动饭本视差滚动页面RiceBookActivity
				Intent intent = new Intent(MainActivity.this, RiceBookActivity.class);
				startActivity(intent);
			}
			
		});
	}
}
