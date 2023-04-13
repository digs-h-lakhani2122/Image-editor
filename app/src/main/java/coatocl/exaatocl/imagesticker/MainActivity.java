package coatocl.exaatocl.imagesticker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST =1 ;
    ImageButton image_Gallery,custom_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_Gallery = findViewById(R.id.image_Gallery);
        custom_gallery = findViewById(R.id.custom_gallery);

        image_Gallery.setOnClickListener(v -> {

            if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            else
            {
                imageChooser();
            }
        });

        custom_gallery.setOnClickListener(v -> {

            if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            else
            {
                Intent intent2 = new Intent(MainActivity.this,RecyclerView.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //do here
            } else {
                Toast.makeText(MainActivity.this, "Allow Access for read storage", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void imageChooser()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"image"),10000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Intent intent = new Intent(MainActivity.this,custom_gallery.class);
        assert uri != null;
        intent.putExtra("uri",uri.toString());
        startActivity(intent);
    }
}
