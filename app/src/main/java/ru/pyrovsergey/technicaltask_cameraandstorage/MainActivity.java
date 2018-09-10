package ru.pyrovsergey.technicaltask_cameraandstorage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewContract {

    private final int TYPE_PHOTO = 1;
    private final int REQUEST_CODE_PHOTO = 1;
    private CardView currentImageCardView;
    private ImageView currentImageView;
    private Button imageSelectButton;
    private Button imageRotateImageButton;
    private Button imageInvertColorsButton;
    private Button imageMirrorButton;
    private RecyclerView recyclerViewResultImage;
    private GridLayoutManager layoutManager;
    private Presenter presenter;
    private File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        presenter = App.getComponent().getPresenter();
        presenter.onAttach(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.select_image_button:
                presenter.onClickImageSelectButton();
                Log.d("MyTAG", "onClickImageSelectButton");
                break;
            case R.id.image_rotate_button:
                presenter.onClickImageRotateImageButton();
                Log.d("MyTAG", "onClickImageRotateImageButton");
                break;
            case R.id.image_invert_colors_button:
                presenter.onClickImageInvertColorsButton();
                Log.d("MyTAG", "onClickImageInvertColorsButton");
                break;
            case R.id.mirror_image_button:
                presenter.onClickImageMirrorButton();
                Log.d("MyTAG", "onClickImageMirrorButton");
                break;
        }
    }

    private void init() {
        currentImageCardView = findViewById(R.id.image_card_view);
        currentImageView = findViewById(R.id.current_image_view);
        imageSelectButton = findViewById(R.id.select_image_button);
        imageRotateImageButton = findViewById(R.id.image_rotate_button);
        imageInvertColorsButton = findViewById(R.id.image_invert_colors_button);
        imageMirrorButton = findViewById(R.id.mirror_image_button);
        imageSelectButton.setOnClickListener(this);
        imageRotateImageButton.setOnClickListener(this);
        imageInvertColorsButton.setOnClickListener(this);
        imageMirrorButton.setOnClickListener(this);
        createDirectory();
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void getPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
        Log.d("MyTAG", "startActivityForResult");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.d("MyTAG", "Intent is null");
                } else {
                    Log.d("MyTAG", "Photo uri: " + intent.getData());
                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            Log.d("MyTAG", "bitmap " + bitmap.getWidth() + " x "
                                    + bitmap.getHeight());
                            currentImageView.setImageBitmap(bitmap);
                            imageSelectButton.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("MyTAG", "Canceled");
            }
        }
    }

    private Uri generateFileUri(int type) {
        File file = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyTestFolder");
        if (!directory.exists())
            directory.mkdirs();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
