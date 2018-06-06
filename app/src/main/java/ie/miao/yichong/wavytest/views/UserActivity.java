package ie.miao.yichong.wavytest.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ie.miao.yichong.wavytest.R;
import ie.miao.yichong.wavytest.expections.UnexpectedException;
import ie.miao.yichong.wavytest.interfaces.UserActivityInterface;
import ie.miao.yichong.wavytest.models.User;
import ie.miao.yichong.wavytest.presenters.UserActivityPresenter;

import static ie.miao.yichong.wavytest.utils.ExceptionHandler.reportException;


public class UserActivity extends AppCompatActivity implements UserActivityInterface {

    private UserActivityPresenter userActivityPresenter;

    private ImageView userAvatar;
    private TextView userNameTextView, emailTextView, phoneTextView;

    private ImageView background;
    private Button deleteButton;

    private String id;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

        //init presenter
        userActivityPresenter = new UserActivityPresenter(this);

        //retrieve user data
        userActivityPresenter.getUserInfo();

    }

    //init layout
    private void initView() {
        userAvatar = findViewById(R.id.avatar_imageView);
        background = findViewById(R.id.backgroundImageView);
        userNameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        deleteButton = findViewById(R.id.deleteButton);

        //set listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setClickable(false);


                //Show dialog
                progressDialog = new ProgressDialog(UserActivity.this);
                progressDialog.show();


                if (!id.equals("")) {
                    userActivityPresenter.deleteUser(id);
                } else {

                    //Report exception
                    reportException(new UnexpectedException("User id is NULL"));

                    showToast(getResources().getString(R.string.delete_failed));
                }
            }
        });
    }


    @Override
    public void displayUser(User user) {
        //set avatar
        setImage(user.getProfilePicture());


        userNameTextView.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        emailTextView.setText(user.getEmail());
        phoneTextView.setText(user.getPhoneNumber());

        id = user.getId();
    }


    /**
     * Set image into avatar and background
     */
    private void setImage(String url) {

        //Load round image
        Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(userAvatar);
        Glide.with(this).load(url).into(background);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //unsubcription
        if (null != userActivityPresenter) {
            userActivityPresenter.unsubcription();
        }
    }

    @Override
    public void deletePhoneNumber(boolean success) {
        deleteButton.setClickable(true);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (success) {
            phoneTextView.setText("");
            deleteButton.setVisibility(View.GONE);
            showToast(getResources().getString(R.string.delete_success));
        } else {
            showToast(getResources().getString(R.string.delete_failed));
        }

    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataLoaded(boolean success) {
        if (success) {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            findViewById(R.id.background).setVisibility(View.VISIBLE);
            findViewById(R.id.loadFailedView).setVisibility(View.GONE);
        } else {


            //Load data failed
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            findViewById(R.id.background).setVisibility(View.GONE);
            findViewById(R.id.loadFailedView).setVisibility(View.VISIBLE);


            //Set listener
            findViewById(R.id.retryButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //Lock the button
                    view.setClickable(false);


                    findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.loadFailedView).setVisibility(View.GONE);

                    view.setClickable(true);
                    //unlock the button


                    //retrieve user data
                    userActivityPresenter.getUserInfo();
                }
            });
        }
    }


}
