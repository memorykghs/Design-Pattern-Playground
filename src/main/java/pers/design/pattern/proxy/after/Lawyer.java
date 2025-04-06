package pers.design.pattern.proxy.after;

import java.math.BigDecimal;

/**
 * <pre>
 * �߮v
 * ��ڳB�z�U�تk�߬����Ʊ����H
 * </pre>
 * 
 * @author memorykghs
 *
 */
public class Lawyer implements DivorceService {

	@Override
	public BigDecimal propertyDistribute() {
		System.out.println("�b��u��100��");
		return new BigDecimal("50");
	}

	@Override
	public BigDecimal getComfortMoney() {
		System.out.println("���O�ƥD�����A�ҥH���ν�");
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getAlimony() {
		System.out.println("�S���p�ġA���Ӫ�ĺ�i�O");
		return BigDecimal.ZERO;
	}

}
