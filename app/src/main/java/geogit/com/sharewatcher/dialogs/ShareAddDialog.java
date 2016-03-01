package geogit.com.sharewatcher.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import geogit.com.sharewatcher.R;

/**
 * Created by sandeep on 25/2/16.
 */
public class ShareAddDialog extends AlertDialog.Builder {

    private Context mContext;
    private ShareAddOkListener mOkListener;

    public ShareAddDialog(Context context) {
        super(context);
        mContext = context;
        initDialog();
    }


    public interface ShareAddOkListener {
        public void onShareAddOkClicked(String input);
    }

    public void setOkListener(ShareAddOkListener listener) {
        this.mOkListener = listener;
    }

    public void showThisDialog() {
        create().show();
    }


    private void initDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.dialog_add_share, null);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        setView(promptView);
        setCancelable(false);
        setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                    if(mOkListener != null) {
                        mOkListener.onShareAddOkClicked(editText.getText().toString().trim());
                    }
            }
        });
        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
    }
}
