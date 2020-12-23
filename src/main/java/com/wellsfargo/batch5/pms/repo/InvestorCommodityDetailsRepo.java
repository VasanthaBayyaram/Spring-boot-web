package com.wellsfargo.batch5.pms.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wellsfargo.batch5.pms.entity.CommodityEntity;
import com.wellsfargo.batch5.pms.entity.InvestorCommodityDetailsEntity;
import com.wellsfargo.batch5.pms.entity.InvestorEntity;
import com.wellsfargo.batch5.pms.exception.PortfolioException;

@Repository
public interface InvestorCommodityDetailsRepo extends JpaRepository<InvestorCommodityDetailsEntity, Integer>{

	boolean existsByInvestor(InvestorEntity investor)throws PortfolioException;
	boolean existsByCommodity(CommodityEntity commodity)throws PortfolioException;

	List<InvestorCommodityDetailsEntity> findAllByInvestor(InvestorEntity investor);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	 @Query("UPDATE InvestorCommodityDetailsEntity icde SET icde.unit=:quantity, icde.date=:date WHERE icde.investorCommKey=:key")
	void update(@Param("quantity") Integer quantity,@Param("key") Integer key, @Param("date") LocalDate date);

	InvestorCommodityDetailsEntity findByCommodity(CommodityEntity commodity);
	InvestorCommodityDetailsEntity findByCommodityAndInvestor(CommodityEntity commodity,
			InvestorEntity investor);


}
