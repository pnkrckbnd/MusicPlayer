package com.subq.musicplayer;

import android.os.*;
import android.support.v7.app.*;
import android.support.v4.app.*;
import android.provider.*;
import android.database.*;
import android.net.*;

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
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		cursor = setUpCursor(currentListType, -1);
		if(savedInstanceState != null && !savedInstanceState.isEmpty()) {
			id = savedInstanceState.getLong("ID");
			Enum.
		}
		fragment = ListFragment.getInstance(cursor,currentListType, id);
		ft.replace(R.id.activity_browse_listview_container, fragment);
		ft.commit();
    }
	
	public Cursor setUpCursor(ListFragmentType currentListType, long id) {
		Cursor cur;
		if(currentListType == ListFragmentType.ALL_TRACKS) {
			cursor = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null);
		}
		else if(currentListType == ListFragmentType.ALL_ALBUMS) {
			cursor = query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null);
		}
		else if(currentListType == ListFragmentType.ALL_ARTISTS) {
			cursor = query (MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null);
		}
		else if(currentListType == ListFragmentType.ARTIST) {
			cursor = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.ARTIST_ID + "=?", new String[]{ String.valueOf(id)});
		}
		else if(currentListType == ListFragmentType.ALL_ARTISTS) {
			cursor = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.ALBUM_ID + "=?", new String[]{ String.valueOf(id)});
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
}

