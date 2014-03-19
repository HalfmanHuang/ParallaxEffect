package org.beifeng.parallax;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 模仿饭本的视差滚动效果实现页面
 * 
 * @author HalfmanHuang
 * @since SDK19 JDK7
 * @version 1.0.0
 */
public class RiceBookActivity extends Activity {
	/** 滑动页面容器 */
	private ViewPager pager;
	/** 页面适配器 */
	private RiceBookAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置布局
		setContentView(R.layout.activity_ricebook);
		// 获取页面实例
		pager = (ViewPager) findViewById(R.id.pager);
		// 设置页面滑动监听器
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if (arg0 < adapter.getCount() - 1) {
					// 获取当前子页面的背景图对象
					ImageView prev = adapter.views.get(arg0).image;
					// 获取当前子页面右边的子页面的背景图对象
					ImageView next = adapter.views.get(arg0 + 1).image;
					// 判断API版本，两种处理
					if (VERSION.SDK_INT >= 11) {
						// API大于等于11的情况，即Android 3.0以上
						// 通过调用View.setX()方法来实现图片位移,此方法API 11添加
						// 设置当前页面背景图片的位移
						prev.setX((int) ((float) arg2 / 2.0f));
						// 设置当前子页面右边的子页面的背景图的位移
						float halfNext = next.getWidth() / 2.0f;
						float currentNextPos = halfNext - arg2 / 2.0f;
						next.setX(-(int) currentNextPos);
					} else {
						// API小于等于11的情况，即低于Android 3.0的版本
						// 通过设置ImageView的Margin来实现图片位移
						// 获取布局参数（关系布局）
						LayoutParams paramPrev = (LayoutParams) prev.getLayoutParams();
						LayoutParams paramNext = (LayoutParams) next.getLayoutParams();
						// 设置左视图位移
						paramPrev.leftMargin = (int) ((float) arg2 / 2.0f);
						paramPrev.rightMargin = -paramPrev.leftMargin;
						prev.setLayoutParams(paramPrev);
						// 设置右视图位移
						float halfNext = next.getWidth() / 2.0f;
						float currentNextPos = halfNext - arg2 / 2.0f;
						paramNext.leftMargin = -(int) currentNextPos;
						paramNext.rightMargin = (int) currentNextPos;
						next.setLayoutParams(paramNext);
					}
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		// 创建页面适配器
		adapter = new RiceBookAdapter(this);
		// 添加第一页
		adapter.addPage("是否还在为一个人学习的困境所烦恼？", R.drawable.back_0);
		// 添加第二页
		adapter.addPage("是否还在为自己的努力无人问津而苦恼？", R.drawable.back_1);
		// 添加第三页
		adapter.addPage("是否还在无法满足企业要求而迷茫？", R.drawable.back_2);
		// 添加第四页
		adapter.addPage("成功，从北风开始！", R.drawable.back_3);

		pager.setAdapter(adapter);
	}
	/**
	 * 页面适配器类
	 * 
	 * @author HalfmanHuang
	 * @version 1.0.0
	 * @since JDK7 SDK19
	 */
	public class RiceBookAdapter extends PagerAdapter {
		/** 子页面列表，注意此处类型为页面描述类 */
		public ArrayList<ViewHolder> views;
		/** 滑动子页面布局反射器 */
		private LayoutInflater inflater;

		/**
		 * 构造方法
		 * 
		 * @param context Activity页面引用
		 */
		public RiceBookAdapter(Context context) {
			// 实例化列表
			views = new ArrayList<ViewHolder>();
			// 获取布局反射器实例
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// 返回子页面数量
			return views.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 销毁处理
			container.removeView(views.get(position).root);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 子页面生成处理
			container.addView(views.get(position).root);
			return views.get(position).root;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// 判断生成处理返回的object与某个子页面的关系
			return arg0 == arg1;
		}

		/**
		 * 创建添加一个子页面到页面适配器
		 * 
		 * @param text 子页面上显示的文字
		 * @param res 当前页背景图ID
		 */
		public void addPage(String text, int res) {
			// 实例化一个页面描述类
			ViewHolder holder = new ViewHolder();
			// 通过布局反射器获取pageitem_ricebook布局根视图对象
			holder.root = inflater.inflate(R.layout.pageitem_ricebook, null);
			// 获取textview实例
			holder.text = (TextView) holder.root.findViewById(R.id.text);
			// 获取子页面背景图实例
			holder.image = (ImageView) holder.root.findViewById(R.id.image);
			// 设置子页面背景图内容
			holder.image.setImageResource(res);
			// 设置子页面文字
			holder.text.setText(text);
			// 若适配器列表不为空
			if (null != views) {
				// 加入本描述类
				adapter.views.add(holder);
			}
		}
	}

	/**
	 * 页面描述类
	 * 
	 * @author HalfmanHuang
	 * @since JDK7 SDK19
	 * @version 1.0.0
	 */
	public class ViewHolder {
		public View root;
		public ImageView image;
		public TextView text;
	}
}
