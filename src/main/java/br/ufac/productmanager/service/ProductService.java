package br.ufac.productmanager.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogProduct;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogProductRepository;
import br.ufac.productmanager.repository.ProductRepository;

@Service
public class ProductService implements ICrudService<Product>{

    private final ProductRepository repo;
    private final LogProductRepository logRepo;

    @Autowired
    public ProductService(ProductRepository repo, LogProductRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = repo.findAll();
        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = repo.findById(id).orElse(null);
        return product;
    }
    
    public List<Product> getByTeam(Team team) {
        List<Product> products = repo.findByTeam(team);
        return products;
    }
    
    public List<Product> getAllByFirstSA(LocalDate firstSA) {
        List<Product> products = repo.findAllByFirstSA(firstSA);
        return products;
    }
    
    public List<Product> getAllByFirstSABetween(Date firstSAStart, Date firstSAEnd) {
        List<Product> products = repo.findAllByFirstSABetween(firstSAStart, firstSAEnd);
        return products;
    }

    @Override
    public Product save(Product product, User user) {
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received Product in DB
    	Product savedProduct = repo.save(product);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedProduct.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogProduct, associated to this Product
        String userName = user.getName();
        LogProduct logProduct = new LogProduct(new Date(instantMilli),
        		logCommentary, savedProduct, userName);
        //Save the created LogProduct in DB
        logRepo.save(logProduct);
        
        //Returns the Product saved
        return savedProduct;
    }

    @Override
    @DeleteMapping
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    // Source code adapted from: https://www.javainuse.com/fullstack/imageupload
	// compress the image bytes before storing it in the database
	public byte[] compressBytes(byte[] data) {
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (deflater.finished() == false) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		
		try {
			outputStream.close();
		} catch (IOException e) {
			System.out.println("Error compressing the file");
		}
		
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public byte[] decompressBytes(byte[] data) {
		
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		try {
			while (inflater.finished() == false) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			
			outputStream.close();
			
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		
		return outputStream.toByteArray();
	}

}
