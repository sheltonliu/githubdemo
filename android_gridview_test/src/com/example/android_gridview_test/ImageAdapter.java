package com.example.android_gridview_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private int[] mThumbIds;
    private String[] texts; 
    public ImageAdapter(Context c, int[] mThumbIds, String[] texts ) {
        this.mContext = c;  
        this.inflater = LayoutInflater.from(mContext);
        this.mThumbIds = mThumbIds;
        this.texts = texts; 
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        
        ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_gridview_list_item, null);
			holder = new ViewHolder();
			
			holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);  
			holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);  
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		holder.iv_image.setImageResource(mThumbIds[position]);   
		holder.tv_text.setText(texts[position].toString());
		
		if ("员工位置".equals(texts[position].toString())) {
			holder.iv_image.setVisibility(View.GONE); 
			holder.tv_text.setVisibility(View.GONE);
		}else{
			holder.iv_image.setVisibility(View.VISIBLE); 
			holder.tv_text.setVisibility(View.VISIBLE);
		}
		
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT)); //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
		
		
		
        return convertView;
    }

   //  references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.icon1, R.drawable.icon2,
//            R.drawable.icon3, R.drawable.icon4,
//            R.drawable.icon5, R.drawable.icon6,
//            R.drawable.icon1, R.drawable.icon2,
//            R.drawable.icon3, R.drawable.icon4,
//            R.drawable.icon5, R.drawable.icon6,
//    };
    
	private class ViewHolder {
		TextView tv_text;
		ImageView iv_image;
		
	}
}
