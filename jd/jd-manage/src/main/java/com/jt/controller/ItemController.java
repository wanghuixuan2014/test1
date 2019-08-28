package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemDescService;
import com.jt.service.ItemService;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/query")
	public List<Item> findObject(Integer page,Integer rows){
		
		return itemService.findObject(page,rows);
	}
	
	@RequestMapping("/save")
	public SysResult itemSave(Item item,ItemDesc itemDesc) {
		itemService.itemSave(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("/update")
	public SysResult itemUpdate(Item item,ItemDesc itemDesc) {
		itemService.itemUpdate(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("/instock")
	public SysResult iteminstock(Long[] ids) {
		int status=2;
		itemService.iteminstock(ids,status);
		return SysResult.success();
	}
	@RequestMapping("/reshelf")
	public SysResult itemreshelf(Long[] ids) {
		int status=1;
		itemService.iteminstock(ids,status);
		return SysResult.success();
	}
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult itemQuery(@PathVariable Long itemId) {
		ItemDesc list= itemService.findItemQuery(itemId);
		return SysResult.success(list);
	}
}
