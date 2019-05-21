package es.jdl.entrysign.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

@Entity
public class EntrySign {

    @Id
    private Long id;
    @Index
    private String user;
    private Date signTime;
    private EntrySignType type;
    private GeoPosition geo;
    @Ignore
    private Date actualTime;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public EntrySignType getType() {
        return type;
    }

    public void setType(EntrySignType type) {
        this.type = type;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeoPosition getGeo() {
        return geo;
    }

    public void setGeo(GeoPosition geo) {
        this.geo = geo;
    }
}
