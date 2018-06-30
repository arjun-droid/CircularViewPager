package circularadapter;

/**
 * Created by arjun.c on 8/17/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public abstract class BaseCircularPagerAdapter<Item> extends FragmentStatePagerAdapter {
    private List<Item> mItems;
    private int fakePageCount;

    public BaseCircularPagerAdapter(final FragmentManager fragmentManager, final List<Item> items, int fakePageCount) {
        super(fragmentManager);
        mItems = items;
        this.fakePageCount = fakePageCount;
    }

    protected abstract Fragment getFragmentForItem(final Item item, int actualPosition);

    @Override
    public Fragment getItem(final int position) {
        final int itemsSize = mItems.size();
        if (position == 0) {
            return getFragmentForItem(mItems.get(itemsSize - 3), position);
        } else if (position == 1) {
            return getFragmentForItem(mItems.get(itemsSize - 2), position);
        } else if (position == 2) {
            return getFragmentForItem(mItems.get(itemsSize - 1), position);
        } else if (position == itemsSize + 3) {
            return getFragmentForItem(mItems.get(0), position);
        } else if (position == itemsSize + 4) {
            return getFragmentForItem(mItems.get(1), position);
        } else if (position == itemsSize + 5) {
            return getFragmentForItem(mItems.get(2), position);
        } else {
            return getFragmentForItem(mItems.get(position - fakePageCount), position);
        }
    }

    @Override
    public int getCount() {
        final int itemsSize = mItems.size();
        return itemsSize > 1 ? itemsSize + (2 * fakePageCount) : itemsSize;
    }

    public int getCountWithoutFakePages() {
        return mItems.size();
    }

    public void setItems(final List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}