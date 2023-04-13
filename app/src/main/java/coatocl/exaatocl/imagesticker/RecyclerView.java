package coatocl.exaatocl.imagesticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {

    androidx.recyclerview.widget.RecyclerView recycler;
    ArrayList<String> imageList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler = findViewById(R.id.recycler);
        imageList = new ArrayList<>();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, imageList);
        recycler.setAdapter(adapter);
        getImagePath();

    }

    private void getImagePath()
    {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
        assert cursor != null;
        int count = cursor.getCount();
        for (int i = 0; i < count; i++)
        {

            cursor.moveToPosition(i);

            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            imageList.add(cursor.getString(dataColumnIndex));
        }
        adapter.notifyDataSetChanged();
        cursor.close();

    }
}