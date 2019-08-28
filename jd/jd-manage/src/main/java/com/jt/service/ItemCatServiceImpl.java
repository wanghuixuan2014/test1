package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUIT;

@Service
public class ItemCatServiceImpl implements ItemCatService{
  
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public String findItemCat(Long itemCatId) {
		ItemCat catId = itemCatMapper.selectById(itemCatId);
		
		return catId.getName();
	
	}
	public List<ItemCat> itemcatd(Long parentId){
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		
		return itemCatMapper.selectList(queryWrapper);
	}
	@Override
	public List<EasyUIT> itemCatTree(Long parentId) {
	List<ItemCat> p=itemcatd(parentId);
	List<EasyUIT> arrayList = new ArrayList<>();
	for (ItemCat ea : p) {
		
		EasyUIT eq = new EasyUIT();
		String st=ea.getIsParent()?"closed":"open";
		eq.setId(ea.getId());
		eq.setText(ea.getName());
		eq.setState(st);
		arrayList.add(eq);
	}
		
		
		return arrayList;
	}

}
