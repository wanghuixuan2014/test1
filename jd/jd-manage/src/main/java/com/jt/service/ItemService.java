package com.jt.service;

import java.util.List;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemService {

	List<Item> findObject(Integer page, Integer rows);

	void itemSave(Item item, ItemDesc itemDesc);

	void itemUpdate(Item item, ItemDesc itemDesc);

	

	void iteminstock(Long[] ids, Integer status);

	ItemDesc findItemQuery(Long itemId);
	
}
