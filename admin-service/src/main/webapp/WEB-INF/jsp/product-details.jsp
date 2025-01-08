<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
</head>
<body>
    <h1>Product Details</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <td>${product.id}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${product.name}</td>
        </tr>
        <tr>
            <th>Price</th>
            <td>${product.price}</td>
        </tr>
        <tr>
            <th>Category</th>
            <td>${product.category}</td>
        </tr>
        <tr>
            <th>Description</th>
            <td>${product.description}</td>
        </tr>
    </table>
    <br>
    <a href="/admin/products/view">Back to Product List</a> |
    <a href="/admin/dashboard">Go to Admin Dashboard</a>
</body>
</html>
