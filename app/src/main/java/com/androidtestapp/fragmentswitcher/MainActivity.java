package com.androidtestapp.fragmentswitcher;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentB.FragmentSwitcher {
    private Fragment leftFragment;
    private Fragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initially, you have FragmentA and FragmentB on the screen,
        * after switching, you now have two different instances of FragmentB
        * */

        leftFragment = new FragmentA();
        rightFragment = new FragmentB();

        //begin transaction

        boolean isLandScape = findViewById(R.id.right_frame_layout) != null;
        if (isLandScape) {
            updateFragments();
        }


    }

    @Override
    public void switchFragment() {
        Fragment temporaryReferenceToRightFragment = rightFragment;
        Fragment temporaryReferenceToLeftFragment = leftFragment;
        leftFragment = rightFragment;
        rightFragment = new FragmentB();
        getSupportFragmentManager().beginTransaction().remove(temporaryReferenceToLeftFragment).commit();
        getSupportFragmentManager().beginTransaction().remove(temporaryReferenceToRightFragment).commit();
        getSupportFragmentManager().executePendingTransactions();
        updateFragments();


        //this line confirms the previous fragment on the right is now on the left
        ((FragmentB)getSupportFragmentManager().findFragmentById(R.id.left_frame_layout)).updateTextView();


    }

    void updateFragments() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.left_frame_layout,
                leftFragment,
                null
        ).commit();

        getSupportFragmentManager().beginTransaction().replace(
                R.id.right_frame_layout,
                rightFragment,
                null
        ).commit();
        getSupportFragmentManager().executePendingTransactions();


    }
}
