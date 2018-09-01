/*
* Copyright (C) 2014 The Android Open Source Project
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

package com.flashdin.fastandroidnetworking.fast.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashdin.fastandroidnetworking.fast.DataModel;
import com.flashdin.testnetworkinglibs.R;

import java.util.List;

//import com.flashdin.thisapp.oth.plugins.recycler.common.logger.

/**
 * Provide views to RecyclerView with data from mList.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<DataModel> mList;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mLbUsername,mLbId;
        private final ImageView mImgUser;

        public ViewHolder(View v) {
            super(v);
            mLbId = (TextView) v.findViewById(R.id.lbId);
            mLbUsername = (TextView) v.findViewById(R.id.lbUsername);
            mImgUser = (ImageView) v.findViewById(R.id.imgUser);

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Posisi : " + getAdapterPosition()
                            + " ID : " + mLbId.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        public TextView getmLbId() {
            return mLbId;
        }

        public TextView getmLbUsername() {
            return mLbUsername;
        }

        public ImageView getmImgUser() {
            return  mImgUser;
        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param ls String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(List<DataModel> ls) {
        mList = ls;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listcontent, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        DataModel mItem = mList.get(position);
        byte[] decodeString = Base64.decode(mItem.getmFoto(), Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        decodeByte = Bitmap.createScaledBitmap(decodeByte, 75, 75, true);
        viewHolder.getmImgUser().setImageBitmap(decodeByte);
        viewHolder.getmLbId().setText(mItem.getmId());
        viewHolder.getmLbUsername().setText(mItem.getmUsername());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
