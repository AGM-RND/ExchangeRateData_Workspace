package com.ls.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EXCHANGE_RATES database table.
 * 
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name="ExchangeRate") @IdClass(ExchangeRateID.class)
@Table(name="EXCHANGE_RATES")
public class ExchangeRate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FROM_CUR")
	private String fromCur;

	@Column(name="FX_RATES")
	private BigDecimal fxRates;

	@Id
    @Temporal( TemporalType.DATE)
	@Column(name="PROCESS_DATE")
	private Date processDate;

	@Column(name="TO_CUR")
	private String toCur;

    public ExchangeRate() {
    }

	public String getFromCur() {
		return this.fromCur;
	}

	public void setFromCur(String fromCur) {
		this.fromCur = fromCur;
	}

	public BigDecimal getFxRates() {
		return this.fxRates.setScale(3,BigDecimal.ROUND_UP);
	}

	public void setFxRates(BigDecimal fxRates) {
		this.fxRates = fxRates;
	}

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getToCur() {
		return this.toCur;
	}

	public void setToCur(String toCur) {
		this.toCur = toCur;
	}

}