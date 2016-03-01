package geogit.com.sharewatcher.datamodels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sandeep on 25/2/16.
 */
public class ResponseShareData extends BaseResponseData {

    public float mCurrentPrice;
    public float mCurretPercentage;

    // [ { "id": "11787956" ,"t" : "ACC" ,"e" : "NSE" ,"l" : "1,200.00" ,
    // "l_fix" : "1200.00" ,"l_cur" : "Rs.1,200.00" ,"s": "0" ,"ltt":"3:29PM GMT+5:30" ,
    // "lt" : "Feb 25, 3:29PM GMT+5:30" ,"lt_dts" : "2016-02-25T15:29:59Z" ,
    // "c" : "-33.15" ,"c_fix" : "-33.15" ,"cp" : "-2.69" ,"cp_fix" : "-2.69" ,
    // "ccol" : "chr" ,"pcls_fix" : "1233.15" } ]

    //httpserver.cc: Response Code 400

    @Override
    public BaseResponseData getParse(String jsonString) {
        if( null == jsonString ) return null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject == null) continue;
                ResponseShareData responseShareData = new ResponseShareData();
                responseShareData.mCurrentPrice = parseString(
                        getJsonAttributeValueString(jsonObject,"l_cur" ));
                responseShareData.mCurretPercentage = parseString(
                        getJsonAttributeValueString(jsonObject,"cp" ));
                return responseShareData;
            }
        } catch (JSONException e) {e.printStackTrace();}

        return null;
    }

    private float parseString( String moneyString) {
        if( null ==  moneyString ) return 0;
        try {
          String newMoneyString = moneyString.replace("Rs.", "");
          newMoneyString = newMoneyString.replaceAll(",", "");
            return Float.parseFloat(newMoneyString);
        } catch (NumberFormatException e) { e.printStackTrace(); }
        return 0;
    }
}
