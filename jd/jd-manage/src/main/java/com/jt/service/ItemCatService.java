package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUIT;

public interface ItemCatService {

	String findItemCat(Long itemCatId);

	List<EasyUIT> itemCatTree(Long parentId);

}
