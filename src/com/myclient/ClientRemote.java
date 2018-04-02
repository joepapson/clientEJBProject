package com.myclient;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.BanqueRemote;
import metier.entities.Compte;

public class ClientRemote {

	public static void main(String[] args) {
		
		try {
			Context ctx = new InitialContext();
			String appName = "banqueEAR";
			String moduleName = "banqueEJB";
			String beanName = "BK";
			String remoteInterface = BanqueRemote.class.getName();
			String name = "ejb:"+ appName +"/" +moduleName+ "/"+beanName+ "!"+ remoteInterface;
			BanqueRemote proxy = (BanqueRemote) ctx.lookup(name);
			
			/*proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());*/
			
			Compte cp = proxy.getCompte(1L);
			System.out.println(cp.getSolde());
			
			proxy.verser(1L,  4000);
			proxy.retirer(1L, 2000);
			proxy.virement(1L, 2L, 1000);
			List<Compte>cptes = proxy.listComptes();
			
			cptes.forEach(x-> System.out.println(x.getCode()+
					":"+ x.getSolde()));
			
		} catch (NamingException e) {
			
			
			e.printStackTrace();
		}
	}
}
