<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .section {
            display: none;
        }
        button {
            margin: 10px;
            padding: 10px 20px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }
        button:hover {
            background-color: #45a049;
        }
        h1, h2, h3 {
            color: #333;
        }
        #products, #customers {
            margin-top: 20px;
        }
        form {
            margin-top: 20px;
        }
        input[type="text"], input[type="email"], input[type="number"] {
            padding: 8px;
            margin: 10px 0;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="number"] {
            -moz-appearance: textfield;
        }
        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        .form-section {
            display: flex;
            flex-direction: column;
            max-width: 400px;
            margin: auto;
        }
        .form-section label {
            margin-bottom: 5px;
        }
        .form-section button {
            margin-top: 20px;
            padding: 10px;
        }
    </style>
</head>
<body>
    <h1>Admin Dashboard</h1>
    <h2>Welcome, Admin!</h2>

    <!-- Buttons to toggle between Admin and Customer features -->
    <button onclick="showAdminFeatures()">Admin Features</button>
    <button onclick="showCustomerFeatures()">Customer Features</button>

    <hr>

    <!-- Admin Features Section -->
    <div id="adminFeatures" class="section">
        <h3>Admin Features</h3>
        <ul>
            <li><button onclick="loadProducts()">Get All Products</button></li>
            <li><button onclick="loadCustomers()">Get All Customers</button></li>
            <li><button onclick="showAddProductForm()">Add New Product</button></li>
            <li><button onclick="showAddCustomerForm()">Add New Customer</button></li>
            <li><button onclick="showRemoveProductForm()">Remove Product by ID</button></li>
            <li><button onclick="showRemoveCustomerForm()">Remove Customer by ID</button></li>
        </ul>
    </div>

    <!-- Customer Features Section -->
    <div id="customerFeatures" class="section">
        <h3>Customer Features</h3>
        <!-- Placeholder for customer features -->
        <p>Feature options for customers will be added here.</p>
    </div>

    <!-- Products Section -->
    <div id="products"></div>

    <!-- Customers Section -->
    <div id="customers"></div>

    <!-- Add Product Form -->
    <div id="addProductForm" class="section">
        <h3>Add New Product</h3>
        <div class="form-section">
            <form onsubmit="addProduct(event)">
                <label>Product Name:</label><br>
                <input type="text" id="productName" required><br>
                <label>Price:</label><br>
                <input type="number" id="productPrice" required><br>
                <label>Quantity:</label><br>
                <input type="number" id="productQuantity" required><br>
                <label>Discount:</label><br>
                <input type="number" id="productDiscount" required><br><br>
                <button type="submit">Add Product</button>
            </form>
        </div>
    </div>

    <!-- Add Customer Form -->
    <div id="addCustomerForm" class="section">
        <h3>Add New Customer</h3>
        <div class="form-section">
            <form onsubmit="addCustomer(event)">
                <label>Customer Name:</label><br>
                <input type="text" id="customerName" required><br>
                <label>Email:</label><br>
                <input type="email" id="customerEmail" required><br>
                <label>Wallet Balance:</label><br>
                <input type="number" id="customerWallet" required><br>
                <label>Discount:</label><br>
                <input type="number" id="customerDiscount" required><br><br>
                <button type="submit">Add Customer</button>
            </form>
        </div>
    </div>

    <!-- Remove Product Form -->
    <div id="removeProductForm" class="section">
        <h3>Remove Product by ID</h3>
        <div class="form-section">
            <form onsubmit="removeProduct(event)">
                <label>Product ID:</label><br>
                <input type="number" id="removeProductId" required><br><br>
                <button type="submit">Remove Product</button>
            </form>
        </div>
    </div>

    <!-- Remove Customer Form -->
    <div id="removeCustomerForm" class="section">
        <h3>Remove Customer by ID</h3>
        <div class="form-section">
            <form onsubmit="removeCustomer(event)">
                <label>Customer ID:</label><br>
                <input type="number" id="removeCustomerId" required><br><br>
                <button type="submit">Remove Customer</button>
            </form>
        </div>
    </div>

    <script>
    // Function to load all products
    function loadProducts() {
    fetch('/admin/getallproducts')
        .then(response => response.json())
        .then(data => {
            console.log('Products:', data); // Log the raw data to check its structure
            let productDiv = document.getElementById('products');
            if (productDiv) {
                productDiv.innerHTML = ''; // Clear previous content
                if (data.length === 0) {
                    productDiv.innerHTML = '<p>No products found.</p>';
                } else {
                    // Create a table for better data presentation
                    let table = `<table border="1">
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Discount</th>
                                    </tr>`;
                    data.forEach(product => {
                        console.log('Product data:', product); // Log each product to see its properties
                        table += `
                            <tr>
                                <td>${product.productName || 'N/A'}</td>
                                <td>$${product.price || 'N/A'}</td>
                                <td>${product.quantity || 'N/A'}</td>
                                <td>${product.discount || 'N/A'}%</td>
                            </tr>`;
                    });
                    table += '</table>';
                    productDiv.innerHTML = table;
                }
            }
        })
        .catch(error => {
            console.error('Error fetching products:', error);
            document.getElementById('products').innerHTML = '<p>There was an error fetching the products.</p>';
        });
}

    // Function to load all customers
   function loadCustomers() {
    fetch('/admin/getallcustomers')
        .then(response => response.json())
        .then(data => {
            console.log('Customers:', data); // Log the raw customer data to check its structure
            let customerDiv = document.getElementById('customers');
            if (customerDiv) {
                customerDiv.innerHTML = ''; // Clear previous content
                if (data.length === 0) {
                    customerDiv.innerHTML = '<p>No customers found.</p>';
                } else {
                    // Create a table for better data presentation
                    let table = `<table border="1">
                                    <tr>
                                        <th>Customer Name</th>
                                        <th>Email</th>
                                        <th>Wallet Balance</th>
                                        <th>Discount</th>
                                    </tr>`;
                    data.forEach(customer => {
                        console.log('Customer data:', customer); // Log each customer to see its properties
                        table += `
                            <tr>
                                <td>${customer.name || 'N/A'}</td>
                                <td>${customer.email || 'N/A'}</td>
                                <td>${customer.walletBalance || 'N/A'}</td>
                                <td>${customer.discount || 'N/A'}%</td>
                            </tr>`;
                    });
                    table += '</table>';
                    customerDiv.innerHTML = table;
                }
            }
        })
        .catch(error => {
            console.error('Error fetching customers:', error);
            document.getElementById('customers').innerHTML = '<p>There was an error fetching the customers.</p>';
        });
}

    // Function to show admin features
    function showAdminFeatures() {
        document.getElementById('adminFeatures').style.display = "block";
        document.getElementById('addProductForm').style.display = "none";
        document.getElementById('addCustomerForm').style.display = "none";
        document.getElementById('removeProductForm').style.display = "none";
        document.getElementById('removeCustomerForm').style.display = "none";
        document.getElementById('customerFeatures').style.display = "none";
    }

    // Function to show customer features
    function showCustomerFeatures() {
        document.getElementById('customerFeatures').style.display = "block";
        document.getElementById('adminFeatures').style.display = "none";
    }

    // Function to show add product form
    function showAddProductForm() {
        document.getElementById('addProductForm').style.display = "block";
        document.getElementById('adminFeatures').style.display = "none";
    }

    // Function to show add customer form
    function showAddCustomerForm() {
        document.getElementById('addCustomerForm').style.display = "block";
        document.getElementById('adminFeatures').style.display = "none";
    }

    // Function to show remove product form
    function showRemoveProductForm() {
        document.getElementById('removeProductForm').style.display = "block";
        document.getElementById('adminFeatures').style.display = "none";
    }

    // Function to show remove customer form
    function showRemoveCustomerForm() {
        document.getElementById('removeCustomerForm').style.display = "block";
        document.getElementById('adminFeatures').style.display = "none";
    }

    // Function to add new product
    function addProduct(event) {
        event.preventDefault();
        const product = {
            productName: document.getElementById('productName').value,
            price: document.getElementById('productPrice').value,
            quantity: document.getElementById('productQuantity').value,
            discount: document.getElementById('productDiscount').value
        };

        fetch('/admin/addnewproducts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product),
        })
        .then(response => response.json())
        .then(data => {
            alert('Product added successfully!');
            showAdminFeatures();
        })
        .catch(error => {
            console.error('Error adding product:', error);
            alert('There was an error adding the product.');
        });
    }

    // Function to add new customer
    function addCustomer(event) {
        event.preventDefault();
        const customer = {
            name: document.getElementById('customerName').value,
            email: document.getElementById('customerEmail').value,
            walletBalance: document.getElementById('customerWallet').value,
            discount: document.getElementById('customerDiscount').value
        };

        fetch('/admin/addnewcustomer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(customer),
        })
        .then(response => response.json())
        .then(data => {
            alert('Customer added successfully!');
            showAdminFeatures();
        })
        .catch(error => {
            console.error('Error adding customer:', error);
            alert('There was an error adding the customer.');
        });
    }

    // Function to remove product by ID (Updated)
    function removeProduct(event) {
        event.preventDefault();
        const productId = document.getElementById('removeProductId').value;

        // Check if the product ID is valid (greater than 0)
        if (!productId || productId <= 0) {
            alert('Please enter a valid product ID.');
            return;
        }

        // Call to delete the product using the provided product ID
        fetch(`/admin/deleteproduct/${productId}`, {
            method: 'DELETE',
        })
        .then(response => response.text())
        .then(data => {
            if (data === 'Product removed successfully!') {
                alert('Product removed successfully!');
            } else {
                alert('Product not found or error occurred.');
            }
            showAdminFeatures();
        })
        .catch(error => {
            console.error('Error removing product:', error);
            alert('There was an error removing the product.');
        });
    }

    // Function to remove customer by ID (Updated)
    function removeCustomer(event) {
        event.preventDefault();
        const customerId = document.getElementById('removeCustomerId').value;

        // Check if the customer ID is valid (greater than 0)
        if (!customerId || customerId <= 0) {
            alert('Please enter a valid customer ID.');
            return;
        }

        // Call to delete the customer using the provided customer ID
        fetch(`/admin/deletecustomer/${customerId}`, {
            method: 'DELETE',
        })
        .then(response => response.text())
        .then(data => {
            if (data === 'Customer removed successfully!') {
                alert('Customer removed successfully!');
            } else {
                alert('Customer not found or error occurred.');
            }
            showAdminFeatures();
        })
        .catch(error => {
            console.error('Error removing customer:', error);
            alert('There was an error removing the customer.');
        });
    }

    // Ensure sections are hidden when the page initially loads
    window.onload = function() {
        document.getElementById("adminFeatures").style.display = "none";
        document.getElementById("customerFeatures").style.display = "none";
    };
</script>

</body>
</html>
