package com.wjl.portal.pojo;
/**
 * solr的Ik分词器的字段
 * @author wujiale
 * 2017-10-30 上午10:26:19
 */
public class SolrField {
	private String id;
	private String title;
	private String sell_point;
	private Long price;
	private String image;
	private String category_name;
	private String item_desc;

	public SolrField() {

	}

	public SolrField(String id, String title, String sell_point, Long price, String image, String category_name,
			String item_desc) {
		super();
		this.id = id;
		this.title = title;
		this.sell_point = sell_point;
		this.price = price;
		this.image = image;
		this.category_name = category_name;
		this.item_desc = item_desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
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

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

}
