package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class DetailActivity4 extends AppCompatActivity {

    private boolean isOpen = false;
    private ConstraintSet constraintSet1, constraintSet2;
    private ConstraintLayout constraintLayout;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail4);

        Window window =  getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        constraintSet1 = new ConstraintSet();
        constraintSet2 = new ConstraintSet();
        imageView = findViewById(R.id.tv_mvPosterDetail);
        constraintLayout = findViewById(R.id.detailConstraint);
        constraintSet2.clone(this, R.layout.detail_expandied);
        constraintSet1.clone(constraintLayout);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    constraintSet2.applyTo(constraintLayout);
                    isOpen = !isOpen;
                } else {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    constraintSet1.applyTo(constraintLayout);
                    isOpen = !isOpen;
                }
            }
        });
    }
}
