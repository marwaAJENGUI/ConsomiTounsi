package tn.consomitounsi.www.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.ProductSearch;
import tn.consomitounsi.www.service.IProductCategoryService;
import tn.consomitounsi.www.service.IProductService;

@RestController
public class ProductController {
	private static final String UPLOADED_FOLDER = "F:\\temp";
	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	
	@GetMapping("/view/Products")
	@ResponseBody

	public List<Product> getProducts() {
		return iProductService.findAll();   
	}
	
	@GetMapping("/view/Product/{barCode}")
	@ResponseBody

    public Product getProduct(@PathVariable("barCode") Long barCode){
		Product product = iProductService.getProductBybarCode(barCode);
		return product;
    }
	
	@PutMapping("/view/searchProduct")
	@ResponseBody
    public List<Product> searchProducts(@RequestBody ProductSearch productSearch){
		String productName=productSearch.getProductName();
		String categoryName= productSearch.getCategoryName();
		List<Product> products = iProductService.searchProducts(productName,categoryName);
		return products;
    }
	@PostMapping("/manage/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product,@RequestParam("file") MultipartFile file)
	{
		if (!iProductService.existsById(product.getBarCode())) {
			product.setCategory(validCategory(product.getCategory()));
			iProductService.addProduct(product);
			//upload image
			if (!file.isEmpty()) {
			        try {
			            // Get the file and save it somewhere
			            byte[] bytes = file.getBytes();
			            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			            Files.write(path, bytes);
			            
			            //get the barCode value from the image
						InputStream barCodeInputStream;
						try {
							barCodeInputStream = new FileInputStream("C:\\Users\\Marwa\\Desktop\\milk_barcod1.jpg");
							BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				
							LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
							BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
							Reader reader = new MultiFormatReader();
							Result result = reader.decode(bitmap);
							System.out.println("Barcode text is :" + result.getText());

						} catch (FileNotFoundException e) {
							System.out.println("barCodeInputStream = new FileInputStream(file.)");
							e.printStackTrace();
						} catch (IOException e) {
							//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
							e.printStackTrace();
						} catch (NotFoundException e) {
							//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
							e.printStackTrace();
						} catch (ChecksumException e) {
							//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
							e.printStackTrace();
						} catch (FormatException e) {
							//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
							e.printStackTrace();
						}

			        } catch (IOException e) {
			           // byte[] bytes = file.getBytes();
			            e.printStackTrace();
			        }
			    }
						return product;
		}else {
			throw new IllegalArgumentException("Product already exist");
		}
	}
	
	@PutMapping(value = "/manage/updateProduct/{barCode}") 
	@ResponseBody
	public Product updateProduct(@PathVariable("barCode") Long barCode,@RequestBody Product product) {
		Product p= product;
		Product prod= iProductService.getOne(p.getBarCode());
		prod.setPrice(p.getPrice());
		prod.setName(p.getName());
		prod.setCategory(validCategory(p.getCategory()));
		return iProductService.updateProduct(p, barCode);
	}
	
	@DeleteMapping("/manage/removeProduct/{barCode}")
	@ResponseBody
    public boolean removeProductCategory(@PathVariable("barCode") Long barCode){
		return iProductService.removeProduct(barCode);
    }
	
	ProductCategory validCategory(ProductCategory category) {
		if (category==null) throw new IllegalArgumentException("Product Category can not be empty");
		if((category.getId()!=null)&&(iProductCategoryService.existsById(category.getId()))) {
			return iProductCategoryService.getOne(category.getId());
		}else if ((category.getName()!=null)&&(iProductCategoryService.findCategoryByName(category.getName()).size()>0)) {
			return iProductCategoryService.findCategoryByName(category.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid  Product Category, could not find reference");
	}
	
}


