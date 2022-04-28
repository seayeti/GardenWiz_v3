package API;

//public class plantImages {
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    private String source;
//
//}

public class plantImages {
    private String batchcomplete;
    Query QueryObject;


    // Getter Methods

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public Query getQuery() {
        return QueryObject;
    }

    // Setter Methods

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public void setQuery(Query queryObject) {
        this.QueryObject = queryObject;
    }
}

class Query {
    Pages PagesObject;


    // Getter Methods

    public Pages getPages() {
        return PagesObject;
    }

    // Setter Methods

    public void setPages(Pages pagesObject) {
        this.PagesObject = pagesObject;
    }
}

class Pages {
  Number NumberObject;


    // Getter Methods

    public Number

    getNumber() {
        return NumberObject;
    }

    // Setter Methods

    public void setNumber( Number NumberObject) {
        this.NumberObject = NumberObject;
    }
}
class Number{
private float pageid;
private float ns;
private String title;
        Thumbnail ThumbnailObject;
private String pageimage;


// Getter Methods

public float getPageid(){
        return pageid;
        }

public float getNs(){
        return ns;
        }

public String getTitle(){
        return title;
        }

public Thumbnail getThumbnail(){
        return ThumbnailObject;
        }

public String getPageimage(){
        return pageimage;
        }

// Setter Methods

public void setPageid(float pageid){
        this.pageid=pageid;
        }

public void setNs(float ns){
        this.ns=ns;
        }

public void setTitle(String title){
        this.title=title;
        }

public void setThumbnail(Thumbnail thumbnailObject){
        this.ThumbnailObject=thumbnailObject;
        }

public void setPageimage(String pageimage){
        this.pageimage=pageimage;
        }
        }

class Thumbnail {
    private String source;
    private float width;
    private float height;


    // Getter Methods

    public String getSource() {
        return source;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Setter Methods

    public void setSource(String source) {
        this.source = source;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}