package com.androidtestapp.fragmentswitcher;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment implements View.OnClickListener {

    FragmentSwitcher switcher;
    TextView textView;

    interface FragmentSwitcher {
        void switchFragment();
    }

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        switcher = (FragmentSwitcher) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_b, container, false);
         textView = (TextView) view.findViewById(R.id.text);
        textView.setOnClickListener(this);

        return view;

    }
    public void updateTextView(){
        textView.setText("I am now on the left!");
    }

    @Override
    public void onClick(View v) {
        switcher.switchFragment();
    }
}
