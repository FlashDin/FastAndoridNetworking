package com.flashdin.fastandroidnetworking.fast;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener;
import com.flashdin.fastandroidnetworking.Config;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by flashdin on 10/11/17.
 */

public class FastMethods {

    private DataModel domains;
    private List<DataModel> mDataList;
    private ProgressDialog progressDialog;
    private static final String url = new Config().URL;

    public FastMethods(Context ctx) {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();
    }

    public List<DataModel> viewData(Context ctx) {
        AndroidNetworking.get(url + "sUser/view.php")
                .addPathParameter("filter", "")
                .setTag(this)
                .setPriority(Priority.LOW)
                .setUserAgent("viewData")
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsOkHttpResponseAndObject(DataModel.class, new OkHttpResponseAndParsedRequestListener<DataModel>() {
                    @Override
                    public void onResponse(Response okHttpResponse, DataModel dataModel) {
                        progressDialog.dismiss();
                        domains = new DataModel();
                        domains.setmId(dataModel.getmId());
                        domains.setmUsername(dataModel.getmUsername());
                        domains.setmFoto(dataModel.getmFoto());
                        mDataList.add(domains);
                        if (okHttpResponse.isSuccessful()) {
                            Log.d(TAG, "onResponse success headers : " + okHttpResponse.headers().toString());
                        } else {
                            Log.d(TAG, "onResponse not success headers : " + okHttpResponse.headers().toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        anError.getMessage();
                    }
                });
        domains = new DataModel();
        domains.setmId("Error");
        domains.setmUsername("Error");
        domains.setmFoto("Error");
        mDataList.add(domains);
        return mDataList;
    }

    public void saveData(DataModel dm, final Context ctx) {
        if (dm.getmId().isEmpty()) {
            AndroidNetworking.post(url + "sUser/add.php")
                    .addBodyParameter("a", dm.getmUsername())
                    .addBodyParameter("b", dm.getmFoto())
                    .setTag(this)
                    .setPriority(Priority.LOW)
                    .build()
                    .setAnalyticsListener(new AnalyticsListener() {
                        @Override
                        public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                            Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                            Log.d(TAG, " bytesSent : " + bytesSent);
                            Log.d(TAG, " bytesReceived : " + bytesReceived);
                            Log.d(TAG, " isFromCache : " + isFromCache);
                        }
                    })
                    .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
                        @Override
                        public void onResponse(Response okHttpResponse, JSONObject response) {
                            progressDialog.dismiss();
                            Log.d(TAG, "onResponse object : " + response.toString());
                            Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                            if (okHttpResponse.isSuccessful()) {
                                Log.d(TAG, "onResponse success headers : " + okHttpResponse.headers().toString());
                            } else {
                                Log.d(TAG, "onResponse not success headers : " + okHttpResponse.headers().toString());
                            }
                            Toast.makeText(ctx, okHttpResponse.headers().get("message"), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(ANError anError) {
                            progressDialog.dismiss();
                            Toast.makeText(ctx, anError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            AndroidNetworking.post(url + "sUser/update.php")
                    .addBodyParameter("a", dm.getmId())
                    .addBodyParameter("b", dm.getmUsername())
                    .addBodyParameter("c", dm.getmFoto())
                    .setTag(this)
                    .setPriority(Priority.LOW)
                    .build()
                    .setAnalyticsListener(new AnalyticsListener() {
                        @Override
                        public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                            Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                            Log.d(TAG, " bytesSent : " + bytesSent);
                            Log.d(TAG, " bytesReceived : " + bytesReceived);
                            Log.d(TAG, " isFromCache : " + isFromCache);
                        }
                    })
                    .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
                        @Override
                        public void onResponse(Response okHttpResponse, JSONObject response) {
                            progressDialog.dismiss();
                            Log.d(TAG, "onResponse object : " + response.toString());
                            Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                            if (okHttpResponse.isSuccessful()) {
                                Log.d(TAG, "onResponse success headers : " + okHttpResponse.headers().toString());
                            } else {
                                Log.d(TAG, "onResponse not success headers : " + okHttpResponse.headers().toString());
                            }
                            Toast.makeText(ctx, okHttpResponse.headers().get("message"), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(ANError anError) {
                            progressDialog.dismiss();
                            Toast.makeText(ctx, anError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    public void deleteData(DataModel dm, final Context ctx) {
        AndroidNetworking.post(url + "sUser/delete.php")
                .addBodyParameter("a", dm.getmId())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
                    @Override
                    public void onResponse(Response okHttpResponse, JSONObject response) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                        if (okHttpResponse.isSuccessful()) {
                            Log.d(TAG, "onResponse success headers : " + okHttpResponse.headers().toString());
                        } else {
                            Log.d(TAG, "onResponse not success headers : " + okHttpResponse.headers().toString());
                        }
                        Toast.makeText(ctx, okHttpResponse.headers().get("message"), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(ctx, anError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
