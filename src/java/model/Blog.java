package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Blog {
    private String id;
    private String title;
    private String content;
    private String description;
    private Date created_date;
    private String status;
    private String marketer_id;
    private String tag;
    private String image;
    public Blog() {
    }

    public Blog(String id, String title, String content, String description, Date created_date, String status, String marketer_id, String tag, String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.created_date = created_date;
        this.status = status;
        this.marketer_id = marketer_id;
        this.tag = tag;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getStatus() {
        return status;
    }

    public String getMarketer_id() {
        return marketer_id;
    }

    public String getTag() {
        return tag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMarketer_id(String marketer_id) {
        this.marketer_id = marketer_id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", content=" + content + ", description=" + description + ", created_date=" + created_date + ", status=" + status + ", marketer_id=" + marketer_id + ", tag=" + tag + ", image=" + image + '}';
    }


    
}
