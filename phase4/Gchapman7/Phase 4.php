<?php
   session_start();
?>
<html lang="en">
   <head>
    <meta charset="utf-8">
      <title>Check</title>
      <link rel="icon" type="image/x-icon" href="/Mproj4/WPB-icon.ico">
      <style>
         * {text-align: center;}
         h3{ 
	         width: 50%;
            font-size: 25px;
	         margin: 0 auto; 
	         background-color:white;
         }
         div.gallery {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 180px;
         }

         div.gallery:hover {
          border: 1px solid #777;
         }

         div.gallery img {
            width: 100%;
            height: auto;
         }

         div.desc {
            padding: 15px;
            text-align: center;
            font-size: 20px;
            background-color:white;
         }
      </style>

   </head>
	
   <body style="background-color:powderblue;">
      <h2>Enter password</h2> 
      <div>
         <?php
            function redirect($url) {
                header('Location: '.$url);
                die();
             }
            if($_post["D1"]=="Rogal Dorn")
            if (isset($_POST['Go'])) {
                redirect("image.php");
            } 
               
            
         ?>
      </div>

      <div class="container">
         <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
         Password:<input type="text" name="D1" id="D1" placeholder="Enter room name and Description here"/> </br>
         </br>
            <button type="submit" name="Go">Go</button> </br></br>
            
      </div>
   </body>
</html>
