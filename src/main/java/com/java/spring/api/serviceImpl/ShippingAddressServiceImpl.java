package com.java.spring.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.api.entity.CartEntity;
import com.java.spring.api.entity.ShipToAddress;
import com.java.spring.api.repository.CartRepository;
import com.java.spring.api.repository.ShippingAddressRepository;
import com.java.spring.api.service.ShippingAddressService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Mono<ShipToAddress> createShippingAddress(Mono<ShipToAddress> shippingAddress) {
		return shippingAddress.map(ShippingAddressServiceImpl::monoToDao)
				.flatMap(shippingAddressRepository::save);
	}

	@Override
	public Mono<ShipToAddress> findByShippingAddressId(Integer id) {
		return shippingAddressRepository.findById(id);
	}
	
	@Override
	public Flux<ShipToAddress> getAllShippingAddress() {
		return shippingAddressRepository.findAll();
	}

	@Override
	public Mono<ShipToAddress> updateByCartId(Mono<ShipToAddress> shippingAddress, Integer id) {
		return shippingAddressRepository.findById(id)
				.flatMap(p->shippingAddress.map(ShippingAddressServiceImpl::monoToDao)
				.doOnNext(e->e.setId(id)))
				.flatMap(shippingAddressRepository::save);
		
		
		/*
		 * System.out.println("shippingAddress.getId():"+shippingAddress.getId());
		 * ShippingAddress shippingAdress =
		 * shippingAddressRepository.findById(shippingAddress.getId()).get();
		 * System.out.println("find by id shippingAdress.getId():"+shippingAdress.getId(
		 * )); System.out.println("shippingAddress.getId():"+shippingAddress.getId());
		 * if(shippingAddress.getId() == shippingAdress.getId()) { return
		 * shippingAddressRepository.save(shippingAddress); } else { return null; }
		 */
	}

	@Override
	public Mono<Void> deleteByShippingAddressId(Integer id) {
		return shippingAddressRepository.deleteById(id);
	}
	
	private static ShipToAddress monoToDao(ShipToAddress shippingAddress) {
		return shippingAddress;
	}

	@Override
	public Mono<CartEntity> createShippingAddressByCartId(
			ShipToAddress shippingAddress, Integer cartId) {
		return cartRepository.findById(cartId)
				.flatMap(c->{
					return shippingAddressRepository.save(shippingAddress)
							.flatMap(s->{
								return cartRepository.save(c);
							});
					//.flatMap(d->Mono.just(cartRepository.save(d)));
					//return cartRepository.save(c);
					
					//return Mono.just(c);
					//return cartRepository.save(c);
					//shippingAddressRepository.save(shippingAddress)
					//.flatMap( cartRepository::save);
					
					// working
					//shippingAddressRepository.save(shippingAddress);
					//return cartRepository.save(c);
				});
	}

}
