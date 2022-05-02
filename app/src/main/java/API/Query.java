
package API;

import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

import com.example.Page;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query implements Parcelable
{

    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;
    public final static Creator<Query> CREATOR = new Creator<Query>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Query createFromParcel(android.os.Parcel in) {
            return new Query(in);
        }

        public Query[] newArray(int size) {
            return (new Query[size]);
        }

    }
    ;

    protected Query(android.os.Parcel in) {
        in.readList(this.pages, (Page.class.getClassLoader()));
    }

    public Query() {
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(pages);
    }

    public int describeContents() {
        return  0;
    }

}
