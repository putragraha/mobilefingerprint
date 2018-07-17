package com.klk.mobilefingerprint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.models.Staff;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    private static final String TAG = StaffAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<Staff> mStaffList;

    public StaffAdapter(Context context, ArrayList<Staff> staffList){
        this.mContext = context;
        this.mStaffList = staffList;
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_staff, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StaffViewHolder holder, int position) {
        Staff staff = mStaffList.get(position);
        holder.tvId.setText(String.valueOf(staff.get_id()));
        holder.tvName.setText(staff.getName());

        if (staff.getPhoto() != null){
            Glide.with(mContext).load(staff.getPhoto()).into(holder.ivAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return mStaffList.size();
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvId, tvName;
        private CircleImageView ivAvatar;

        private StaffViewHolder(View view) {
            super(view);
            tvId = view.findViewById(R.id.tvCardStaffId);
            tvName = view.findViewById(R.id.tvCardStaffName);
            ivAvatar = view.findViewById(R.id.imageCardStaffPhoto);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            long position = getAdapterPosition();
            Toast.makeText(mContext, String.valueOf(position), Toast.LENGTH_SHORT).show();
        }
    }
}

