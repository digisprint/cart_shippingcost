package com.java.spring.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.entity.ShipToAddress;
import com.java.spring.api.exception.ResourceNotFoundException;
import com.java.spring.api.repository.CartRepository;
import com.java.spring.api.repository.ShippingCostRepository;
import com.java.spring.api.service.CartService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CartServiceImpl implements CartService {
	
	Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ShippingCostRepository shippingCostRepository;
	
	@Value("${default.tax.value}")
	private double defaultTaxValue;
	
	@Value("${default.shippingCost.value}")
	private double defaultShippingCostValue;

	//@Override
	//public Mono<CartEntity> createCart(Mono<CartEntity> cartEntity) throws ResourceNotFoundException {
		// working with no tax
		//return cartEntity.map(CartServiceImpl::monoToDao)
		//		.flatMap(cartRepository::save);
	//}
	
	// working
	//@Override
	//public Mono<CartEntity> createCart(CartEntity cartEntity) throws ResourceNotFoundException {
	//	ShipToAddress shippingAddress = cartEntity.getShippingAddress();
	//	return taxRepository.findByCountryCodeAndStateCodeAndCityCodeAndCountyCodeIgnoreCase(
	//				shippingAddress.getCountryCode(), shippingAddress.getStateCode(),
	//				shippingAddress.getCityCode(), shippingAddress.getCountyCode())
	//	.flatMap(fluxUsingWhen -> {
	//			cartEntity.setCountryRate(fluxUsingWhen.getCountryRate());
	//			cartEntity.setStateRate(fluxUsingWhen.getStateRate());
	//			cartEntity.setCityRate(fluxUsingWhen.getCityRate());
	//			cartEntity.setCountyRate(fluxUsingWhen.getCountyRate());
	//			cartEntity.setTaxCost(cartEntity.getCountryRate()+cartEntity.getStateRate()+cartEntity.getCityRate()+cartEntity.getCountyRate());
	//			cartEntity.setTotal(cartEntity.getSubTotal()+cartEntity.getTaxCost());
	//			return cartRepository.save(cartEntity);
	//		});
	//}
	
	@Override
	public Mono<CartEntity> createCartByCodes(CartEntity cartEntity) throws ResourceNotFoundException {
		ShipToAddress shippingAddress = cartEntity.getShippingAddress();
		return shippingCostRepository.findByZipCode(shippingAddress.getZipCode())
					.flatMap(shippingCost -> {
						cartEntity.setShippingCost(shippingCost.getShippingCost());
						//cartEntity.setSubTotal(cartEntity.getAllItemsPrice()+cartEntity.getShippingCost());
						cartEntity.setTotal(cartEntity.getAllItemsPrice()+cartEntity.getShippingCost()+cartEntity.getTaxCost());
						return cartRepository.save(cartEntity);
					})
					.onErrorResume(err -> {
						System.out.println("onErrorResume : "+err);
						cartEntity.setShippingCost(defaultShippingCostValue);
						cartEntity.setSubTotal(cartEntity.getAllItemsPrice()+cartEntity.getShippingCost());
						cartEntity.setTotal(cartEntity.getSubTotal()+cartEntity.getTaxCost());
						return cartRepository.save(cartEntity);
					});
	}
	
	@Override
	public Flux<CartEntity> createMultiCart(Iterable<CartEntity> cartEntityList) throws ResourceNotFoundException {
		return Flux.fromIterable(cartEntityList)
				.flatMap(cartEntity -> {
					ShipToAddress shippingAddress = cartEntity.getShippingAddress();
					return shippingCostRepository.findByZipCode(shippingAddress.getZipCode())
										.flatMap(shippingCost -> {
											cartEntity.setShippingCost(shippingCost.getShippingCost());
											//cartEntity.setSubTotal(cartEntity.getAllItemsPrice()+cartEntity.getShippingCost());
											cartEntity.setTotal(cartEntity.getSubTotal()+cartEntity.getShippingCost()+cartEntity.getTaxCost());
											return cartRepository.save(cartEntity);
										});
							});
		// working fine
		//return cartRepository.saveAll(cartEntityList);
	}

	@Override
	public Mono<CartEntity> findById(Integer id) {
		return cartRepository.findById(id);
	}

	@Override
	public Mono<CartEntity> updateByCartId(Mono<CartEntity> cartEntity, Integer id) {
		return cartRepository.findById(id)
				.flatMap(p->cartEntity.map(CartServiceImpl::dtoToEntity))
				.doOnNext(e->e.setCartId(id))
				.flatMap(cartRepository::save);
				//.map(AppUtils::entityToDto);
		
		//Function<Mono<CartEntity>, Integer> func = data->data.filter(s->s.)
		//Function<Flux<String>, Flux<String>> filterData = null;
		//.filter(s->s.length() > 3);
		//Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > 3);
		
		// working but adding new ones
		//return cartEntity.map(CartServiceImpl::monoToDao).flatMap(cartRepository::save);
	}

	@Override
	public Mono<Void> deleteByCartId(Integer id) {
		return cartRepository.deleteById(id);
	}
	
	@Override
	public Mono<Void> deleteAllCarts() {
		return cartRepository.deleteAll();
	}

	@Override
	public Flux<CartEntity> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Mono<Object> getShippingCostByCart(Integer id) {
		return cartRepository.findById(id)
				.flatMap(c -> {
					System.out.println("query: "+shippingCostRepository.findByZipCode(c.getShippingAddress().getZipCode()));
					return shippingCostRepository.findByZipCode(c.getShippingAddress().getZipCode())
							.flatMap(shippingCost -> {
								System.out.println("shippingCost:"+shippingCost.getShippingCost());
								
								Predicate<Object> p = i -> (i == null);
								if(!p.test(shippingCost)) {
									System.out.println("--if--");
									c.setShippingCost(shippingCost.getShippingCost());
									System.out.println("shippingCost.getShippingCost() :::"+shippingCost.getShippingCost());
									log.debug("c.getShippingCost():",c.getShippingCost());
									return Mono.just(c.getShippingCost());
								} else {
									System.out.println("else---");
									return Mono.error(new RuntimeException("Shipping Cost Not Available!!!"));
									}
								})
								.defaultIfEmpty(defaultShippingCostValue);
				});
	}
	
	
	
	private static CartEntity monoToDao(CartEntity cartEntity) {
		return cartEntity;
	}
	
	private static CartEntity dtoToEntity(CartEntity cartEntity) {
		return cartEntity;
	}

}
