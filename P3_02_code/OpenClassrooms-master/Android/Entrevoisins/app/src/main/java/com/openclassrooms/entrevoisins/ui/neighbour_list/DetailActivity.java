package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEIGHBOUR_ID = "EXTRA_NEIGHBOUR_INDEX";
    private Neighbour neighbour;
    private NeighbourApiService mApiService;

    @BindView(R.id.photo)
    ImageView mImageAvatar;
    @BindView(R.id.name)
    TextView mNamePhoto;
    @BindView(R.id.name_1)
    TextView mNameCard1;
    @BindView(R.id.mail)
    TextView mMail;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private long neighbourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        neighbourId = getIntent().getLongExtra(EXTRA_NEIGHBOUR_ID, 0);
        mApiService = DI.getNeighbourApiService();
        ButterKnife.bind(this);
        neighbour = mApiService.getNeighboursById(neighbourId);
        initView();
        initListener();

    }

    private void initListener() {
        //button fab favorite
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService.changeStatus(neighbour.getId());
                neighbour = mApiService.getNeighboursById(neighbourId);
                initView();
            }
        });
    }

    // Update the items with the Neighbour information
    private void initView() {
        this.configureToolbar();

        mNamePhoto.setText(neighbour.getName());
        mNameCard1.setText(neighbour.getName());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mImageAvatar);
        mMail.setText("www.facebook.fr/" + neighbour.getName());
        initStarView();
    }

    private void initStarView() {
        if (neighbour.isFavorite()) {
            fab.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            fab.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        }
    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

// Back home page view
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}