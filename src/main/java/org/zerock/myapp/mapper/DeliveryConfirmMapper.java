package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.DeliveryConfirmDTO;
import org.zerock.myapp.domain.OrderDTO;

public interface DeliveryConfirmMapper {


	@Select("""
			SELECT O.no, O.member_id, O.order_date, P.name
			FROM mymg_order O
			join order_list L
			on O.no = L.Order_no
			join product P
			on L.product_no = P.no
			WHERE member_id = #{id}
			""")
	public abstract List<DeliveryConfirmDTO> SelectOrderList(String id);		// 주문내역 가져오기
	
	@Select("""
			SELECT *
			FROM delivery_confirm
			WHERE order_no = #{order_no}
			""")
	public abstract List<DeliveryConfirmDTO> SelectDeliveryList(Integer order_no);	// 배송현황 가져오기
	
	public abstract Integer DeliveryConfirmInsert(DeliveryConfirmDTO dto);
	
} // end interface
