package com.ls.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;


/**
 * The persistent class for the CURRENCY database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long code;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ISO_CURR_CODE")
	private String isoCurrCode;

	private String name;

	private String symbol;

	@Column(name="XUP_SCALE")
	private BigDecimal xupScale;

    public Currency() {
    }

	public long getCode() {
		return this.code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsoCurrCode() {
		return this.isoCurrCode;
	}

	public void setIsoCurrCode(String isoCurrCode) {
		this.isoCurrCode = isoCurrCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getXupScale() {
		return this.xupScale;
	}

	public void setXupScale(BigDecimal xupScale) {
		this.xupScale = xupScale;
	}

}