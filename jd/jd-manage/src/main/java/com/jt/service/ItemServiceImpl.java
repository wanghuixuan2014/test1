package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
   @Autowired
   private ItemDescMapper itemDescMapper;
	@Override
	public List<Item> findObject(Integer page, Integer rows) {
		Integer total= itemMapper.selectCount(null);
		int start=(page-1)*rows;
		
	List<Item> op=	(List<Item>) itemMapper.findById(start,rows);
		return op;
	}

	
	@Transactional
	@Override
	public void itemSave(Item item,ItemDesc itemDesc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void itemUpdate(Item item,ItemDesc itemDesc) {
		
		item.setUpdated(new Date());
		
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId())
	      .setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
		
	}

	@Override
	public void iteminstock(Long[] ids, Integer status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		List<Long> idlist=Arrays.asList(ids);
		updateWrapper.in("id", idlist);
		itemMapper.update(item, updateWrapper);
		
	}


	@Override
	public ItemDesc findItemQuery(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}

	
	
	
	
	
	
	
}
