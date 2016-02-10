package com.ls.handlers;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IHandler {
	public String sayHello(String name);
}
