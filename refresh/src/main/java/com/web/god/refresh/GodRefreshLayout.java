package com.web.god.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by anson on 2019/4/7.
 */
public class GodRefreshLayout extends LinearLayout{

    private BaseRefreshManager mRefreshManager;
    private Context mContext;
    private View mHeadView;
    private int mHeadViewHeight;
    private int minHeadViewHeight; // 头部布局最小的一个高度
    private int maxHeadViewHeight; // 头部布局最大的一个高度
    private RefreshingListener mRefreshingListener; // 正在刷新回调接口
    private RecyclerView mRecyClerView;
    private View mScrollView;
    private int downY ;
    private int interceptDowY ;
    private int interceptDowX ;

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    /**
     * 开启下拉刷新 使用用户自定义的下拉刷新效果
     * @param manager
     */
    public void setRefreshManager(BaseRefreshManager manager) {
        mRefreshManager = manager;
        intHeaderView();
    }

    /*
       开启下拉刷新 下拉刷新的效果 是默认的
     */
    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager(mContext);
        intHeaderView();
    }

    /**
     * 刷新完成后的操作
     */
    public void refreshOver() {
        hideHeadView(getHeadViewLayoutParams());
    }

    public interface RefreshingListener{
        void onRefreshing();
    }
    //自定义回调接口
    public void setRefreshListener(RefreshingListener refreshListener) {
        this.mRefreshingListener = refreshListener;
    }


    private void intHeaderView() {
        setOrientation(VERTICAL);
        mHeadView = mRefreshManager.getHeaderView();
        mHeadView.measure(0,0);
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        minHeadViewHeight = -mHeadViewHeight;
        maxHeadViewHeight = (int) (mHeadViewHeight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,mHeadViewHeight);
        params.topMargin = minHeadViewHeight;
        addView(mHeadView,0,params);
    }

    // 这个方法回调时  可以获取当前ViewGroup子View
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = getChildAt(0);
        //获取RecyclerView
        if (childAt instanceof RecyclerView) {
            mRecyClerView = (RecyclerView) childAt;
        }
        //比如获取 ScrollView
        if (childAt instanceof ScrollView) {
            mScrollView =  childAt;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                return  true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                if (downY == 0) {
                    downY = interceptDowY;
                }
                int dy = moveY - downY;
                if (dy > 0) {
                    LayoutParams layoutParams = getHeadViewLayoutParams();
                    int topMargin = (int) Math.min(dy / 1.8f + minHeadViewHeight, maxHeadViewHeight);
                    //这个事件的处理是为了 不断回调这个 比例 用于 一些 视觉效果
                    if (topMargin <= 0) {
                        // 0 ~ 1 进行变化
                        float percent = ((-minHeadViewHeight) - (-topMargin)) * 1.0f /(-minHeadViewHeight);
                        mRefreshManager.downRefreshPercent(percent);
                    }
                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNREFRESH) {
                        mCurrentRefreshState = RefreshState.DOWNREFRESH;
                        // 提示下拉刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    } else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH) {
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        //提示释放刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    }
                    //阻尼效果
                    layoutParams.topMargin = topMargin;
                    mHeadView.setLayoutParams(layoutParams);
                }
                return  true;
            case MotionEvent.ACTION_UP:
                if (handleEventUp(event)) {
                    return true;
                }
                break;
            default: break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                interceptDowY = (int) ev.getY();
                interceptDowX = (int) ev.getX();
                break;
                case MotionEvent.ACTION_MOVE:
                    //1、确定滑动的一个方向，只有上下滑动才会触发
                    int dy = (int) (ev.getY() - interceptDowY);
                    int dx = (int) (ev.getX() - interceptDowX);
                    if (Math.abs(dy) > Math.abs(dx) && dy >0 ) {
                        if (handleChildViewIsTop()) {
                            //上下滑动
                            return true;
                        }
                    }
                break;
                default: break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    //判断子View 是否是 滑动到顶端的
    private boolean handleChildViewIsTop() {
        if (mRecyClerView != null) {
            return RefreshScrollingUtil.isRecyclerViewToTop(mRecyClerView);
        }

        if (mScrollView != null) {
            return RefreshScrollingUtil.isScrollViewOrWebViewToTop(mScrollView);
        }
        // TODO: 2019/4/21  是否是ScrollView 到达顶端
        return false;
    }


    private boolean handleEventUp(MotionEvent event) {
        downY = 0;
        final LayoutParams layoutParams = getHeadViewLayoutParams();
        if (mCurrentRefreshState == RefreshState.DOWNREFRESH) {
            hideHeadView(layoutParams);
        } else if (mCurrentRefreshState == RefreshState.RELEASEREFRESH) {
            //保持刷新的一个状态
            layoutParams.topMargin = 0;
            mHeadView.setLayoutParams(layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(mCurrentRefreshState);
            if (mRefreshingListener != null) {
                mRefreshingListener.onRefreshing();
            }
        }
        return layoutParams.topMargin > minHeadViewHeight;
    }

    private void hideHeadView(final LayoutParams layoutParams) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin, minHeadViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeadView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentRefreshState = RefreshState.IDDLE;
                handleRefreshState(mCurrentRefreshState);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeadView.getLayoutParams();
    }

    private void handleRefreshState(RefreshState mCurrentRefreshState) {
        switch (mCurrentRefreshState) {
            case IDDLE:
                mRefreshManager.iddleRefresh();
                break;
            case REFRESHING:
                mRefreshManager.refreshing();
                break;
            case DOWNREFRESH:
                mRefreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                mRefreshManager.releaseRefresh();
                break;
                default:
                    break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDDLE;
    //定义下拉刷新的状态 ，依次为  静止、下拉刷新、释放刷新、正在刷新、刷新完成

    private enum RefreshState{
        IDDLE, DOWNREFRESH,RELEASEREFRESH,REFRESHING
    }
}
