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
	protected View view;
	protected long id = -1;
	int layout;
	
	public static ListFragment getInstance(Cursor c, ListFragmentType type, long id, int layout) {
		ListFragment frag = new ListFragment(layout);
		frag.type = type;
		frag.id = id;
		frag.cursor = c;
		return frag;
	}

	private ListFragment(int layout) {
		this.layout = layout;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(layout, container, false);
		if(view != null)
			return view;
		else 
			return super.onCreateView(inflater, container,savedInstanceState);
	}
	
	public void setCursor(Cursor c) {
		cursor = c;
	}
	
	public void setAdapter(ListAdapter adapter) {
		if(view != null) {
			ListView listView = (ListView) view.findViewById(R.id.fragmentlistview_listView);
			listView.setAdapter(adapter);
		}
	}
}

enum ListFragmentType{
	ALL_TRACKS,
	ALL_ALBUMS,
	ALL_ARTISTS,
	ARTIST,
	ALBUM
	}




