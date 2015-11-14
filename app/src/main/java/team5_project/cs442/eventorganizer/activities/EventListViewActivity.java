package team5_project.cs442.eventorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.plus.Plus;

import java.util.List;

import team5_project.cs442.eventorganizer.R;
import team5_project.cs442.eventorganizer.database.Database;
import team5_project.cs442.eventorganizer.event.Event;
import team5_project.cs442.eventorganizer.event.Tuple;

/**
 * Created by sangwon on 10/23/15.
 */
public class EventListViewActivity extends BaseActivity {

    private Tuple mTuple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flag_for_activity);

        //Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        //EventAdapter infoAdapter = (EventAdapter) bundle.getSerializable("EventAdapter");
        mTuple = (Tuple) bundle.getSerializable("Tuple");

        listInstance = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        int resID = R.layout.event_info_list_view;
        final List<Event> events = Database.readList(mTuple.getKey(), mTuple.getValue());
        EventAdapterForListViewActivity eventAdapterForEventInfo = new EventAdapterForListViewActivity(this, resID, events);

        ListView list = (ListView) findViewById(R.id.flag_list_view_activity);
        list.setAdapter(eventAdapterForEventInfo);

        list.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), UpdateActivity.class);
                Event event = events.get(position);
                i.putExtra("Event", event);
                startActivity(i);
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /**
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                Plus.AccountApi.clearDefaultAccount(LoginActivity.mGoogleApiClient);
                Plus.AccountApi.revokeAccessAndDisconnect(LoginActivity.mGoogleApiClient);
                LoginActivity.mGoogleApiClient.disconnect();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

}