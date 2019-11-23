
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author 
 */

@Entity
@Table(name="users")
@NamedQuery(name = "dbget_User",query = "from User where UserID = :uid")
public class User {
    @Id
    @Column(name="UserID")
    private int UserID;
    
    @Column(name="StoreID")
    private int StoreID;
    
    @Column(name="UserPassword")
    private int Password;

    @Column(name="UserName")
    private String UserName;
    
    @Column(name="AdminLevel")
    private String AdminLevel;
    
    @Transient
    private int PwdAttempt;
    
    public User() {
        UserID = 0;
        StoreID = 0;
        Password = 0;
        UserName = "";
        AdminLevel = "";
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int Password) {
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getAdminLevel() {
        return AdminLevel;
    }

    public void setAdminLevel(String AdminLevel) {
        this.AdminLevel = AdminLevel;
    }
    
    public void setPwdAttempt(int PwdAttempt) {
        this.PwdAttempt = PwdAttempt;
    }
    
    public boolean isAuthenticated() {
        boolean result = false;
        
        if (Password > 0) {
            if (Password == PwdAttempt) {result = true;}
        }
        return result;
    }
    
}
