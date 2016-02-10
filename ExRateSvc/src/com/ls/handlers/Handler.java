package com.ls.handlers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.BeforeCompletion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ls.entities.ExchangeRate;


@Stateless(name="HandlerEJB")
public class Handler implements IHandler{
	
	@PersistenceContext(unitName="ExRateJPA")
    EntityManager em;
	
	public String sayHello(String name){
		String str = "Hello: " + name;

		Query q = em.createQuery("SELECT ex.fxRates FROM ExchangeRate ex", ExchangeRate.class).setMaxResults(200);
		
		@SuppressWarnings("unchecked")
		List<BigDecimal> values = (List<BigDecimal>)q.getResultList();
		for(BigDecimal bd : values){
			System.out.println(bd);
		}
		
		return str;
	}
	
		
}
