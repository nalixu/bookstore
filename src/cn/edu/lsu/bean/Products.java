package cn.edu.lsu.bean;


import java.io.Serializable;

public class Products implements Serializable{
	
	public int hashCode() {
		
		System.out.println("hashCode :" + (this.getId() + this.getName()).hashCode());
		return (this.getId() + this.getName()).hashCode();
	
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null) {
			return false;
		}
		if(obj instanceof Products) {
			Products i = (Products)obj;
			//System.out.println(" ����   "+this.getName()  + " �� �ȽϵĶ���   " +i.getName());
			//System.out.println(" ����   "+this.getId()  + " �� �ȽϵĶ���   " +i.getId());
			//System.out.println("�ȽϽ��" +(this.getId().equals(i.getId()) && this.getName().equals(i.getName())));
			if(this.getId().equals(i.getId()) && this.getName().equals(i.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", pnum=" + pnum + ", imgurl="
				+ imgurl + ", description=" + description + "]";
	}
	public Products(String id, String name, Double price, String category,
			int pnum, String imgurl, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
	}
	public Products() {
		
		
	}
	
	
	private String id;
	private String name;
	private Double price;
	private String category;
	private int pnum;
	private String imgurl;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
