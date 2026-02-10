package item.service;

import java.util.List;

import item.model.Item;

public interface itemService {
	
	List<Item> getItems();
	Item getItem(Long id);
	Item getItemByName(String name);
	boolean createItem(Item item);
	boolean updateItem(Item item);
	boolean removeItem(Long id);
	boolean isNameExists(String name);
	boolean isNameExistsForOtherId(String name, Long currentId);
	
	

}
