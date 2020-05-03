package com.example.assignment_4;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.get().load(basePath + book_Cover[position]).into(holder.book_cover);
        Log.i("Path ", basePath + book_Cover[position]);

        holder.Book_title.setText(titles[position]);
        holder.level.setText(levels[position]);
        holder.book_desc.setText(books_description[position]);



        String CompleteUrl[] =Urls[position].split("\\.");
        String format = CompleteUrl[CompleteUrl.length - 1];

        if(format.equals("zip") || format.equals("pdf"))
        {
            holder.btn.setText("DOWNLOAD");
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String DownloadUrl = Urls[position];
                    DownloadManager downloadManager = (DownloadManager)context.getSystemService(context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(DownloadUrl);
                    DownloadManager.Request downloadRequest = new DownloadManager.Request(uri);
                    downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    downloadManager.enqueue(downloadRequest);
                }
            });
        }
        else{
            holder.btn.setText("READ ONLINE");
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Url = Urls[position];
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Url));
                    ContextCompat.startActivity(context, intent, null);
                }
            });
        }

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
