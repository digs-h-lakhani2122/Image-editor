package coatocl.exaatocl.imagesticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.rtugeek.android.colorseekbar.ColorSeekBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class custom_gallery extends AppCompatActivity
{
    float dX,dY;
    RelativeLayout layoutSaved;
    ImageView imageView;
    OutputStream outputStream;
    ImageView cakeImage, babyFaceImage, ridingImage, editImage;
    ImageButton cancel, save, cake, babyFace, riding, edit;
    ColorSeekBar colorSeekBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gallery);

        ActivityCompat.requestPermissions(custom_gallery.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(custom_gallery.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        imageView = findViewById(R.id.imageView);
        layoutSaved = findViewById(R.id.layoutSaved);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        cakeImage = findViewById(R.id.cakeImage);
        babyFaceImage = findViewById(R.id.babyFaceImage);
        ridingImage = findViewById(R.id.ridingImage);
        editImage = findViewById(R.id.editImage);

        edit = findViewById(R.id.edit);
        cake = findViewById(R.id.cake);
        riding = findViewById(R.id.riding);
        babyFace = findViewById(R.id.babyFace);

        colorSeekBar = findViewById(R.id.colorSeekBar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Uri uri = Uri.parse(bundle.getString("uri"));
            imageView.setImageURI(uri);
        }

        cakeImage.setOnTouchListener((view, event) -> {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    dX=view.getX()-event.getRawX();
                    dY=view.getY()-event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.animate().x(event.getRawX()+dX).y(event.getRawY()+dY).setDuration(0).start();
                    break;

                default:
                    return false;
            }
            return true;
        });

        babyFaceImage.setOnTouchListener((view, event) -> {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    dX=view.getX()-event.getRawX();
                    dY=view.getY()-event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.animate().x(event.getRawX()+dX).y(event.getRawY()+dY).setDuration(0).start();
                    break;

                default:
                    return false;
            }
            return true;
        });

        editImage.setOnTouchListener((view, event) -> {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    dX=view.getX()-event.getRawX();
                    dY=view.getY()-event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.animate().x(event.getRawX()+dX).y(event.getRawY()+dY).setDuration(0).start();
                    break;

                default:
                    return false;
            }
            return true;
        });

        ridingImage.setOnTouchListener((view, event) -> {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    dX=view.getX()-event.getRawX();
                    dY=view.getY()-event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.animate().x(event.getRawX()+dX).y(event.getRawY()+dY).setDuration(0).start();
                    break;

                default:
                    return false;
            }
            return true;
        });


        cancel.setOnClickListener(v -> {
            Intent intentBack = new Intent(custom_gallery.this, MainActivity.class);
            startActivity(intentBack);
        });

        save.setOnClickListener(v ->
        {
            Bitmap bitmap = Bitmap.createBitmap(layoutSaved.getWidth(), layoutSaved.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            layoutSaved.draw(canvas);


                File filepath = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)));
//            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/DIGAL-Image_editor/");
            dir.mkdir();
            File file = new File(dir, System.currentTimeMillis() + ".jpg");
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Toast.makeText(custom_gallery.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            Intent intent12 = new Intent(custom_gallery.this,MainActivity.class);
            startActivity(intent12);
            try {
                outputStream.flush();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cake.setOnClickListener(v -> {
            cakeImage.setImageResource(R.drawable.ic_cake_black_24dp);

            colorSeekBar.setOnColorChangeListener((progress, color) -> cakeImage.setColorFilter(color));
        });

        edit.setOnClickListener(v -> {
            editImage.setImageResource(R.drawable.ic_edit_black_24dp);

            colorSeekBar.setOnColorChangeListener((progress, color) -> editImage.setColorFilter(color));
        });

        riding.setOnClickListener(v -> {
            ridingImage.setImageResource(R.drawable.ic_directions_bike_black_24dp);

            colorSeekBar.setOnColorChangeListener((progress, color) -> ridingImage.setColorFilter(color));
        });

        babyFace.setOnClickListener(v -> {
            babyFaceImage.setImageResource(R.drawable.ic_child_care_black_24dp);

            colorSeekBar.setOnColorChangeListener((progress, color) -> babyFaceImage.setColorFilter(color));
        });
    }
}