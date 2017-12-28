package cn.edu.lsu.dao;

import java.util.List;

import cn.edu.lsu.bean.Products;



public interface ProductsDAO {
	
	//插入商品记录
	
	public int addProducts(Products products);
	
	
	//删除商品记录
	
	public int delProducts(String id);
	
	//查询所有商品记录
	
	public List<Products> queryAll();
	
	//查询指定商品
	public Products queryById(String id);
	
	//更新商品记录
	public Products update(Products products);
	
	
	public List<Products> getWeekHotProduct();
	
	//多条件查询
	public List<Products> findProductByManyCondition(String id,
			String category, String name, String minprice, String maxprice);


	public List<Products> findBookByName(String name);


}
