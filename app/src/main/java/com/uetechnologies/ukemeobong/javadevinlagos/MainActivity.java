package com.uetechnologies.ukemeobong.javadevinlagos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgress;
    private TextView emptyTextView;

    boolean isConnected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Displays the loading progress bar
        mProgress = new ProgressDialog(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Displays empty textview if no user is found
        emptyTextView=(TextView)findViewById(R.id.emptyTextView);

        //Checks the Connection status
        isConnected = ConnectionTest.isNetworkAvailable(this);

        fetchUsers();


    }

    private void fetchUsers() {

        if (isConnected) {
            mProgress.setMessage(getString(R.string.progress_text) );
            mProgress.show();

            String searchParams = "language:java location:lagos"; //filter
            RestApiService apiService = new RestApiBuilder().getService(); //Instantiates the service interface for the url
            Call<UserList> userListCall = apiService.getUserList(searchParams);
            userListCall.enqueue(new Callback<UserList>() {

                @Override
                public void onResponse(Call<UserList> call, Response<UserList> response) {
                    if (response.isSuccessful()) {
                        UserList userList = response.body();
                        setAdapterData(userList);
                        mProgress.dismiss();
                    }
                    else {
                        mProgress.dismiss();
                        Toast.makeText(MainActivity.this, getString(R.string.bad_request), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserList> call, Throwable t) {
                    mProgress.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.request_fail), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {

            Toast.makeText(this,getString(R.string.offline_text),Toast.LENGTH_LONG).show();

            return;
        }
    }


    private void setAdapterData(UserList userList) {
        UserAdapter adapter = new UserAdapter(userList.getItems());
        mRecyclerView.addItemDecoration(new ItemDivider());
        mRecyclerView.setAdapter(adapter);
        if(adapter.getItemCount()==0) {

            emptyTextView.setVisibility(VISIBLE);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates the menu by adding items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, as long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {

            fetchUsers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
