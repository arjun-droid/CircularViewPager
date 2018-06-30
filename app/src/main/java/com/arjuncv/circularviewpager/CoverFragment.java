package com.arjuncv.circularviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arjuncv.circularviewpager.R;

public class CoverFragment extends Fragment {

    private String cover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        cover = args.getString("brand");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.img_pager, container, false);
        initView(itemView);
        return itemView;
    }

    private void initView(View view) {
        TextView coverTv = view.findViewById(R.id.tv_cover_date);
        coverTv.setText(cover);
    }
}
