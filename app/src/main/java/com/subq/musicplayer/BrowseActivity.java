package com.subq.musicplayer;

import android.os.*;
import android.support.v7.app.*;
import android.support.v4.app.*;
import android.provider.*;
import android.database.*;
import android.net.*;
import android.widget.*;

public class BrowseActivity extends AppCompatActivity 
{
	public static ListFragmentType currentListType;
	
	ListFragment fragment;
	Cursor cursor;
	long id = -1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
		
		currentListType = ListFragmentType.ALL_TRACKS;
		
		if(savedInstanceState != null && !savedInstanceState.isEmpty()) {
			id = savedInstanceState.getLong("ID");
			currentListType = ListFragmentType.valueOf(savedInstanceState.getString("LIST_TYPE"));
		}
		
		cursor = setUpCursor(currentListType, id);
		
		fragment = ListFragment.getInstance(cursor, currentListType, id, getLayout());
		fragment.setAdapter(new TrackListAdapter(this, cursor));
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.activity_browse_listview_container, fragment);
		ft.commit();
    }

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		if(outState == null){
			outState = new Bundle();
		}
		outState.putLong("ID", id);
		outState.putString("LIST_TYPE", currentListType.name());
		super.onSaveInstanceState(outState);
	}
	
	public Cursor setUpCursor(ListFragmentType currentListType, long id) {
		Cursor cur;
		if(currentListType == ListFragmentType.ALL_ALBUMS) {
			cur = query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null);
		}
		else if(currentListType == ListFragmentType.ALL_ARTISTS) {
			cur = query (MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null);
		}
		else if(currentListType == ListFragmentType.ARTIST) {
			cur = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.ARTIST_ID + "=?", new String[]{ String.valueOf(id)});
		}
		else if(currentListType == ListFragmentType.ALL_ARTISTS) {
			cur = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.ALBUM_ID + "=?", new String[]{ String.valueOf(id)});
		}
		else {
			cur = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null);
		}
		return cur;
	}

	private Cursor query(Uri uri, String where, String[] args)
	{
		Cursor c = getContentResolver().query(
			uri,
			null,
			where, 
			args,
			MediaStore.Audio.Media.TITLE + " ASC");
	
		return c;
	}
	
	int getLayout() {
		// TODO create different layouts
		if(currentListType == ListFragmentType.ALL_ALBUMS) {
			return R.layout.listitem_track;
		}
		if(currentListType == ListFragmentType.ALL_ARTISTS) {
			return R.layout.listitem_track;
		}
		if(currentListType == ListFragmentType.ARTIST) {
			return R.layout.listitem_track;
		}
		if(currentListType == ListFragmentType.ALL_ARTISTS) {
			return R.layout.listitem_track;
		}
		return R.layout.listitem_track;
		
	}
}

