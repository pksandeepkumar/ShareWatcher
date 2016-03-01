/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geogit.com.sharewatcher.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sandeep on 25/2/16.
 */
public class LOG {
	
	public static void log(String tag, String msg){
		Log.e(tag, msg);
	}
	
	public static void showMessage(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

}
