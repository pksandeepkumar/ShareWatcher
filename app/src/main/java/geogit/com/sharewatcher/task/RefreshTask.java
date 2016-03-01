package geogit.com.sharewatcher.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import geogit.com.sharewatcher.ConfigAndConst;
import geogit.com.sharewatcher.MainActivity;
import geogit.com.sharewatcher.adapters.ShareValueAdapter;
import geogit.com.sharewatcher.datamodels.ResponseShareData;
import geogit.com.sharewatcher.datamodels.ShareData;
import geogit.com.sharewatcher.network.NetworkService;
/**
 * Created by sandeep on 25/2/16.
 */
public class RefreshTask extends  AsyncTask<Void, Void, Void> {


    private Context mContext;
    private ArrayList<ShareData> mShareDatas;
    private ShareValueAdapter mShareValueAdapter;

    public RefreshTask(Context context,ArrayList<ShareData> shareDatas,
                       ShareValueAdapter shareValueAdapter ) {
    	this.mContext = context;
        this.mShareDatas = shareDatas;
        this.mShareValueAdapter = shareValueAdapter;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//        Toast.makeText(mContext,"Refreshing...", Toast.LENGTH_LONG).show();
        MainActivity.showRefreshing();
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
            //Take each share data and call Google finance api for getting share value
            for(ShareData shareData : mShareDatas) {

                String response = NetworkService.Get(ConfigAndConst.BASE_URL + shareData.mShareName);
                //First two character will be // so we have to remove that string
                response = response.replace("//","");
                ResponseShareData responseShareData = new ResponseShareData();
                //Parse response json, if response is not a json then null will be return
                responseShareData = (ResponseShareData) responseShareData.getParse(response);
                shareData.loadResponseShareData(responseShareData);

                //Update particular share data in the list
                publishProgress();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mShareValueAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        MainActivity.hideRefreshing();
    }

}
