package com.atulparida.spacemate;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.atulparida.spacemate.booking_tabs.*;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new upcoming_fragment();
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
