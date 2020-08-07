package entity;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {
	private String itemId;
	private String name;
	private String address;
	private Set<String> keywords;
	private String imageUrl;
	private String url;


public JSONObject toJSONObject() {
	JSONObject obj = new JSONObject();
	obj.put("item_id", itemId);
	obj.put("name", name);
	obj.put("address", address);
	obj.put("keywords", new JSONArray(keywords));
	obj.put("image_url", imageUrl);
	obj.put("url", url);
	return obj;
}
}


/*
public class Item {
	private String itemId;
	private String name;
	private String address;
	private Set<String> keywords;
	private String imageUrl;
	private String url;
	
	//constructor, support overload
	public Item(String itemId, String name, String address, String keywords, String imageUrl, String url) {
		this.itemId = itemId;
		this.name = name;
	}
	
	public Item(String itemId, String name) {
		this.itemId = itemId;
		this.name = name;
		this.address = null;
	}
	//item builder, cannot be public, otherwise users may cause trouble.
	private Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.address = builder.address;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.keywords = builder.keywords;
	}

	// we could delete setter in this program
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	//build pattern(embedded class), can avoid writing many constructors
	public static class ItemBuilder {
		private String itemId;
		private String name;
		private String address;
		private Set<String> keywords;
		private String imageUrl;
		private String url;
		
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setKeywords(Set<String> keywords) {
			this.keywords = keywords;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public void setUrl(String url) {
			this.url = url;
//			return this; every method contain return this could support continuous setting
		}
		
		public Item build() {
			return new Item(this); //call item function: itembuilder --> item, here 
									// "this" is the current object that call the function

		}
	}
	
}
*/
//  ItemBuilder builder = new ItemBuilder(); //if no parameters,  then all filed == null(0, false, null)
//	builder.setItem("id");
//	builder.setName("name"); // more flexible
// generate item object: Item item = builder.build(); or Item item = new Item("id","name",..)
//	create second item
//	builder.setName("sss");
//	Item item2 = builder.build();