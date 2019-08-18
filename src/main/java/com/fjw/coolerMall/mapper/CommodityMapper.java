package com.fjw.coolerMall.mapper;

import java.util.List;
import com.fjw.coolerMall.model.request.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;


public interface CommodityMapper {

	public List<Product> getProductList(@Param(value = "filterName")String filterName,
										@Param(value = "classify")String classify, @Param(value = "sortKey")String sortKey,
										@Param(value = "sortDir")String sortDir, @Param(value = "brand")String brand, @Param(value = "pageNo")int pageNo,
										@Param(value = "pageSize")int pageSize) throws DataAccessException;
	
	public int getProductListCount (@Param(value = "filterName")String filterName, 
			@Param(value = "classify")String classify,@Param(value = "brand")String brand) throws DataAccessException;
}
