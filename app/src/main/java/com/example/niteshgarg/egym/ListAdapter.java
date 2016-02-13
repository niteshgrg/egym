package com.example.niteshgarg.egym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niteshgarg on 13/02/16.
 */
public class ListAdapter extends BaseAdapter {

    public static class ViewHolder {
        public ImageView iconView;
        public TextView nameView;
        public TextView phoneView;

    }

    private Context mContext;
    private LayoutInflater mInflater;


    private final String LOG_TAG = ListAdapter.class.getSimpleName();

    ArrayList<com.example.niteshgarg.egym.model.results> results;

    public ListAdapter(Context c, ArrayList<com.example.niteshgarg.egym.model.results> temp) {
        super();
        mContext = c;
        results = temp;
        mInflater = LayoutInflater.from(mContext);

    }

    public void updateContent(ArrayList<com.example.niteshgarg.egym.model.results> temp)
    {
        this.results.clear();
        this.results = temp;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        return results.size();
    }

    public String getItem(int pos) {
        return null;
    }

    public long getItemId(int position) {

        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        View vi = convertView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            vi = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) vi.findViewById(R.id.list_item_icon);
            holder.nameView = (TextView) vi.findViewById(R.id.list_item_name_textview);
            holder.phoneView = (TextView) vi.findViewById(R.id.list_item_phone_textview);
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();
        }

        String url = results.get(position).getUser().getPicture().getThumbnail();
        Picasso.with(mContext).load(url).fit().into(holder.iconView);
        holder.nameView.setText(results.get(position).getUser().getUsername());
        holder.phoneView.setText(results.get(position).getUser().getPhone());
        return vi;
    }
}
