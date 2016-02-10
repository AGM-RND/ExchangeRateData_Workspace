package com.ls.svc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import com.chetty.reporting.engine.Reporter;
import com.ls.handlers.IHandler;
import com.ls.common.CommonUtils;
import com.ls.entities.Currency;
import com.ls.entities.CurrencyNetwork;
import com.ls.entities.CurrencyNetworkPK_;
import com.ls.entities.CurrencyNetwork_;
import com.ls.entities.Currency_;
import com.ls.entities.ExchangeRate;
import com.ls.entities.ExchangeRate_;
import com.ls.svc.api.IExRateSvc;

@Stateless
@javax.ws.rs.Path("/")
@CrossOriginResourceSharing(allowAllOrigins = true)
@Produces({ MediaType.APPLICATION_JSON })
public class ExRateSvc implements IExRateSvc {

	@PersistenceContext(unitName = "ExRateJPA")
	protected EntityManager em;
	
	public static enum FileType {PDF, XLS, XLSX};

	@EJB
	IHandler handler;
	
	public File forAll(FileType fileType, String startDateStr, String endDateStr, String fromCurCode, String toCurCode) throws Exception{
		final List<ExchangeRate> dataBeanList = get1MethodXML("ashu", startDateStr, endDateStr, fromCurCode, toCurCode);
		File f = Reporter.download(fileType, dataBeanList);
		return f;
	}
	
	/*
	 * Download PDF file
	 */
    @GET
    @Path("/pdf/{startDateStr}/{endDateStr}/{fromCurCode}/{toCurCode}")
    @Produces("application/pdf")
    public Response getPDF(@PathParam("startDateStr") String startDateStr,
			@PathParam("endDateStr") String endDateStr,
			@PathParam("fromCurCode") String fromCurCode,
			@PathParam("toCurCode") String toCurCode) throws Exception {
    	File f = forAll(FileType.PDF, startDateStr, endDateStr, fromCurCode, toCurCode);
    	return Response.ok(f).build();
    }
    
    @GET
    @Path("/excel/{startDateStr}/{endDateStr}/{fromCurCode}/{toCurCode}")
    @Produces("application/vnd.ms-excel")
    public Response getExcel(@PathParam("startDateStr") String startDateStr,
			@PathParam("endDateStr") String endDateStr,
			@PathParam("fromCurCode") String fromCurCode,
			@PathParam("toCurCode") String toCurCode) throws Exception{
    	File f = forAll(FileType.XLS, startDateStr, endDateStr, fromCurCode, toCurCode);
    	return Response.ok(f).build();
    }
    
    @GET
    @Path("/excelx")
    @Produces("application/vnd.ms-excel")
    public Response getExcelx(@PathParam("startDateStr") String startDateStr,
			@PathParam("endDateStr") String endDateStr,
			@PathParam("fromCurCode") String fromCurCode,
			@PathParam("toCurCode") String toCurCode) throws Exception{
    	File f = forAll(FileType.XLSX, startDateStr, endDateStr, fromCurCode, toCurCode);
    	return Response.ok(f).build();
    }


	@GET
	@javax.ws.rs.Path("/name/{i}/{startDateStr}/{endDateStr}/{fromCurCode}/{toCurCode}/xml")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Produces({ MediaType.APPLICATION_XML })
	public List<ExchangeRate> get1MethodXML(@PathParam("i") String i,
			@PathParam("startDateStr") String startDateStr,
			@PathParam("endDateStr") String endDateStr,
			@PathParam("fromCurCode") String fromCurCode,
			@PathParam("toCurCode") String toCurCode) {

		Date startDate = CommonUtils.parseDate(startDateStr);
		Date endDate = CommonUtils.parseDate(endDateStr);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExchangeRate> cq = cb.createQuery(ExchangeRate.class);
		Root<ExchangeRate> exRate = cq.from(ExchangeRate.class);
		cq.select(exRate);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate pr = cb.between(exRate.get(ExchangeRate_.processDate), startDate, endDate);
		predicates.add(pr);
		
		if(!"*".equalsIgnoreCase(fromCurCode)){
			predicates.add(cb.and(cb.upper(exRate.get(ExchangeRate_.fromCur)).in(fromCurCode.toUpperCase())));
		}
		if(!"*".equalsIgnoreCase(toCurCode)){
			predicates.add(cb.and(cb.upper(exRate.get(ExchangeRate_.toCur)).in(toCurCode.toUpperCase())));
		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		
		TypedQuery<ExchangeRate> qu = em.createQuery(cq);
		List<ExchangeRate> exRates = qu.getResultList();
		
		return exRates;
	}

	@GET
	@javax.ws.rs.Path("/name/{i}/{startDateStr}/{endDateStr}/{fromCurCode}/{toCurCode}/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ExchangeRate> get2MethodJSON(@PathParam("i") String i,
			@PathParam("startDateStr") String startDateStr,
			@PathParam("endDateStr") String endDateStr,
			@PathParam("fromCurCode") String fromCurCode,
			@PathParam("toCurCode") String toCurCode) {
		return get1MethodXML(i, startDateStr, endDateStr, fromCurCode,
				toCurCode);
	}

	@GET
	@javax.ws.rs.Path("/sum/{i}/{j}")
	public String get2Method(@PathParam("i") int i, @PathParam("j") int j) {
		String str = "Sum is:" + (i + j);
		System.out.println(str);
		return str;
	}
	
	@GET
	@javax.ws.rs.Path("/networkCurrencies")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Currency> getNetworkCurrencies() {
		
		CriteriaBuilder cb1 = em.getCriteriaBuilder();
		CriteriaQuery<Currency> cq1 = cb1.createQuery(Currency.class);
		Root<Currency> cur = cq1.from(Currency.class);
		cq1.select(cur).distinct(true);
		
		
		CriteriaBuilder cb2 = em.getCriteriaBuilder();
		CriteriaQuery<CurrencyNetwork> cq2 = cb2.createQuery(CurrencyNetwork.class);
		Root<CurrencyNetwork> curNetwork = cq2.from(CurrencyNetwork.class);
		cq2.select(curNetwork);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate pr = cur.get(Currency_.code).in(curNetwork.get(CurrencyNetwork_.id).get(CurrencyNetworkPK_.code));
		predicates.add(pr);
		
		cq1.where(predicates.toArray(new Predicate[predicates.size()]));
		
		TypedQuery<Currency> qu = em.createQuery(cq1);
		List<Currency> currencies = qu.getResultList();
		
		return currencies;
	}
	
	@GET
	@javax.ws.rs.Path("/allActiveCurrencies")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Currency> getAllActiveCurrencies() {
		
		CriteriaBuilder cb1 = em.getCriteriaBuilder();
		CriteriaQuery<Currency> cq1 = cb1.createQuery(Currency.class);
		Root<Currency> cur = cq1.from(Currency.class);
		cq1.select(cur).distinct(true);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate pr = cb1.and(cur.get(Currency_.isoCurrCode).isNotNull(), cb1.equal(cur.get(Currency_.isActive), "Y"));
		predicates.add(pr);
		
		cq1.where(predicates.toArray(new Predicate[predicates.size()]));
		
		TypedQuery<Currency> qu = em.createQuery(cq1);
		List<Currency> currencies = qu.getResultList();
		
		System.out.println(currencies.size());
		return currencies;
	}

}
