package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 *	FileName :  PurchaseServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		Purchase purchase = new Purchase();
		User user = new User();
		Product prod = new Product();
		
		user.setUserId("user01");
		prod.setProdNo(10000);
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(prod);
		
		purchase.setPaymentOption("1");
		purchase.setReceiverName("�Ϸ�");
		purchase.setReceiverPhone("1234");
		purchase.setDivyAddr("����");
		purchase.setDivyRequest("��似��");
		purchase.setTranCode("5");

		purchaseService.addPurchase(purchase);
		
		System.out.println("purchase : "+purchase);

		// product = productService.getProduct(10000);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10000, prod.getProdNo());
		Assert.assertEquals("�Ϸ�", purchase.getReceiverName());
		Assert.assertEquals("5", purchase.getTranCode());
	}

	//@Test
	public void testGetPurchase() throws Exception {
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10010);
		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("�Ϸ�", purchase.getReceiverName());
		Assert.assertEquals("����", purchase.getDivyAddr());

	}

	 @Test
	public void testUpdatePurchase() throws Exception {
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10010);
		//Assert.assertNotNull(purchase);
		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("�Ϸ�", purchase.getReceiverName());
		Assert.assertEquals("����", purchase.getDivyAddr());
		
		purchase.setPaymentOption("2");
		purchase.setReceiverName("updateUser");
		purchase.setReceiverPhone("123123");
		//purchase.setDivyAddr("testUpdateAddr");
		//purchase.setDivyRequest("������~");
		
		purchaseService.updatePurcahse(purchase);
		
		purchaseService.getPurchase(10010);
		
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("updateUser", purchase.getReceiverName());
		Assert.assertEquals("123123", purchase.getReceiverPhone());
	}

	// @Test
	public void testUpdateTranCode() throws Exception {
		
		Product product = new Product();
		product.setProdNo(10000);
		
		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setTranCode("2");
		
		purchaseService.updateTranCode(purchase);

	}

	// @Test
	public void testGetPurchaseListByProdNo() throws Exception {

	}
}
