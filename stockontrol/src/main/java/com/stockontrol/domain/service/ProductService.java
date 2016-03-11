package com.stockontrol.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockontrol.domain.entity.Product;
import com.stockontrol.domain.repository.ProductRepository;

import junit.framework.Assert;

@Service("productService")
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Product> listAllByFilters(Long categoryId, String name)
	{
		if(categoryId == null && name == null)
		{
			return productRepository.findAll();
		}
		else if(categoryId == null)
		{
			return productRepository.findAllByNameContaining(name);
		}
		else if(name == null)
		{
			return productRepository.findAllByCategoryId(categoryId);
		}
		else
		{
			return productRepository.findAllByCategoryIdAndNameContaining(categoryId, name);
		}
		//return productRepository.listAllByFilters(categoryId, name);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public void delete(Long id)
	{
		productRepository.delete(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Product find(Long id)
	{
		return productRepository.findOne(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Product insert(Product product)
	{
		Assert.assertNull("Produto já existe!", product.getId());
		return productRepository.saveAndFlush(product);
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Product update(Product product)
	{
		Assert.assertNotNull("Produto não existe!", product.getId());
		return productRepository.saveAndFlush(product);
	}
}