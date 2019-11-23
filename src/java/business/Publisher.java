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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasmine
 */
@Entity
@Table(name="publisher")
public class Publisher {
    @Id
    @Column(name = "Publisher_Code")
    private String pubcd;
    
    @Column(name = "Publisher_Name")
    private String pubname;

    @Column(name = "Publisher_City")
    private String pubcity;
    
    @Column(name = "Publisher_State")
    private String pubstate;

    public Publisher(){
        this.pubcd = "";
        this.pubcity="";
        this.pubname="";
        this.pubstate ="";
                
    }    

    public String getPubcd() {
        return pubcd;
    }

    public void setPubcd(String pubcd) {
        this.pubcd = pubcd;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }

    public String getPubcity() {
        return pubcity;
    }

    public void setPubcity(String pubcity) {
        this.pubcity = pubcity;
    }

    public String getPubstate() {
        return pubstate;
    }

    public void setPubstate(String pubstate) {
        this.pubstate = pubstate;
    }

}
