package blackharold.home.lollpaper;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.IOException;

public class MainActivity extends Activity implements ViewSwitcher.ViewFactory {

    private ImageSwitcher mImageSwitcher;
    int pos = 0;
    private int[] mImageIds = {
            R.drawable.lollipop1,
            R.drawable.lollipop2,
            R.drawable.lollipop3,
            R.drawable.lollipop4,
            R.drawable.lollipop5
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSwitcher = findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);


        mImageSwitcher.setImageResource(mImageIds[0]);

//        Анонимная кнопка
        findViewById(R.id.okBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wpmngr = WallpaperManager.getInstance(getApplicationContext());

                try {
                    wpmngr.setResource(mImageIds[pos]);

                    Context context = getApplicationContext();
                    CharSequence text = "Фон установлен!";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                setPositionNext();
                mImageSwitcher.setImageResource(mImageIds[pos]);
                break;
            case R.id.btnPrev:
                setPositionPrev();
                mImageSwitcher.setImageResource(mImageIds[pos]);
                break;

            default:
                break;
        }
    }

    public void setPositionNext() {
        pos++;
        if (pos > mImageIds.length - 1) {
            pos = 0;
        }
    }

    public void setPositionPrev() {
        pos--;
        if (pos < 0) {
            pos = mImageIds.length - 1;
        }
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout
                        .LayoutParams.MATCH_PARENT,
                LinearLayout
                        .LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        return imageView;
    }
}
