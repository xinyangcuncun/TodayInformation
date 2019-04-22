/**
 * Copyright 2015 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.web.god.refresh;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AbsListView;

import java.lang.reflect.Field;


public class RefreshScrollingUtil {

	private RefreshScrollingUtil() {
	}


	public static boolean isScrollViewOrWebViewToTop(View view) {
		return view != null && view.getScrollY() == 0;
	}

	public static boolean isAbsListViewToTop(AbsListView absListView) {
		if (absListView != null) {
			int firstChildTop = 0;
			if (absListView.getChildCount() > 0) {
				// 如果AdapterView的子控件数量不为0，获取第一个子控件的top
				firstChildTop = absListView.getChildAt(0).getTop() - absListView.getPaddingTop();
			}
			if (absListView.getFirstVisiblePosition() == 0 && firstChildTop == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isRecyclerViewToTop(RecyclerView recyclerView) {
		if (recyclerView != null) {
			RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
			if (manager == null) {
				return true;
			}
			if (manager.getItemCount() == 0) {
				return true;
			}

			if (manager instanceof LinearLayoutManager) {
				LinearLayoutManager layoutManager = (LinearLayoutManager) manager;

				int firstChildTop = 0;
				if (recyclerView.getChildCount() > 0) {
					// 处理item高度超过一屏幕时的情况
					View firstVisibleChild = recyclerView.getChildAt(0);
					if (firstVisibleChild != null && firstVisibleChild.getMeasuredHeight() >= recyclerView.getMeasuredHeight()) {
						if (android.os.Build.VERSION.SDK_INT < 14) {
							return !(ViewCompat.canScrollVertically(recyclerView, -1) || recyclerView.getScrollY() > 0);
						} else {
							return !ViewCompat.canScrollVertically(recyclerView, -1);
						}
					}

					// 如果RecyclerView的子控件数量不为0，获取第一个子控件的top

					// 解决item的topMargin不为0时不能触发下拉刷新
					View firstChild = recyclerView.getChildAt(0);
					RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) firstChild.getLayoutParams();
					firstChildTop = firstChild.getTop() - layoutParams.topMargin - getRecyclerViewItemTopInset(layoutParams) - recyclerView.getPaddingTop();
				}

				if (layoutManager.findFirstCompletelyVisibleItemPosition() < 1 && firstChildTop == 0) {
					return true;
				}
			} else if (manager instanceof StaggeredGridLayoutManager) {
				StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) manager;

				int[] out = layoutManager.findFirstCompletelyVisibleItemPositions(null);
				if (out[0] < 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 通过反射获取RecyclerView的item的topInset
	 *
	 * @param layoutParams
	 * @return
	 */
	private static int getRecyclerViewItemTopInset(RecyclerView.LayoutParams layoutParams) {
		try {
			Field field = RecyclerView.LayoutParams.class.getDeclaredField("mDecorInsets");
			field.setAccessible(true);
			// 开发者自定义的滚动监听器
			Rect decorInsets = (Rect) field.get(layoutParams);
			return decorInsets.top;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



}