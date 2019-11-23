/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author jasmine
 */
@Entity
@Table(name="booklist")
public class Book {
    @Id
    @Column(name="bookID")
    private String bookID;
    
    @Column(name="title")
    private String title;
    
    @Column(name="author")
    private String author;
    
    @Column(name="Publisher_Code")
    private String pubcd;
    
    @Column(name="booktype")
    private String booktype;
    
    @Column(name="price")
    private double price;
    
     @OneToMany (fetch=FetchType.EAGER)
    @JoinColumn(name = "Publisher_Code")
    @Cascade(CascadeType.ALL)
    private List<Publisher> publisher;
    
    public Book(){
        this.bookID = "";
        this.title = "";
        this.author = "";
        this.pubcd = "";
        this.booktype = "";
        this.price = 0;
    }

    /**
     * @return the bookID
     */
    public String getBookID() {
        return bookID;
    }

    /**
     * @param bookID the bookID to set
     */
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the pubcd
     */
    public String getPubcd() {
        return pubcd;
    }

    /**
     * @param pubcd the pubcd to set
     */
    public void setPubcd(String pubcd) {
        this.pubcd = pubcd;
    }

    /**
     * @return the booktype
     */
    public String getBooktype() {
        return booktype;
    }

    /**
     * @param booktype the booktype to set
     */
    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String isValid(){
        String msg = "";
        //validate checks for book object ready to write to db
        return msg;
    }
    
        public List<Publisher> getPublisher(){
        return this.publisher;
    }
}
