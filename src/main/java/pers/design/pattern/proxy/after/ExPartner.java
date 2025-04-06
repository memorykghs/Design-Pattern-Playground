package pers.design.pattern.proxy.after;

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
		DivorceService service = new LawyerProxy(new Lawyer());
		service.propertyDistribute();
		service.getComfortMoney();
		service.getAlimony();
		
//		DivorceService service = new Lawyer();
	}
}
