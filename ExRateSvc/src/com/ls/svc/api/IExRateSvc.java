package com.ls.svc.api;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import com.ls.entities.ExchangeRate;

/*import com.entities.ExchangeRate;*/

@Local
public interface IExRateSvc {
	public List<ExchangeRate> get1MethodXML(String i, String startDateStr, String endDateStr, String fromCurCode, String toCurCode);
	public List<ExchangeRate> get2MethodJSON(String i, String startDateStr, String endDateStr, String fromCurCode, String toCurCode);
	public String get2Method(int i, int j);
}
