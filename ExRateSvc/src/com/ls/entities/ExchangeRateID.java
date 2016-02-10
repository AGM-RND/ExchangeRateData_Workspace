package com.ls.entities;

import java.io.Serializable;
import java.util.Date;



public class ExchangeRateID implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fromCur;
	private Date processDate;
	
	public String getFromCur() {
		return fromCur;
	}
	public void setFromCur(String fromCur) {
		this.fromCur = fromCur;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	public int hashCode() {
        return (int) fromCur.hashCode() + (int)processDate.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ExchangeRateID)) return false;
        ExchangeRateID pk = (ExchangeRateID) obj;
        return this.getFromCur().equalsIgnoreCase(pk.getFromCur()) && (this.getProcessDate().compareTo(pk.getProcessDate()) == 0);
    }
}
