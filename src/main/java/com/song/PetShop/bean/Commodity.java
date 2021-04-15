package com.song.PetShop.bean;

import java.util.Date;
import java.util.List;

public class Commodity {
    private Integer cid;

    private String cname;

    private String cphoto;
    
    //数据库没有该字段
    private String cphoto2;

    private Double mprice;

    private Double cprice;

    private Date cBtime;

    private Integer hot;

    private Date cEtime;

    private String csize;

    private Integer cnum;

    private String cdescride;

    private Integer tid;

    private Integer sid;

    private String temp1;

    private Integer temp2;

    private String temp3;
    
    private Type type;
    
    private List<Favorite> favorite;
    
    
    
    public String getCphoto2() {
		return cphoto2;
	}

	public void setCphoto2(String cphoto2) {
		this.cphoto2 = cphoto2;
	}

	public List<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(List<Favorite> favorite) {
		this.favorite = favorite;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCphoto() {
        return cphoto;
    }

    public void setCphoto(String cphoto) {
        this.cphoto = cphoto == null ? null : cphoto.trim();
    }

    public Double getMprice() {
        return mprice;
    }

    public void setMprice(Double mprice) {
        this.mprice = mprice;
    }

    public Double getCprice() {
        return cprice;
    }

    public void setCprice(Double cprice) {
        this.cprice = cprice;
    }

    public Date getcBtime() {
        return cBtime;
    }

    public void setcBtime(Date cBtime) {
        this.cBtime = cBtime;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Date getcEtime() {
        return cEtime;
    }

    public void setcEtime(Date cEtime) {
        this.cEtime = cEtime;
    }

    public String getCsize() {
        return csize;
    }

    public void setCsize(String csize) {
        this.csize = csize == null ? null : csize.trim();
    }

    public Integer getCnum() {
        return cnum;
    }

    public void setCnum(Integer cnum) {
        this.cnum = cnum;
    }

    public String getCdescride() {
        return cdescride;
    }

    public void setCdescride(String cdescride) {
        this.cdescride = cdescride == null ? null : cdescride.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1 == null ? null : temp1.trim();
    }

    public Integer getTemp2() {
        return temp2;
    }

    public void setTemp2(Integer temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3 == null ? null : temp3.trim();
    }
}