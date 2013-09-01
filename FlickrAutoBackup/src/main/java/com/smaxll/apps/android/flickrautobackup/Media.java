package com.smaxll.apps.android.flickrautobackup;


public class Media {

	public int id;

	public String path;

	public String name;

	public Utils.MediaType mediaType;

	public int size;

	public long date;

	@Override
	public String toString() {
		return mediaType + " - " + id + " - " + path;
	}

	@Override
	public int hashCode() {
		if (path != null)
			return path.hashCode();
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Media) {
			return ((Media) o).path.equals(path);
		}
		return super.equals(o);
	}
}
