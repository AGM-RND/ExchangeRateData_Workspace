package com.ls.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CURRENCY_NETWORK database table.
 * 
 */
@Embeddable
public class CurrencyNetworkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long code;

	private long nid;

    public CurrencyNetworkPK() {
    }
	public long getCode() {
		return this.code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public long getNid() {
		return this.nid;
	}
	public void setNid(long nid) {
		this.nid = nid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CurrencyNetworkPK)) {
			return false;
		}
		CurrencyNetworkPK castOther = (CurrencyNetworkPK)other;
		return 
			(this.code == castOther.code)
			&& (this.nid == castOther.nid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.code ^ (this.code >>> 32)));
		hash = hash * prime + ((int) (this.nid ^ (this.nid >>> 32)));
		
		return hash;
    }
}