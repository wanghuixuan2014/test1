package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		Integer total = itemMapper.selectCount(null);
		int start=(page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start, rows);
		
		return new EasyUITable(total, itemList);
	}
	@Transactional
	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
		
	}
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
		
	}
	@Override
	public void upateStatus(Long[] ids, int status) {
		Item item = new Item();
		item.setStatus(status);
		UpdateWrapper<Item> updateWrapper=new UpdateWrapper<>();
		List<Long> asList = Arrays.asList(ids);
		updateWrapper.in("id", asList);
		itemMapper.update(item, updateWrapper);
		
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}
	@Override
	public void deleteItems(Long[] ids) {
		List<Long> asList = Arrays.asList(ids);
		
		itemMapper.deleteBatchIds(asList);
		itemDescMapper.deleteItems(ids);
		
	}
	@Override
	public Item findItemById(Long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
