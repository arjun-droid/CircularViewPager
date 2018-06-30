package circularadapter;

/**
 * Created by arjun.c on 8/16/2017.
 */

import android.support.v4.view.ViewPager;

public class CircularViewPagerOperator implements ViewPager.OnPageChangeListener {
    public static final int SET_ITEM_DELAY = 300;
    private ViewPager mViewPager;
    private int fakePageCount;
    private ViewPager.OnPageChangeListener mListener;

    public CircularViewPagerOperator(final ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public void setOnPageChangeListener(final ViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageSelected(final int position) {
        handleSetCurrentItemWithDelay(position);
        invokeOnPageSelected(position);
    }

    private void handleSetCurrentItemWithDelay(final int position) {
        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                handleSetCurrentItem(position);
            }
        }, SET_ITEM_DELAY);
    }

    public void setNumberOfFakePages(int fakePageCount){
        this.fakePageCount=fakePageCount;
    }

    private void handleSetCurrentItem(final int position) {
        final int lastPosition = mViewPager.getAdapter().getCount() - fakePageCount;
        if (position == fakePageCount-2) {
            mViewPager.setCurrentItem(lastPosition - 2, false);
        }else if (position == fakePageCount-1) {
            mViewPager.setCurrentItem(lastPosition - 1, false);
        } else if (position == lastPosition) {
            mViewPager.setCurrentItem(fakePageCount, false);
        } else if (position == 0) {
            mViewPager.setCurrentItem(lastPosition-2, false);
        }  else if (position == lastPosition + 1) {
            mViewPager.setCurrentItem(fakePageCount+1, false);
        }else if (position == lastPosition + 2) {
            mViewPager.setCurrentItem(fakePageCount+2, false);
        }
    }

    private void invokeOnPageSelected(final int position) {
        if (mListener != null) {
            mListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(final int state) {
        invokeOnPageScrollStateChanged(state);
    }

    private void invokeOnPageScrollStateChanged(final int state) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        invokeOnPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    private void invokeOnPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        if (mListener != null) {
            mListener.onPageScrolled(position - 1, positionOffset, positionOffsetPixels);
        }
    }
}