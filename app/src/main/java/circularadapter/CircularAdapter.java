package circularadapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.arjuncv.circularviewpager.CoverFragment;

import java.util.List;

/**
 * Created by arjun.c on 8/17/2017.
 */

public class CircularAdapter extends BaseCircularPagerAdapter<String> {
    private int fakePageCount;

    public CircularAdapter(FragmentManager fragmentManager, List list, int fakePageCount) {
        super(fragmentManager, list, fakePageCount);
        this.fakePageCount = fakePageCount;
    }

    public Fragment getFragment(ViewPager viewPager, int index) {
        /* Fragments are cached in Adapter, so instantiateItem will
           return the cached one (if any) or a new instance if necessary.
         */
        Fragment fragment = (Fragment) instantiateItem(viewPager, index + fakePageCount);
        return fragment;
    }

    @Override
    protected Fragment getFragmentForItem(String s, int actualPosition) {
        CoverFragment fragment = new CoverFragment();
        Bundle args = new Bundle();
        args.putString("brand", s);
        fragment.setArguments(args);
        return fragment;
    }
}
