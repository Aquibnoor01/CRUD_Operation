Endpoints Of CRUD Operations
1. Create Product :
   ============================================
   POST http://localhost:8080/api/products
  Content-Type: application/json
  {
    "name": "Laptop",
    "price": 800.0,
    "description": "High-performance laptop with 16GB RAM and 512GB SSD."
  }
2. Get All Products:
   =========================================================================
  GET http://localhost:8080/api/products
   [
      {
        "id": 1,
        "name": "Laptop",
        "price": 800.0,
        "description": "High-performance laptop with 16GB RAM and 512GB SSD."
    },
    {
        "id": 2,
        "name": "Smartphone",
        "price": 500.0,
        "description": "Latest smartphone with 128GB storage."
    }

   ]

3.Get a Single Product by ID
===============================================================================
Method: GET
http://localhost:8080/api/products/{id}
{
    "id": 1,
    "name": "Laptop",
    "price": 800.0,
    "description": "High-performance laptop with 16GB RAM and 512GB SSD."
}  or 
Status 404 if the product Does not exist
{
    "message": "Product not found"
}

4.Update a Product
=======================================================================
URL: http://localhost:8080/api/products/{id}
Method: PUT
{
    "name": "Updated Product Name",
    "price": 900.0,
    "description": "Updated description of the product."
}

5.Delete a Product
=======================================================================
URL: http://localhost:8080/api/products/{id}
Method: DELETE
{
    "message": "Product deleted successfully"
} OR 
Status 404 if not found 
{
    "message": "Product not found"
}
===========================================================================
400 Bad Request: The request is invalid (e.g., missing required fields).
404 Not Found: The requested resource (product) could not be found.
500 Internal Server Error: Something went wrong on the server.
