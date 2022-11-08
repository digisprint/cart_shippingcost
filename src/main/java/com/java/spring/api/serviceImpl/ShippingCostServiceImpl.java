package com.java.spring.api.serviceImpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.api.entity.ShippingCost;
import com.java.spring.api.repository.ShippingCostRepository;
import com.java.spring.api.service.ShippingCostService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ShippingCostServiceImpl implements ShippingCostService{

	@Autowired
	private ShippingCostRepository shippingCostRepository;
	
	public Flux<ShippingCost> getAllShippingCosts(){
		return shippingCostRepository.findAll()
				.flatMap(p->{
					System.out.println("p.getId():"+p.getId());
					return Flux.just(p);
				}).delayElements(Duration.ofMillis(1000));
		//return shippingCostRepository.findAll();
	}
	
	public Mono<ShippingCost> getByShippingCostId(Integer id){
		return shippingCostRepository.findById(id)
				.doOnNext(p->{
					System.out.println("p.getId():"+p.getId());
					System.out.println("p.getZipCode():"+p.getZipCode());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					System.out.println("p.getShippingCost():"+p.getDefaultCost());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					
				})
				.flatMap(a-> Mono.just(a));
		//return shippingCostRepository.findById(id);
	}
	
	@Override
	public Mono<ShippingCost> getByZipCode(String zipCode) {
		return shippingCostRepository.findByZipCode(zipCode)
				.doOnNext(p->{
					System.out.println("p.getId():"+p.getId());
					System.out.println("p.getZipCode():"+p.getZipCode());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					System.out.println("p.getDefaultCost():"+p.getDefaultCost());
					
				})
				.flatMap(a-> Mono.just(a));
	}

	public Mono<ShippingCost> createShippingCost(Mono<ShippingCost> shippingCost) {
		return shippingCost.map(ShippingCostServiceImpl::monoToObj)
		.flatMap(shippingCostRepository::save);
	}
	
	public Flux<ShippingCost> createShippingCosts(Iterable<ShippingCost> shippingCosts) {
		return shippingCostRepository.saveAll(shippingCosts);
	}
	
	
	public Mono<ShippingCost> updateShippingCost(Mono<ShippingCost> shippingCost, Integer id){
		return shippingCostRepository.findById(id)
				.flatMap(p->shippingCost.map(ShippingCostServiceImpl::monoToObj)
				.doOnNext(e->e.setId(id)))
				.flatMap(shippingCostRepository::save);
				//.map(AppUtils::entityToDto);
	}
	
	
	public Mono<ShippingCost> updateShippingCostByZipCode(Mono<ShippingCost> shippingCost, String zipCode){
		return shippingCostRepository.findByZipCode(zipCode)
				.flatMap(p->shippingCost.map(ShippingCostServiceImpl::monoToObj)
				.doOnNext(e->e.setZipCode(zipCode)))
				.flatMap(shippingCostRepository::save);
	}
	
	public Mono<ShippingCost> updateShippingCost2(Mono<ShippingCost> shippingCost, Integer id){
		Mono<ShippingCost> saveShippingCost = shippingCostRepository.findById(id);
		BeanUtils.copyProperties(shippingCost, saveShippingCost);
		return saveShippingCost.map(ShippingCostServiceImpl::monoToObj)
				.flatMap(shippingCostRepository::save);
	}
	
	public Mono<Void> deleteAllShippingCosts() {
		return shippingCostRepository.deleteAll();
	}
	
	public Mono<Void> deleteByShippingCostId(Integer id){
		return shippingCostRepository.deleteById(id);
	}
	
	@Override
	public Mono<Void> deleteByShippingCostZipCode(String zipCode) {
		return shippingCostRepository.deleteByZipCode(zipCode);
	}
	
	private static ShippingCost monoToObj(ShippingCost shippingCost) {
		return shippingCost;
	}
	
	public Mono<ShippingCost> getByShippingCostId1(Integer id){
		return shippingCostRepository.findById(id)
				.flatMap(p->{
					System.out.println("p.getId():"+p.getId());
					System.out.println("p.getZipCode():"+p.getZipCode());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					System.out.println("p.getShippingCost():"+p.getDefaultCost());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					return Mono.just(p);
				});
		//return shippingCostRepository.findById(id);
	}

	@Override
	public Mono<ShippingCost> getShippingCost() {
		//ShipToAddress shippingAddress = cartEntity.getShippingAddress();
		//System.out.println("shippingAddress.getId():"+shippingAddress.getId());
		//return getByShippingCostId(shippingAddress.getId());//sopcoming
		return shippingCostRepository.findById(2)
				.flatMap(p->{
					System.out.println("p.getId():"+p.getId());
					System.out.println("p.getZipCode():"+p.getZipCode());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					System.out.println("p.getShippingCost():"+p.getDefaultCost());
					System.out.println("p.getShippingCost():"+p.getShippingCost());
					return Mono.just(p);
				});
		
		
		/*
		 * return taxRepository.findById(shippingAddress.getId()) .flatMap(p->{
		 * cartEntity.setCountryRate(p.getCountryRate());
		 * cartEntity.setStateRate(p.getStateRate());
		 * cartEntity.setCityRate(p.getCityRate());
		 * cartEntity.setCountyRate(p.getCountyRate());
		 * System.out.println("p.getIddddddd():"+p.getId());
		 * System.out.println("p.getCityyyyy():"+p.getCity());
		 * System.out.println("p.getCityRate():"+p.getCityRate()); return Mono.just(p);
		 * });
		 */
		/*
		 * Optional<Tax> optTax =
		 * taxRepository.findByCountryCodeAndStateCodeAndCityCodeAndCountyCode(
		 * shippingAddress.getCountryCode(), shippingAddress.getStateCode(),
		 * shippingAddress.getCityCode(), shippingAddress.getCountyCode());
		 * if(optTax.get() == null) { System.out.println("tax is null"); } else {
		 * System.out.println("tax is not null"); return
		 * optTax.flatMap(TaxServiceImpl::optToTax) .;
		 * //System.out.println("tax is not null cityrate:"+optTax.get().getCityRate());
		 * 
		 * } return optTax.get();
		 */
	}

	private static ShippingCost optToShippingCost(ShippingCost shippingCost1) {
		return shippingCost1;
	}

	@Override
	public Flux<ShippingCost> createConfigShippingCost() {
		ShippingCost shippingCost1 = new ShippingCost(1, "1234", 10.0, 11.0,true);
		ShippingCost shippingCost2 = new ShippingCost(2, "5678", 20.0, 21.0,false);
		ShippingCost shippingCost3 = new ShippingCost(3, "9012", 30.0, 31.0,true);
		ShippingCost shippingCost4 = new ShippingCost(4, "3456", 40.0, 41.0,false);
		ShippingCost shippingCost5 = new ShippingCost(5, "7890", 50.0, 51.0,true);
		List<ShippingCost> shippingCostList = new ArrayList<>();
		shippingCostList.add(shippingCost1);
		shippingCostList.add(shippingCost2);
		shippingCostList.add(shippingCost3);
		shippingCostList.add(shippingCost4);
		shippingCostList.add(shippingCost5);
		
		return shippingCostRepository.saveAll(shippingCostList);
	}

}
