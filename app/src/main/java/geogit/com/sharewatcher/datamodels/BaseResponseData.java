package geogit.com.sharewatcher.datamodels;

import geogit.com.sharewatcher.json.JsonParserBase;

/**
 * Created by sandeep on 25/2/16.
 */
public abstract class BaseResponseData extends JsonParserBase{

    //Every response data must have getParse method
    public abstract BaseResponseData getParse(String jsonString);
}
