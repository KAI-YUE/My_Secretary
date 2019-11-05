package android.eric.kaiyue.secretary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    // Member Variables
    private ArrayList<Reminder> mReminderList;
    private Context mContext;

    /* Constructor  that passes in the reminder list and the context. */
    ReminderAdapter(Context context, ArrayList<Reminder> reminderData){
        this.mReminderList = reminderData;
        this.mContext = context;
    }


    public ReminderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.reminder_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReminderAdapter.ViewHolder holder,
                                 int position) {
        Reminder currentReminder = mReminderList.get(position);
        holder.bindTo(currentReminder);
    }

    @Override
    public int getItemCount() {
        return mReminderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTitleText;
        private TextView mDateText;
        private TextView mTimeText;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.reminder_title);
            mDateText = itemView.findViewById(R.id.reminder_date);
            mTimeText = itemView.findViewById(R.id.reminder_time);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Reminder currentReminder){
            mTitleText.setText(currentReminder.getTitle());
            mDateText.setText(currentReminder.getDate());
            mTimeText.setText(currentReminder.getTime());
        }

        @Override
        public void onClick(View view){
/*            Reminder currentReminder = mReminderList.get(getAdapterPosition());
            Toast myToast = Toast.makeText(this, currentReminder.getTitle(), Toast.LENGTH_SHORT);
            myToast.show();*/
        }
    }
}
