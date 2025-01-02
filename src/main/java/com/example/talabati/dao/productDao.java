// package com.example.talabati.dao;

// import java.util.List;

// import org.springframework.dao.DataAccessException;
// import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Repository;

// import com.example.talabati.model.Product;

// @Repository
// public class productDao { // work as product Repository 

//     private final JdbcTemplate jdbcTemplate;

//     public productDao(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     public int addProduct(Product product) {

//         String sql = "insert into products(p_name, p_price) values  (?,?)";
//         System.out.println("Created !!!!!!");
//         return jdbcTemplate.update(sql, product.getName(), product.getPrice());
//     }

//     public Product getProductById(int id) {
//         String sql = "select * from products where p_id = ?";

//         try {
//             return jdbcTemplate.queryForObject(sql, new Object[]{id}, productRowMapper());

//         } 
//         catch ( EmptyResultDataAccessException e) {
//             System.out.println("No product found with the ID  : "+ id );
//             return  null;
//         }
//         catch (DataAccessException e) {
//             System.out.println("Error while getting the product >>" +e.getMessage());
//             return  null;
//         }

//     }

//     public List<Product> getAllProducts() {
//         String sql = "select * from products";
//         return jdbcTemplate.query(sql, productRowMapper());
//     }

//     public int updateProduct(Product product) {
//         String sql = "update products set p_name = ? , p_price = ?  WHERE p_id =  ?";
//         return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
//     }

//     public int deleteProduct(int id) {
//         String sql = "delete from products where p_id = ?";
//         return jdbcTemplate.update(sql, id);
//     }

//     private RowMapper<Product> productRowMapper() {
//         return (rs, rowNum) -> new Product(
//             rs.getLong("p_id"),                  // Maps to Product.id
//             rs.getString("p_name"),             // Maps to Product.name
//             rs.getDouble("p_price"),            // Maps to Product.price
//             rs.getString("description"),        // Maps to Product.description
//             rs.getLong("restaurant_id"),        // Maps to Product.restaurantId
//             rs.getLong("sub_category_id"),      // Maps to Product.subCategoryId
//             rs.getBoolean("is_availabe")        // Maps to Product.isAvailabe
//     );
//     }
// }
