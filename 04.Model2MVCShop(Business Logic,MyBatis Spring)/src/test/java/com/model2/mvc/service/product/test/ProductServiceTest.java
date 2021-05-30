package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;

/*
 *	FileName :  ProductServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	// @Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setPrice(50000);
		product.setProdNo(10008);
		product.setProdDetail("testProdDetail");
		product.setProdName("testProd");

		productService.addProduct(product);

		// product = productService.getProduct(10000);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertEquals("testProd", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals(10008, product.getProdNo());
	}

	// @Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
//		//==> �ʿ��ϴٸ�...
//		product.setPrice(50000);
//		product.setProdDetail("testProdDetail");
//		product.setProdName("testProd");
//		product.setProdNo(10008);

		product = productService.getProduct(10020);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertEquals("testProd", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals(10020, product.getProdNo());

		Assert.assertNotNull(productService.getProduct(10001));
		// Assert.assertNotNull(userService.getUser("user05"));
	}

	// @Test
	public void testUpdateProduct() throws Exception {

		Product product = productService.getProduct(10020);
		Assert.assertNotNull(product);

		Assert.assertEquals("testProd", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals(10020, product.getProdNo());

		product.setProdName("testProdupdated");
		product.setProdDetail("testProdDetailupdated");
		product.setPrice(80000);

		productService.updateProduct(product);

		product = productService.getProduct(10020);
		Assert.assertNotNull(product);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertEquals("testProdupdated", product.getProdName());
		Assert.assertEquals("testProdDetailupdated", product.getProdDetail());
		Assert.assertEquals(80000, product.getPrice());
		Assert.assertEquals(10020, product.getProdNo());
	}

	// @Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
		// System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}

	@Test
	public void testGetProductListByProdNo() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10000");
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(1, list.size());

		// ==> console Ȯ��
		System.out.println("1"+list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword("" + System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());

		// ==> console Ȯ��
		System.out.println("2"+list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}
}