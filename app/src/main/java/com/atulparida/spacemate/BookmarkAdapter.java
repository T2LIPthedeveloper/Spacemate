package com.atulparida.spacemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.atulparida.spacemate.R;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {
    private List<Bookmark> bookmarkList;

    public BookmarkAdapter(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bookmark_item, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.titleTextView.setText(bookmark.getTitle());
        holder.descriptionTextView.setText(bookmark.getDescription());
        holder.imageView.setImageResource(bookmark.getImageResource());
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;
        public TextView occupancyTextView;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.bookmark_title);
            descriptionTextView = itemView.findViewById(R.id.bookmark_description);
            occupancyTextView = itemView.findViewById(R.id.occupancy);
            imageView = itemView.findViewById(R.id.bookmark_image);
        }
    }
}
