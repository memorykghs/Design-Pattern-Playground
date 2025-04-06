package pers.design.pattern.proxy.before;

/**
 * <pre>
 * �e�t���P�Q�H�����˱�
 * �N�O Client ��
 * </pre>
 * 
 * @author memorykghs
 */
public class ExPartner {
	public static void main(String[] args) {
		DivorceService service = new Lawyer();
		service.propertyDistribute();
		service.getComfortMoney();
		service.getAlimony();
	}
}
