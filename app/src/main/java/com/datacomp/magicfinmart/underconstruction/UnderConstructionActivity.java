package com.datacomp.magicfinmart.underconstruction;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

public class UnderConstructionActivity extends BaseActivity {
    ImageView ivUnderCons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_construction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivUnderCons = (ImageView) findViewById(R.id.ivUnderCons);
       /* Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.axis_bank);
        Bitmap bitmap = combineImages(icon,createBitmap());
        ivUnderCons.setImageBitmap(bitmap);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
