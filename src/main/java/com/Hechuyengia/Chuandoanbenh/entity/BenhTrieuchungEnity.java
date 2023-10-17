/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;


/**
 *
 * @author tranm
 */
import javax.persistence.*;
import java.io.Serializable;
import com.Hechuyengia.Chuandoanbenh.entity.BenhEnity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuchungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhTrieuChungId;
@Entity
@Table(name = "Benh_TrieuChung")
public class BenhTrieuchungEnity implements Serializable{
    
    
    @EmbeddedId
    private BenhTrieuChungId id;

    @ManyToOne
    @MapsId("maBenh")
    @JoinColumn(name = "MaBenh")
    private BenhEnity benh;

    @ManyToOne
    @MapsId("maTC")
    @JoinColumn(name = "MaTC")
    private TrieuchungEntity trieuTrung;

    public BenhTrieuChungId getId() {
        return id;
    }

    public void setId(BenhTrieuChungId id) {
        this.id = id;
    }

    public BenhEnity getBenh() {
        return benh;
    }

    public void setBenh(BenhEnity benh) {
        this.benh = benh;
    }

    public TrieuchungEntity getTrieuTrung() {
        return trieuTrung;
    }

    public void setTrieuTrung(TrieuchungEntity trieuTrung) {
        this.trieuTrung = trieuTrung;
    }
    
    
}
