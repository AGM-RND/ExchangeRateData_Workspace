package com.ls.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T16:17:17.252-0400")
@StaticMetamodel(ExchangeRate.class)
public class ExchangeRate_ {
	public static volatile SingularAttribute<ExchangeRate, String> fromCur;
	public static volatile SingularAttribute<ExchangeRate, BigDecimal> fxRates;
	public static volatile SingularAttribute<ExchangeRate, Date> processDate;
	public static volatile SingularAttribute<ExchangeRate, String> toCur;
}
