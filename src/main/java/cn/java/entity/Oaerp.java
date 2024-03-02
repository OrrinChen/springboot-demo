package cn.java.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Oaerp {
    private String id;

    private String status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")
    private Date time;

    private String sourcetable;

    private String sourceid;

    private String sourcejson;

    private String changelog;
    
    private String targettable;
    
    private String targetid;
    
    private String targetjson;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")
    private Date changetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSourcetable() {
        return sourcetable;
    }

    public void setSourcetable(String sourcetable) {
        this.sourcetable = sourcetable;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourcejson() {
        return sourcejson;
    }

    public void setSourcejson(String sourcejson) {
        this.sourcejson = sourcejson;
    }

    public String getchangelog() {
        return changelog;
    }

    public void setchangelog(String changelog) {
        this.changelog = changelog;
    }

	public String getTargettable() {
		return targettable;
	}

	public void setTargettable(String targettable) {
		this.targettable = targettable;
	}

	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}

	public String getTargetjson() {
		return targetjson;
	}

	public void setTargetjson(String targetjson) {
		this.targetjson = targetjson;
	}

	public Date getchangetime() {
		return changetime;
	}

	public void setchangetime(Date changetime) {
		this.changetime = changetime;
	}
	
}