
package API;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantImages implements Parcelable
{

    @SerializedName("batchcomplete")
    @Expose
    private Boolean batchcomplete;
    @SerializedName("query")
    @Expose
    private Query query;
    public final static Creator<PlantImages> CREATOR = new Creator<PlantImages>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PlantImages createFromParcel(android.os.Parcel in) {
            return new PlantImages(in);
        }

        public PlantImages[] newArray(int size) {
            return (new PlantImages[size]);
        }

    }
    ;

    protected PlantImages(android.os.Parcel in) {
        this.batchcomplete = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.query = ((Query) in.readValue((Query.class.getClassLoader())));
    }

    public PlantImages() {
    }

    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(batchcomplete);
        dest.writeValue(query);
    }

    public int describeContents() {
        return  0;
    }

}
