package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jt.service.ItemCatService;
import com.jt.vo.EasyUIT;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService  itemCatService;
	@RequestMapping("/queryItemName")
	public String findItemCat(Long itemCatId) {
		return itemCatService.findItemCat(itemCatId);
				
	}
	@RequestMapping("list")
	private List<EasyUIT> itemCatTree(@RequestParam(defaultValue = "0",name = "id")Long parentId) {
	
		return itemCatService.itemCatTree(parentId);
	}
}
