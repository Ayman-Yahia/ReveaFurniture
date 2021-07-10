<h1>Revea Furniture</h1>

<h2>Java Spring Project<h2>

  <h3>Abstract</h3>

<p>An online furniture shop that allows users to check for various furniture available at the online store and purchase online. The store consists of  list of furniture products displayed in various models and designs. 
The user may browse through these products as per categories. If the user likes a product he may add it to his shopping cart. Once the user wishes to checkout he must register on the site first.  pay through a credit card. Once the user makes a successful transaction he gets a copy of the shopping receipt. 
Thus the online furniture shopping project brings an entire furniture shop online and makes it easy for both buyer and seller to make furniture deals.</p>
  
  <h3>Roles:</h3>
 <b>ADMIN_ROLE : </b>this role will let the admin access In the admin page he can create a new Product,  Category and edit, delete Products and see users and visual reports.
  
 <b> USER_ROLE :</b> this role will let the user access the home page,The user can only browse the products if he is not logged in, while if he is logged in, he can add products to the cart and make the purchase.<br><br>

  <h3>Main Features:</h3>
<b>Log in & Registration( /registration)</b>
This page will enable us for login to the user account if he is already registered in the site before or to register if he is new user. It will contain filling for some of the personal information for the user such as first & last name, email & password.<br><br>
  ![Alt text](https://www.linkpicture.com/q/screencapture-localhost-8080-login-2021-07-10-14_02_40.png)
  ![Alt text](https://www.linkpicture.com/q/screencapture-localhost-8080-login-2021-07-10-14_02_59.png)


  <b>The main page( /home)</b>
will display all Furniture categories, each category will lead you to a page with a list of all books with the same category.<br><br>
[![image](https://www.linkpicture.com/q/screencapture-localhost-8080-2021-07-10-13_59_15.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  
  <b>Ctegory Page( /categories/{category_id})</b>
  [![image](https://www.linkpicture.com/q/catPage.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  
   display the products of each category and the product can be added to the basket (if the user is logged in) and also access to the product page.<br><br>
  
  <b>Product Page( /products/{product_id})</b>
  Displays product details (image, name, price, quantity, description).<br><br>
  
  <b>Cart Page( /cart)</b>
  Displays the products and their details that the customer has added to the cart, in addition to the total prices and the checkout button.<br><br>
  
  <b>Checkout Page( /checkout)</b>
  [![image](https://www.linkpicture.com/q/screencapture-localhost-8080-checkout-2021-07-10-14_09_46.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  contains a form with information that the customer enters, such as name, address, email and  phone number.<br><br>
  
  <b>Thank You Page( /thankyou)</b>
  ![Alt text](https://www.linkpicture.com/q/screencapture-localhost-8080-thankyou-2021-07-10-16_06_17.png)

  Thank the user and confirm that his request has been received.<br><br>
  
  <b>Admin Page(/admin)</b>
  [![image](https://www.linkpicture.com/q/addProduct.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  Only this admin can access it. This page displays the products on the site and their details, and also enables the admin to delete or modify the products. <br><br>
  
  <b>Users Page ( /admin/users) </b>
  Show all users of the site <br><br>
  
  <b>Add Product Page ( /admin/add)</b>
  [![image](https://www.linkpicture.com/q/Capture_104.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  Through this page, the admin can add products to the store.<br><br> 
  
  <b>Add Category Page ( /admin/addcategory)</b>
  [![image](https://www.linkpicture.com/q/addCat.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  Through this page, the admin can add categories to the store.<br><br> 
  

  
  <b>Chart Page( /admin/charts)</b>
  [![image](https://www.linkpicture.com/q/chart_3.png)](https://www.linkpicture.com/view.php?img=LPic60e99e2894f46841632124)
  Displays visual reports such as sales of each category.<br><br>  
  
Authors:<br>
Ayman Yahia<br>
Reem Ismail<br>
Malak Akkas<br>
  

