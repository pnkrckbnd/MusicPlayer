package com.subq.musicplayer;

import android.content.*;
import android.database.*;
import android.support.v4.widget.*;
import android.support.v7.appcompat.*;
import android.view.*;
import android.widget.*;

import android.support.v4.widget.CursorAdapter;
import android.provider.*;

public class TrackListAdapter extends CursorAdapter {

	public static int TITLE;
	public static int DURATION;;
	
	public TrackListAdapter(Context context, Cursor cursor)
	{
		super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		TITLE = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
		DURATION = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent)
	{
		View v = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
		return v;
	}

	@Override
	public void bindView(View v, Context context, Cursor cursor)
	{
		ViewHolder vh = (TrackListAdapter.ViewHolder) v.getTag();
		if(vh == null) {
			vh = new ViewHolder();
			vh.ivMain = (ImageView) v.findViewById(R.id.listitemtrack_imageView_main);
			vh.tvMain = (TextView)v.findViewById(R.id.listitemtrack_textView_main);
			vh.tvRight = (TextView)v.findViewById(R.id.listitemtrack_textView_right);
			v.setTag(vh);
		}
		//TODO: replace with acture image
		vh.ivMain.setVisibility(View.GONE);
		
		vh.tvMain.setText(cursor.getString(TITLE));
		// Test which textview works
		vh.tvRight.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
	}

	class ViewHolder{
		public ImageView ivMain;
		public TextView tvMain;
		public TextView tvRight;
	}
}
