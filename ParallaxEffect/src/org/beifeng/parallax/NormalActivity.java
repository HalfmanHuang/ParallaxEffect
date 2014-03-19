package org.beifeng.parallax;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 普通滚动效果页面
 * @author HalfmanHuang
 * @since SDK19 JDK7
 * @version 1.0.0
 */
public class NormalActivity extends Activity {
	/** viewpager对象 */
	private ViewPager pager;
	/** viewpager的适配器对象 */
	private NormalAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置布局
		setContentView(R.layout.activity_normal);
		// 获取pager对象
		pager = (ViewPager)findViewById(R.id.pager);
		// 实例化适配器对象
		adapter = new NormalAdapter();
		// 手工代码创建第一个子页面
		ImageView v1 = new ImageView(this);
		v1.setLayoutParams(new LayoutParams(-1,-1));
		v1.setScaleType(ScaleType.FIT_XY);
		v1.setImageResource(R.drawable.back_0);
		adapter.views.add(v1);
		// 手工代码创建第二个子页面
		ImageView v2 = new ImageView(this);
		v2.setLayoutParams(new LayoutParams(-1,-1));
		v2.setScaleType(ScaleType.FIT_XY);
		v2.setImageResource(R.drawable.back_1);
		adapter.views.add(v2);
		// 手工代码创建第三个子页面
		ImageView v3 = new ImageView(this);
		v3.setLayoutParams(new LayoutParams(-1,-1));
		v3.setScaleType(ScaleType.FIT_XY);
		v3.setImageResource(R.drawable.back_2);
		adapter.views.add(v3);
		// 手工代码创建第四个子页面
		ImageView v4 = new ImageView(this);
		v4.setLayoutParams(new LayoutParams(-1,-1));
		v4.setScaleType(ScaleType.FIT_XY);
		v4.setImageResource(R.drawable.back_3);
		adapter.views.add(v4);
		// 设置viewpager的适配器为适配器对象
		pager.setAdapter(adapter);
	}
	/**
	 * ViewPager的适配器类
	 * @author HalfmanHuang
	 * @since SDK19 JDK7
	 * @version 1.0.0
	 */
	public class NormalAdapter extends PagerAdapter {
		/** viewpager子页面视图列表 */
		public ArrayList<View> views;
		/**
		 * 构造方法
		 */
		public NormalAdapter() {
			// 实例化列表
			views = new ArrayList<View>();
		}
		@Override
		public int getCount() {
			// 此处返回列表的长度，表示viewpager子页面的数量
			return views.size();
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 此处处理子页面销毁的处理，此处是将某个子页面移出，但保留在列表中
			container.removeView(views.get(position));
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 此处处理子页面生成的处理，讲某个子页面插入到viewpager中，并将其返回
			container.addView(views.get(position));
			return views.get(position);
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// 此处判断instantiateItem返回的对象与某个子页面视图的关系
			return arg0 == arg1;
		}
	}
}
