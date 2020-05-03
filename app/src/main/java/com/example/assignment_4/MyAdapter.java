package com.example.assignment_4;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    private String basePath = "https://raw.githubusercontent.com/revolunet/PythonBooks/master/";
    private String [] titles;
    private String [] levels;
    private String [] books_description;
    private String [] Urls;
    private String [] book_Cover;


    MyAdapter(Context context,String [] title,String [] level,String [] description,String [] url,String [] cover)
    {
        this.context=context;
        titles=title;
        levels=level;
        books_description=description;
        Urls=url;
        book_Cover=cover;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View book_Row_View= LayoutInflater.from(context).inflate(R.layout.books_list ,parent,false);
        return new ViewHolder(book_Row_View);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(basePath + book_Cover[position]).into(holder.book_cover);
        Log.i("Path ", basePath + book_Cover[position]);

        holder.Book_title.setText(titles[position]);
        holder.level.setText(levels[position]);
        holder.book_desc.setText(books_description[position]);
        String format = Urls[position].substring(Urls[position].length() - 4);

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView Book_title;
        public TextView level;
        public TextView book_desc;
        public ImageView book_cover;
        public Button btn;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            Book_title = itemView.findViewById(R.id.Book_title);
            level = itemView.findViewById(R.id.level);
            book_desc = itemView.findViewById(R.id.Book_description);
            book_cover = itemView.findViewById(R.id.Cover);
            btn = itemView.findViewById(R.id.btn);

        }
    }
}
