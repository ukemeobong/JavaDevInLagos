package com.uetechnologies.ukemeobong.javadevinlagos;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.saket.bettermovementmethod.BetterLinkMovementMethod;


public class UserDetails extends AppCompatActivity {

    ImageView ImageView;
    TextView userNameView;
    TextView userUrlView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareProfile();
            }
        });


        ImageView = (ImageView) findViewById(R.id.userImageDetails);
        userNameView = (TextView) findViewById(R.id.userTextViewDetails);
        userUrlView = (TextView) findViewById(R.id.urlTextViewDetails);

        UserInformation user = getIntent().getParcelableExtra("user");

        Picasso.with(this).load(user.getAvatarUrl()).into(ImageView);
        userNameView.setText(getString(R.string.user_name_full, user.getLogin()));
        userUrlView.setText(getString(R.string.user_url_full, user.getHtmlUrl()));
        BetterLinkMovementMethod movementMethod = BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, userUrlView);
        movementMethod.setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
            @Override
            public boolean onClick(TextView textView, String url) {
                getCustomTabIntentInstance().launchUrl(UserDetails.this, Uri.parse(url));
                return true;
            }
        });
    }

    private CustomTabsIntent getCustomTabIntentInstance() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        return builder.build();
    }

    // This method helps to share the developers details to other apps
    private void shareProfile() {

        UserInformation user = getIntent().getParcelableExtra("user");


        String message = "Check out this awesome developer @" + user.getLogin()+", " + user.getUrl();

        ShareUrl.share(message, this);

    }
}