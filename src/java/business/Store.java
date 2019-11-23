
package business;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author
 */

@Entity
@Table(name = "stores")
public class Store {
        @Id
        @Column(name = "storeID")
        private int StoreID;
        
        @Column(name = "storeEmp")
        private int StoreEmp;
        
        @Column(name = "storeName")
        private String StoreName;
        
        
        @Column(name = "storeAddr")
        private String StoreAddr;
        
        @OneToMany (fetch=FetchType.EAGER)
        @JoinColumn(name = "storeID")
        @Cascade(CascadeType.ALL)
        @OrderBy("bookID")
        private List<Inventory> bookinv;

        
    public Store() {
        
        StoreID = 0;
        StoreEmp = 0;
        StoreName = "";
        StoreAddr = "";
    }

    public int getStoreID() {
        return StoreID;
    }
    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }
    public int getStoreEmp() {
        return StoreEmp;
    }
    public void setStoreEmp(int StoreEmp) {
        this.StoreEmp = StoreEmp;
    }
    public String getStoreName() {
        return StoreName;
    }
    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }
    public String getStoreAddr() {
        return StoreAddr;
    }
    public void setStoreAddr(String StoreAddr) {
        this.StoreAddr = StoreAddr;
    }
    
    public List<Inventory> getBookinv(){
        return this.bookinv;
    }


}
