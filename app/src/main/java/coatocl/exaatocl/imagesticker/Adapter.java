package coatocl.exaatocl.imagesticker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private ArrayList<String> imageList;
    private Context context;

    Adapter(Context context, ArrayList<String> imageList)
    {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.define,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        File imgFile = new File(imageList.get(position));

        if(imgFile.exists())
        {
            Glide.with(context).load(imgFile).into(holder.imageView);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, default_gallery.class);
            i.putExtra("imgPath", imageList.get(position));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.picture);

        }
    }
}
