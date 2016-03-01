package geogit.com.sharewatcher;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.ArrayList;

import geogit.com.sharewatcher.adapters.ShareValueAdapter;
import geogit.com.sharewatcher.datamodels.ShareData;
import geogit.com.sharewatcher.dialogs.ShareAddDialog;
import geogit.com.sharewatcher.task.RefreshTask;

/**
 * Created by sandeep on 25/2/16.
 */
public class MainActivity extends AppCompatActivity implements ShareAddDialog.ShareAddOkListener {

    private static ImageButton imRefresh = null;
    private static Context context = null;


    private RecyclerView rcList;
    private RecyclerView.LayoutManager  mLayoutManager;
    private ShareValueAdapter mShareValueAdapter;

    private ArrayList<ShareData> shareDatas = null;
    private static Animation rotation;

    private Handler mHandlerRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        //Find views and load initial values
        findAndInitViews();

        //Chack network availability
        if( !ConfigAndConst.getInstance().isNetworkConnected(this) ) {
            Snackbar.make(rcList, "No Network Connection Available...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        //Load some share values and list them
        loadAndListData();
        //Refresh timer imaplementation
        startRefreshTimer();


    }

    private void startRefreshTimer() {
        mHandlerRefresh = new Handler();
        mHandlerRefresh.postDelayed(refreshRunnable, ConfigAndConst.REFRESH_TIME_IN_MILLISECONDS);
    }



    private void loadAndListData() {
        shareDatas = new ArrayList<ShareData>();
        shareDatas.add(new ShareData("ACC"));
        shareDatas.add(new ShareData("ABB"));
        shareDatas.add(new ShareData("TCS"));
        shareDatas.add(new ShareData("INFY"));
        mShareValueAdapter = new ShareValueAdapter(this,shareDatas);
        rcList.setAdapter(mShareValueAdapter);
        refreshShareValueList();


    }

    public void refreshShareValueList() {
        RefreshTask task = new RefreshTask(this, shareDatas, mShareValueAdapter);
        task.execute();
    }

    public void doRefresh( View view) {
        refreshShareValueList();
    }

    public void showAddShareDialog( View view) {
        ShareAddDialog dialog = new ShareAddDialog(this);
        dialog.setOkListener(this);
        dialog.showThisDialog();
    }

    private void findAndInitViews() {
        imRefresh = (ImageButton) this.findViewById(R.id.imRefresh);
        rcList = (RecyclerView) this.findViewById(R.id.rcList);
        mLayoutManager = new LinearLayoutManager(this);
        rcList.setLayoutManager(mLayoutManager);
        //For rotation animation for refresh button
        rotation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim);

    }

    public static void showRefreshing() {
        if( imRefresh == null ) return;
        imRefresh.setAnimation(rotation);
        imRefresh.animate();
    }

    public static void hideRefreshing() {
        if( imRefresh == null ) return;
        imRefresh.clearAnimation();
    }

    public void onShareAddOkClicked(String input) {
        if(input.length() > 0) {
            shareDatas.add(new ShareData(input.toUpperCase()));
            mShareValueAdapter.notifyDataSetChanged();
        }
    }

    private Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            imRefresh.post(new Runnable() {
                @Override
                public void run() {
                    imRefresh.startAnimation(rotation);
                    imRefresh.performClick();
                }
            });
            mHandlerRefresh.postDelayed(refreshRunnable,
                    ConfigAndConst.REFRESH_TIME_IN_MILLISECONDS);
        }
    };
}





