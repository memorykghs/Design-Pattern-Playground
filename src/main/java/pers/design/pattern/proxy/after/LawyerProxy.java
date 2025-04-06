package pers.design.pattern.proxy.after;

import java.math.BigDecimal;

/**
 * �e�U���߮v���N�z
 * 
 * @author memorykghs
 */
public class LawyerProxy implements DivorceService {

	private Lawyer lawyer;
	
	public LawyerProxy(Lawyer lawyer) {
		this.lawyer = lawyer;
	}

	@Override
	public BigDecimal propertyDistribute() {
		System.out.println("�]�����t - �߮v�����СG");
		return lawyer.propertyDistribute();
	}

	@Override
	public BigDecimal getComfortMoney() {
		System.out.println("������ - �߮v�����СG");
		return lawyer.getComfortMoney();
	}

	@Override
	public BigDecimal getAlimony() {
		System.out.println("ĺ�i�O - �߮v�����СG");
		return lawyer.getAlimony();
	}

}
