package com.subq.musicplayer;
import android.support.v4.app.*;
import android.widget.*;
import android.database.*;
import android.view.*;
import android.content.*;
import android.os.*;
import android.database.sqlite.*;
import android.provider.*;

public class ListFragment extends Fragment
{
	protected ListFragmentType type;
	protected Cursor cursor;
	protected long id = -1;
	int layout;
	
	public static ListFragment getInstance(Cursor c, ListFragmentType type, long id) {
		ListFragment frag = new ListFragment();
		frag.type = type;
		frag.id = id;
		frag.cursor = c;
		if(type == ListFragmentType.ALL_TRACKS){
			frag.layout = R.layout.listitem_track;
		}
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(layout, container, false);
		ListAdapter adapter = new TrackListAdapter(getContext(), cursor);
		ListView listView = (ListView) v.findViewById(R.id.fragmentlistview_listView);
		listView.setAdapter(adapter);
		
		return v;
	}
	
	public void setCursor(Cursor c) {
		cursor = c;
		
	}
}

enum ListFragmentType{
	ALL_TRACKS,
	ALL_ALBUMS,
	ALL_ARTISTS,
	ARTIST,
	ALBUM
	}




