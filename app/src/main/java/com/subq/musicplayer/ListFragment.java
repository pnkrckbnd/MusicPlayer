package com.subq.musicplayer;
import android.support.v4.app.*;
import android.widget.*;
import android.database.*;
import android.view.*;
import android.content.*;

public class ListFragment extends Fragment
{
	protected ListFragmentType type;
	protected long id = -1;
	
	
	public ListFragment getInstance(ListFragmentType type, CursorAdapter adapter) {
		ListFragment frag = new ListFragment();
		this.type = type;
		return frag;
	}
	
	
}

enum ListFragmentType{
	ALL_TRACKS,
	ALL_ALBUMS,
	ALL_ARTISTS,
	ARTIST,
	ALBUM
}
