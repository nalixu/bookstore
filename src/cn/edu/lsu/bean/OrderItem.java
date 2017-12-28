package cn.edu.lsu.bean;

public class OrderItem {
	
	public String toString() {
		return "OrderItem [order_id=" + order_id + ", product_id=" + product_id
				+ ", buynum=" + buynum + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", imgurl=" + imgurl
				+ ", description=" + description + "]";
	}
	public OrderItem(String order_id, String product_id, int buynum,
			String name, Double price, String category, String imgurl,
			String description) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.buynum = buynum;
		this.name = name;
		this.price = price;
		this.category = category;
		this.imgurl = imgurl;
		this.description = description;
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
	private String order_id;
	private String product_id;
	private int buynum;
	private String name;
	private Double price;
	private String category;
	private String imgurl;
	private String description;


	public OrderItem() {
		
	}

	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}


}
