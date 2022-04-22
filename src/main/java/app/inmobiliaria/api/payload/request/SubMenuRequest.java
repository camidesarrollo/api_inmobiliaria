package app.inmobiliaria.api.payload.request;


import app.inmobiliaria.api.entity.Menu;

public class SubMenuRequest {

    private Long sub_menu_id;
   

   private String sub_menu_title;
	

   private String sub_menu_path;
	

   private String sub_menu_icon;

   	private Long menu;

	public Long getSub_menu_id() {
		return sub_menu_id;
	}

	public void setSub_menu_id(Long sub_menu_id) {
		this.sub_menu_id = sub_menu_id;
	}

	public String getSub_menu_title() {
		return sub_menu_title;
	}

	public void setSub_menu_title(String sub_menu_title) {
		this.sub_menu_title = sub_menu_title;
	}

	public String getSub_menu_path() {
		return sub_menu_path;
	}

	public void setSub_menu_path(String sub_menu_path) {
		this.sub_menu_path = sub_menu_path;
	}

	public String getSub_menu_icon() {
		return sub_menu_icon;
	}

	public void setSub_menu_icon(String sub_menu_icon) {
		this.sub_menu_icon = sub_menu_icon;
	}

	public Long getMenu() {
		return menu;
	}

	public void setMenu(Long menu) {
		this.menu = menu;
	}
   	
   	
}
