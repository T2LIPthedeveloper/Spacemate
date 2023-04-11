package com.atulparida.spacemate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.atulparida.spacemate.booking_tabs.*;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private Bundle bundle;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Bundle bundle) {
        super(fragmentActivity);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                upcoming_fragment upcomingFragment = new upcoming_fragment();
                //pass bundle into upcoming fragment
                if (bundle != null) upcomingFragment.setArguments(bundle);
                return upcomingFragment;
            case 1:
                return new new_book_fragment();
            case 2:
                return new bookmarks_fragment();
            default:
                return new new_book_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
