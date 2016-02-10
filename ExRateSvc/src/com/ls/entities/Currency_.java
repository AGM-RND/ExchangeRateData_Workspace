package com.ls.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-19T14:30:35.441-0500")
@StaticMetamodel(Currency.class)
public class Currency_ {
	public static volatile SingularAttribute<Currency, Long> code;
	public static volatile SingularAttribute<Currency, String> isActive;
	public static volatile SingularAttribute<Currency, String> isoCurrCode;
	public static volatile SingularAttribute<Currency, String> name;
	public static volatile SingularAttribute<Currency, String> symbol;
	public static volatile SingularAttribute<Currency, BigDecimal> xupScale;
}
