package geogit.com.sharewatcher.datamodels;

/**
 * Created by sandeep on 25/2/16.
 *
 * This class holds sharedata
 */
public class ShareData {

    //Some negative number that value wont be a real scenario percentage, no share holds this value
    private final float MINIMUM_PERCENTAGE = -1000;

    public String mShareName;
    public float mCurrentPrice;
    public float mPreviousPrice;
    public float mCurrentPercentage;
    public float mPreviousPercentage;

    public ShareData( String shareName) {
        mShareName = shareName;
        mCurrentPrice = 0;
        mPreviousPrice = 0;
        mCurrentPercentage = 0;
        mPreviousPercentage = MINIMUM_PERCENTAGE;
    }

    public void loadResponseShareData( ResponseShareData responseShareData) {

        if( null == responseShareData ) return;
        //If previous percentage is minimum, means there is no current value
        if(mPreviousPercentage != MINIMUM_PERCENTAGE) {
            mPreviousPrice = mCurrentPrice;
            mPreviousPercentage = mCurrentPercentage;
        }
        mCurrentPrice = responseShareData.mCurrentPrice;
        mCurrentPercentage = responseShareData.mCurretPercentage;
    }

}
