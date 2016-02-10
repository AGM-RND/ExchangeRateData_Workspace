package com.ls.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CURRENCY_NETWORK database table.
 * 
 */
@Entity
@Table(name="CURRENCY_NETWORK")
public class CurrencyNetwork implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CurrencyNetworkPK id;

	@Column(name="DEFAULT_CURRENCY")
	private String defaultCurrency;

    public CurrencyNetwork() {
    }

	public CurrencyNetworkPK getId() {
		return this.id;
	}

	public void setId(CurrencyNetworkPK id) {
		this.id = id;
	}
	
	public String getDefaultCurrency() {
		return this.defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

}