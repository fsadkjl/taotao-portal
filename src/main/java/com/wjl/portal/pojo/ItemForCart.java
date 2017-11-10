package com.wjl.portal.pojo;
/**
 * 购物车商品的属性
 * @author wujiale
 * 2017-11-02 下午10:03:03
 */
public class ItemForCart {
	
	private Long id;
	private String title;
	private Integer num;
	private Long price;
	private String image;

	public ItemForCart() {
		// TODO Auto-generated constructor stub
	}

	public ItemForCart(Long id, String title, Integer num, Long price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.num = num;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
