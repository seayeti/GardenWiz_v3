
package com.example;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import API.Thumbnail;

public class Page implements Parcelable
{

    @SerializedName("pageid")
    @Expose
    private Integer pageid;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("missing")
    @Expose
    private String missing;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("pageimage")
    @Expose
    private String pageimage;
    public final static Creator<Page> CREATOR = new Creator<Page>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Page createFromParcel(android.os.Parcel in) {
            return new Page(in);
        }

        public Page[] newArray(int size) {
            return (new Page[size]);
        }

    }
    ;

    protected Page(android.os.Parcel in) {
        this.pageid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ns = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.missing = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
        this.pageimage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Page() {
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getMissing() {
        return missing;
    }

    public void setMissing(String missing) {
        this.missing = missing;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPageimage() {
        return pageimage;
    }

    public void setPageimage(String pageimage) {
        this.pageimage = pageimage;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(pageid);
        dest.writeValue(ns);
        dest.writeValue(title);
        dest.writeValue(missing);
        dest.writeValue(thumbnail);
        dest.writeValue(pageimage);
    }

    public int describeContents() {
        return  0;
    }

}
