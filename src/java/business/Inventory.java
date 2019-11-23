
package business;

import java.text.NumberFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author 
 */

@Entity
@Table(name="bookinv")
public class Inventory {
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    
    @Column(name="storeID")
    private int StoreID;
    
    @Column(name="OnHand")
    private int OnHand;
    
    @Column(name="bookID")
    private String BookID;
    
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="bookID", insertable=false,updatable=false)
    private Book book;
    
    @Transient 
    NumberFormat c = NumberFormat.getCurrencyInstance();
    
    public Inventory() {
        id = 0;
        StoreID = 0;
        OnHand = 0;
        BookID = "";
        book = null;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getOnHand() {
        return OnHand;
    }

    public void setOnHand(int OnHand) {
        this.OnHand = OnHand;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }
    
    public Book getBook(){
        return this.book;
    }
        
}
